package org.tigris.gefdemo.classdiagram.ui;

import java.util.Collection;
import java.util.Iterator;

import org.tigris.gef.base.Globals;
import org.tigris.gef.base.Layer;
import org.tigris.gef.presentation.ArrowHead;
import org.tigris.gef.presentation.ArrowHeadGreater;
import org.tigris.gef.presentation.FigNode;
import org.tigris.gefdemo.classdiagram.UmlGraphModel;
import org.tigris.gefdemo.classdiagram.model.UmlAssociation;
import org.tigris.gefdemo.classdiagram.model.UmlClassifier;

/**
 * @author Bob Tarling
 */
public class AssociationEndFig extends ModelElementEdgeFig {
    private ArrowHead arrowHead = new ArrowHeadGreater();
    
    public AssociationEndFig(Object edge, Layer lay) {
        super(edge, lay);
    }
    
    public void dispose() {
        AssociationFig af = (AssociationFig)getSourceFigNode();
        UmlGraphModel gm = (UmlGraphModel)Globals.curEditor().getGraphModel();
        UmlAssociation association = (UmlAssociation)af.getOwner();
        super.dispose();
        Collection remainingEdges = af.getFigEdges(null);
        int edgeCount = remainingEdges.size();
        if (edgeCount == 2) {
            Iterator it = remainingEdges.iterator();
            while (it.hasNext()) {
                AssociationEndFig fig = (AssociationEndFig)it.next();
                fig.delete();
            }
            af.delete();
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
