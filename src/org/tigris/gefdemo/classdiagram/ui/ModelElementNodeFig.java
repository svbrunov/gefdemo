
package org.tigris.gefdemo.classdiagram.ui;

import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;

import org.tigris.gef.graph.GraphModel;
import org.tigris.gef.presentation.Fig;
import org.tigris.gef.presentation.FigNode;

import org.tigris.gefdemo.classdiagram.model.UmlClass;
import org.tigris.gefdemo.classdiagram.model.UmlModelElement;

/**
 * A Fig representing a UML modelelement node
 * @author Bob Tarling
 */
abstract public class ModelElementNodeFig extends FigNode {
    
    Fig boundryFig;
            
    public ModelElementNodeFig(GraphModel gm, Object node) {
        super();
        setOwner(node);
    }
    
    public String getName() {
        return ((UmlClass)getOwner()).getName();
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
        String name = ((UmlModelElement)node).getName();
        if (boundryFig != null) {
            bindPort(node, boundryFig);
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
    public void setBounds(int x, int y, int w, int h) {
        Rectangle oldBounds = getBounds();
        boundryFig.setBounds(x, y, w, h);
        calcBounds(); //_x = x; _y = y; _w = w; _h = h;
        firePropChange("bounds", oldBounds, getBounds());
        updateEdges();
    }
}
