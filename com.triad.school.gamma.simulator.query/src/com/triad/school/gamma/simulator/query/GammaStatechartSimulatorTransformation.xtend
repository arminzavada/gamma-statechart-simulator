package com.triad.school.gamma.simulator.query

import com.triad.school.gamma.simulator.active_state.ActiveStateContainer
import com.triad.school.gamma.simulator.active_state.Active_stateFactory
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
    
    val ActiveStateContainer activeStateContainer = Active_stateFactory.eINSTANCE.createActiveStateContainer
    
    val IModelManipulations manipulation
    
    val ViatraQueryEngine engine

	ActiveStateListener activeStateListener = []

    new(Resource resource) {    	
		val resourceSet = new ResourceSetImpl
		resourceSet.resources.add(resource)
		
		val active_stateResource = resourceSet.createResource(URI.createURI(""))
		active_stateResource.contents.add(activeStateContainer)
		
        val scope = new EMFScope(resourceSet)
        engine = ViatraQueryEngine.on(scope);
        queries.prepare(engine)
        manipulation = new SimpleModelManipulations(engine)
        
        transformation = EventDrivenTransformation.forEngine(engine)
	        .addRule(fireNonTriggerTransition)
	        .addRule(fireTriggerTransition)
	        .addRule(activeState)
	        .build
    }

    public def execute() { 
        activeStateContainer.activeState = InitialNode.Matcher.on(engine).oneArbitraryMatch.get.state
        transformation.executionSchema.startUnscheduledExecution
    }

    public def sendEvent(Event event) {
    	
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
    	println('''Firing transition: «source.name» to «target.name»''')
    	activeStateContainer.activeState = target
    ].build
    
    val fireTriggerTransition = factory.createRule(FireableTriggerTransition.instance).action(CRUDActivationStateEnum.CREATED) [
    	//println('''Firing transition: «source.name» to «target.name»''')
    	//activeStateContainer.activeState = target    	
    ].build
    
    val activeState = factory.createRule(ActiveState.instance).action(CRUDActivationStateEnum.CREATED) [
    	println('''Active state: «state.name»''')
    	activeStateListener.activeStateChanged(it.state)
    ].build

    def dispose() {
        if (transformation !== null) {
            transformation.dispose
        }
        
        transformation = null
    }
}
