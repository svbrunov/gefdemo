package org.tigris.gefdemo.bert.ui;

import java.awt.Color;
import java.awt.Font;
import java.util.UUID;

import org.tigris.gef.base.Layer;
import org.tigris.gef.presentation.FigEdgePoly;
import org.tigris.gef.presentation.FigText;

import org.tigris.gefdemo.bert.ModelFacade;
import org.tigris.gefdemo.bert.model.ModelElement;

/**
 * @author Bob Tarling
 */
public class ModelElementEdgeFig extends FigEdgePoly {
    
    private static final long serialVersionUID = 5456328668689081675L;

    private UUID uuid = UUID.randomUUID();
    
    /**
     * The Fig that displays the name of this model element.
     * Use getNameFig(), no setter should be required.
     */
    private FigText nameFig;
    /**
     * The Fig that displays the stereotype of this model element.
     * Use getStereotypeFig(), no setter should be required.
     */
    private FigText stereoFig = new FigText(10, 30, 90, 20);
    
    public ModelElementEdgeFig(Object edge, Layer lay) {
        final Font LABEL_FONT = new Font("Dialog", Font.PLAIN, 10);
        nameFig = new FigText(10, 30, 90, 20);
        nameFig.setFont(LABEL_FONT);
        nameFig.setTextColor(Color.black);
        nameFig.setTextFilled(false);
        nameFig.setFilled(false);
        nameFig.setLineWidth(0);
        nameFig.setExpandOnly(false);
        nameFig.setReturnAction(FigText.INSERT);
        nameFig.setTabAction(FigText.IGNORE);

        stereoFig.setFont(LABEL_FONT);
        stereoFig.setTextColor(Color.black);
        stereoFig.setTextFilled(false);
        stereoFig.setFilled(false);
        stereoFig.setLineWidth(0);
        stereoFig.setExpandOnly(false);
        stereoFig.setReturnAction(FigText.IGNORE);
        stereoFig.setTabAction(FigText.IGNORE);
        setBetweenNearestPoints(true);
        setLayer(lay);
        setOwner(edge);
    }

    public String getName() {
        return ((ModelElement)getOwner()).getName();
    }
    
    public FigText getNameFig() {
        return nameFig;
    }
    
    public FigText getStereotypeFig() {
        return stereoFig;
    }
    
    public void deleteFromModel() {
        Object owner = getOwner();
        super.deleteFromModel();
        ModelFacade.getInstance().removeModelElement(owner);
    }
    
    public UUID getUuid() {
        return uuid;
    }
}
