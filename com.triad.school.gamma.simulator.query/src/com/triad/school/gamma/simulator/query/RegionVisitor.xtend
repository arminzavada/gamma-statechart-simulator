package com.triad.school.gamma.simulator.query

import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.statechart.statechart.Region
import hu.bme.mit.gamma.statechart.statechart.Transition
import java.util.List
import com.triad.school.gamma.simulator.model.ActiveStateContainer
import com.triad.school.gamma.simulator.model.ModelPackage
import com.triad.school.gamma.simulator.model.ModelFactory

@FunctionalInterface
interface TransitionFireHandler {
	def void fire(Transition transition)
}

abstract class RegionVisitorFactory {
	val FireableTriggerTransition.Matcher fireableTriggerTransitionMatcher
	val RootRegion.Matcher rootRegionMatcher
	val SubRegion.Matcher subRegionMatcher
	val ActiveStateContainer activeStateContainer
	val TransitionFireHandler transitionFireHandler
	
	new (
		FireableTriggerTransition.Matcher fireableTriggerTransitionMatcher, 
		RootRegion.Matcher rootRegionMatcher, 
		SubRegion.Matcher subRegionMatcher,
		ActiveStateContainer activeStateContainer,
		TransitionFireHandler transitionFireHandler
	) {
		this.fireableTriggerTransitionMatcher = fireableTriggerTransitionMatcher
		this.rootRegionMatcher = rootRegionMatcher
		this.subRegionMatcher = subRegionMatcher
		this.activeStateContainer = activeStateContainer
		this.transitionFireHandler = transitionFireHandler
	}
	
	final def List<RegionVisitor> rootVisitors() {
		return rootRegionMatcher.allMatches.map [
			createRegionalActiveState(it.region)
			createVisitor(it.region)
		].toList
	}
	
	final def List<RegionVisitor> childVisitors(Region region) {
		return subRegionMatcher.getAllMatches(region, null).map [
			createRegionalActiveState(it.region)
			createVisitor(it.region)
		].toList
	}
	
	final def createRegionalActiveState(Region region) {
		val activeState = ModelFactory.eINSTANCE.createRegionalActiveState
		activeState.region = region
		activeState.state = null
		activeStateContainer.activeStates.add(activeState)
	}
	
	protected def RegionVisitor createVisitor(Region region)
	
	protected final def boolean fireEventInRegion(Region region, Event event) {
		val matches = fireableTriggerTransitionMatcher.getAllMatches(region, null, event)
		
		if (!matches.empty) {
			transitionFireHandler.fire(matches.get(0).transition)
			
			return true
		}
		
		return false
	}
}

abstract class RegionVisitor {	
	val Region region
	val RegionVisitorFactory factory
	
	protected val List<RegionVisitor> childVisitors
	
	new (Region region, RegionVisitorFactory factory) {
		this.region = region
		this.factory = factory
		
		childVisitors = factory.childVisitors(region)
	}
	
	protected def fire(Event event) {
		factory.fireEventInRegion(region, event)
	}
	
	def void visit(Event event)
}