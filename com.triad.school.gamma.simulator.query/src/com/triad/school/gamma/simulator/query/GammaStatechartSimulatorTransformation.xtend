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
import hu.bme.mit.gamma.statechart.statechart.Region
import hu.bme.mit.gamma.statechart.statechart.State
import com.triad.school.gamma.simulator.model.RegionalActiveState
import hu.bme.mit.gamma.statechart.interface_.Port
import hu.bme.mit.gamma.expression.model.ExpressionModelFactory
import hu.bme.mit.gamma.expression.model.VariableDeclaration
import hu.bme.mit.gamma.expression.model.IntegerTypeDefinition
import java.math.BigInteger
import hu.bme.mit.gamma.expression.model.ExpressionModelPackage
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator

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
        TransformationQueries.instance.prepare(engine)
        
		val visitorFactory = new SequentialTopToBottomRegionVisitorFactory(
        	FireableTriggerTransition.Matcher.on(engine), 
        	FireableEmptyTransition.Matcher.on(engine),
        	RootRegion.Matcher.on(engine),
        	SubRegion.Matcher.on(engine),
        	activeStateContainer
        ) [
        	fireTransition(it)
        ]
        
        visitors = visitorFactory.rootVisitors()
	    
	    val activeState = factory.createRule(ActiveState.instance).action(CRUDActivationStateEnum.CREATED) [
	    	println('''Entering state: «state.name»''')
	    	activeStateListener.activeStateAdded(it.state)
	    ].action(CRUDActivationStateEnum.DELETED) [
	    	println('''Exiting state: «state.name»''')
	    	activeStateListener.activeStateRemoved(it.state)
	    ].build
        
        transformation = EventDrivenTransformation.forEngine(engine)
	        .addRule(activeState)
	        .build
	        
        manipulations = new SimpleModelManipulations(engine)
    }

    public def void execute() {
    	RootRegion.Matcher.on(engine).streamAllMatches.forEach [
    		initialiseRegion(it.region)
    	]
    	    	
        transformation.executionSchema.startUnscheduledExecution
    	
    	fireEmptyTriggersContinously()
    }

    public def void sendEvent(Event event) {
		visitors.forEach[
			it.visit(event)
		] 
    	
    	fireEmptyTriggersContinously()
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
    
    public def List<VariableDeclaration> everyVariable() {
    	return Variables.Matcher.on(engine).allMatches.map [
    		it.variable
    	].toList    	
    }
    
    public def changeVariableValue(VariableDeclaration variable, String value) {
    	try {
	    	if (variable.type instanceof IntegerTypeDefinition) {
	    		val expression = ExpressionModelFactory.eINSTANCE.createIntegerLiteralExpression
	    		expression.value = BigInteger.valueOf(Integer.parseInt(value))
	    		
				manipulations.set(variable, ExpressionModelPackage.eINSTANCE.initializableElement_Expression, expression)
	    	}
    	} catch (Exception e) { }    	
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
    
    def void fireEmptyTriggersContinously() {
    	while (firedTransition) {
    		firedTransition = false
    	}
    	
    	visitors.forEach[
			it.visit()
		] 
    }
    

    var firedTransition = false
    def void fireTransition(List<Transition> transitions) {
    	val transition = transitions.findFirst[
    		SimulationExpressionEvaluator.INSTANCE.evaluateGuard(it.guard)
    	]
    	
    	if (transition !== null) {
	    	firedTransition = true
	    	
	    	drillUp(transition.sourceState, transition.targetState)
	    	
	    	println('''Firing transition: «transition.sourceState.name» - «transition.targetState.name»''')   
	    	
	    	drillDown(transition.targetState, transition.sourceState)
    	}
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
