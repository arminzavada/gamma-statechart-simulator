/**
 */
package com.triad.school.gamma.simulator.model.impl;

import com.triad.school.gamma.simulator.model.ModelPackage;
import com.triad.school.gamma.simulator.model.RegionalActiveState;

import hu.bme.mit.gamma.statechart.statechart.Region;
import hu.bme.mit.gamma.statechart.statechart.StateNode;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Regional Active State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.triad.school.gamma.simulator.model.impl.RegionalActiveStateImpl#getState <em>State</em>}</li>
 *   <li>{@link com.triad.school.gamma.simulator.model.impl.RegionalActiveStateImpl#getRegion <em>Region</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RegionalActiveStateImpl extends MinimalEObjectImpl.Container implements RegionalActiveState {
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
	 * The cached value of the '{@link #getRegion() <em>Region</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegion()
	 * @generated
	 * @ordered
	 */
	protected Region region;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RegionalActiveStateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.REGIONAL_ACTIVE_STATE;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.REGIONAL_ACTIVE_STATE__STATE,
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.REGIONAL_ACTIVE_STATE__STATE, oldState,
					state));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Region getRegion() {
		if (region != null && region.eIsProxy()) {
			InternalEObject oldRegion = (InternalEObject) region;
			region = (Region) eResolveProxy(oldRegion);
			if (region != oldRegion) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ModelPackage.REGIONAL_ACTIVE_STATE__REGION, oldRegion, region));
			}
		}
		return region;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Region basicGetRegion() {
		return region;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRegion(Region newRegion) {
		Region oldRegion = region;
		region = newRegion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.REGIONAL_ACTIVE_STATE__REGION, oldRegion,
					region));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ModelPackage.REGIONAL_ACTIVE_STATE__STATE:
			if (resolve)
				return getState();
			return basicGetState();
		case ModelPackage.REGIONAL_ACTIVE_STATE__REGION:
			if (resolve)
				return getRegion();
			return basicGetRegion();
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
		case ModelPackage.REGIONAL_ACTIVE_STATE__STATE:
			setState((StateNode) newValue);
			return;
		case ModelPackage.REGIONAL_ACTIVE_STATE__REGION:
			setRegion((Region) newValue);
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
		case ModelPackage.REGIONAL_ACTIVE_STATE__STATE:
			setState((StateNode) null);
			return;
		case ModelPackage.REGIONAL_ACTIVE_STATE__REGION:
			setRegion((Region) null);
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
		case ModelPackage.REGIONAL_ACTIVE_STATE__STATE:
			return state != null;
		case ModelPackage.REGIONAL_ACTIVE_STATE__REGION:
			return region != null;
		}
		return super.eIsSet(featureID);
	}

} //RegionalActiveStateImpl
