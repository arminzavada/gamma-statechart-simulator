/**
 */
package com.triad.school.gamma.simulator.active_state.impl;

import com.triad.school.gamma.simulator.active_state.ActiveStateContainer;
import com.triad.school.gamma.simulator.active_state.Active_statePackage;

import hu.bme.mit.gamma.statechart.statechart.StateNode;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Active State Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.triad.school.gamma.simulator.active_state.impl.ActiveStateContainerImpl#getActiveState <em>Active State</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActiveStateContainerImpl extends MinimalEObjectImpl.Container implements ActiveStateContainer {
	/**
	 * The cached value of the '{@link #getActiveState() <em>Active State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActiveState()
	 * @generated
	 * @ordered
	 */
	protected StateNode activeState;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActiveStateContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Active_statePackage.Literals.ACTIVE_STATE_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateNode getActiveState() {
		if (activeState != null && activeState.eIsProxy()) {
			InternalEObject oldActiveState = (InternalEObject) activeState;
			activeState = (StateNode) eResolveProxy(oldActiveState);
			if (activeState != oldActiveState) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							Active_statePackage.ACTIVE_STATE_CONTAINER__ACTIVE_STATE, oldActiveState, activeState));
			}
		}
		return activeState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateNode basicGetActiveState() {
		return activeState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActiveState(StateNode newActiveState) {
		StateNode oldActiveState = activeState;
		activeState = newActiveState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					Active_statePackage.ACTIVE_STATE_CONTAINER__ACTIVE_STATE, oldActiveState, activeState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case Active_statePackage.ACTIVE_STATE_CONTAINER__ACTIVE_STATE:
			if (resolve)
				return getActiveState();
			return basicGetActiveState();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case Active_statePackage.ACTIVE_STATE_CONTAINER__ACTIVE_STATE:
			setActiveState((StateNode) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case Active_statePackage.ACTIVE_STATE_CONTAINER__ACTIVE_STATE:
			setActiveState((StateNode) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case Active_statePackage.ACTIVE_STATE_CONTAINER__ACTIVE_STATE:
			return activeState != null;
		}
		return super.eIsSet(featureID);
	}

} //ActiveStateContainerImpl
