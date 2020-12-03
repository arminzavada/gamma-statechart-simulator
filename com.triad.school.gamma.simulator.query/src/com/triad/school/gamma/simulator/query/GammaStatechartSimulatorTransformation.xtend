package com.triad.school.gamma.simulator.query

import com.triad.school.gamma.simulator.model.ActiveStateContainer
import com.triad.school.gamma.simulator.model.ModelFactory
import com.triad.school.gamma.simulator.query.util.QueryUtils
import com.triad.school.gamma.simulator.query.util.SimulationExpressionEvaluator
import hu.bme.mit.gamma.expression.model.BooleanTypeDefinition
import hu.bme.mit.gamma.expression.model.ExpressionModelFactory
import hu.bme.mit.gamma.expression.model.ExpressionModelPackage
import hu.bme.mit.gamma.expression.model.IntegerTypeDefinition
import hu.bme.mit.gamma.expression.model.VariableDeclaration
import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.statechart.interface_.Port
import hu.bme.mit.gamma.statechart.statechart.StateNode
import hu.bme.mit.gamma.statechart.statechart.Transition
import java.math.BigInteger
import java.util.List
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
import com.triad.school.gamma.simulator.query.util.SimulationActionEvaluator
import hu.bme.mit.gamma.action.model.Statement
import hu.bme.mit.gamma.statechart.statechart.State

interface ActiveStateListener {
	def void activeStateAdded(StateNode node);
	def void activeStateRemoved(StateNode node);
}

class GammaStatechartSimulatorTransformation {	
    val EventDrivenTransformationRuleFactory factory = new EventDrivenTransformationRuleFactory    
    var EventDrivenTransformation transformation    
    var IModelManipulations manipulations

	var activeStateListener = new ActiveStateListener() {
		override activeStateAdded(StateNode node) {}
		override activeStateRemoved(StateNode node) {}
	}
	
	val QueryUtils utils
	var SimulationActionEvaluator actionEvaluator

    new(Resource resource) {    	
    	val activeStateContainer = ModelFactory.eINSTANCE.createActiveStateContainer
		val resourceSet = new ResourceSetImpl()
		resourceSet.resources.add(resource)
		val modelResource = resourceSet.createResource(URI.createURI(""))
		modelResource.contents.add(activeStateContainer)
		
        val scope = new EMFScope(resourceSet)
        val engine = ViatraQueryEngine.on(scope);

		utils = new QueryUtils(engine)
		
		initialiseTransformation(activeStateContainer)
              
	    val activeState = factory.createRule(ActiveState.instance).action(CRUDActivationStateEnum.CREATED) [
	    	println('''Entering state: «state.name»''')
	    	activeStateListener.activeStateAdded(it.state)
	    	
	    	if (state instanceof State) {
		        (state as State).entryActions.forEach [
		        	actionEvaluator.evaluateAction(it as Statement)
		        ]
	        }
	    ].action(CRUDActivationStateEnum.DELETED) [
	    	println('''Exiting state: «state.name»''')
	    	activeStateListener.activeStateRemoved(it.state)
	    	
	        if (state instanceof State) {
			    (state as State).exitActions.forEach [
		        	actionEvaluator.evaluateAction(it as Statement)
		        ]
	        }
	    ].build
        
        transformation = EventDrivenTransformation.forEngine(engine)
	        .addRule(activeState)
	        .build
	        
        manipulations = new SimpleModelManipulations(engine)
		actionEvaluator = new SimulationActionEvaluator(manipulations)
    }
    
    def void initialiseTransformation(ActiveStateContainer activeStateContainer) {
    	createActiveStates(activeStateContainer)
    	utils.initialiseRootRegions()
    }
    
    def void createActiveStates(ActiveStateContainer activeStateContainer) {
    	utils.visit [
    		val activeState = ModelFactory.eINSTANCE.createRegionalActiveState
			activeState.region = it
			activeState.state = null
			activeStateContainer.activeStates.add(activeState)
    		
    		true
    	]
    }

    def void execute() {
        transformation.executionSchema.startUnscheduledExecution
    	
    	fireEmptyTriggersContinously()
    }

    def void sendEvent(Event event) {
		utils.visit [
			checkFireTransitions(utils.fireableTriggerTransitions(it, event))
			
			!firedTransition
		]
    	
    	fireEmptyTriggersContinously()
    }
    
    def List<Port> requiredInterfaces() {
    	utils.requiredInterfaces()
    }
    
    def List<StateNode> everyState() {
    	utils.everyState()
    }
    
    def List<VariableDeclaration> everyVariable() {
    	utils.everyVariable()   	
    }
    
    def getVariableValueString(VariableDeclaration variable) {
    	if (variable.type instanceof IntegerTypeDefinition) {
    		return SimulationExpressionEvaluator.INSTANCE.evaluateInteger(variable.expression).toString()
    	}
    	if (variable.type instanceof BooleanTypeDefinition) {
    		return SimulationExpressionEvaluator.INSTANCE.evaluateBoolean(variable.expression).toString()
    	}
    }
    
    def changeVariableValue(VariableDeclaration variable, String value) {
    	try {
	    	if (variable.type instanceof IntegerTypeDefinition) {
	    		val expression = ExpressionModelFactory.eINSTANCE.createIntegerLiteralExpression
	    		expression.value = BigInteger.valueOf(Integer.parseInt(value))
	    		
				manipulations.set(variable, ExpressionModelPackage.eINSTANCE.initializableElement_Expression, expression)
	    	}
	    	if (variable.type instanceof BooleanTypeDefinition) {
	    		val boolValue = Boolean.parseBoolean(value)
	    		val expression = boolValue ? ExpressionModelFactory.eINSTANCE.createTrueExpression : ExpressionModelFactory.eINSTANCE.createFalseExpression
	    		
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
    	
    	utils.visit [
			checkFireTransitions(utils.fireableEmptyTransitions(it))
			
			!firedTransition
		]
    }
    
    def checkFireTransitions(List<Transition> transitions) {
    	val transition = transitions.stream.sorted[ a, b|
    		if (a.guard === null) {
    			-1
    		} else {
    			1
    		} 
    	].filter[
    		SimulationExpressionEvaluator.INSTANCE.evaluateGuard(it.guard)
    	].findFirst
    	
    	if (transition.present) {
    		fireTransition(transition.get)
    	}
    }

    var firedTransition = false
    def void fireTransition(Transition transition) {
    	firedTransition = true
    	
    	drillUp(transition.sourceState, transition.targetState)
    	
    	println('''Firing transition: «transition.sourceState.name» - «transition.targetState.name»''')   
    	transition.effects.forEach [
    		actionEvaluator.evaluateAction(it as Statement)
    	]
    	
    	drillDown(transition.targetState, transition.sourceState)
    }
    
    def void drillUp(StateNode state, StateNode target) {
    	utils.deactivateState(state)
    	
		val parent = utils.parentState(state)
		if (parent !== null && !utils.isAncestorOf(parent, target)) {
			drillUp(parent, target)
		}
    }
    
    def void drillDown(StateNode state, StateNode target) {    	
		val parent = utils.parentState(state)
		if (parent !== null && !utils.isAncestorOf(parent, target)) {
			drillDown(parent, target)
		}
    	
    	utils.activateState(state)
    }
    
}
