package org.tigris.gefdemo.uml;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import org.tigris.gef.base.CmdSetMode;
import org.tigris.gef.base.Globals;
import org.tigris.gef.base.ModeCreatePolyEdge;
import org.tigris.gef.base.ModeSelect;
import org.tigris.gef.event.ModeChangeEvent;
import org.tigris.gef.event.ModeChangeListener;
import org.tigris.gef.graph.ConnectionConstrainer;
import org.tigris.gef.graph.GraphModelException;
import org.tigris.gef.graph.presentation.JGraph;
import org.tigris.gef.graph.presentation.JSplitGraphPane;
import org.tigris.gefdemo.uml.model.UmlAssociationClass;
import org.tigris.gefdemo.uml.model.UmlAssociationEnd;
import org.tigris.gefdemo.uml.model.UmlClass;
import org.tigris.gefdemo.uml.model.UmlDependency;
import org.tigris.gefdemo.uml.model.UmlInterface;

import org.tigris.toolbar.ToolBarFactory;

/**
 * A panel containing the toolbar and split diagram
 * @author Bob Tarling
 * @since 16-Feb-04
 */
public class DiagramPanel extends JPanel implements ModeChangeListener, InternalFrameListener {
    /** The graph pane (shown in middle of window). */
    private JGraph graph;
    private JToolBar toolbar;

    private List actions = new ArrayList();
    
    public DiagramPanel(ConnectionConstrainer connectionConstrainer) throws GraphModelException {
        super(new BorderLayout());

        setName("Diagram");
        
        actions.add(new CmdCreateNode(UmlClass.class, "Class"));
        actions.add(new CmdCreateNode(UmlInterface.class, "Interface"));
        actions.add(new CmdSetMode(ModeCreateAssociationEnd.class, "edgeClass", UmlAssociationEnd.class, "Association"));
        actions.add(new CmdSetMode(ModeCreatePolyEdge.class, "edgeClass", UmlDependency.class, "Dependency"));
        actions.add(new CmdSetMode(ModeCreatePolyEdge.class, "edgeClass", UmlAssociationClass.class, "AssociationClass"));
//        
//        actions.add(new ZoomSliderButton());
    
        graph = new JGraph(connectionConstrainer);
        graph.setGraphModel(new UmlGraphModel());

        add(makeToolBar(), BorderLayout.NORTH);
        add(new JSplitGraphPane(graph), BorderLayout.CENTER);
        graph.addModeChangeListener(this);
        
        ClassDiagramRenderer renderer = new ClassDiagramRenderer();
        graph.setGraphNodeRenderer(renderer);
        graph.setGraphEdgeRenderer(renderer);
    }

    public void addAction(Action action) {
        toolbar.add(action);
    }
    private JToolBar makeToolBar() {
        toolbar = ToolBarFactory.createToolBar(true, actions, false);
        return toolbar;
    }

    private List getActions() {
        return actions;
    }

    /* (non-Javadoc)
     * @see org.tigris.gef.event.ModeChangeListener#modeChange(org.tigris.gef.event.ModeChangeEvent)
     */
    public void modeChange(ModeChangeEvent mce) {
        if (!Globals.getSticky() && Globals.mode() instanceof ModeSelect) {
            //toolbar.unpressAllButtons();
        }
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
