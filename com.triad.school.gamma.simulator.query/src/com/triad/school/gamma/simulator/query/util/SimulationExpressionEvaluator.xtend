package com.triad.school.gamma.simulator.query.util

import hu.bme.mit.gamma.expression.model.AddExpression
import hu.bme.mit.gamma.expression.model.AndExpression
import hu.bme.mit.gamma.expression.model.ConstantDeclaration
import hu.bme.mit.gamma.expression.model.DivideExpression
import hu.bme.mit.gamma.expression.model.EqualityExpression
import hu.bme.mit.gamma.expression.model.Expression
import hu.bme.mit.gamma.expression.model.FalseExpression
import hu.bme.mit.gamma.expression.model.GreaterEqualExpression
import hu.bme.mit.gamma.expression.model.GreaterExpression
import hu.bme.mit.gamma.expression.model.ImplyExpression
import hu.bme.mit.gamma.expression.model.InequalityExpression
import hu.bme.mit.gamma.expression.model.IntegerLiteralExpression
import hu.bme.mit.gamma.expression.model.LessEqualExpression
import hu.bme.mit.gamma.expression.model.LessExpression
import hu.bme.mit.gamma.expression.model.MultiplyExpression
import hu.bme.mit.gamma.expression.model.NotExpression
import hu.bme.mit.gamma.expression.model.OrExpression
import hu.bme.mit.gamma.expression.model.ReferenceExpression
import hu.bme.mit.gamma.expression.model.SubtractExpression
import hu.bme.mit.gamma.expression.model.TrueExpression
import hu.bme.mit.gamma.expression.model.VariableDeclaration
import hu.bme.mit.gamma.expression.model.XorExpression

class SimulationExpressionEvaluator {
	public static val INSTANCE = new SimulationExpressionEvaluator
	protected new () {}
	
	def evaluateGuard(Expression expression) {
		if (expression === null) {
			return true
		}
		
		return evaluateBoolean(expression)
	}
	
	def int evaluateInteger(Expression expression) {
		if (expression instanceof IntegerLiteralExpression) {
			return expression.value.intValue
		}
		if (expression instanceof MultiplyExpression) {
			return expression.operands.stream.map[
				evaluateInteger(it)
			].reduce(1) [ a, b|
				a * b
			]
		}
		if (expression instanceof DivideExpression) {
			return evaluateInteger(expression.leftOperand) / evaluateInteger(expression.rightOperand);
		}
		if (expression instanceof AddExpression) {
			return expression.operands.stream.map[
				evaluateInteger(it)
			].reduce(0) [ a, b|
				a + b
			]
		}
		if (expression instanceof SubtractExpression) {
			return evaluateInteger(expression.leftOperand) - evaluateInteger(expression.rightOperand);
		}
		if (expression instanceof ReferenceExpression) {
			val declaration = expression.declaration
			if (declaration instanceof ConstantDeclaration) {
				return evaluateInteger(declaration.expression);
			}
			if (declaration instanceof VariableDeclaration) {
				return evaluateInteger(declaration.expression);
			}
		}
		throw new IllegalArgumentException("Not transformable expression: " + expression);
	}
	
	def boolean evaluateBoolean(Expression expression) {
		if (expression instanceof TrueExpression) {
			return true;
		}
		if (expression instanceof FalseExpression) {
			return false;
		}
		if (expression instanceof OrExpression) {
			return expression.operands.stream.anyMatch[
				evaluateBoolean(it)
			]
		}
		if (expression instanceof AndExpression) {
			return expression.operands.stream.allMatch[
				evaluateBoolean(it)
			]
		}
		if (expression instanceof XorExpression) {
			return expression.operands.stream.filter[
				evaluateBoolean(it)
			].count % 2 == 1
		}
		if (expression instanceof ImplyExpression) {
			return !evaluateBoolean(expression.leftOperand) || evaluateBoolean(expression.rightOperand);
		}
		if (expression instanceof NotExpression) {
			return !evaluateBoolean(expression.operand);
		}
		if (expression instanceof EqualityExpression) {
			try {
				return evaluateInteger(expression.leftOperand) == evaluateInteger(expression.rightOperand)
			} catch(IllegalArgumentException e) {
				return evaluateBoolean(expression.leftOperand) == evaluateBoolean(expression.rightOperand)
			}
		}
		if (expression instanceof InequalityExpression) {
			try {
				return evaluateInteger(expression.leftOperand) != evaluateInteger(expression.rightOperand)
			} catch(IllegalArgumentException e) {
				return evaluateBoolean(expression.leftOperand) != evaluateBoolean(expression.rightOperand) 
			}
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
		if (expression instanceof ReferenceExpression) {
			val declaration = expression.declaration
			if (declaration instanceof ConstantDeclaration) {
				return evaluateBoolean(declaration.expression);
			}
			if (declaration instanceof VariableDeclaration) {
				return evaluateBoolean(declaration.expression);
			}
		}
		throw new IllegalArgumentException("Not transformable expression: " + expression);
	}
}