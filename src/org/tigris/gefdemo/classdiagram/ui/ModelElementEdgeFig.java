package org.tigris.gefdemo.classdiagram.ui;

import org.tigris.gef.base.Layer;
import org.tigris.gef.presentation.ArrowHead;
import org.tigris.gef.presentation.ArrowHeadGreater;
import org.tigris.gef.presentation.FigEdgePoly;

import org.tigris.gefdemo.classdiagram.ModelFacade;
import org.tigris.gefdemo.classdiagram.model.UmlModelElement;

/**
 * @author Bob Tarling
 */
public class ModelElementEdgeFig extends FigEdgePoly {
    private ArrowHead destArrowHead = new ArrowHeadGreater();
    
    public ModelElementEdgeFig(Object edge, Layer lay) {
        setBetweenNearestPoints(true);
        setDestArrowHead(destArrowHead);
        setLayer(lay);
        setOwner(edge);
    }

    public String getName() {
        return ((UmlModelElement)getOwner()).getName();
    }
    
    public void dispose() {
        Object owner = getOwner();
        super.dispose();
        ModelFacade.getInstance().removeModelElement(owner);
    }
}
