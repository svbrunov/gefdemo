package org.tigris.gefdemo.classdiagram.ui;

import org.tigris.gef.presentation.ArrowHead;
import org.tigris.gef.presentation.ArrowHeadGreater;
import org.tigris.gef.presentation.FigEdgePoly;

import org.tigris.gefdemo.classdiagram.model.AssociationEdge;

/**
 * @author Bob Tarling
 */
public class AssociationFig extends FigEdgePoly {
    private ArrowHead destArrowHead = new ArrowHeadGreater();
    
    public AssociationFig() {
        setBetweenNearestPoints(true);
        setDestArrowHead(destArrowHead);
    }
    
    public String getName() {
        return ((AssociationEdge)getOwner()).getName();
    }
}
