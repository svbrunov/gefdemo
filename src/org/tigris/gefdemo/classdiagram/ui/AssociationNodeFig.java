package org.tigris.gefdemo.classdiagram.ui;

import java.awt.*;
import java.util.Vector;

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

    public Vector getGravityPoints() {
        Vector ret = new Vector();
        Rectangle r = boundryFig.getBounds();
        ret.add(new Point(r.x + r.width / 2, r.y));
        ret.add(new Point(r.x + r.width, r.y + r.height / 2));
        ret.add(new Point(r.x + r.width / 2, r.y + r.height));
        ret.add(new Point(r.x, r.y + r.height / 2));
        return ret;
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
    public void setBounds(int x, int y, int w, int h) {
        Rectangle oldBounds = getBounds();
        boundryFig.setBounds(x, y, w, h);
        calcBounds(); //_x = x; _y = y; _w = w; _h = h;
        firePropChange("bounds", oldBounds, getBounds());
        updateEdges();
    }
}
