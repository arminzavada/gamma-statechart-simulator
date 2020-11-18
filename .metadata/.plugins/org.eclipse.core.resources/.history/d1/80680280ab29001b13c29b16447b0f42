package com.triad.school.gamma.simulator.ui.views;


import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.*;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.emf.EMFScope;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformation;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.triad.school.gamma.simulator.query.FireableTransitions;
import com.triad.school.gamma.simulator.query.GammaStatechartSimulatorTransformation;
import com.triad.school.gamma.simulator.query.InitialNode;
import hu.bme.mit.gamma.statechart.interface_.Event;
import hu.bme.mit.gamma.statechart.interface_.Interface;
import hu.bme.mit.gamma.statechart.interface_.Port;
import hu.bme.mit.gamma.statechart.interface_.RealizationMode;
import hu.bme.mit.gamma.statechart.statechart.StateNode;

import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionListener;

import java.util.List;

import javax.inject.Inject;

public class GammaStatechartSimulator extends ViewPart {
	public static final String ID = "com.triad.school.gamma.simulator.ui.views.GammaStatechartSimulator";

    @Inject IWorkbench workbench;
		 
    GammaStatechartSimulatorTransformation transformation;

    class SignalContainer {
    	public final String interfaceName;
    	public final List<Event> events;
    	
    	public SignalContainer(String name, List<Event> events) {
    		interfaceName = name;
    		this.events = events;
    	}
    }
    
    Composite parent;
    Group eventButtons;
    private Event selectedEvent;
    Label activeStateLabel;

	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;
		
		RowLayout rowLayout = new RowLayout();
        rowLayout.marginLeft = 10;
        rowLayout.marginTop = 10;
        rowLayout.spacing = 15;
        parent.setLayout(rowLayout);
		
        activeStateLabel = new Label(parent, SWT.NONE);
        
        Button sendButton = new Button(parent, SWT.PUSH);
		sendButton.setText("Send event");
		sendButton.addMouseListener(MouseListener.mouseDownAdapter((event) -> {
			if (selectedEvent != null) {
				transformation.sendEvent(selectedEvent);
				updateActiveState();	
			}
		}));

		Button tickButton = new Button(parent, SWT.PUSH);
		tickButton.setText("Tick");
		tickButton.addMouseListener(MouseListener.mouseDownAdapter((event) -> {
			transformation.tick();
			updateActiveState();	
		}));

		Button refreshButton = new Button(parent, SWT.PUSH);
		refreshButton.setText("Refresh");
		refreshButton.addMouseListener(MouseListener.mouseDownAdapter((event) -> createTransformation()));
		
		// Create the help context id for the viewer's control
		//workbench.getHelpSystem().setHelp(parent.getControl(), "com.triad.school.gamma.simulator.ui.viewer");

		createTransformation();
	}
	
	private void updateActiveState() {
		StateNode active = transformation.activeState().get(0);
		
		activeStateLabel.setText(active.getName());
	}
	
	private void createTransformation() {
		XtextEditor editor = EditorUtils.getActiveXtextEditor();
		IXtextDocument document = editor.getDocument();
		XtextResource xtextResource = document.readOnly((state) -> state);
		
		if (transformation != null) {
			transformation.dispose();
			transformation = null;
        }
		
        transformation = new GammaStatechartSimulatorTransformation(xtextResource);
        
        selectedEvent = null;
        redrawEvents(transformation.requiredInterfaces());
		updateActiveState();
    }

	private void redrawEvents(List<Port> ports) {
    	if (eventButtons != null) {
    		eventButtons.dispose();
    	}
    	
    	eventButtons = new Group(parent, SWT.NONE);
        eventButtons.setLayout(new RowLayout(SWT.VERTICAL));
        eventButtons.setText("Select event");
        
        parent.layout(true, true);
		
		ports.stream()
			.filter(port -> port.getInterfaceRealization().getRealizationMode() == RealizationMode.REQUIRED)
			.forEach(port -> {				
				final String portName = port.getName();
		        
		        port.getInterfaceRealization().getInterface().getEvents().forEach(event -> {
					Button button = new Button(eventButtons, SWT.RADIO);
					
					button.setText(portName + "." + event.getEvent().getName());	
					
					button.addSelectionListener(SelectionListener.widgetSelectedAdapter((dsa) -> {
						selectedEvent = event.getEvent();
					}));
		        });
	        });
	}
	
	@Override
	public void setFocus() {
		//viewer.getControl().setFocus();
	}
}
