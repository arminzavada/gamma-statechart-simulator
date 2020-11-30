/**
 */
package com.triad.school.gamma.simulator.model;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.triad.school.gamma.simulator.model.ModelFactory
 * @model kind="package"
 * @generated
 */
public interface ModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.triad.com/school/gamma/simulator/model";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "model";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelPackage eINSTANCE = com.triad.school.gamma.simulator.model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.triad.school.gamma.simulator.model.impl.ActiveStateContainerImpl <em>Active State Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.triad.school.gamma.simulator.model.impl.ActiveStateContainerImpl
	 * @see com.triad.school.gamma.simulator.model.impl.ModelPackageImpl#getActiveStateContainer()
	 * @generated
	 */
	int ACTIVE_STATE_CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Active States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVE_STATE_CONTAINER__ACTIVE_STATES = 0;

	/**
	 * The number of structural features of the '<em>Active State Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVE_STATE_CONTAINER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Active State Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVE_STATE_CONTAINER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.triad.school.gamma.simulator.model.impl.RegionalActiveStateImpl <em>Regional Active State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.triad.school.gamma.simulator.model.impl.RegionalActiveStateImpl
	 * @see com.triad.school.gamma.simulator.model.impl.ModelPackageImpl#getRegionalActiveState()
	 * @generated
	 */
	int REGIONAL_ACTIVE_STATE = 1;

	/**
	 * The feature id for the '<em><b>State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGIONAL_ACTIVE_STATE__STATE = 0;

	/**
	 * The feature id for the '<em><b>Region</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGIONAL_ACTIVE_STATE__REGION = 1;

	/**
	 * The number of structural features of the '<em>Regional Active State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGIONAL_ACTIVE_STATE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Regional Active State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGIONAL_ACTIVE_STATE_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link com.triad.school.gamma.simulator.model.ActiveStateContainer <em>Active State Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Active State Container</em>'.
	 * @see com.triad.school.gamma.simulator.model.ActiveStateContainer
	 * @generated
	 */
	EClass getActiveStateContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link com.triad.school.gamma.simulator.model.ActiveStateContainer#getActiveStates <em>Active States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Active States</em>'.
	 * @see com.triad.school.gamma.simulator.model.ActiveStateContainer#getActiveStates()
	 * @see #getActiveStateContainer()
	 * @generated
	 */
	EReference getActiveStateContainer_ActiveStates();

	/**
	 * Returns the meta object for class '{@link com.triad.school.gamma.simulator.model.RegionalActiveState <em>Regional Active State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Regional Active State</em>'.
	 * @see com.triad.school.gamma.simulator.model.RegionalActiveState
	 * @generated
	 */
	EClass getRegionalActiveState();

	/**
	 * Returns the meta object for the reference '{@link com.triad.school.gamma.simulator.model.RegionalActiveState#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>State</em>'.
	 * @see com.triad.school.gamma.simulator.model.RegionalActiveState#getState()
	 * @see #getRegionalActiveState()
	 * @generated
	 */
	EReference getRegionalActiveState_State();

	/**
	 * Returns the meta object for the reference '{@link com.triad.school.gamma.simulator.model.RegionalActiveState#getRegion <em>Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Region</em>'.
	 * @see com.triad.school.gamma.simulator.model.RegionalActiveState#getRegion()
	 * @see #getRegionalActiveState()
	 * @generated
	 */
	EReference getRegionalActiveState_Region();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelFactory getModelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.triad.school.gamma.simulator.model.impl.ActiveStateContainerImpl <em>Active State Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.triad.school.gamma.simulator.model.impl.ActiveStateContainerImpl
		 * @see com.triad.school.gamma.simulator.model.impl.ModelPackageImpl#getActiveStateContainer()
		 * @generated
		 */
		EClass ACTIVE_STATE_CONTAINER = eINSTANCE.getActiveStateContainer();

		/**
		 * The meta object literal for the '<em><b>Active States</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVE_STATE_CONTAINER__ACTIVE_STATES = eINSTANCE.getActiveStateContainer_ActiveStates();

		/**
		 * The meta object literal for the '{@link com.triad.school.gamma.simulator.model.impl.RegionalActiveStateImpl <em>Regional Active State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.triad.school.gamma.simulator.model.impl.RegionalActiveStateImpl
		 * @see com.triad.school.gamma.simulator.model.impl.ModelPackageImpl#getRegionalActiveState()
		 * @generated
		 */
		EClass REGIONAL_ACTIVE_STATE = eINSTANCE.getRegionalActiveState();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REGIONAL_ACTIVE_STATE__STATE = eINSTANCE.getRegionalActiveState_State();

		/**
		 * The meta object literal for the '<em><b>Region</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REGIONAL_ACTIVE_STATE__REGION = eINSTANCE.getRegionalActiveState_Region();

	}

} //ModelPackage
