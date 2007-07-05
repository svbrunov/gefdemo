/*
 * Created on 13-Jun-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.tigris.gefdemo.uml;

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
import org.tigris.gef.undo.UndoManager;
import org.tigris.gefdemo.uml.model.UmlAssociation;
import org.tigris.gefdemo.uml.ui.AssociationEdgeFig;
import org.tigris.gefdemo.uml.ui.AssociationEndEdgeFig;
import org.tigris.gefdemo.uml.ui.AssociationNodeFig;

/**
 * 
 * @author Bob Tarling
 * @since 13-Jun-2004
 */
public class ModeCreateAssociationEnd extends ModeCreatePolyEdge {
    
    private static Log LOG = LogFactory.getLog(ModeCreatePolyEdge.class);
    FigNode newAssociationNodeFig;
    FigEdge oldAssociationEdgeFig;
    UmlAssociation association;
    List associationEnds;
    
    /** On mousePressed determine what port the user is dragging from.
     *  The mousePressed event is sent via ModeSelect. */
    public void mousePressed(MouseEvent me) {
        if (me.isConsumed()) {
            if (LOG.isDebugEnabled()) LOG.debug("MousePressed detected but rejected as already consumed");
            return;
        }
        
        UndoManager.getInstance().addMementoLock(this);
        
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
        
        if (underMouse instanceof AssociationEdgeFig && _npoints == 0) {
            if (LOG.isDebugEnabled()) LOG.debug("MousePressed detected on an association so replacing FigEdge with FigNode");
            oldAssociationEdgeFig = (FigEdge)underMouse;
            association = (UmlAssociation)oldAssociationEdgeFig.getOwner();
            associationEnds = association.getAssociationEnds();
            oldAssociationEdgeFig.setOwner(null);
            newAssociationNodeFig = placeTempNode(me);
            underMouse = newAssociationNodeFig;
            setSourceFigNode(newAssociationNodeFig);
            setStartPort(newAssociationNodeFig.getOwner());
            setStartPortFig(newAssociationNodeFig);
        } else {
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
        }
        
        if (_npoints == 0) {
            createFig(me);
        }
        if (LOG.isDebugEnabled()) LOG.debug("MousePressed detected and processed by ancestor - consumed");
        me.consume();
    }

    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyChar() == KeyEvent.VK_ESCAPE) { // escape
            LOG.debug("ESC pressed");
            if (newAssociationNodeFig != null) {
                newAssociationNodeFig.removeFromDiagram();
                newAssociationNodeFig = null;
                oldAssociationEdgeFig.setOwner(association);
            }
        }
        super.keyTyped(ke);
    }
    
    /**
     * This will be called when the edge is successfully connected.
     * What we do in this class is to determine if we are creating
     * an n-ary association. If so then FigNode representing the n-ary
     * association is made visible. The FigEdge representing the old
     * binary association is removed and replaced with edges representing
     * the 2 association ends of that original fig.
     */
    protected void endAttached() {
        if (newAssociationNodeFig != null) {
            newAssociationNodeFig.setVisible(true);
            oldAssociationEdgeFig.removeFromDiagram();
        
            Editor editor = Globals.curEditor();
            UmlGraphModel gm = (UmlGraphModel)editor.getGraphModel();
            gm.addEdge(associationEnds.get(0));
            gm.addEdge(associationEnds.get(1));
            editor.getSelectionManager().deselectAll();
        }
    }
    
    private FigNode placeTempNode(MouseEvent me) {
        Editor editor = Globals.curEditor();
        UmlGraphModel gm = (UmlGraphModel)editor.getGraphModel();
        GraphNodeRenderer renderer = editor.getGraphNodeRenderer();
        Layer lay = editor.getLayerManager().getActiveLayer();
        FigNode figNode = renderer.getFigNodeFor(gm, lay, association, null);
        figNode.setX(me.getX() - figNode.getWidth()/2);
        figNode.setY(me.getY() - figNode.getHeight()/2);
        figNode.setVisible(false);
        editor.add(figNode);
        editor.getSelectionManager().deselectAll();
        return figNode;
    }
    
    private void replaceNodeWithEdge(FigNode figNode) {
        System.out.println("Trying to replace assoc node with edge");
        AssociationNodeFig af = (AssociationNodeFig)figNode;
        UmlGraphModel gm = (UmlGraphModel)Globals.curEditor().getGraphModel();
        UmlAssociation association = (UmlAssociation)af.getOwner();
        Collection remainingEdges = af.getFigEdges(null);
        Iterator it = remainingEdges.iterator();
        while (it.hasNext()) {
            AssociationEndEdgeFig fig = (AssociationEndEdgeFig)it.next();
            fig.removeFromDiagram();
        }
        af.removeFromDiagram();
        gm.addEdge(association);
    }
}
