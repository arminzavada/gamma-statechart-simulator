/**
 */
package com.triad.school.gamma.simulator.model;

import hu.bme.mit.gamma.statechart.statechart.Region;
import hu.bme.mit.gamma.statechart.statechart.StateNode;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Regional Active State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.triad.school.gamma.simulator.model.RegionalActiveState#getState <em>State</em>}</li>
 *   <li>{@link com.triad.school.gamma.simulator.model.RegionalActiveState#getRegion <em>Region</em>}</li>
 *   <li>{@link com.triad.school.gamma.simulator.model.RegionalActiveState#getLast <em>Last</em>}</li>
 * </ul>
 *
 * @see com.triad.school.gamma.simulator.model.ModelPackage#getRegionalActiveState()
 * @model
 * @generated
 */
public interface RegionalActiveState extends EObject {
	/**
	 * Returns the value of the '<em><b>State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' reference.
	 * @see #setState(StateNode)
	 * @see com.triad.school.gamma.simulator.model.ModelPackage#getRegionalActiveState_State()
	 * @model
	 * @generated
	 */
	StateNode getState();

	/**
	 * Sets the value of the '{@link com.triad.school.gamma.simulator.model.RegionalActiveState#getState <em>State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' reference.
	 * @see #getState()
	 * @generated
	 */
	void setState(StateNode value);

	/**
	 * Returns the value of the '<em><b>Region</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Region</em>' reference.
	 * @see #setRegion(Region)
	 * @see com.triad.school.gamma.simulator.model.ModelPackage#getRegionalActiveState_Region()
	 * @model required="true"
	 * @generated
	 */
	Region getRegion();

	/**
	 * Sets the value of the '{@link com.triad.school.gamma.simulator.model.RegionalActiveState#getRegion <em>Region</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Region</em>' reference.
	 * @see #getRegion()
	 * @generated
	 */
	void setRegion(Region value);

	/**
	 * Returns the value of the '<em><b>Last</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last</em>' reference.
	 * @see #setLast(StateNode)
	 * @see com.triad.school.gamma.simulator.model.ModelPackage#getRegionalActiveState_Last()
	 * @model
	 * @generated
	 */
	StateNode getLast();

	/**
	 * Sets the value of the '{@link com.triad.school.gamma.simulator.model.RegionalActiveState#getLast <em>Last</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last</em>' reference.
	 * @see #getLast()
	 * @generated
	 */
	void setLast(StateNode value);

} // RegionalActiveState
