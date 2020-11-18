/**
 */
package com.triad.school.gamma.simulator.active_state;

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
 * @see com.triad.school.gamma.simulator.active_state.Active_stateFactory
 * @model kind="package"
 * @generated
 */
public interface Active_statePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "active_state";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.triad.com/school/gamma/simulator/active_state";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "active_state";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Active_statePackage eINSTANCE = com.triad.school.gamma.simulator.active_state.impl.Active_statePackageImpl.init();

	/**
	 * The meta object id for the '{@link com.triad.school.gamma.simulator.active_state.impl.ActiveStateContainerImpl <em>Active State Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.triad.school.gamma.simulator.active_state.impl.ActiveStateContainerImpl
	 * @see com.triad.school.gamma.simulator.active_state.impl.Active_statePackageImpl#getActiveStateContainer()
	 * @generated
	 */
	int ACTIVE_STATE_CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Active State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVE_STATE_CONTAINER__ACTIVE_STATE = 0;

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
	 * Returns the meta object for class '{@link com.triad.school.gamma.simulator.active_state.ActiveStateContainer <em>Active State Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Active State Container</em>'.
	 * @see com.triad.school.gamma.simulator.active_state.ActiveStateContainer
	 * @generated
	 */
	EClass getActiveStateContainer();

	/**
	 * Returns the meta object for the reference '{@link com.triad.school.gamma.simulator.active_state.ActiveStateContainer#getActiveState <em>Active State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Active State</em>'.
	 * @see com.triad.school.gamma.simulator.active_state.ActiveStateContainer#getActiveState()
	 * @see #getActiveStateContainer()
	 * @generated
	 */
	EReference getActiveStateContainer_ActiveState();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Active_stateFactory getActive_stateFactory();

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
		 * The meta object literal for the '{@link com.triad.school.gamma.simulator.active_state.impl.ActiveStateContainerImpl <em>Active State Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.triad.school.gamma.simulator.active_state.impl.ActiveStateContainerImpl
		 * @see com.triad.school.gamma.simulator.active_state.impl.Active_statePackageImpl#getActiveStateContainer()
		 * @generated
		 */
		EClass ACTIVE_STATE_CONTAINER = eINSTANCE.getActiveStateContainer();

		/**
		 * The meta object literal for the '<em><b>Active State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVE_STATE_CONTAINER__ACTIVE_STATE = eINSTANCE.getActiveStateContainer_ActiveState();

	}

} //Active_statePackage
