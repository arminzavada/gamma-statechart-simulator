package com.triad.school.gamma.simulator.query.util

import hu.bme.mit.gamma.action.model.Action
import hu.bme.mit.gamma.action.model.AssignmentStatement
import hu.bme.mit.gamma.expression.model.ExpressionModelFactory
import hu.bme.mit.gamma.expression.model.ExpressionModelPackage
import hu.bme.mit.gamma.expression.model.IntegerTypeDefinition
import hu.bme.mit.gamma.expression.model.VariableDeclaration
import java.math.BigInteger
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.IModelManipulations

class SimulationActionExecutor {
	IModelManipulations manipulations
	
	new (IModelManipulations manipulations) {
		this.manipulations = manipulations
	}
	
	def executeAction(Action action) {
		if (action instanceof AssignmentStatement) {
			executeAssignment(action)
		}
	}
	
	def executeAssignment(AssignmentStatement statement) {
		val declaration = statement.lhs.declaration as VariableDeclaration
		if (declaration.type instanceof IntegerTypeDefinition) {
    		val expression = ExpressionModelFactory.eINSTANCE.createIntegerLiteralExpression
    		expression.value = BigInteger.valueOf(SimulationExpressionEvaluator.INSTANCE.evaluateInteger(statement.rhs))
    		
			manipulations.set(declaration, ExpressionModelPackage.eINSTANCE.initializableElement_Expression, expression)
    	}
	}
}