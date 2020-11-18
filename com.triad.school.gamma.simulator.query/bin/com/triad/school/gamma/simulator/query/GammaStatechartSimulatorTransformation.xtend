package com.triad.school.gamma.simulator.query

import com.triad.school.gamma.simulator.active_state.Active_statePackage
import hu.bme.mit.gamma.statechart.statechart.StatechartModelPackage
import org.apache.log4j.Logger
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine
import org.eclipse.viatra.query.runtime.emf.EMFScope
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.IModelManipulations
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.SimpleModelManipulations
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRuleFactory
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformation
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformationStatements
import com.triad.school.gamma.simulator.active_state.Active_stateFactory
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.common.util.URI
import hu.bme.mit.gamma.statechart.interface_.Event
import java.util.Map.Entry
import com.triad.school.gamma.simulator.active_state.ActiveStateContainer

class DummyEntry<T> implements Entry<String, T> {
	
	val extension String key
	var extension T value
	
	new (String key, T value) {
		this.key = key
		this.value = value
	}
	
	override getKey() {
		key
	}
	
	override getValue() {
		value
	}
	
	override setValue(T arg0) {
		value = arg0
	}
}

class GammaStatechartSimulatorTransformation {
	val FireableTransitions queries = FireableTransitions.instance	
	
    extension Logger logger = Logger.getLogger(GammaStatechartSimulatorTransformation)

    /* Transformation-related extensions */
    extension BatchTransformation transformation
    extension BatchTransformationStatements statements
    
    /* Transformation rule-related extensions */
    extension BatchTransformationRuleFactory = new BatchTransformationRuleFactory
    extension IModelManipulations manipulation
    
	val extension StatechartModelPackage statePackage = StatechartModelPackage.eINSTANCE
	val extension Active_statePackage activePackage = Active_statePackage.eINSTANCE

    protected ViatraQueryEngine engine
    
    private val ActiveStateContainer activeStateContainer = Active_stateFactory.eINSTANCE.createActiveStateContainer

    new(Resource resource) {    	
		val resourceSet = new ResourceSetImpl
		resourceSet.resources.add(resource)
		
		val active_stateResource = resourceSet.createResource(URI.createURI(""))
		active_stateResource.contents.add(activeStateContainer)
		
        val scope = new EMFScope(resourceSet)
        engine = ViatraQueryEngine.on(scope);
        queries.prepare(engine)
        
        manipulation = new SimpleModelManipulations(engine)
        transformation = BatchTransformation.forEngine(engine).build
        statements = transformation.transformationStatements
        
        activeStateContainer.activeState = InitialNode.Matcher.on(engine).oneArbitraryMatch.get.state
    }
    
    public def tick() {
    	fireNonTriggerTransition.fireOne
    }

    public def sendEvent(Event event) {
        debug('''Executing transformation on:�resource.URI�''')
    	fireOne(fireTriggerTransition, new DummyEntry("event", event))     
    }
    
    public def requiredInterfaces() {
    	return Ports.Matcher.on(engine).allMatches.map[
    		it.port
    	].toList
    }    
    public def activeState() {
    	return ActiveState.Matcher.on(engine).allMatches.map[
    		it.state
    	].toList
    }
    
    val fireNonTriggerTransition = createRule(FireableNonTriggerTransition.instance).action[
    	println('''Firing transition: «source.name» to «target.name»''')
    	activeStateContainer.activeState = target
    ].build
    
    val fireTriggerTransition = createRule(FireableTriggerTransition.instance).action[
    	println('''Firing transition: «source.name» to «target.name»''')
    	activeStateContainer.activeState = target    	
    ].build

    def dispose() {
        if (transformation != null) {
            transformation.dispose
        }
        
        transformation = null
    }
}
