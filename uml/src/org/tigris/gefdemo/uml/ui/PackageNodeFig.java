
package org.tigris.gefdemo.uml.ui;

import java.awt.Rectangle;

import org.tigris.gef.presentation.Fig;
import org.tigris.gef.presentation.FigLine;
import org.tigris.gef.presentation.FigRect;

public class PackageNodeFig extends ModelElementNodeFig {
    
    FigRect tabFig;
            
    public PackageNodeFig(Object node) {
        super(node);
        
        tabFig = new FigRect(0,0,70,20);
        boundryFig = new FigRect(0,20,70,50);
        
        addFig(boundryFig);
        addFig(tabFig);
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
    protected void setBoundsImpl(int x, int y, int w, int h) {
        Rectangle oldBounds = getBounds();
        boundryFig = new FigRect(0,20,w,h-20);
        calcBounds(); //_x = x; _y = y; _w = w; _h = h;
        firePropChange("bounds", oldBounds, getBounds());
        updateEdges();
    }
}
