/**
 */
package com.triad.school.gamma.simulator.model;

import hu.bme.mit.gamma.statechart.statechart.Region;
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
 *   <li>{@link com.triad.school.gamma.simulator.model.ActiveStateContainer#getActiveState <em>Active State</em>}</li>
 *   <li>{@link com.triad.school.gamma.simulator.model.ActiveStateContainer#getContainingRegion <em>Containing Region</em>}</li>
 * </ul>
 *
 * @see com.triad.school.gamma.simulator.model.ModelPackage#getActiveStateContainer()
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
	 * @see com.triad.school.gamma.simulator.model.ModelPackage#getActiveStateContainer_ActiveState()
	 * @model
	 * @generated
	 */
	StateNode getActiveState();

	/**
	 * Sets the value of the '{@link com.triad.school.gamma.simulator.model.ActiveStateContainer#getActiveState <em>Active State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active State</em>' reference.
	 * @see #getActiveState()
	 * @generated
	 */
	void setActiveState(StateNode value);

	/**
	 * Returns the value of the '<em><b>Containing Region</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containing Region</em>' reference.
	 * @see #setContainingRegion(Region)
	 * @see com.triad.school.gamma.simulator.model.ModelPackage#getActiveStateContainer_ContainingRegion()
	 * @model required="true"
	 * @generated
	 */
	Region getContainingRegion();

	/**
	 * Sets the value of the '{@link com.triad.school.gamma.simulator.model.ActiveStateContainer#getContainingRegion <em>Containing Region</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containing Region</em>' reference.
	 * @see #getContainingRegion()
	 * @generated
	 */
	void setContainingRegion(Region value);

} // ActiveStateContainer
