package org.tigris.gefdemo.uml.ui;

import org.tigris.gef.base.Layer;
import org.tigris.gef.presentation.ArrowHead;
import org.tigris.gef.presentation.ArrowHeadGreater;

/**
 * @author Bob Tarling
 */
public class AssociationEdgeFig extends ModelElementEdgeFig {
    private ArrowHead destArrowHead = new ArrowHeadGreater();
    
    public AssociationEdgeFig(Object edge, Layer lay) {
        super(edge, lay);
    }
}
