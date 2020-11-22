/**
 */
package com.triad.school.gamma.simulator.model;

import hu.bme.mit.gamma.statechart.interface_.Event;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Queue</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.triad.school.gamma.simulator.model.EventQueue#getEvents <em>Events</em>}</li>
 * </ul>
 *
 * @see com.triad.school.gamma.simulator.model.ModelPackage#getEventQueue()
 * @model
 * @generated
 */
public interface EventQueue extends EObject {
	/**
	 * Returns the value of the '<em><b>Events</b></em>' reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.statechart.interface_.Event}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Events</em>' reference list.
	 * @see com.triad.school.gamma.simulator.model.ModelPackage#getEventQueue_Events()
	 * @model
	 * @generated
	 */
	EList<Event> getEvents();

} // EventQueue
