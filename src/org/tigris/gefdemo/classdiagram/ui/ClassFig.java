
package org.tigris.gefdemo.classdiagram.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;

import org.tigris.gef.presentation.Fig;
import org.tigris.gef.presentation.FigLine;
import org.tigris.gef.presentation.FigNode;
import org.tigris.gef.presentation.FigRect;
import org.tigris.gef.presentation.FigText;

import org.tigris.gefdemo.classdiagram.model.MClass;

/**
 * A Fig representing a target node of an Ant script
 * @author Bob Tarling
 */
public class ClassFig extends FigNode {
    
    Fig boundryFig;
    FigLine seperator1;
    FigLine seperator2;
            
    public ClassFig() {
        
        boundryFig = new FigRect(0,0,70,60);

        seperator1 = new FigLine(0,20,70,20);    
        seperator2 = new FigLine(0,40,70,40);    
        
        addFig(boundryFig);
        addFig(seperator1);
        addFig(seperator2);
    }
    
    public String getName() {
        return ((MClass)getOwner()).getName();
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
        String name = ((MClass)node).getName();
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
        seperator2.setBounds(x, y + h*2/3, w, y + h*2/3);
        calcBounds(); //_x = x; _y = y; _w = w; _h = h;
        firePropChange("bounds", oldBounds, getBounds());
        updateEdges();
    }
    
}
