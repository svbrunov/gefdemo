// $Id$
// Copyright (c) 1996-99 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies.  This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason.  IN NO EVENT SHALL THE
// UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
// SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
// ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
// THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
// PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
// UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

// File: ClassDiagramRenderer.java
// Classes: ClassDiagramRenderer
// Original jrobbins@ics.uci.edu
// $Id$

package org.tigris.gefdemo.bert;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.tigris.gef.base.Globals;
import org.tigris.gef.base.Layer;
import org.tigris.gef.graph.GraphEdgeRenderer;
import org.tigris.gef.graph.GraphModel;
import org.tigris.gef.graph.GraphNodeRenderer;
import org.tigris.gef.presentation.Fig;
import org.tigris.gef.presentation.FigEdge;
import org.tigris.gef.presentation.FigNode;
import org.tigris.gefdemo.bert.model.Attribute;
import org.tigris.gefdemo.bert.model.Table;
import org.tigris.gefdemo.bert.model.Relationship;
import org.tigris.gefdemo.bert.ui.TableNodeFig;
import org.tigris.gefdemo.bert.ui.RelationshipEdgeFig;
import org.tigris.gefdemo.bert.ui.ModelElementEdgeFig;

/**
 * This class defines a renderer object for UML Class Diagrams.
 */

public class FigFactory implements GraphNodeRenderer,
        GraphEdgeRenderer {

    private static final long serialVersionUID = 6154525960061172622L;
    
    private static Log LOG = LogFactory.getLog(FigFactory.class);

    /** Return a Fig that can be used to represent the given node */
    public FigNode getFigNodeFor(GraphModel gm, Layer lay, Object node,
            Map styleAttributes) {
        return getFigNodeFor(node, 0, 0, styleAttributes);
    }

    /** Return a Fig that can be used to represent the given node */
    public FigNode getFigNodeFor(Object node, int x, int y, Map styleAttributes) {
        LOG.debug("getFigNodeFor node " + node);
        if (node instanceof Table) {
            return new TableNodeFig(node);
        }
        LOG.error("Unable to create FigNode for " + node);
        return null;
    }

    /** Return a Fig that can be used to represent the given edge */
    public FigEdge getFigEdgeFor(Object edge, Map styleAttributes) {
        Layer lay = Globals.curEditor().getLayerManager().getActiveLayer();
        return getFigEdgeFor(null, lay, edge, styleAttributes);
    }

    /** Return a Fig that can be used to represent the given edge */
    public FigEdge getFigEdgeFor(GraphModel gm, Layer lay, Object edge,
            Map styleAttributes) {
        LOG.debug("making figedge for " + edge);
        ModelElementEdgeFig newEdgeFig = null;
        Object source = null;
        Object dest = null;

        if (edge instanceof Relationship) {
            Relationship dep = (Relationship) edge;
            newEdgeFig = new RelationshipEdgeFig(edge, lay);

            source = dep.getClient();
            dest = dep.getSupplier();
        }

        if (newEdgeFig != null) {
            LOG.info("Got a new edge " + newEdgeFig.getClass().getName());
            LOG.info("The source attribute is " + source.getClass().getName());
            LOG.info("The dest attribute is " + dest.getClass().getName());
            
            Attribute sourceAttribute = (Attribute) source;
            Table sourceTable = sourceAttribute.getTable();
            
            Attribute destAttribute = (Attribute) dest;
            Table destTable = destAttribute.getTable();
            
            LOG.info("The source table is " + sourceTable.getClass().getName());
            LOG.info("The dest table is " + destTable.getClass().getName());
            
            LOG.info("There are " + lay.getContentsNoEdges().size() + " nodes");
            List figNodes = lay.getContentsNoEdges();
            for (Iterator it = figNodes.iterator(); it.hasNext(); ) {
        	FigNode fn = (FigNode) it.next();
                LOG.info(fn.getClass().getName() + " has owner " + fn.getOwner());
            }
            
            FigNode sourceFN = (FigNode) lay.presentationFor(sourceTable);
            assert sourceFN != null : "There is no FigNode found for " + sourceTable.getClass().getName();
            
            FigNode destFN = (FigNode) lay.presentationFor(destTable);
            assert destFN != null : "There is no FigNode found for " + destTable.getClass().getName();
            
            Fig sourcePF = sourceFN.getPortFig(sourceAttribute);
            Fig destPF = destFN.getPortFig(destAttribute);
            
            newEdgeFig.setSourcePortFig(sourcePF);
            newEdgeFig.setSourceFigNode(sourceFN);
            newEdgeFig.setDestPortFig(destPF);
            newEdgeFig.setDestFigNode(destFN);
            return newEdgeFig;
        }
        LOG.error("Unable to create FigEdge for " + edge);
        return null;
    }
} /* end class ClassDiagramRenderer */
