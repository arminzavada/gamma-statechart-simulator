package com.triad.school.gamma.simulator.ui.views;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;
import com.triad.school.gamma.simulator.query.ActiveStateListener;
import com.triad.school.gamma.simulator.query.GammaStatechartSimulatorTransformation;
import hu.bme.mit.gamma.statechart.interface_.Event;
import hu.bme.mit.gamma.statechart.interface_.Port;
import hu.bme.mit.gamma.statechart.interface_.RealizationMode;
import hu.bme.mit.gamma.statechart.statechart.StateNode;

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

    class StateContainer {
    	public final StateNode stateNode;
    	public final Button button;
    	
    	public StateContainer(StateNode stateNode, Button button) {
    		this.stateNode = stateNode;
    		this.button = button;
    	}
    }
    
    Composite parent;
    
    Group eventButtons;
    private Event selectedEvent;

    Group activeStateGroup;
    List<StateContainer> activeStateButtons = new ArrayList<>();

	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;
		
		RowLayout rowLayout = new RowLayout();
        rowLayout.marginLeft = 10;
        rowLayout.marginTop = 10;
        rowLayout.spacing = 15;
        parent.setLayout(rowLayout);
        
        Button sendButton = new Button(parent, SWT.PUSH);
		sendButton.setText("Send event");
		sendButton.addMouseListener(MouseListener.mouseDownAdapter((event) -> {
			if (selectedEvent != null) {
				transformation.sendEvent(selectedEvent);
			}
		}));

		Button refreshButton = new Button(parent, SWT.PUSH);
		refreshButton.setText("Refresh");
		refreshButton.addMouseListener(MouseListener.mouseDownAdapter((event) -> createTransformation()));
		
		// Create the help context id for the viewer's control
		//workbench.getHelpSystem().setHelp(parent.getControl(), "com.triad.school.gamma.simulator.ui.viewer");
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
        transformation.setActiveStateListener(new ActiveStateListener() {
			@Override
			public void activeStateAdded(StateNode node) {
				Button button = activeStateButtons.stream().filter(x -> x.stateNode == node).findFirst().get().button;
				button.setSelection(true);
			}

			@Override
			public void activeStateRemoved(StateNode node) {
				Button button = activeStateButtons.stream().filter(x -> x.stateNode == node).findFirst().get().button;
				button.setSelection(false);
			}
        });
     
        selectedEvent = null;
        redrawStates(transformation.everyState());
        redrawEvents(transformation.requiredInterfaces());
        
        transformation.execute();
    }

	private void redrawEvents(List<Port> ports) {
    	if (eventButtons != null) {
    		eventButtons.dispose();
    	}
    	
    	eventButtons = new Group(parent, SWT.NONE);
        eventButtons.setLayout(new RowLayout(SWT.VERTICAL));
        eventButtons.setText("Select event");
		
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
        
        parent.layout(true, true);
	}

	private void redrawStates(List<StateNode> states) {
    	if (activeStateGroup != null) {
    		activeStateGroup.dispose();
    	}
		
        activeStateGroup = new Group(parent, SWT.NONE);
        activeStateGroup.setText("Active states");
        activeStateGroup.setLayout(new RowLayout(SWT.VERTICAL));
		
        activeStateButtons = states.stream().map(state -> {	
			Button button = new Button(activeStateGroup, SWT.RADIO);
			button.setEnabled(false);
			button.setSelection(false);
			button.setText(state.getName());
			return new StateContainer(state, button);			
        }).collect(Collectors.toList());
        
        parent.layout(true, true);
	}
	
	@Override
	public void setFocus() {
		//viewer.getControl().setFocus();
	}
}
