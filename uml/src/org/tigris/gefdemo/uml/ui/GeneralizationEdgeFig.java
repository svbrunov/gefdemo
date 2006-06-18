package org.tigris.gefdemo.uml.ui;

import org.tigris.gef.base.Layer;
import org.tigris.gef.presentation.ArrowHead;
import org.tigris.gef.presentation.ArrowHeadHalfTriangle;
import org.tigris.gef.presentation.ArrowHeadTriangle;

/**
 * @author Bob Tarling
 */
public class GeneralizationEdgeFig extends ModelElementEdgeFig {
    private ArrowHead destArrowHead = new ArrowHeadTriangle();
    
    public GeneralizationEdgeFig(Object edge, Layer lay) {
        super(edge, lay);
        setDestArrowHead(destArrowHead);
    }
    
    public void deleteFromModel() {
        System.out.println("Deleting generalization from model");
        super.deleteFromModel();
    }

    public void removeFromDiagram() {
        System.out.println("Removing generalization from diagram");
        super.removeFromDiagram();
    }

}
