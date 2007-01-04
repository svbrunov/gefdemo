package org.tigris.gefdemo.bert.ui;

import org.tigris.gef.base.Layer;
import org.tigris.gef.presentation.ArrowHead;
import org.tigris.gef.presentation.ArrowHeadGreater;
import org.tigris.gef.presentation.Fig;

/**
 * @author Bob Tarling
 */
public class RelationshipEdgeFig extends ModelElementEdgeFig {

    private static final long serialVersionUID = -5236951948313711311L;
    
    private ArrowHead destArrowHead = new ArrowHeadGreater();
    
    public RelationshipEdgeFig(Object edge, Layer lay) {
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
