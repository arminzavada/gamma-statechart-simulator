package com.triad.school.gamma.simulator.query

import com.triad.school.gamma.simulator.model.ModelFactory
import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.statechart.statechart.StateNode
import java.util.List
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine
import org.eclipse.viatra.query.runtime.emf.EMFScope
import org.eclipse.viatra.transformation.evm.specific.crud.CRUDActivationStateEnum
import org.eclipse.viatra.transformation.runtime.emf.rules.eventdriven.EventDrivenTransformationRuleFactory
import org.eclipse.viatra.transformation.runtime.emf.transformation.eventdriven.EventDrivenTransformation
import hu.bme.mit.gamma.statechart.statechart.Transition
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.IModelManipulations
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.SimpleModelManipulations
import com.triad.school.gamma.simulator.model.ModelPackage
import hu.bme.mit.gamma.statechart.statechart.Region
import hu.bme.mit.gamma.statechart.statechart.State
import com.triad.school.gamma.simulator.model.RegionalActiveState
import hu.bme.mit.gamma.statechart.interface_.Port

interface ActiveStateListener {
	def void activeStateAdded(StateNode node);
	def void activeStateRemoved(StateNode node);
}

class GammaStatechartSimulatorTransformation {	
    val EventDrivenTransformationRuleFactory factory = new EventDrivenTransformationRuleFactory    
    var EventDrivenTransformation transformation    
    var IModelManipulations manipulations
    
    val activeStateContainer = ModelFactory.eINSTANCE.createActiveStateContainer
    
    val ViatraQueryEngine engine

	var activeStateListener = new ActiveStateListener() {
		override activeStateAdded(StateNode node) {}
		override activeStateRemoved(StateNode node) {}
	}
	
	val List<RegionVisitor> visitors

    new(Resource resource) {    	
		val resourceSet = new ResourceSetImpl
		resourceSet.resources.add(resource)
		
		val modelResource = resourceSet.createResource(URI.createURI(""))
		modelResource.contents.add(activeStateContainer)
		
        val scope = new EMFScope(resourceSet)
        engine = ViatraQueryEngine.on(scope);
        FireableTransitions.instance.prepare(engine)
        
		val visitorFactory = new SequentialTopToBottomRegionVisitorFactory(
        	FireableTriggerTransition.Matcher.on(engine), 
        	RootRegion.Matcher.on(engine),
        	SubRegion.Matcher.on(engine),
        	activeStateContainer
        ) [
        	fireTransition(it)
        ]
        
        visitors = visitorFactory.rootVisitors()
        
	    val fireNonTriggerTransition = factory.createRule(FireableEmptyTransition.instance).action(CRUDActivationStateEnum.CREATED) [
	    	fireTransition(it.transition)
	    ].build
	    
	    val activeState = factory.createRule(ActiveState.instance).action(CRUDActivationStateEnum.CREATED) [
	    	println('''Entering state: «state.name»''')
	    	activeStateListener.activeStateAdded(it.state)
	    ].action(CRUDActivationStateEnum.DELETED) [
	    	println('''Exiting state: «state.name»''')
	    	activeStateListener.activeStateRemoved(it.state)
	    ].build
        
        transformation = EventDrivenTransformation.forEngine(engine)
	        .addRule(fireNonTriggerTransition)
	        .addRule(activeState)
	        .build
	        
        manipulations = new SimpleModelManipulations(engine)
    }

    public def void execute() {
    	RootRegion.Matcher.on(engine).streamAllMatches.forEach [
    		initialiseRegion(it.region)
    	]
    	
        transformation.executionSchema.startUnscheduledExecution
    }

    public def void sendEvent(Event event) {
		visitors.forEach[
			it.visit(event)
		] 
    }
    
    public def List<Port> requiredInterfaces() {
    	return StatechartInputPort.Matcher.on(engine).allMatches.map[
    		it.port
    	].toList
    }
    
    public def List<StateNode> everyState() {
    	return AllStates.Matcher.on(engine).allMatches.map[
    		it.node
    	].toList
    }
    
    def void setActiveStateListener(ActiveStateListener listener) {
    	activeStateListener = listener
    }

    def void dispose() {
        if (transformation !== null) {
            transformation.dispose
        }
        
        transformation = null
    }
    
    def void fireTransition(Transition transition) {
    	drillUp(transition.sourceState, transition.targetState)
    	
    	println('''Firing transition: «transition.sourceState.name» - «transition.targetState.name»''')   
    	
    	drillDown(transition.targetState, transition.sourceState)
    }
    
    def void drillUp(StateNode state, StateNode target) {
    	deactivateState(state)
    	
		val parent = parentState(state)
		if (parent !== null && !isAncestorOf(parent, target)) {
			drillUp(parent, target)
		}
    }
    
    def void drillDown(StateNode state, StateNode target) {    	
		val parent = parentState(state)
		if (parent !== null && !isAncestorOf(parent, target)) {
			drillDown(parent, target)
		}
    	
    	activateState(state)
    }
    
    def State parentState(StateNode state) {
    	val parent = CompositeState.Matcher.on(engine).getOneArbitraryMatch(null, state)
    	
    	if (parent.empty) null
    	else parent.get.parent
    }
    def boolean isAncestorOf(State ancestor, StateNode state) {
    	!DescendantState.Matcher.on(engine).getAllMatches(ancestor, state).empty
    }

	def void activateState(StateNode state) {		
		associatedRegionalActiveState(state).state = state
		
		if (state instanceof State) {
			state.regions.forEach[
				initialiseRegion(it)
			]
		}
	}
	def void deactivateState(StateNode state) {
		if (state instanceof State) {
			state.regions.forEach[
				resetRegion(it)
			]
		}
		
		associatedRegionalActiveState(state).state = null
	}
	
	def RegionalActiveState associatedRegionalActiveState(StateNode state) {
		AssociatedRegionalActiveState.Matcher.on(engine).getOneArbitraryMatch(null, state).get.regionalActiveState
	}
	def RegionalActiveState regionalState(Region region) {
		RegionalState.Matcher.on(engine).getOneArbitraryMatch(null, region).get.regionalActiveState
	}
    
    def void initialiseRegion(Region region) {
    	activateState(EntryState.Matcher.on(engine).getOneArbitraryMatch(region, null).get.state)
    }
    def void resetRegion(Region region) {
    	deactivateState(regionalState(region).state)
    }
}
