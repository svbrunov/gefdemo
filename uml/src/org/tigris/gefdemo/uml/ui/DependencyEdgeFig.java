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

    public void deleteFromModel() {
        System.out.println("Deleting dependancy from model");
        super.deleteFromModel();
    }

    public void removeFromDiagram() {
        System.out.println("Removing dependancy from diagram");
        super.removeFromDiagram();
    }

}
