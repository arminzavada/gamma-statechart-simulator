package com.triad.school.gamma.simulator.query.util

import com.triad.school.gamma.simulator.model.RegionalActiveState
import com.triad.school.gamma.simulator.query.AllStates
import com.triad.school.gamma.simulator.query.AssociatedRegionalActiveState
import com.triad.school.gamma.simulator.query.DescendantState
import com.triad.school.gamma.simulator.query.EntryState
import com.triad.school.gamma.simulator.query.RegionalState
import com.triad.school.gamma.simulator.query.RootRegion
import com.triad.school.gamma.simulator.query.StatechartInputPort
import com.triad.school.gamma.simulator.query.TransformationQueries
import com.triad.school.gamma.simulator.query.Variables
import hu.bme.mit.gamma.expression.model.VariableDeclaration
import hu.bme.mit.gamma.statechart.interface_.Port
import hu.bme.mit.gamma.statechart.statechart.Region
import hu.bme.mit.gamma.statechart.statechart.State
import hu.bme.mit.gamma.statechart.statechart.StateNode
import java.util.List
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine
import java.util.stream.Collectors
import com.triad.school.gamma.simulator.query.SubRegion
import com.triad.school.gamma.simulator.query.FireableTransition
import hu.bme.mit.gamma.statechart.interface_.Event
import com.triad.school.gamma.simulator.query.HistoryEntryState
import com.triad.school.gamma.simulator.query.CompositeState
import com.triad.school.gamma.simulator.query.FireablePseudoStateTransition

class QueryUtils {
	val HistoryEntryState.Matcher historyEntryStateMatcher
	val EntryState.Matcher entryStateMatcher
	val CompositeState.Matcher compositeStateMatcher
	val DescendantState.Matcher descendantStateMatcher
	val AssociatedRegionalActiveState.Matcher associatedRegionalActiveStateMatcher
	val RegionalState.Matcher regionalStateMatcher
	val RootRegion.Matcher rootRegionMatcher
	val StatechartInputPort.Matcher statechartInputPortMatcher
	val AllStates.Matcher allStatesMatcher
	val Variables.Matcher variablesMatcher
	val SubRegion.Matcher subRegionMatcher
	val FireableTransition.Matcher fireableTransitionMatcher
	val FireablePseudoStateTransition.Matcher fireablePseudoStateTransitionMatcher
	
	new (ViatraQueryEngine engine) {
		TransformationQueries.instance.prepare(engine)
		
		historyEntryStateMatcher = HistoryEntryState.Matcher.on(engine)
		entryStateMatcher = EntryState.Matcher.on(engine)
		compositeStateMatcher = CompositeState.Matcher.on(engine)
		descendantStateMatcher = DescendantState.Matcher.on(engine)
		associatedRegionalActiveStateMatcher = AssociatedRegionalActiveState.Matcher.on(engine)
		regionalStateMatcher = RegionalState.Matcher.on(engine)
		rootRegionMatcher = RootRegion.Matcher.on(engine)
		statechartInputPortMatcher = StatechartInputPort.Matcher.on(engine)
		allStatesMatcher = AllStates.Matcher.on(engine)
		variablesMatcher = Variables.Matcher.on(engine)
		subRegionMatcher = SubRegion.Matcher.on(engine)
		fireableTransitionMatcher = FireableTransition.Matcher.on(engine)
		fireablePseudoStateTransitionMatcher = FireablePseudoStateTransition.Matcher.on(engine)
	}
	
	def State parentState(StateNode state) {
    	val parent = compositeStateMatcher.getOneArbitraryMatch(null, state)
    	
    	if (parent.empty) null
    	else parent.get.parent
    }
    
    def boolean isAncestorOf(State ancestor, StateNode state) {
    	descendantStateMatcher.hasMatch(ancestor, state)
    }
    
    def initialiseRootRegions() {
    	rootRegionMatcher.streamAllMatches.forEach [
    		initialiseRegion(it.region)
    	]
    }
    
    def List<Port> requiredInterfaces() {
    	return statechartInputPortMatcher.streamAllMatches.map [
    		it.port
    	].collect(Collectors.toList)
    }
    
    def List<StateNode> everyState() {
    	return allStatesMatcher.streamAllMatches.map [
    		it.node
    	].collect(Collectors.toList)
    }
    
    def List<VariableDeclaration> everyVariable() {
    	return variablesMatcher.streamAllMatches.map [
    		it.variable
    	].collect(Collectors.toList)    	
    }

	def void activateState(StateNode state) {		
		val regionalState = regionalState(state)
		regionalState.state = state
		regionalState.last = state
		
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
		
		regionalState(state).state = null
	}
	
	def fireableTransitions(Region region, Event event) {
		fireableTransitionMatcher.streamAllMatches(region, null, event).map [
			it.transition
		].collect(Collectors.toList)
	}
	
	def fireablePseudoStateTransitions(Region region) {
		fireablePseudoStateTransitionMatcher.streamAllMatches(region, null).map [
			it.transition
		].collect(Collectors.toList)
	}
	
	def visit((Region) => boolean visitor) {
		rootRegionMatcher.streamAllMatches.forEach [
			visitTopDown(it.region, visitor)
		]
	}
	
	private def void visitTopDown(Region region, (Region) => boolean visitor) {
		if (visitor.apply(region)) {
			subRegionMatcher.streamAllMatches(region, null).forEach [			
				visitTopDown(it.region, visitor)
			]
		}
	}
	
	def RegionalActiveState regionalState(StateNode state) {
		associatedRegionalActiveStateMatcher.getOneArbitraryMatch(null, state).get.regionalActiveState
	}
	def RegionalActiveState regionalState(Region region) {
		regionalStateMatcher.getOneArbitraryMatch(null, region).get.regionalActiveState
	}
    
    def void initialiseRegion(Region region) {
    	val historyEntryState = historyEntryStateMatcher.getOneArbitraryMatch(region, null)
    	
    	if (historyEntryState.isPresent) {
    		activateState(historyEntryState.get.state)    		
    	} else {
    		activateState(entryStateMatcher.getOneArbitraryMatch(region, null).get.state)
    	}
    	
    }
    def void resetRegion(Region region) {
    	deactivateState(regionalState(region).state)
    }
}