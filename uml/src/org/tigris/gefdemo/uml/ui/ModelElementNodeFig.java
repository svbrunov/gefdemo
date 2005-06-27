
package org.tigris.gefdemo.uml.ui;

import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.util.List;

import org.tigris.gef.presentation.Fig;
import org.tigris.gef.presentation.FigNode;

import org.tigris.gefdemo.uml.ModelFacade;
import org.tigris.gefdemo.uml.model.UmlModelElement;

/**
 * A Fig representing a UML modelelement node
 * @author Bob Tarling
 */
abstract public class ModelElementNodeFig extends FigNode {
    
    Fig boundryFig;
            
    public ModelElementNodeFig(Object node) {
        super(node);
    }
    
    public String getName() {
        return ((UmlModelElement)getOwner()).getName();
    }
    
    /**
     * @return
     */
    public Fig getBoundryFig() {
        return boundryFig;
    }

    public boolean isDragConnectable() {
        return false;
    }
    
    public String toString() {
        if (getOwner() == null) return null;
        return getOwner().toString();
    }
    
    
    /** Called whenever the properties of the underlying node
     * model change
     */
    public void propertyChange(PropertyChangeEvent pce) {
        super.propertyChange(pce);
        if (pce.getPropertyName().equals("name")) {
            //nameFig.setText((String)pce.getNewValue());
        }
    }

    /**
     * Called to tie this fig to a model node
     */
    public void setOwner(Object node) {
        super.setOwner(node);
        if (node != null) {
            String name = ((UmlModelElement)node).getName();
            if (boundryFig != null) {
                bindPort(node, boundryFig);
            }
        }
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

    public void dispose() {
        Object owner = getOwner();
        super.dispose();
        ModelFacade.getInstance().removeModelElement(owner);
    }
    
    
    /**
     * Makes sure that the edges stick to the outline of the fig.
     * @see org.tigris.gef.presentation.Fig#getGravityPoints()
     */
    public List getGravityPoints() {
        return boundryFig.getGravityPoints();
    }
}
