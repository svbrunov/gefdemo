package org.tigris.gefdemo.classdiagram.ui;

import org.tigris.gef.base.Layer;
import org.tigris.gef.presentation.ArrowHead;
import org.tigris.gef.presentation.ArrowHeadGreater;

/**
 * @author Bob Tarling
 */
public class DependencyFig extends ModelElementEdgeFig {
    private ArrowHead destArrowHead = new ArrowHeadGreater();
    
    public DependencyFig(Object edge, Layer lay) {
        super(edge, lay);
        getFig().setDashed(true);
    }
}
