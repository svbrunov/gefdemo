package org.tigris.gefdemo.uml.ui;

import org.tigris.gef.base.Layer;
import org.tigris.gef.presentation.ArrowHead;
import org.tigris.gef.presentation.ArrowHeadGreater;
import org.tigris.gef.presentation.Fig;

/**
 * @author Bob Tarling
 */
public class DependencyEdgeFig extends ModelElementEdgeFig {
    private ArrowHead destArrowHead = new ArrowHeadGreater();
    
    public DependencyEdgeFig(Object edge, Layer lay) {
        super(edge, lay);
        setDestArrowHead(destArrowHead);
        getFig().setDashed(true);
    }
    
    public void setFig(Fig f) {
        super.setFig(f);
        f.setDashed(true);
    }

    public void dispose() {
        System.out.println("Disposing of dependancy from model");
        super.dispose();
    }

    public void delete() {
        System.out.println("Deleting dependancy from diagram");
        super.delete();
    }

}
