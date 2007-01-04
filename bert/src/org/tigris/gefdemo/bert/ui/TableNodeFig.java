
package org.tigris.gefdemo.bert.ui;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.tigris.gef.presentation.FigRect;
import org.tigris.gef.presentation.FigText;
import org.tigris.gefdemo.bert.model.Attribute;
import org.tigris.gefdemo.bert.model.Table;

/**
 * A Fig representing a target node of an Ant script
 * @author Bob Tarling
 */
public class TableNodeFig extends ModelElementNodeFig {
    
    private static final long serialVersionUID = -8519206619869189441L;
    
    private List<FigText> figAttributes = new ArrayList<FigText>();
    private FigText tableNameFig;
    
    public TableNodeFig(Object node) {
        super(node);
        
        boundryFig = new FigRect(0,0,70,60);
        addFig(boundryFig);
        
        int textHeight = 20;
        
        tableNameFig = new FigText(0,0,70, textHeight);
        tableNameFig.setText("TableName");
        addFig(tableNameFig);
        
        List<Attribute> attributes = ((Table) node).getAttributes();
        int y = textHeight;
        for (Attribute attribute : attributes) {
            FigText attributeFig = new FigText(0,y,70,y + textHeight);
            attributeFig.setOwner(attribute);
            y += textHeight;
            attributeFig.setText(attribute.getName());
            figAttributes.add(attributeFig);    
            addFig(attributeFig);
            bindPort(attribute, attributeFig);
        }

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
        
        int eachHeight = h / (figAttributes.size() + 1);
        
        tableNameFig.setBounds(x, y, w, eachHeight);
        y += eachHeight;
        
        for (FigText attributeFig : figAttributes) {
            attributeFig.setBounds(x, y, w, eachHeight);
        }
        
        calcBounds(); //_x = x; _y = y; _w = w; _h = h;
        updateEdges();
    }
}
