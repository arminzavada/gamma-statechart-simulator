package com.triad.school.gamma.simulator.query

import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.statechart.statechart.StateNode
import java.util.Map.Entry
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine
import org.eclipse.viatra.query.runtime.emf.EMFScope
import org.eclipse.viatra.transformation.evm.specific.crud.CRUDActivationStateEnum
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.IModelManipulations
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.SimpleModelManipulations
import org.eclipse.viatra.transformation.runtime.emf.rules.eventdriven.EventDrivenTransformationRuleFactory
import org.eclipse.viatra.transformation.runtime.emf.transformation.eventdriven.EventDrivenTransformation
import com.triad.school.gamma.simulator.model.ModelPackage
import com.triad.school.gamma.simulator.model.ModelFactory



@FunctionalInterface
interface ActiveStateListener {
	def void activeStateChanged(StateNode node);
}

class DummyEntry<T> implements Entry<String, T> {
	
	val extension String key
	var extension T value
	
	new (String key, T value) {
		this.key = key
		this.value = value
	}
	
	override getKey() {
		key
	}
	
	override getValue() {
		value
	}
	
	override setValue(T arg0) {
		value = arg0
	}
}

class GammaStatechartSimulatorTransformation {
	val FireableTransitions queries = FireableTransitions.instance
	
    val EventDrivenTransformationRuleFactory factory = new EventDrivenTransformationRuleFactory    
    EventDrivenTransformation transformation    
    
    val activeStateContainer = ModelFactory.eINSTANCE.createActiveStateContainer
    val eventQueue = ModelFactory.eINSTANCE.createEventQueue
    
    IModelManipulations manipulation = null
    
    val ViatraQueryEngine engine

	ActiveStateListener activeStateListener = []

    new(Resource resource) {    	
		val resourceSet = new ResourceSetImpl
		resourceSet.resources.add(resource)
		
		val active_stateResource = resourceSet.createResource(URI.createURI(""))
		active_stateResource.contents.add(activeStateContainer)
		active_stateResource.contents.add(eventQueue)
		
        val scope = new EMFScope(resourceSet)
        engine = ViatraQueryEngine.on(scope);
        queries.prepare(engine)
        manipulation = new SimpleModelManipulations(engine)
        
        transformation = EventDrivenTransformation.forEngine(engine)
	        .addRule(fireNonTriggerTransition)
	        .addRule(fireTriggerTransition)
	        .addRule(activeState)
	        .addRule(swallowEvent)
	        .build
    }

    public def execute() { 
        activeStateContainer.activeState = InitialNode.Matcher.on(engine).oneArbitraryMatch.get.state
        transformation.executionSchema.startUnscheduledExecution
    }

    public def sendEvent(Event event) {
    	manipulation.add(eventQueue, ModelPackage.eINSTANCE.eventQueue_Events, event)
    }
    
    public def requiredInterfaces() {
    	return Ports.Matcher.on(engine).allMatches.map[
    		it.port
    	].toList
    }
    
    def setActiveStateListener(ActiveStateListener listener) {
    	activeStateListener = listener
    }
    
    val fireNonTriggerTransition = factory.createRule(FireableNonTriggerTransition.instance).action(CRUDActivationStateEnum.CREATED) [
    	println('''Firing empty transition: «source.name» to «target.name»''')
    	activeStateContainer.activeState = target
    ].build
    
    val fireTriggerTransition = factory.createRule(FireableTriggerTransition.instance).action(CRUDActivationStateEnum.CREATED) [
    	println('''Firing event transition: «source.name» to «target.name»''')
    	manipulation.remove(eventQueue, ModelPackage.eINSTANCE.eventQueue_Events, event)
    	activeStateContainer.activeState = target    	
    ].build
    
    val activeState = factory.createRule(ActiveState.instance).action(CRUDActivationStateEnum.CREATED) [
    	println('''Active state: «state.name»''')
    	activeStateListener.activeStateChanged(it.state)
    ].build
    
    val swallowEvent = factory.createRule(SwallowableEvents.instance).action(CRUDActivationStateEnum.CREATED) [
    	println('''Swallowed event: «event.name»''')
    	manipulation.remove(eventQueue, ModelPackage.eINSTANCE.eventQueue_Events, event)
    ].build

    def dispose() {
        if (transformation !== null) {
            transformation.dispose
        }
        
        transformation = null
    }
}
