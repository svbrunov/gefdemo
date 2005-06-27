package org.tigris.gefdemo.uml.ui;

import java.awt.Rectangle;

import org.tigris.gef.presentation.FigDiamond;

/**
 * A Fig representing an association between 2 or more classifiers
 * @author Bob Tarling
 */
public class AssociationNodeFig extends ModelElementNodeFig {
    
    public AssociationNodeFig(Object node) {
        super(node);
        
        boundryFig = new FigDiamond(0,0,70,70);
        addFig(boundryFig);
        setOwner(node);
    }
    
    /**
     * Set the bounding box to the given rect. Figs in the group are
     * scaled and/or positioned to fit.
     * This overrides the ancestor version which has poor
     * resize/position technique.
     * Fires PropertyChange with "bounds"
     * @param x new X co ordinate for fig
     * @param y new Y co ordinate for fig
     * @param w new width for fig
     * @param h new height for fig
     */
    public void setBoundsImpl(int x, int y, int w, int h) {
        Rectangle oldBounds = getBounds();
        boundryFig.setBounds(x, y, w, h);
        calcBounds(); //_x = x; _y = y; _w = w; _h = h;
        firePropChange("bounds", oldBounds, getBounds());
        updateEdges();
    }
}
