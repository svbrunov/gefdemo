package org.tigris.gefdemo.uml;

import java.awt.BorderLayout;

import org.tigris.gef.base.CmdSetMode;
import org.tigris.gef.base.ModeCreatePolyEdge;
import org.tigris.gef.graph.ConnectionConstrainer;
import org.tigris.gef.graph.GraphModelException;
import org.tigris.gefdemo.uml.model.UmlAssociationClass;
import org.tigris.gefdemo.uml.model.UmlAssociationEnd;
import org.tigris.gefdemo.uml.model.UmlClass;
import org.tigris.gefdemo.uml.model.UmlDependency;
import org.tigris.gefdemo.uml.model.UmlInterface;

/**
 * A panel containing the toolbar and split diagram
 * @author Bob Tarling
 * @since 31-Jan-05
 */
public class ActivityDiagramPanel extends DiagramPanel {
    
    public ActivityDiagramPanel(
            ConnectionConstrainer connectionConstrainer,
            String name) throws GraphModelException {
        super(connectionConstrainer);
    
        setName(name);
        
        getActions().add(new CmdCreateNode(UmlClass.class, "Class"));
        getActions().add(new CmdCreateNode(UmlInterface.class, "Interface"));
        getActions().add(new CmdSetMode(ModeCreateAssociationEnd.class, "edgeClass", UmlAssociationEnd.class, "Association"));
        getActions().add(new CmdSetMode(ModeCreatePolyEdge.class, "edgeClass", UmlDependency.class, "Dependency"));
        getActions().add(new CmdSetMode(ModeCreatePolyEdge.class, "edgeClass", UmlAssociationClass.class, "AssociationClass"));
        add(makeToolBar(), BorderLayout.NORTH);
        
        ClassDiagramRenderer renderer = new ClassDiagramRenderer();
        getGraph().setGraphNodeRenderer(renderer);
        getGraph().setGraphEdgeRenderer(renderer);
    }
}
