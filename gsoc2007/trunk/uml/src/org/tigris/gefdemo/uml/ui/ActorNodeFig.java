
package org.tigris.gefdemo.uml.ui;

import java.awt.Rectangle;

import org.tigris.gef.presentation.FigRect;
import org.tigris.gef.presentation.FigCircle;
import org.tigris.gef.presentation.FigLine;
import java.awt.Color;

public class ActorNodeFig extends ModelElementNodeFig {
    
    private static final long serialVersionUID = -2427794589648628383L;
    private static final int FIGWIDTH=50;
    private static final int FIGHEIGHT=70;
        
    private FigCircle headerFig;
    private FigLine bodyFig;
    private FigLine armsFig;
    private FigLine leftLegFig;
    private FigLine rightLegFig;
            
    public ActorNodeFig(Object node) {
        super(node);
        Color tColor=new Color(255,255,255,0);
        boundryFig =new FigRect(0,0,FIGWIDTH,FIGHEIGHT, tColor, tColor);
        headerFig = new FigCircle(FIGWIDTH/3,0,FIGWIDTH/3,FIGWIDTH/3);
        bodyFig = new FigLine(FIGWIDTH/2,FIGWIDTH/3,FIGWIDTH/2,FIGHEIGHT/3*2);
        armsFig = new FigLine(0,FIGHEIGHT*2/5,FIGWIDTH,FIGHEIGHT*2/5);
        leftLegFig = new FigLine(FIGWIDTH/2,FIGHEIGHT/3*2,0,FIGHEIGHT);
        rightLegFig = new FigLine(FIGWIDTH/2,FIGHEIGHT/3*2,FIGWIDTH,FIGHEIGHT);
        addFig(boundryFig);
        addFig(headerFig);
        addFig(bodyFig);
        addFig(armsFig);
        addFig(leftLegFig);
        addFig(rightLegFig);
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
        boundryFig.setBounds(x,y,w,h);
        headerFig.setBounds(x+w/3, y, w/3, w/3);
        bodyFig.setBounds(x+w/2, y+w/3, w/2, h/3);
        int armsH=(h*2/5<w/3+5)?(w/3+5):h*2/5;
        armsFig.setBounds(x, y+armsH, w, 1);
        leftLegFig.setBounds(x, y+w/3+h/3, w/2, h-h/3-w/3);
        rightLegFig.setBounds(x+w/2, y+w/3+h/3, w/2, h-h/3-w/3);
        calcBounds(); //_x = x; _y = y; _w = w; _h = h;
        firePropChange("bounds", oldBounds, getBounds());
        updateEdges();
    }
}
