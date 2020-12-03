package com.triad.school.gamma.simulator.query

import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.statechart.statechart.Region
import com.triad.school.gamma.simulator.model.ActiveStateContainer

class SequentialTopToBottomRegionVisitorFactory extends RegionVisitorFactory {
	new(
		FireableTriggerTransition.Matcher fireableTriggerTransitionMatcher, 
		FireableEmptyTransition.Matcher fireableEmptyTransitionMatcher,
		RootRegion.Matcher rootRegionMatcher, 
		SubRegion.Matcher subRegionMatcher,
		ActiveStateContainer activeStateContainer,
		TransitionFireHandler transitionFireHandler
	) {
		super(fireableTriggerTransitionMatcher, fireableEmptyTransitionMatcher, rootRegionMatcher, subRegionMatcher, activeStateContainer, transitionFireHandler)
	}
	
	override protected createVisitor(Region region) {
		return new SequentialTopToBottomRegionVisitor(region, this)
	}

	static class SequentialTopToBottomRegionVisitor extends RegionVisitor {
		new (Region region, SequentialTopToBottomRegionVisitorFactory factory) {
			super(region, factory)
		}
		
		override visit(Event event) {
			if (!fire(event)) {
				for (RegionVisitor visitor : childVisitors) {
					visitor.visit(event)
				}
			}
		}
		override visit() {
			if (!fire()) {
				for (RegionVisitor visitor : childVisitors) {
					visitor.visit()
				}
			}
		}
	}
}