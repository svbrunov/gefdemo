
package org.tigris.gefdemo.classdiagram.ui;

import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;

import org.tigris.gef.graph.GraphModel;
import org.tigris.gef.presentation.Fig;
import org.tigris.gef.presentation.FigLine;
import org.tigris.gef.presentation.FigNode;
import org.tigris.gef.presentation.FigRect;

import org.tigris.gefdemo.classdiagram.model.UmlInterface;

public class InterfaceFig extends FigNode {
    
    Fig boundryFig;
    FigLine seperator1;
            
    public InterfaceFig() {
        
        boundryFig = new FigRect(0,0,70,60);

        seperator1 = new FigLine(0,20,70,20);    
        
        addFig(boundryFig);
        addFig(seperator1);
    }
    
    public InterfaceFig(GraphModel gm, Object node) {
        this();
        setOwner(node);
    }
    
    public String getName() {
        return ((UmlInterface)getOwner()).getName();
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
        String name = ((UmlInterface)node).getName();
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
        seperator1.setBounds(x, y + h/3, w, y + h/3);
        calcBounds(); //_x = x; _y = y; _w = w; _h = h;
        firePropChange("bounds", oldBounds, getBounds());
        updateEdges();
    }
    
}
