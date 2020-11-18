/**
 */
package com.triad.school.gamma.simulator.active_state;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.triad.school.gamma.simulator.active_state.Active_statePackage
 * @generated
 */
public interface Active_stateFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Active_stateFactory eINSTANCE = com.triad.school.gamma.simulator.active_state.impl.Active_stateFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Active State Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Active State Container</em>'.
	 * @generated
	 */
	ActiveStateContainer createActiveStateContainer();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Active_statePackage getActive_statePackage();

} //Active_stateFactory
