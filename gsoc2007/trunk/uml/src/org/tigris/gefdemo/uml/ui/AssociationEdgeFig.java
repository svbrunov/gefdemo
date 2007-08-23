package org.tigris.gefdemo.uml.ui;

import org.tigris.gef.base.Layer;
import org.tigris.gef.base.PathConvPercent;

/**
 * @author Bob Tarling
 */
public class AssociationEdgeFig extends ModelElementEdgeFig {
    
    private static final long serialVersionUID = 2860173906299928711L;

    /**
     * Group for the FigTexts concerning the name and stereotype of the 
     * association itself.
     */
    private FigTextGroup middleGroup = new FigTextGroup();
    
    public AssociationEdgeFig(Object edge, Layer lay) {
        super(edge, lay);
        
        middleGroup.addFig(getNameFig());
        middleGroup.addFig(getStereotypeFig());
        getNameFig().setText("anon assoc");
        addPathItem(middleGroup, new PathConvPercent(this, 50, 25));
    }
    
    /**
     * @return Returns the middleGroup.
     */
    protected FigTextGroup getMiddleGroup() {
        return middleGroup;
    }

}
