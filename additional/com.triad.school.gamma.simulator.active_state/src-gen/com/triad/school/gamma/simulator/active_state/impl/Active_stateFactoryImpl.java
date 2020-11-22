/**
 */
package com.triad.school.gamma.simulator.active_state.impl;

import com.triad.school.gamma.simulator.active_state.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Active_stateFactoryImpl extends EFactoryImpl implements Active_stateFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Active_stateFactory init() {
		try {
			Active_stateFactory theActive_stateFactory = (Active_stateFactory) EPackage.Registry.INSTANCE
					.getEFactory(Active_statePackage.eNS_URI);
			if (theActive_stateFactory != null) {
				return theActive_stateFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Active_stateFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Active_stateFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case Active_statePackage.ACTIVE_STATE_CONTAINER:
			return createActiveStateContainer();
		case Active_statePackage.EVENT_QUEUE:
			return createEventQueue();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActiveStateContainer createActiveStateContainer() {
		ActiveStateContainerImpl activeStateContainer = new ActiveStateContainerImpl();
		return activeStateContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventQueue createEventQueue() {
		EventQueueImpl eventQueue = new EventQueueImpl();
		return eventQueue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Active_statePackage getActive_statePackage() {
		return (Active_statePackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Active_statePackage getPackage() {
		return Active_statePackage.eINSTANCE;
	}

} //Active_stateFactoryImpl
