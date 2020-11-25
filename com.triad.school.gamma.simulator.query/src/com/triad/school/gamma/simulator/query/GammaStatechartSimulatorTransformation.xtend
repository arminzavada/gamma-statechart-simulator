package com.triad.school.gamma.simulator.query

import com.triad.school.gamma.simulator.model.ModelFactory
import com.triad.school.gamma.simulator.model.ModelPackage
import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.statechart.statechart.StateNode
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
import java.util.List

@FunctionalInterface
interface ActiveStateListener {
	def void activeStateChanged(StateNode node);
}

class GammaStatechartSimulatorTransformation {
	val FireableTransitions queries = FireableTransitions.instance
	
    val EventDrivenTransformationRuleFactory factory = new EventDrivenTransformationRuleFactory    
    EventDrivenTransformation transformation    
    
    val activeStateContainer = ModelFactory.eINSTANCE.createActiveStateContainer
    val eventQueue = ModelFactory.eINSTANCE.createEventQueue
    
    val ViatraQueryEngine engine

	ActiveStateListener activeStateListener = []
	
	List<RegionVisitor> visitors = null

    new(Resource resource) {    	
		val resourceSet = new ResourceSetImpl
		resourceSet.resources.add(resource)
		
		val modelResource = resourceSet.createResource(URI.createURI(""))
		modelResource.contents.add(activeStateContainer)
		modelResource.contents.add(eventQueue)
		
        val scope = new EMFScope(resourceSet)
        engine = ViatraQueryEngine.on(scope);
        queries.prepare(engine)
        
		val RegionVisitorFactory visitorFactory = new SequentialTopToBottomRegionVisitorFactory(
        	FireableTriggerTransition.Matcher.on(engine), 
        	RootRegion.Matcher.on(engine),
        	SubRegion.Matcher.on(engine),
        	InitialNode.Matcher.on(engine),
        	activeStateContainer
        )
        
        visitors = visitorFactory.rootVisitors()
        
        transformation = EventDrivenTransformation.forEngine(engine)
	        .addRule(fireNonTriggerTransition)
	        .addRule(activeState)
	        .build
    }

    public def execute() {
        transformation.executionSchema.startUnscheduledExecution
    }

    public def sendEvent(Event event) {
		visitors.forEach[
			it.visit(event)
		] 
    }
    
    public def requiredInterfaces() {
    	return StatechartInputPort.Matcher.on(engine).allMatches.map[
    		it.port
    	].toList
    }
    
    def setActiveStateListener(ActiveStateListener listener) {
    	activeStateListener = listener
    }
    
    val fireNonTriggerTransition = factory.createRule(FireableEmptyTransition.instance).action(CRUDActivationStateEnum.CREATED) [
    	println('''Firing empty transition: «transition.sourceState.name» to «transition.targetState.name»''')
    	it.activeState.state = transition.targetState
    ].build
    
    val activeState = factory.createRule(ActiveStateNode.instance).action(CRUDActivationStateEnum.CREATED) [
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
