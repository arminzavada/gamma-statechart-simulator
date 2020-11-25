package com.triad.school.gamma.simulator.query

import com.triad.school.gamma.simulator.model.ActiveState
import com.triad.school.gamma.simulator.model.ActiveStateContainer
import hu.bme.mit.gamma.statechart.interface_.Event

class SequentialTopToBottomRegionVisitorFactory extends RegionVisitorFactory {
	new(
		FireableTriggerTransition.Matcher fireableTriggerTransitionMatcher, 
		RootRegion.Matcher rootRegionMatcher, 
		SubRegion.Matcher subRegionMatcher, 
		InitialNode.Matcher initialNodeMatcher, 
		ActiveStateContainer container
	) {
		super(fireableTriggerTransitionMatcher, rootRegionMatcher, subRegionMatcher, initialNodeMatcher, container)
	}
	
	override protected createVisitor(ActiveState activeState) {
		return new SequentialTopToBottomRegionVisitor(activeState, this)
	}

	static class SequentialTopToBottomRegionVisitor extends RegionVisitor {
		new (ActiveState activeState, RegionVisitorFactory factory) {
			super(activeState, factory)
		}
		
		override visit(Event event) {
			if (!factory.fireEventInRegion(activeState, event)) {
				for (RegionVisitor visitor : childVisitors) {
					visitor.visit(event)
				}
			}
		}
	}
}