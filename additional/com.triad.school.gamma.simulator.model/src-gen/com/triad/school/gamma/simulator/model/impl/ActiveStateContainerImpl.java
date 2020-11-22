/**
 */
package com.triad.school.gamma.simulator.model.impl;

import com.triad.school.gamma.simulator.model.ActiveState;
import com.triad.school.gamma.simulator.model.ActiveStateContainer;
import com.triad.school.gamma.simulator.model.ModelPackage;
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
 *   <li>{@link com.triad.school.gamma.simulator.model.impl.ActiveStateContainerImpl#getActivestates <em>Activestates</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActiveStateContainerImpl extends MinimalEObjectImpl.Container implements ActiveStateContainer {
	/**
	 * The cached value of the '{@link #getActivestates() <em>Activestates</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivestates()
	 * @generated
	 * @ordered
	 */
	protected EList<ActiveState> activestates;

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
	public EList<ActiveState> getActivestates() {
		if (activestates == null) {
			activestates = new EObjectContainmentEList<ActiveState>(ActiveState.class, this,
					ModelPackage.ACTIVE_STATE_CONTAINER__ACTIVESTATES);
		}
		return activestates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ModelPackage.ACTIVE_STATE_CONTAINER__ACTIVESTATES:
			return ((InternalEList<?>) getActivestates()).basicRemove(otherEnd, msgs);
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
		case ModelPackage.ACTIVE_STATE_CONTAINER__ACTIVESTATES:
			return getActivestates();
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
		case ModelPackage.ACTIVE_STATE_CONTAINER__ACTIVESTATES:
			getActivestates().clear();
			getActivestates().addAll((Collection<? extends ActiveState>) newValue);
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
		case ModelPackage.ACTIVE_STATE_CONTAINER__ACTIVESTATES:
			getActivestates().clear();
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
		case ModelPackage.ACTIVE_STATE_CONTAINER__ACTIVESTATES:
			return activestates != null && !activestates.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ActiveStateContainerImpl
