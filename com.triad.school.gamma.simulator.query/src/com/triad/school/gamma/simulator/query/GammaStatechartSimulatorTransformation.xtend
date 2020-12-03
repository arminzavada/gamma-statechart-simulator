package com.triad.school.gamma.simulator.query

import com.triad.school.gamma.simulator.model.ModelFactory
import com.triad.school.gamma.simulator.query.util.QueryUtils
import com.triad.school.gamma.simulator.query.util.SimulationExpressionEvaluator
import com.triad.school.gamma.simulator.query.visitors.RegionVisitor
import com.triad.school.gamma.simulator.query.visitors.SequentialTopToBottomRegionVisitorFactory
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
	
	val List<RegionVisitor> visitors
	val QueryUtils utils

    new(Resource resource) {    	
    	val activeStateContainer = ModelFactory.eINSTANCE.createActiveStateContainer
    	
		val resourceSet = new ResourceSetImpl()
		resourceSet.resources.add(resource)
		
		val modelResource = resourceSet.createResource(URI.createURI(""))
		modelResource.contents.add(activeStateContainer)
		
        val scope = new EMFScope(resourceSet)
        val engine = ViatraQueryEngine.on(scope);

		utils = new QueryUtils(engine)
        
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

    def void execute() {
    	utils.initialiseRootRegions()
    	    	
        transformation.executionSchema.startUnscheduledExecution
    	
    	fireEmptyTriggersContinously()
    }

    def void sendEvent(Event event) {
		visitors.forEach[
			it.visit(event)
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
    	
    	visitors.forEach[
			it.visit()
		] 
    }
    

    var firedTransition = false
    def void fireTransition(List<Transition> transitions) {
    	val transition = transitions.stream.sorted[ a, b|
    		if (a.guard === null) {
    			-1
    		} else {
    			1
    		} 
    	].filter[
    		SimulationExpressionEvaluator.INSTANCE.evaluateGuard(it.guard)
    	].findFirst
    	
    	if (!transition.empty) {
	    	firedTransition = true
	    	
	    	drillUp(transition.get.sourceState, transition.get.targetState)
	    	
	    	println('''Firing transition: «transition.get.sourceState.name» - «transition.get.targetState.name»''')   
	    	
	    	drillDown(transition.get.targetState, transition.get.sourceState)
    	}
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
