package org.tigris.gefdemo.uml.ui;

import java.util.Collection;
import java.util.Iterator;

import org.tigris.gef.base.Globals;
import org.tigris.gef.base.Layer;
import org.tigris.gef.presentation.ArrowHead;
import org.tigris.gef.presentation.ArrowHeadGreater;
import org.tigris.gef.presentation.FigNode;
import org.tigris.gefdemo.uml.UmlGraphModel;
import org.tigris.gefdemo.uml.model.UmlAssociation;
import org.tigris.gefdemo.uml.model.UmlClassifier;

/**
 * @author Bob Tarling
 */
public class AssociationEndEdgeFig extends ModelElementEdgeFig {
    
    private static final long serialVersionUID = 5021601258693181303L;
    
    private ArrowHead arrowHead = new ArrowHeadGreater();
    
    public AssociationEndEdgeFig(Object edge, Layer lay) {
        super(edge, lay);
    }
    
    public void dispose() {
        AssociationNodeFig af = (AssociationNodeFig)getSourceFigNode();
        UmlGraphModel gm = (UmlGraphModel)Globals.curEditor().getGraphModel();
        UmlAssociation association = (UmlAssociation)af.getOwner();
        super.deleteFromModel();
        Collection remainingEdges = af.getFigEdges(null);
        int edgeCount = remainingEdges.size();
        if (edgeCount == 2) {
            Iterator it = remainingEdges.iterator();
            while (it.hasNext()) {
                AssociationEndEdgeFig fig = (AssociationEndEdgeFig)it.next();
                fig.removeFromDiagram();
            }
            af.removeFromDiagram();
            gm.addEdge(association);
        }
    }
    
    public void setDestFigNode(FigNode fn) {
        super.setDestFigNode(fn);
        if (fn.getOwner() instanceof UmlClassifier) {
            setDestArrowHead(arrowHead);
        }
    }

    public void setSourceFigNode(FigNode fn) {
        super.setSourceFigNode(fn);
        if (fn.getOwner() instanceof UmlClassifier) {
            setSourceArrowHead(arrowHead);
        }
    }
}
