package com.triad.school.gamma.simulator.query

import org.eclipse.emf.ecore.resource.Resource
import com.triad.school.gamma.simulator.active_state.Active_stateFactory
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine
import hu.bme.mit.gamma.statechart.statechart.StatechartModelPackage
import com.triad.school.gamma.simulator.active_state.Active_statePackage
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRuleFactory
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformation
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformationStatements
import org.apache.log4j.Logger
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.common.util.URI
import org.eclipse.viatra.query.runtime.emf.EMFScope
import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.statechart.statechart.StateNode
import hu.bme.mit.gamma.statechart.interface_.Port

class DummyEntry<T>(
	override val key: String,
	override val value: T
) : Map.Entry<String, T> 

class GammaStatechartSimulatorTransformation(
	val resource: Resource
) {
	val queries = FireableTransitions.instance()	
	
    val logger = Logger.getLogger(GammaStatechartSimulatorTransformation::class.java)

    /* Transformation-related extensions */
    val transformation: BatchTransformation
    val statements: BatchTransformationStatements
    
    /* Transformation rule-related extensions */
    val transformationFactory = BatchTransformationRuleFactory()
    
	val statePackage = StatechartModelPackage.eINSTANCE
	val activePackage = Active_statePackage.eINSTANCE

    protected val engine: ViatraQueryEngine
    
    private val activeStateContainer = Active_stateFactory.eINSTANCE.createActiveStateContainer()

    init {    	
		val resourceSet = ResourceSetImpl()
		resourceSet.resources.add(resource)
		
		val active_stateResource = resourceSet.createResource(URI.createURI(""))
		active_stateResource.contents.add(activeStateContainer)
		
        val scope = EMFScope(resourceSet)
        engine = ViatraQueryEngine.on(scope);
        queries.prepare(engine)
        
        transformation = BatchTransformation.forEngine(engine).build()
        statements = transformation.transformationStatements
        
        activeStateContainer.activeState = InitialNode.Matcher.on(engine).oneArbitraryMatch.get().state
    }
    
    fun tick() {
    	statements.fireOne(fireNonTriggerTransition)
    }

    fun sendEvent(event: Event) {
        logger.debug("Executing transformation on:${resource.getURI()}")
    	statements.fireOne(fireTriggerTransition, DummyEntry("t", event))     
    }
    
    fun requiredInterfaces(): List<Port> {
    	return Ports.Matcher.on(engine).allMatches.map {
    		it.port
		}.toList()
    }    
    fun activeState(): List<StateNode> {
    	return ActiveState.Matcher.on(engine).allMatches.map {
    		it.state
		}.toList()
    }
    
    val fireNonTriggerTransition = transformationFactory.createRule(FireableNonTriggerTransition.instance()).action {
    	println("Firing transition: ${it.source.name} to ${it.target.name}")
    	activeStateContainer.activeState = it.target    	
    }.build()
    
    val fireTriggerTransition = transformationFactory.createRule(FireableTriggerTransition.instance()).action {
    	println("Firing transition: ${it.source.name} to ${it.target.name}")
    	activeStateContainer.activeState = it.target    	
	}.build()

    fun dispose() {
        transformation.dispose()
    }
}