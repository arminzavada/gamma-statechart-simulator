/**
 */
package com.triad.school.gamma.simulator.active_state;

import hu.bme.mit.gamma.statechart.statechart.StateNode;

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
 *   <li>{@link com.triad.school.gamma.simulator.active_state.ActiveStateContainer#getActiveState <em>Active State</em>}</li>
 * </ul>
 *
 * @see com.triad.school.gamma.simulator.active_state.Active_statePackage#getActiveStateContainer()
 * @model
 * @generated
 */
public interface ActiveStateContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Active State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active State</em>' reference.
	 * @see #setActiveState(StateNode)
	 * @see com.triad.school.gamma.simulator.active_state.Active_statePackage#getActiveStateContainer_ActiveState()
	 * @model
	 * @generated
	 */
	StateNode getActiveState();

	/**
	 * Sets the value of the '{@link com.triad.school.gamma.simulator.active_state.ActiveStateContainer#getActiveState <em>Active State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active State</em>' reference.
	 * @see #getActiveState()
	 * @generated
	 */
	void setActiveState(StateNode value);

} // ActiveStateContainer
