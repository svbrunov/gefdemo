package org.tigris.gefdemo.classdiagram.ui;

import org.tigris.gef.base.Layer;
import org.tigris.gef.presentation.ArrowHead;
import org.tigris.gef.presentation.ArrowHeadGreater;
import org.tigris.gef.presentation.FigEdgePoly;

import org.tigris.gefdemo.classdiagram.model.UmlAssociationEnd;

/**
 * @author Bob Tarling
 */
public class AssociationEndFig extends FigEdgePoly {
    private ArrowHead destArrowHead = new ArrowHeadGreater();
    
    public AssociationEndFig() {
        setBetweenNearestPoints(true);
        setDestArrowHead(destArrowHead);
    }
    
    public AssociationEndFig(Object edge, Layer lay) {
        this();
        setLayer(lay);
        setOwner(edge);
    }

    public String getName() {
        return ((UmlAssociationEnd)getOwner()).getName();
    }
}
