package com.triad.school.gamma.simulator.query;

import com.google.common.base.Objects;
import com.triad.school.gamma.simulator.active_state.ActiveStateContainer;
import com.triad.school.gamma.simulator.active_state.Active_stateFactory;
import com.triad.school.gamma.simulator.active_state.Active_statePackage;
import com.triad.school.gamma.simulator.query.ActiveState;
import com.triad.school.gamma.simulator.query.DummyEntry;
import com.triad.school.gamma.simulator.query.FireableNonTriggerTransition;
import com.triad.school.gamma.simulator.query.FireableTransitions;
import com.triad.school.gamma.simulator.query.FireableTriggerTransition;
import com.triad.school.gamma.simulator.query.InitialNode;
import com.triad.school.gamma.simulator.query.Ports;
import hu.bme.mit.gamma.statechart.interface_.Event;
import hu.bme.mit.gamma.statechart.interface_.Port;
import hu.bme.mit.gamma.statechart.statechart.StateNode;
import hu.bme.mit.gamma.statechart.statechart.StatechartModelPackage;
import java.util.List;
import java.util.function.Consumer;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.emf.EMFScope;
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.IModelManipulations;
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.SimpleModelManipulations;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRule;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRuleFactory;
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformation;
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformationStatements;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class GammaStatechartSimulatorTransformation {
  private final FireableTransitions queries = FireableTransitions.instance();
  
  @Extension
  private Logger logger = Logger.getLogger(GammaStatechartSimulatorTransformation.class);
  
  /**
   * Transformation-related extensions
   */
  @Extension
  private BatchTransformation transformation;
  
  @Extension
  private BatchTransformationStatements statements;
  
  /**
   * Transformation rule-related extensions
   */
  @Extension
  private BatchTransformationRuleFactory _batchTransformationRuleFactory = new BatchTransformationRuleFactory();
  
  @Extension
  private IModelManipulations manipulation;
  
  @Extension
  private final StatechartModelPackage statePackage = StatechartModelPackage.eINSTANCE;
  
  @Extension
  private final Active_statePackage activePackage = Active_statePackage.eINSTANCE;
  
  protected ViatraQueryEngine engine;
  
  private final ActiveStateContainer activeStateContainer = Active_stateFactory.eINSTANCE.createActiveStateContainer();
  
  public GammaStatechartSimulatorTransformation(final Resource resource) {
    final ResourceSetImpl resourceSet = new ResourceSetImpl();
    resourceSet.getResources().add(resource);
    final Resource active_stateResource = resourceSet.createResource(URI.createURI(""));
    active_stateResource.getContents().add(this.activeStateContainer);
    final EMFScope scope = new EMFScope(resourceSet);
    this.engine = ViatraQueryEngine.on(scope);
    this.queries.prepare(this.engine);
    SimpleModelManipulations _simpleModelManipulations = new SimpleModelManipulations(this.engine);
    this.manipulation = _simpleModelManipulations;
    this.transformation = BatchTransformation.forEngine(this.engine).build();
    this.statements = this.transformation.getTransformationStatements();
    this.activeStateContainer.setActiveState(InitialNode.Matcher.on(this.engine).getOneArbitraryMatch().get().getState());
  }
  
  public void tick() {
    this.statements.<FireableNonTriggerTransition.Match>fireOne(this.fireNonTriggerTransition);
  }
  
  public void sendEvent(final Event event) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Executing transformation on:�resource.URI�");
    this.logger.debug(_builder);
    DummyEntry<Event> _dummyEntry = new DummyEntry<Event>("event", event);
    this.statements.<FireableTriggerTransition.Match>fireOne(this.fireTriggerTransition, _dummyEntry);
  }
  
  public List<Port> requiredInterfaces() {
    final Function1<Ports.Match, Port> _function = (Ports.Match it) -> {
      return it.getPort();
    };
    return IterableExtensions.<Port>toList(IterableExtensions.<Ports.Match, Port>map(Ports.Matcher.on(this.engine).getAllMatches(), _function));
  }
  
  public List<StateNode> activeState() {
    final Function1<ActiveState.Match, StateNode> _function = (ActiveState.Match it) -> {
      return it.getState();
    };
    return IterableExtensions.<StateNode>toList(IterableExtensions.<ActiveState.Match, StateNode>map(ActiveState.Matcher.on(this.engine).getAllMatches(), _function));
  }
  
  private final BatchTransformationRule<FireableNonTriggerTransition.Match, FireableNonTriggerTransition.Matcher> fireNonTriggerTransition = this._batchTransformationRuleFactory.<FireableNonTriggerTransition.Match, FireableNonTriggerTransition.Matcher>createRule(FireableNonTriggerTransition.instance()).action(
    ((Consumer<FireableNonTriggerTransition.Match>) (FireableNonTriggerTransition.Match it) -> {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Firing transition: ");
      String _name = it.getSource().getName();
      _builder.append(_name);
      _builder.append(" to ");
      String _name_1 = it.getTarget().getName();
      _builder.append(_name_1);
      InputOutput.<String>println(_builder.toString());
      this.activeStateContainer.setActiveState(it.getTarget());
    })).build();
  
  private final BatchTransformationRule<FireableTriggerTransition.Match, FireableTriggerTransition.Matcher> fireTriggerTransition = this._batchTransformationRuleFactory.<FireableTriggerTransition.Match, FireableTriggerTransition.Matcher>createRule(FireableTriggerTransition.instance()).action(
    ((Consumer<FireableTriggerTransition.Match>) (FireableTriggerTransition.Match it) -> {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Firing transition: ");
      String _name = it.getSource().getName();
      _builder.append(_name);
      _builder.append(" to ");
      String _name_1 = it.getTarget().getName();
      _builder.append(_name_1);
      InputOutput.<String>println(_builder.toString());
      this.activeStateContainer.setActiveState(it.getTarget());
    })).build();
  
  public BatchTransformation dispose() {
    BatchTransformation _xblockexpression = null;
    {
      boolean _notEquals = (!Objects.equal(this.transformation, null));
      if (_notEquals) {
        this.transformation.dispose();
      }
      _xblockexpression = this.transformation = null;
    }
    return _xblockexpression;
  }
}
