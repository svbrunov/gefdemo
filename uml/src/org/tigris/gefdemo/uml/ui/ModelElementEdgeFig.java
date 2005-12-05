package org.tigris.gefdemo.uml.ui;

import java.awt.Color;
import java.awt.Font;

import org.tigris.gef.base.Layer;
import org.tigris.gef.presentation.Fig;
import org.tigris.gef.presentation.FigEdgePoly;
import org.tigris.gef.presentation.FigText;

import org.tigris.gefdemo.uml.ModelFacade;
import org.tigris.gefdemo.uml.model.UmlModelElement;

/**
 * @author Bob Tarling
 */
public class ModelElementEdgeFig extends FigEdgePoly {
    
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
        nameFig.setMultiLine(false);
        nameFig.setAllowsTab(false);

        stereoFig.setFont(LABEL_FONT);
        stereoFig.setTextColor(Color.black);
        stereoFig.setTextFilled(false);
        stereoFig.setFilled(false);
        stereoFig.setLineWidth(0);
        stereoFig.setExpandOnly(false);
        stereoFig.setMultiLine(false);
        stereoFig.setAllowsTab(false);
        setBetweenNearestPoints(true);
        setLayer(lay);
        setOwner(edge);
    }

    public String getName() {
        return ((UmlModelElement)getOwner()).getName();
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
}
