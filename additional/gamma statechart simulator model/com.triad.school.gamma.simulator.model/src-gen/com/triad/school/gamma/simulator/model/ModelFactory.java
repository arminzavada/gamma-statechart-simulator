/**
 */
package com.triad.school.gamma.simulator.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.triad.school.gamma.simulator.model.ModelPackage
 * @generated
 */
public interface ModelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelFactory eINSTANCE = com.triad.school.gamma.simulator.model.impl.ModelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Active State Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Active State Container</em>'.
	 * @generated
	 */
	ActiveStateContainer createActiveStateContainer();

	/**
	 * Returns a new object of class '<em>Regional Active State</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Regional Active State</em>'.
	 * @generated
	 */
	RegionalActiveState createRegionalActiveState();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ModelPackage getModelPackage();

} //ModelFactory
