package org.tigris.gefdemo.classdiagram.ui;

import org.tigris.gef.base.Layer;
import org.tigris.gef.presentation.ArrowHead;
import org.tigris.gef.presentation.ArrowHeadGreater;

/**
 * @author Bob Tarling
 */
public class AssociationEndFig extends ModelElementEdgeFig {
    private ArrowHead destArrowHead = new ArrowHeadGreater();
    
    public AssociationEndFig(Object edge, Layer lay) {
        super(edge, lay);
    }
}
