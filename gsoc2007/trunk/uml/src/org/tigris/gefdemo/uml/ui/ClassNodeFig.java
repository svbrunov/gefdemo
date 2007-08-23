
package org.tigris.gefdemo.uml.ui;

import java.awt.Rectangle;

import org.tigris.gef.presentation.FigLine;
import org.tigris.gef.presentation.FigRect;

/**
 * A Fig representing a target node of an Ant script
 * @author Bob Tarling
 */
public class ClassNodeFig extends ModelElementNodeFig {
    
    private static final long serialVersionUID = -8519206619869189441L;
    
    private FigLine seperator1;
    private FigLine seperator2;
            
    public ClassNodeFig(Object node) {
        super(node);
        System.out.println("XXXXXXXXXXXXXXX constructing");
        
        boundryFig = new FigRect(0,0,70,60);

        seperator1 = new FigLine(0,20,70,20);    
        seperator2 = new FigLine(0,40,70,40);    
        
        addFig(boundryFig);
        addFig(seperator1);
        addFig(seperator2);
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
        boundryFig.setBounds(x, y, w, h);
        seperator1.setBounds(x, y + h/3, w, y + h/3);
        seperator2.setBounds(x, y + h*2/3, w, y + h*2/3);
        calcBounds(); //_x = x; _y = y; _w = w; _h = h;
        firePropChange("bounds", oldBounds, getBounds());
        updateEdges();
    }
    
    public Object clone() {
        System.out.println("YYYYYYYYYYYYYYYY cloning");
        return super.clone();
    }
}
