/*
 * Created on 13-Jun-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.tigris.gefdemo.classdiagram;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.tigris.gef.base.Editor;
import org.tigris.gef.base.Globals;
import org.tigris.gef.base.Layer;
import org.tigris.gef.base.ModeCreatePolyEdge;
import org.tigris.gef.graph.GraphNodeRenderer;
import org.tigris.gef.presentation.Fig;
import org.tigris.gef.presentation.FigEdge;
import org.tigris.gef.presentation.FigNode;
import org.tigris.gefdemo.classdiagram.model.UmlAssociation;
import org.tigris.gefdemo.classdiagram.ui.AssociationEdgeFig;
import org.tigris.gefdemo.classdiagram.ui.AssociationEndFig;
import org.tigris.gefdemo.classdiagram.ui.AssociationFig;

/**
 * 
 * @author Bob Tarling
 * @since 13-Jun-2004
 */
public class ModeCreateAssociationEnd extends ModeCreatePolyEdge {
    
    private static Log LOG = LogFactory.getLog(ModeCreatePolyEdge.class);
    FigNode newAssociationNode;
    
    /** On mousePressed determine what port the user is dragging from.
     *  The mousePressed event is sent via ModeSelect. */
    public void mousePressed(MouseEvent me) {
        if (me.isConsumed()) {
            if (LOG.isDebugEnabled()) LOG.debug("MousePressed detected but rejected as already consumed");
            return;
        }
        int x = me.getX();
        int y = me.getY();
        //Editor editor = Globals.curEditor();
        Fig underMouse = editor.hit(x, y);
        if (underMouse == null) {
            //System.out.println("bighit");
            underMouse = editor.hit(x - 16, y - 16, 32, 32);
        }
        if (underMouse == null && _npoints == 0) {
            done();
            me.consume();
            if (LOG.isDebugEnabled()) LOG.debug("MousePressed detected but nothing under mouse - consumed anyway");
            return;
        }
        
        if (underMouse instanceof AssociationEdgeFig) {
            if (LOG.isDebugEnabled()) LOG.debug("MousePressed detected on an association so replacing FigEdge with FigNode");
            newAssociationNode = replaceEdgeWithNode((FigEdge)underMouse, me);
            underMouse = newAssociationNode;
        }
        
        if (!(underMouse instanceof FigNode) && _npoints == 0) {
            done();
            me.consume();
            if (LOG.isDebugEnabled()) LOG.debug("MousePressed detected but not on a FigNode - consumed anyway");
            return;
        }
        
        if (getSourceFigNode() == null) { //_npoints == 0) {
            setSourceFigNode((FigNode) underMouse);
            setStartPort(getSourceFigNode().deepHitPort(x, y));
        }
        
        if (getStartPort() == null) {
            if (LOG.isDebugEnabled()) LOG.debug("MousePressed detected but not on a port - consumed anyway");
            done();
            me.consume();
            return;
        }
        setStartPortFig(getSourceFigNode().getPortFig(getStartPort()));

        if (_npoints == 0) {
            super.mousePressed(me);
        }
        if (LOG.isDebugEnabled()) LOG.debug("MousePressed detected and processed by ancestor - consumed");
        me.consume();
    }

    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyChar() == KeyEvent.VK_ESCAPE) { // escape
            LOG.debug("ESC pressed");
            if (newAssociationNode != null) {
                replaceNodeWithEdge(newAssociationNode);
            }
        }
        super.keyTyped(ke);
    }
    
    private FigNode replaceEdgeWithNode(FigEdge figEdge, MouseEvent me) {
        UmlAssociation association = (UmlAssociation)figEdge.getOwner();
        Editor editor = Globals.curEditor();
        UmlGraphModel gm = (UmlGraphModel)editor.getGraphModel();
        GraphNodeRenderer renderer = editor.getGraphNodeRenderer();
        Layer lay = editor.getLayerManager().getActiveLayer();
        FigNode figNode = renderer.getFigNodeFor(gm, lay, association);
        figNode.setX(me.getX() - figNode.getWidth()/2);
        figNode.setY(me.getY() - figNode.getHeight()/2);
        figEdge.delete();
        List associationEnds = association.getAssociationEnds();
        editor.add(figNode);
        gm.addEdge(associationEnds.get(0));
        gm.addEdge(associationEnds.get(1));
        editor.getSelectionManager().deselectAll();
        return figNode;
    }
    
    private void replaceNodeWithEdge(FigNode figNode) {
        System.out.println("Trying to replace assoc node with edge");
        AssociationFig af = (AssociationFig)figNode;
        UmlGraphModel gm = (UmlGraphModel)Globals.curEditor().getGraphModel();
        UmlAssociation association = (UmlAssociation)af.getOwner();
        Collection remainingEdges = af.getFigEdges(null);
        Iterator it = remainingEdges.iterator();
        while (it.hasNext()) {
            AssociationEndFig fig = (AssociationEndFig)it.next();
            fig.delete();
        }
        af.delete();
        gm.addEdge(association);
    }
}
