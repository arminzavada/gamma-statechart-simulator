/**
 */
package com.triad.school.gamma.simulator.model;

import hu.bme.mit.gamma.statechart.statechart.Region;
import hu.bme.mit.gamma.statechart.statechart.StateNode;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Active State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.triad.school.gamma.simulator.model.ActiveState#getState <em>State</em>}</li>
 *   <li>{@link com.triad.school.gamma.simulator.model.ActiveState#getContainingRegion <em>Containing Region</em>}</li>
 * </ul>
 *
 * @see com.triad.school.gamma.simulator.model.ModelPackage#getActiveState()
 * @model
 * @generated
 */
public interface ActiveState extends EObject {
	/**
	 * Returns the value of the '<em><b>State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' reference.
	 * @see #setState(StateNode)
	 * @see com.triad.school.gamma.simulator.model.ModelPackage#getActiveState_State()
	 * @model
	 * @generated
	 */
	StateNode getState();

	/**
	 * Sets the value of the '{@link com.triad.school.gamma.simulator.model.ActiveState#getState <em>State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' reference.
	 * @see #getState()
	 * @generated
	 */
	void setState(StateNode value);

	/**
	 * Returns the value of the '<em><b>Containing Region</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containing Region</em>' reference.
	 * @see #setContainingRegion(Region)
	 * @see com.triad.school.gamma.simulator.model.ModelPackage#getActiveState_ContainingRegion()
	 * @model required="true"
	 * @generated
	 */
	Region getContainingRegion();

	/**
	 * Sets the value of the '{@link com.triad.school.gamma.simulator.model.ActiveState#getContainingRegion <em>Containing Region</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containing Region</em>' reference.
	 * @see #getContainingRegion()
	 * @generated
	 */
	void setContainingRegion(Region value);

} // ActiveState
