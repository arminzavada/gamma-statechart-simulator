/**
 * Generated from platform:/resource/com.triad.school.gamma.simulator.query/src/com/triad/school/gamma/simulator/query/fireableTransitions.vql
 */
package com.triad.school.gamma.simulator.query;

import com.triad.school.gamma.simulator.query.ActiveState;
import hu.bme.mit.gamma.statechart.statechart.StateNode;
import hu.bme.mit.gamma.statechart.statechart.Transition;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Equality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.NegativePatternCall;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.PositivePatternCall;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PVisibility;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuples;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;

/**
 * A pattern-specific query specification that can instantiate Matcher in a type-safe way.
 * 
 * <p>Original source:
 *         <code><pre>
 *         pattern fireableNonTriggerTransition(source: StateNode, transition: Transition, target: StateNode) {
 *         	Transition.sourceState(transition, source);
 *         	Transition.targetState(transition, target);
 *         	
 *         	find activeState(source);
 *         	
 *         	neg Transition.trigger(transition, _trigger);
 *         }
 * </pre></code>
 * 
 * @see Matcher
 * @see Match
 * 
 */
@SuppressWarnings("all")
public final class FireableNonTriggerTransition extends BaseGeneratedEMFQuerySpecification<FireableNonTriggerTransition.Matcher> {
  /**
   * Pattern-specific match representation of the com.triad.school.gamma.simulator.query.fireableNonTriggerTransition pattern,
   * to be used in conjunction with {@link Matcher}.
   * 
   * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
   * Each instance is a (possibly partial) substitution of pattern parameters,
   * usable to represent a match of the pattern in the result of a query,
   * or to specify the bound (fixed) input parameters when issuing a query.
   * 
   * @see Matcher
   * 
   */
  public static abstract class Match extends BasePatternMatch {
    private StateNode fSource;
    
    private Transition fTransition;
    
    private StateNode fTarget;
    
    private static List<String> parameterNames = makeImmutableList("source", "transition", "target");
    
    private Match(final StateNode pSource, final Transition pTransition, final StateNode pTarget) {
      this.fSource = pSource;
      this.fTransition = pTransition;
      this.fTarget = pTarget;
    }
    
    @Override
    public Object get(final String parameterName) {
      switch(parameterName) {
          case "source": return this.fSource;
          case "transition": return this.fTransition;
          case "target": return this.fTarget;
          default: return null;
      }
    }
    
    @Override
    public Object get(final int index) {
      switch(index) {
          case 0: return this.fSource;
          case 1: return this.fTransition;
          case 2: return this.fTarget;
          default: return null;
      }
    }
    
    public StateNode getSource() {
      return this.fSource;
    }
    
    public Transition getTransition() {
      return this.fTransition;
    }
    
    public StateNode getTarget() {
      return this.fTarget;
    }
    
    @Override
    public boolean set(final String parameterName, final Object newValue) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      if ("source".equals(parameterName) ) {
          this.fSource = (StateNode) newValue;
          return true;
      }
      if ("transition".equals(parameterName) ) {
          this.fTransition = (Transition) newValue;
          return true;
      }
      if ("target".equals(parameterName) ) {
          this.fTarget = (StateNode) newValue;
          return true;
      }
      return false;
    }
    
