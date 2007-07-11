package org.tigris.gefdemo.uml;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import org.tigris.gef.base.Globals;
import org.tigris.gef.base.ModeSelect;
import org.tigris.gef.event.ModeChangeEvent;
import org.tigris.gef.event.ModeChangeListener;
import org.tigris.gef.graph.ConnectionConstrainer;
import org.tigris.gef.graph.GraphModelException;
import org.tigris.gef.graph.presentation.Graph;
import org.tigris.gef.graph.presentation.SplitGraphPane;

import org.tigris.toolbar.ToolBarFactory;

/**
 * A panel containing the toolbar and split diagram
 * @author Bob Tarling
 * @since 16-Feb-04
 */
public abstract class DiagramPanel extends JPanel implements ModeChangeListener, InternalFrameListener {
    /** The graph pane (shown in middle of window). */
    private Graph graph;
    private JToolBar toolbar;
    
    private static Graph currentGraph;

    private List actions = new ArrayList();
    
    public DiagramPanel(ConnectionConstrainer connectionConstrainer) throws GraphModelException {
        super(new BorderLayout());
    
        org.tigris.gef.swing.JGraph g = new org.tigris.gef.swing.JGraph(connectionConstrainer);
        graph = g;
        graph.setGraphModel(new UmlGraphModel());
        currentGraph = graph;

        add(new org.tigris.gef.swing.JSplitGraphPane(g), BorderLayout.CENTER);
        graph.addModeChangeListener(this);
    }

    public void addAction(Action action) {
        toolbar.add(action);
    }
    protected JToolBar makeToolBar() {
        toolbar = ToolBarFactory.createToolBar(true, actions, false);
        return toolbar;
    }

    protected List getActions() {
        return actions;
    }
    
    protected Graph getGraph() {
        return graph;
    }

    /**
     * @see org.tigris.gef.event.ModeChangeListener#modeChange(org.tigris.gef.event.ModeChangeEvent)
     */
    public void modeChange(ModeChangeEvent mce) {
        if (!Globals.getSticky() && Globals.mode() instanceof ModeSelect) {
            //toolbar.unpressAllButtons();
        }
    }
    
    public static Graph getCurrentGraph() {
        return currentGraph;
    }

    /**
     * @see javax.swing.event.InternalFrameListener#internalFrameActivated(javax.swing.event.InternalFrameEvent)
     */
    public void internalFrameActivated(InternalFrameEvent arg0) {
    }

    /**
     * @see javax.swing.event.InternalFrameListener#internalFrameClosed(javax.swing.event.InternalFrameEvent)
     */
    public void internalFrameClosed(InternalFrameEvent arg0) {
    }

    /**
     * @see javax.swing.event.InternalFrameListener#internalFrameClosing(javax.swing.event.InternalFrameEvent)
     */
    public void internalFrameClosing(InternalFrameEvent arg0) {
    }

    /**
     * @see javax.swing.event.InternalFrameListener#internalFrameDeactivated(javax.swing.event.InternalFrameEvent)
     */
    public void internalFrameDeactivated(InternalFrameEvent arg0) {
    }

    /**
     * @see javax.swing.event.InternalFrameListener#internalFrameDeiconified(javax.swing.event.InternalFrameEvent)
     */
    public void internalFrameDeiconified(InternalFrameEvent arg0) {
    }

    /**
     * @see javax.swing.event.InternalFrameListener#internalFrameIconified(javax.swing.event.InternalFrameEvent)
     */
    public void internalFrameIconified(InternalFrameEvent arg0) {
    }

    /**
     * @see javax.swing.event.InternalFrameListener#internalFrameOpened(javax.swing.event.InternalFrameEvent)
     */
    public void internalFrameOpened(InternalFrameEvent arg0) {
    }
}
