package com.triad.school.gamma.simulator.query

import hu.bme.mit.gamma.expression.model.Expression
import hu.bme.mit.gamma.expression.model.LessExpression
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator
import hu.bme.mit.gamma.expression.model.LessEqualExpression
import hu.bme.mit.gamma.expression.model.GreaterExpression
import hu.bme.mit.gamma.expression.model.GreaterEqualExpression
import hu.bme.mit.gamma.expression.model.EqualityExpression
import hu.bme.mit.gamma.expression.model.InequalityExpression
import hu.bme.mit.gamma.expression.model.ReferenceExpression
import hu.bme.mit.gamma.expression.model.VariableDeclaration

class SimulationExpressionEvaluator {
	public static val INSTANCE = new SimulationExpressionEvaluator
	protected new () {}
	
	public def evaluateGuard(Expression expression) {
		if (expression === null) {
			return true
		}
		if (expression instanceof LessExpression) {
			return evaluateInteger(expression.leftOperand) < evaluateInteger(expression.rightOperand)
		}
		if (expression instanceof LessEqualExpression) {
			return evaluateInteger(expression.leftOperand) <= evaluateInteger(expression.rightOperand)
		}
		if (expression instanceof GreaterExpression) {
			return evaluateInteger(expression.leftOperand) > evaluateInteger(expression.rightOperand)
		}
		if (expression instanceof GreaterEqualExpression) {
			return evaluateInteger(expression.leftOperand) >= evaluateInteger(expression.rightOperand)
		}
		if (expression instanceof EqualityExpression) {
			return evaluateInteger(expression.leftOperand) == evaluateInteger(expression.rightOperand)
		}
		if (expression instanceof InequalityExpression) {
			return evaluateInteger(expression.leftOperand) == evaluateInteger(expression.rightOperand)
		}
		return evaluateBoolean(expression)
	}
	
	def int evaluateInteger(Expression expression) {
		if (expression instanceof ReferenceExpression) {
			val declaration = expression.declaration
			if (declaration instanceof VariableDeclaration) {
				return evaluateInteger(declaration.expression)
			}
		}
		return ExpressionEvaluator.INSTANCE.evaluateInteger(expression)
	}
	def evaluateBoolean(Expression expression) {
		return ExpressionEvaluator.INSTANCE.evaluateBoolean(expression)
	}
}