    public void setSource(final StateNode pSource) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fSource = pSource;
    }
    
    public void setTransition(final Transition pTransition) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fTransition = pTransition;
    }
    
    public void setTarget(final StateNode pTarget) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fTarget = pTarget;
    }
    
    @Override
    public String patternName() {
      return "com.triad.school.gamma.simulator.query.fireableNonTriggerTransition";
    }
    
    @Override
    public List<String> parameterNames() {
      return FireableNonTriggerTransition.Match.parameterNames;
    }
    
    @Override
    public Object[] toArray() {
      return new Object[]{fSource, fTransition, fTarget};
    }
    
    @Override
    public FireableNonTriggerTransition.Match toImmutable() {
      return isMutable() ? newMatch(fSource, fTransition, fTarget) : this;
    }
    
    @Override
    public String prettyPrint() {
      StringBuilder result = new StringBuilder();
      result.append("\"source\"=" + prettyPrintValue(fSource) + ", ");
      result.append("\"transition\"=" + prettyPrintValue(fTransition) + ", ");
      result.append("\"target\"=" + prettyPrintValue(fTarget));
      return result.toString();
    }
    
    @Override
    public int hashCode() {
      return Objects.hash(fSource, fTransition, fTarget);
    }
    
    @Override
    public boolean equals(final Object obj) {
      if (this == obj)
          return true;
      if (obj == null) {
          return false;
      }
      if ((obj instanceof FireableNonTriggerTransition.Match)) {
          FireableNonTriggerTransition.Match other = (FireableNonTriggerTransition.Match) obj;
          return Objects.equals(fSource, other.fSource) && Objects.equals(fTransition, other.fTransition) && Objects.equals(fTarget, other.fTarget);
      } else {
          // this should be infrequent
          if (!(obj instanceof IPatternMatch)) {
              return false;
          }
          IPatternMatch otherSig  = (IPatternMatch) obj;
          return Objects.equals(specification(), otherSig.specification()) && Arrays.deepEquals(toArray(), otherSig.toArray());
      }
    }
    
    @Override
    public FireableNonTriggerTransition specification() {
      return FireableNonTriggerTransition.instance();
    }
    
    /**
     * Returns an empty, mutable match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @return the empty match.
     * 
     */
    public static FireableNonTriggerTransition.Match newEmptyMatch() {
      return new Mutable(null, null, null);
    }
    
    /**
     * Returns a mutable (partial) match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the new, mutable (partial) match object.
     * 
     */
    public static FireableNonTriggerTransition.Match newMutableMatch(final StateNode pSource, final Transition pTransition, final StateNode pTarget) {
      return new Mutable(pSource, pTransition, pTarget);
    }
    
    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public static FireableNonTriggerTransition.Match newMatch(final StateNode pSource, final Transition pTransition, final StateNode pTarget) {
      return new Immutable(pSource, pTransition, pTarget);
    }
    
    private static final class Mutable extends FireableNonTriggerTransition.Match {
      Mutable(final StateNode pSource, final Transition pTransition, final StateNode pTarget) {
        super(pSource, pTransition, pTarget);
      }
      
      @Override
      public boolean isMutable() {
        return true;
      }
    }
    
    private static final class Immutable extends FireableNonTriggerTransition.Match {
      Immutable(final StateNode pSource, final Transition pTransition, final StateNode pTarget) {
        super(pSource, pTransition, pTarget);
      }
      
      @Override
      public boolean isMutable() {
        return false;
      }
    }
  }
  
  /**
   * Generated pattern matcher API of the com.triad.school.gamma.simulator.query.fireableNonTriggerTransition pattern,
   * providing pattern-specific query methods.
   * 
   * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
   * e.g. in conjunction with {@link ViatraQueryEngine#on(QueryScope)}.
   * 
   * <p>Matches of the pattern will be represented as {@link Match}.
   * 
   * <p>Original source:
   * <code><pre>
   * pattern fireableNonTriggerTransition(source: StateNode, transition: Transition, target: StateNode) {
   * 	Transition.sourceState(transition, source);
   * 	Transition.targetState(transition, target);
   * 	
   * 	find activeState(source);
   * 	
   * 	neg Transition.trigger(transition, _trigger);
   * }
   * </pre></code>
   * 
   * @see Match
   * @see FireableNonTriggerTransition
   * 
   */
  public static class Matcher extends BaseMatcher<FireableNonTriggerTransition.Match> {
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    public static FireableNonTriggerTransition.Matcher on(final ViatraQueryEngine engine) {
      // check if matcher already exists
      Matcher matcher = engine.getExistingMatcher(querySpecification());
      if (matcher == null) {
          matcher = (Matcher)engine.getMatcher(querySpecification());
      }
      return matcher;
    }
    
    /**
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * @return an initialized matcher
     * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
     * 
     */
    public static FireableNonTriggerTransition.Matcher create() {
      return new Matcher();
    }
    
    private static final int POSITION_SOURCE = 0;
    
    private static final int POSITION_TRANSITION = 1;
    
    private static final int POSITION_TARGET = 2;
    
    private static final Logger LOGGER = ViatraQueryLoggingUtil.getLogger(FireableNonTriggerTransition.Matcher.class);
    
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    private Matcher() {
      super(querySpecification());
    }
    
    /**
     * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return matches represented as a Match object.
     * 
     */
    public Collection<FireableNonTriggerTransition.Match> getAllMatches(final StateNode pSource, final Transition pTransition, final StateNode pTarget) {
      return rawStreamAllMatches(new Object[]{pSource, pTransition, pTarget}).collect(Collectors.toSet());
    }
    
    /**
     * Returns a stream of all matches of the pattern that conform to the given fixed values of some parameters.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return a stream of matches represented as a Match object.
     * 
     */
    public Stream<FireableNonTriggerTransition.Match> streamAllMatches(final StateNode pSource, final Transition pTransition, final StateNode pTarget) {
      return rawStreamAllMatches(new Object[]{pSource, pTransition, pTarget});
    }
    
    /**
     * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return a match represented as a Match object, or null if no match is found.
     * 
     */
    public Optional<FireableNonTriggerTransition.Match> getOneArbitraryMatch(final StateNode pSource, final Transition pTransition, final StateNode pTarget) {
      return rawGetOneArbitraryMatch(new Object[]{pSource, pTransition, pTarget});
    }
    
    /**
     * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
     * under any possible substitution of the unspecified parameters (if any).
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return true if the input is a valid (partial) match of the pattern.
     * 
     */
    public boolean hasMatch(final StateNode pSource, final Transition pTransition, final StateNode pTarget) {
      return rawHasMatch(new Object[]{pSource, pTransition, pTarget});
    }
    
    /**
     * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the number of pattern matches found.
     * 
     */
    public int countMatches(final StateNode pSource, final Transition pTransition, final StateNode pTarget) {
      return rawCountMatches(new Object[]{pSource, pTransition, pTarget});
    }
    
    /**
     * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @param processor the action that will process the selected match.
     * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
     * 
     */
    public boolean forOneArbitraryMatch(final StateNode pSource, final Transition pTransition, final StateNode pTarget, final Consumer<? super FireableNonTriggerTransition.Match> processor) {
      return rawForOneArbitraryMatch(new Object[]{pSource, pTransition, pTarget}, processor);
    }
    
    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTransition the fixed value of pattern parameter transition, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public FireableNonTriggerTransition.Match newMatch(final StateNode pSource, final Transition pTransition, final StateNode pTarget) {
      return FireableNonTriggerTransition.Match.newMatch(pSource, pTransition, pTarget);
    }
    
    /**
     * Retrieve the set of values that occur in matches for source.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<StateNode> rawStreamAllValuesOfsource(final Object[] parameters) {
      return rawStreamAllValues(POSITION_SOURCE, parameters).map(StateNode.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for source.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<StateNode> getAllValuesOfsource() {
      return rawStreamAllValuesOfsource(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for source.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<StateNode> streamAllValuesOfsource() {
      return rawStreamAllValuesOfsource(emptyArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for source.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<StateNode> streamAllValuesOfsource(final FireableNonTriggerTransition.Match partialMatch) {
      return rawStreamAllValuesOfsource(partialMatch.toArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for source.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<StateNode> streamAllValuesOfsource(final Transition pTransition, final StateNode pTarget) {
      return rawStreamAllValuesOfsource(new Object[]{null, pTransition, pTarget});
    }
    
    /**
     * Retrieve the set of values that occur in matches for source.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<StateNode> getAllValuesOfsource(final FireableNonTriggerTransition.Match partialMatch) {
      return rawStreamAllValuesOfsource(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for source.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<StateNode> getAllValuesOfsource(final Transition pTransition, final StateNode pTarget) {
      return rawStreamAllValuesOfsource(new Object[]{null, pTransition, pTarget}).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for transition.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<Transition> rawStreamAllValuesOftransition(final Object[] parameters) {
      return rawStreamAllValues(POSITION_TRANSITION, parameters).map(Transition.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for transition.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Transition> getAllValuesOftransition() {
      return rawStreamAllValuesOftransition(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for transition.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<Transition> streamAllValuesOftransition() {
      return rawStreamAllValuesOftransition(emptyArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for transition.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<Transition> streamAllValuesOftransition(final FireableNonTriggerTransition.Match partialMatch) {
      return rawStreamAllValuesOftransition(partialMatch.toArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for transition.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<Transition> streamAllValuesOftransition(final StateNode pSource, final StateNode pTarget) {
      return rawStreamAllValuesOftransition(new Object[]{pSource, null, pTarget});
    }
    
    /**
     * Retrieve the set of values that occur in matches for transition.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Transition> getAllValuesOftransition(final FireableNonTriggerTransition.Match partialMatch) {
      return rawStreamAllValuesOftransition(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for transition.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Transition> getAllValuesOftransition(final StateNode pSource, final StateNode pTarget) {
      return rawStreamAllValuesOftransition(new Object[]{pSource, null, pTarget}).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<StateNode> rawStreamAllValuesOftarget(final Object[] parameters) {
      return rawStreamAllValues(POSITION_TARGET, parameters).map(StateNode.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<StateNode> getAllValuesOftarget() {
      return rawStreamAllValuesOftarget(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<StateNode> streamAllValuesOftarget() {
      return rawStreamAllValuesOftarget(emptyArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<StateNode> streamAllValuesOftarget(final FireableNonTriggerTransition.Match partialMatch) {
      return rawStreamAllValuesOftarget(partialMatch.toArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<StateNode> streamAllValuesOftarget(final StateNode pSource, final Transition pTransition) {
      return rawStreamAllValuesOftarget(new Object[]{pSource, pTransition, null});
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<StateNode> getAllValuesOftarget(final FireableNonTriggerTransition.Match partialMatch) {
      return rawStreamAllValuesOftarget(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<StateNode> getAllValuesOftarget(final StateNode pSource, final Transition pTransition) {
      return rawStreamAllValuesOftarget(new Object[]{pSource, pTransition, null}).collect(Collectors.toSet());
    }
    
    @Override
    protected FireableNonTriggerTransition.Match tupleToMatch(final Tuple t) {
      try {
          return FireableNonTriggerTransition.Match.newMatch((StateNode) t.get(POSITION_SOURCE), (Transition) t.get(POSITION_TRANSITION), (StateNode) t.get(POSITION_TARGET));
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in tuple not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected FireableNonTriggerTransition.Match arrayToMatch(final Object[] match) {
      try {
          return FireableNonTriggerTransition.Match.newMatch((StateNode) match[POSITION_SOURCE], (Transition) match[POSITION_TRANSITION], (StateNode) match[POSITION_TARGET]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected FireableNonTriggerTransition.Match arrayToMatchMutable(final Object[] match) {
      try {
          return FireableNonTriggerTransition.Match.newMutableMatch((StateNode) match[POSITION_SOURCE], (Transition) match[POSITION_TRANSITION], (StateNode) match[POSITION_TARGET]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }
    
    /**
     * @return the singleton instance of the query specification of this pattern
     * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
     * 
     */
    public static IQuerySpecification<FireableNonTriggerTransition.Matcher> querySpecification() {
      return FireableNonTriggerTransition.instance();
    }
  }
  
  private FireableNonTriggerTransition() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static FireableNonTriggerTransition instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected FireableNonTriggerTransition.Matcher instantiate(final ViatraQueryEngine engine) {
    return FireableNonTriggerTransition.Matcher.on(engine);
  }
  
  @Override
  public FireableNonTriggerTransition.Matcher instantiate() {
    return FireableNonTriggerTransition.Matcher.create();
  }
  
  @Override
  public FireableNonTriggerTransition.Match newEmptyMatch() {
    return FireableNonTriggerTransition.Match.newEmptyMatch();
  }
  
  @Override
  public FireableNonTriggerTransition.Match newMatch(final Object... parameters) {
    return FireableNonTriggerTransition.Match.newMatch((hu.bme.mit.gamma.statechart.statechart.StateNode) parameters[0], (hu.bme.mit.gamma.statechart.statechart.Transition) parameters[1], (hu.bme.mit.gamma.statechart.statechart.StateNode) parameters[2]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link FireableNonTriggerTransition} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link FireableNonTriggerTransition#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final FireableNonTriggerTransition INSTANCE = new FireableNonTriggerTransition();
    
    /**
     * Statically initializes the query specification <b>after</b> the field {@link #INSTANCE} is assigned.
     * This initialization order is required to support indirect recursion.
     * 
     * <p> The static initializer is defined using a helper field to work around limitations of the code generator.
     * 
     */
    private static final Object STATIC_INITIALIZER = ensureInitialized();
    
    public static Object ensureInitialized() {
      INSTANCE.ensureInitializedInternal();
      return null;
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private static final FireableNonTriggerTransition.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_source = new PParameter("source", "hu.bme.mit.gamma.statechart.statechart.StateNode", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.mit.bme.hu/gamma/statechart/Model/Statechart", "StateNode")), PParameterDirection.INOUT);
    
    private final PParameter parameter_transition = new PParameter("transition", "hu.bme.mit.gamma.statechart.statechart.Transition", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.mit.bme.hu/gamma/statechart/Model/Statechart", "Transition")), PParameterDirection.INOUT);
    
    private final PParameter parameter_target = new PParameter("target", "hu.bme.mit.gamma.statechart.statechart.StateNode", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.mit.bme.hu/gamma/statechart/Model/Statechart", "StateNode")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_source, parameter_transition, parameter_target);
    
    private class Embedded_1_Transition_trigger extends BaseGeneratedEMFPQuery {
      private final PParameter parameter_p0 = new PParameter("p0", "hu.bme.mit.gamma.statechart.statechart.Transition", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.mit.bme.hu/gamma/statechart/Model/Statechart", "Transition")), PParameterDirection.INOUT);
      
      private final PParameter parameter_p1 = new PParameter("p1", "hu.bme.mit.gamma.statechart.interface_.Trigger", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.mit.bme.hu/gamma/statechart/Model/Interface", "Trigger")), PParameterDirection.INOUT);
      
      private final List<PParameter> embeddedParameters = Arrays.asList(parameter_p0, parameter_p1);
      
      public Embedded_1_Transition_trigger() {
        super(PVisibility.EMBEDDED);
      }
      
      @Override
      public String getFullyQualifiedName() {
        return GeneratedPQuery.this.getFullyQualifiedName() + "$Embedded_1_Transition_trigger";
      }
      
      @Override
      public List<PParameter> getParameters() {
        return embeddedParameters;
      }
      
      @Override
      public Set<PBody> doGetContainedBodies() {
        PBody body = new PBody(this);
        PVariable var_p0 = body.getOrCreateVariableByName("p0");
        PVariable var_p1 = body.getOrCreateVariableByName("p1");
        body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
           new ExportedParameter(body, var_p0, parameter_p0),
           new ExportedParameter(body, var_p1, parameter_p1)
        ));
        //  Transition.trigger(transition, _trigger)
        new TypeConstraint(body, Tuples.flatTupleOf(var_p0), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Statechart", "Transition")));
        PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
        new TypeConstraint(body, Tuples.flatTupleOf(var_p0, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Statechart", "Transition", "trigger")));
        new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Interface", "Trigger")));
        new Equality(body, var__virtual_0_, var_p1);
        return Collections.singleton(body);
      }
    }
    
    private GeneratedPQuery() {
      super(PVisibility.PUBLIC);
    }
    
    @Override
    public String getFullyQualifiedName() {
      return "com.triad.school.gamma.simulator.query.fireableNonTriggerTransition";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("source","transition","target");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return parameters;
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() {
      setEvaluationHints(new QueryEvaluationHint(null, QueryEvaluationHint.BackendRequirement.UNSPECIFIED));
      Set<PBody> bodies = new LinkedHashSet<>();
      {
          PBody body = new PBody(this);
          PVariable var_source = body.getOrCreateVariableByName("source");
          PVariable var_transition = body.getOrCreateVariableByName("transition");
          PVariable var_target = body.getOrCreateVariableByName("target");
          PVariable var__trigger = body.getOrCreateVariableByName("_trigger");
          new TypeConstraint(body, Tuples.flatTupleOf(var_source), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Statechart", "StateNode")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_transition), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Statechart", "Transition")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Statechart", "StateNode")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_source, parameter_source),
             new ExportedParameter(body, var_transition, parameter_transition),
             new ExportedParameter(body, var_target, parameter_target)
          ));
          // 	Transition.sourceState(transition, source)
          new TypeConstraint(body, Tuples.flatTupleOf(var_transition), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Statechart", "Transition")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_transition, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Statechart", "Transition", "sourceState")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Statechart", "StateNode")));
          new Equality(body, var__virtual_0_, var_source);
          // 	Transition.targetState(transition, target)
          new TypeConstraint(body, Tuples.flatTupleOf(var_transition), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Statechart", "Transition")));
          PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_transition, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Statechart", "Transition", "targetState")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_1_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Statechart", "StateNode")));
          new Equality(body, var__virtual_1_, var_target);
          // 		find activeState(source)
          new PositivePatternCall(body, Tuples.flatTupleOf(var_source), ActiveState.instance().getInternalQueryRepresentation());
          // 		neg Transition.trigger(transition, _trigger)
          new NegativePatternCall(body, Tuples.flatTupleOf(var_transition, var__trigger), new FireableNonTriggerTransition.GeneratedPQuery.Embedded_1_Transition_trigger());
          bodies.add(body);
      }
      return bodies;
    }
  }
}
