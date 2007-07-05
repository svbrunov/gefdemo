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
public class SequenceDiagramPanel extends DiagramPanel {
    
    private static final long serialVersionUID = -5782383290781868487L;

    public SequenceDiagramPanel(
            ConnectionConstrainer connectionConstrainer,
            String name) throws GraphModelException {
        super(connectionConstrainer);
    
        setName(name);
        
        add(makeToolBar(), BorderLayout.NORTH);
        
        FigFactory renderer = new FigFactory();
        getGraph().setGraphNodeRenderer(renderer);
        getGraph().setGraphEdgeRenderer(renderer);
    }
}
