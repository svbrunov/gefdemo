package org.tigris.gefdemo.classdiagram.ui;

import org.tigris.gef.base.Layer;
import org.tigris.gef.presentation.ArrowHead;
import org.tigris.gef.presentation.ArrowHeadGreater;
import org.tigris.gef.presentation.FigEdgePoly;

import org.tigris.gefdemo.classdiagram.model.UmlDependency;

/**
 * @author Bob Tarling
 */
public class DependencyFig extends FigEdgePoly {
    private ArrowHead destArrowHead = new ArrowHeadGreater();
    
    public DependencyFig() {
        setBetweenNearestPoints(true);
        setDestArrowHead(destArrowHead);
        setDashed(true);
    }
    
    public DependencyFig(Object edge, Layer lay) {
        this();
        setLayer(lay);
        setOwner(edge);
    }

    public String getName() {
        return ((UmlDependency)getOwner()).getName();
    }
}
