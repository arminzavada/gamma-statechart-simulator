/**
 */
package com.triad.school.gamma.simulator.active_state.impl;

import com.triad.school.gamma.simulator.active_state.ActiveStateContainer;
import com.triad.school.gamma.simulator.active_state.Active_stateFactory;
import com.triad.school.gamma.simulator.active_state.Active_statePackage;

import com.triad.school.gamma.simulator.active_state.EventQueue;
import hu.bme.mit.gamma.action.model.ActionModelPackage;

import hu.bme.mit.gamma.expression.model.ExpressionModelPackage;

import hu.bme.mit.gamma.statechart.composite.CompositeModelPackage;

import hu.bme.mit.gamma.statechart.interface_.InterfaceModelPackage;

import hu.bme.mit.gamma.statechart.statechart.StatechartModelPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Active_statePackageImpl extends EPackageImpl implements Active_statePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activeStateContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventQueueEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.triad.school.gamma.simulator.active_state.Active_statePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Active_statePackageImpl() {
		super(eNS_URI, Active_stateFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link Active_statePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Active_statePackage init() {
		if (isInited)
			return (Active_statePackage) EPackage.Registry.INSTANCE.getEPackage(Active_statePackage.eNS_URI);

		// Obtain or create and register package
		Object registeredActive_statePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		Active_statePackageImpl theActive_statePackage = registeredActive_statePackage instanceof Active_statePackageImpl
				? (Active_statePackageImpl) registeredActive_statePackage
				: new Active_statePackageImpl();

		isInited = true;

		// Initialize simple dependencies
		ActionModelPackage.eINSTANCE.eClass();
		ExpressionModelPackage.eINSTANCE.eClass();
		CompositeModelPackage.eINSTANCE.eClass();
		InterfaceModelPackage.eINSTANCE.eClass();
		StatechartModelPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theActive_statePackage.createPackageContents();

		// Initialize created meta-data
		theActive_statePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theActive_statePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Active_statePackage.eNS_URI, theActive_statePackage);
		return theActive_statePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActiveStateContainer() {
		return activeStateContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActiveStateContainer_ActiveState() {
		return (EReference) activeStateContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEventQueue() {
		return eventQueueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventQueue_Events() {
		return (EReference) eventQueueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Active_stateFactory getActive_stateFactory() {
		return (Active_stateFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		activeStateContainerEClass = createEClass(ACTIVE_STATE_CONTAINER);
		createEReference(activeStateContainerEClass, ACTIVE_STATE_CONTAINER__ACTIVE_STATE);

		eventQueueEClass = createEClass(EVENT_QUEUE);
		createEReference(eventQueueEClass, EVENT_QUEUE__EVENTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		StatechartModelPackage theStatechartModelPackage = (StatechartModelPackage) EPackage.Registry.INSTANCE
				.getEPackage(StatechartModelPackage.eNS_URI);
		InterfaceModelPackage theInterfaceModelPackage = (InterfaceModelPackage) EPackage.Registry.INSTANCE
				.getEPackage(InterfaceModelPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(activeStateContainerEClass, ActiveStateContainer.class, "ActiveStateContainer", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActiveStateContainer_ActiveState(), theStatechartModelPackage.getStateNode(), null,
				"activeState", null, 0, 1, ActiveStateContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventQueueEClass, EventQueue.class, "EventQueue", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEventQueue_Events(), theInterfaceModelPackage.getEvent(), null, "events", null, 0, -1,
				EventQueue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //Active_statePackageImpl
