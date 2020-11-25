package com.triad.school.gamma.simulator.query

import com.triad.school.gamma.simulator.model.ActiveStateContainer
import com.triad.school.gamma.simulator.model.ModelFactory
import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.statechart.statechart.Region
import hu.bme.mit.gamma.statechart.statechart.Transition
import java.util.List
import com.triad.school.gamma.simulator.model.ActiveState

@FunctionalInterface
interface TransitionFireHandler {
	def void fire(Transition transition)
}

abstract class RegionVisitorFactory {
	val FireableTriggerTransition.Matcher fireableTriggerTransitionMatcher
	val RootRegion.Matcher rootRegionMatcher
	val SubRegion.Matcher subRegionMatcher
	val InitialNode.Matcher initialNodeMatcher
	val ActiveStateContainer container
	
	new (
		FireableTriggerTransition.Matcher fireableTriggerTransitionMatcher, 
		RootRegion.Matcher rootRegionMatcher, 
		SubRegion.Matcher subRegionMatcher, 
		InitialNode.Matcher initialNodeMatcher, 
		ActiveStateContainer container
	) {
		this.fireableTriggerTransitionMatcher = fireableTriggerTransitionMatcher
		this.rootRegionMatcher = rootRegionMatcher
		this.subRegionMatcher = subRegionMatcher
		this.initialNodeMatcher = initialNodeMatcher
		this.container = container
	}
	
	final def List<RegionVisitor> rootVisitors() {
		return rootRegionMatcher.allMatches.map [
			createVisitor(createActiveState(it.region))
		].toList
	}
	
	final def List<RegionVisitor> childVisitors(Region region) {
		return subRegionMatcher.getAllMatches(region, null).map [
			createVisitor(createActiveState(it.region))
		].toList
	}
	
	def createActiveState(Region region) {
		val state = initialNodeMatcher.getOneArbitraryMatch(region, null).get.state
		val activeState = ModelFactory.eINSTANCE.createActiveState
		activeState.containingRegion = region
		activeState.state = state
		container.activestates.add(activeState)
		return activeState
	}
	
	protected def RegionVisitor createVisitor(ActiveState activeState)
	
	protected final def boolean fireEventInRegion(ActiveState activeState, Event event) {
		val matches = fireableTriggerTransitionMatcher.getAllMatches(activeState, null, event)
		
		if (!matches.empty) {
			activeState.state = matches.findFirst[true].transition.targetState // TODO: implement more robust logic for selecting fireable transitions.
		}
		
		return !matches.empty
	}
}

abstract class RegionVisitor {	
	protected val ActiveState activeState
	protected val RegionVisitorFactory factory
	protected val List<RegionVisitor> childVisitors
	
	new (ActiveState activeState, RegionVisitorFactory factory) {
		this.activeState = activeState
		this.factory = factory
		
		childVisitors = factory.childVisitors(activeState.containingRegion)
	}
	
	def void visit(Event event)
}