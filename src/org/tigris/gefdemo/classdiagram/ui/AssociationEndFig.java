package org.tigris.gefdemo.classdiagram.ui;

import java.util.Collection;
import java.util.Iterator;

import org.tigris.gef.base.Layer;
import org.tigris.gef.presentation.ArrowHead;
import org.tigris.gef.presentation.ArrowHeadGreater;

/**
 * @author Bob Tarling
 */
public class AssociationEndFig extends ModelElementEdgeFig {
    private ArrowHead destArrowHead = new ArrowHeadGreater();
    
    public AssociationEndFig(Object edge, Layer lay) {
        super(edge, lay);
    }
    
    public void delete() {
        AssociationFig af = (AssociationFig)getSourceFigNode();
        super.delete();
        Collection remainingEdges = af.getFigEdges(null);
        int edgeCount = remainingEdges.size();
        if (edgeCount == 2) {
            Iterator it = remainingEdges.iterator();
            while (it.hasNext()) {
                AssociationEndFig fig = (AssociationEndFig)it.next();
                fig.delete();
            }
            af.delete();
        }
    }
}
