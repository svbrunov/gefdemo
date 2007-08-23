
package org.tigris.gefdemo.bert.ui;

import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.UUID;

import org.tigris.gef.presentation.Fig;
import org.tigris.gef.presentation.FigNode;

import org.tigris.gefdemo.bert.ModelFacade;
import org.tigris.gefdemo.bert.model.ModelElement;

/**
 * A Fig representing a UML modelelement node
 * @author Bob Tarling
 */
abstract public class ModelElementNodeFig extends FigNode {
    
    Fig boundryFig;
    UUID uuid = UUID.randomUUID();
            
    public ModelElementNodeFig(Object node) {
        super(node);
    }
    
    public String getName() {
        return ((ModelElement)getOwner()).getName();
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
    protected void setBoundsImpl(int x, int y, int w, int h) {
        Rectangle oldBounds = getBounds();
        boundryFig.setBounds(x, y, w, h);
        calcBounds(); //_x = x; _y = y; _w = w; _h = h;
        firePropChange("bounds", oldBounds, getBounds());
        updateEdges();
    }

    public void deleteFromModel() {
        Object owner = getOwner();
        super.deleteFromModel();
        ModelFacade.getInstance().removeModelElement(owner);
    }
    
    
    /**
     * Makes sure that the edges stick to the outline of the fig.
     * @see org.tigris.gef.presentation.Fig#getGravityPoints()
     */
    public List getGravityPoints() {
        return boundryFig.getGravityPoints();
    }
    
    public UUID getUuid() {
        return uuid;
    }
}
