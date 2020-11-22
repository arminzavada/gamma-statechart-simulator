/**
 */
package com.triad.school.gamma.simulator.model.impl;

import com.triad.school.gamma.simulator.model.ActiveState;
import com.triad.school.gamma.simulator.model.ModelPackage;

import hu.bme.mit.gamma.statechart.statechart.Region;
import hu.bme.mit.gamma.statechart.statechart.StateNode;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Active State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.triad.school.gamma.simulator.model.impl.ActiveStateImpl#getState <em>State</em>}</li>
 *   <li>{@link com.triad.school.gamma.simulator.model.impl.ActiveStateImpl#getContainingRegion <em>Containing Region</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActiveStateImpl extends MinimalEObjectImpl.Container implements ActiveState {
	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected StateNode state;

	/**
	 * The cached value of the '{@link #getContainingRegion() <em>Containing Region</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainingRegion()
	 * @generated
	 * @ordered
	 */
	protected Region containingRegion;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActiveStateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.ACTIVE_STATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateNode getState() {
		if (state != null && state.eIsProxy()) {
			InternalEObject oldState = (InternalEObject) state;
			state = (StateNode) eResolveProxy(oldState);
			if (state != oldState) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.ACTIVE_STATE__STATE,
							oldState, state));
			}
		}
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateNode basicGetState() {
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setState(StateNode newState) {
		StateNode oldState = state;
		state = newState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ACTIVE_STATE__STATE, oldState, state));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Region getContainingRegion() {
		if (containingRegion != null && containingRegion.eIsProxy()) {
			InternalEObject oldContainingRegion = (InternalEObject) containingRegion;
			containingRegion = (Region) eResolveProxy(oldContainingRegion);
			if (containingRegion != oldContainingRegion) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ModelPackage.ACTIVE_STATE__CONTAINING_REGION, oldContainingRegion, containingRegion));
			}
		}
		return containingRegion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Region basicGetContainingRegion() {
		return containingRegion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainingRegion(Region newContainingRegion) {
		Region oldContainingRegion = containingRegion;
		containingRegion = newContainingRegion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ACTIVE_STATE__CONTAINING_REGION,
					oldContainingRegion, containingRegion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ModelPackage.ACTIVE_STATE__STATE:
			if (resolve)
				return getState();
			return basicGetState();
		case ModelPackage.ACTIVE_STATE__CONTAINING_REGION:
			if (resolve)
				return getContainingRegion();
			return basicGetContainingRegion();
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
		case ModelPackage.ACTIVE_STATE__STATE:
			setState((StateNode) newValue);
			return;
		case ModelPackage.ACTIVE_STATE__CONTAINING_REGION:
			setContainingRegion((Region) newValue);
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
		case ModelPackage.ACTIVE_STATE__STATE:
			setState((StateNode) null);
			return;
		case ModelPackage.ACTIVE_STATE__CONTAINING_REGION:
			setContainingRegion((Region) null);
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
		case ModelPackage.ACTIVE_STATE__STATE:
			return state != null;
		case ModelPackage.ACTIVE_STATE__CONTAINING_REGION:
			return containingRegion != null;
		}
		return super.eIsSet(featureID);
	}

} //ActiveStateImpl
