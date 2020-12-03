/**
 */
package com.triad.school.gamma.simulator.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Active State Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.triad.school.gamma.simulator.model.ActiveStateContainer#getActiveStates <em>Active States</em>}</li>
 * </ul>
 *
 * @see com.triad.school.gamma.simulator.model.ModelPackage#getActiveStateContainer()
 * @model
 * @generated
 */
public interface ActiveStateContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Active States</b></em>' containment reference list.
	 * The list contents are of type {@link com.triad.school.gamma.simulator.model.RegionalActiveState}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active States</em>' containment reference list.
	 * @see com.triad.school.gamma.simulator.model.ModelPackage#getActiveStateContainer_ActiveStates()
	 * @model containment="true"
	 * @generated
	 */
	EList<RegionalActiveState> getActiveStates();

} // ActiveStateContainer
