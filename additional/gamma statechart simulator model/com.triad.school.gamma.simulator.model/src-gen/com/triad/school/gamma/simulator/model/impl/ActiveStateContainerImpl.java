/**
 */
package com.triad.school.gamma.simulator.model.impl;

import com.triad.school.gamma.simulator.model.ActiveStateContainer;
import com.triad.school.gamma.simulator.model.ModelPackage;
import com.triad.school.gamma.simulator.model.RegionalActiveState;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Active State Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.triad.school.gamma.simulator.model.impl.ActiveStateContainerImpl#getActiveStates <em>Active States</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActiveStateContainerImpl extends MinimalEObjectImpl.Container implements ActiveStateContainer {
	/**
	 * The cached value of the '{@link #getActiveStates() <em>Active States</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActiveStates()
	 * @generated
	 * @ordered
	 */
	protected EList<RegionalActiveState> activeStates;

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
		return ModelPackage.Literals.ACTIVE_STATE_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RegionalActiveState> getActiveStates() {
		if (activeStates == null) {
			activeStates = new EObjectContainmentEList<RegionalActiveState>(RegionalActiveState.class, this,
					ModelPackage.ACTIVE_STATE_CONTAINER__ACTIVE_STATES);
		}
		return activeStates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ModelPackage.ACTIVE_STATE_CONTAINER__ACTIVE_STATES:
			return ((InternalEList<?>) getActiveStates()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ModelPackage.ACTIVE_STATE_CONTAINER__ACTIVE_STATES:
			return getActiveStates();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ModelPackage.ACTIVE_STATE_CONTAINER__ACTIVE_STATES:
			getActiveStates().clear();
			getActiveStates().addAll((Collection<? extends RegionalActiveState>) newValue);
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
		case ModelPackage.ACTIVE_STATE_CONTAINER__ACTIVE_STATES:
			getActiveStates().clear();
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
		case ModelPackage.ACTIVE_STATE_CONTAINER__ACTIVE_STATES:
			return activeStates != null && !activeStates.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ActiveStateContainerImpl
