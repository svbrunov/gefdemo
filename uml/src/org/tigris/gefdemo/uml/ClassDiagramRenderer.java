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

package org.tigris.gefdemo.uml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.tigris.gef.base.Layer;
import org.tigris.gef.graph.GraphEdgeRenderer;
import org.tigris.gef.graph.GraphModel;
import org.tigris.gef.graph.GraphNodeRenderer;
import org.tigris.gef.presentation.FigEdge;
import org.tigris.gef.presentation.FigNode;
import org.tigris.gefdemo.uml.model.UmlAssociation;
import org.tigris.gefdemo.uml.model.UmlAssociationClass;
import org.tigris.gefdemo.uml.model.UmlAssociationEnd;
import org.tigris.gefdemo.uml.model.UmlClass;
import org.tigris.gefdemo.uml.model.UmlClassifier;
import org.tigris.gefdemo.uml.model.UmlDependency;
import org.tigris.gefdemo.uml.model.UmlInterface;
import org.tigris.gefdemo.uml.ui.AssociationEdgeFig;
import org.tigris.gefdemo.uml.ui.AssociationEndEdgeFig;
import org.tigris.gefdemo.uml.ui.AssociationNodeFig;
import org.tigris.gefdemo.uml.ui.ClassNodeFig;
import org.tigris.gefdemo.uml.ui.DependencyEdgeFig;
import org.tigris.gefdemo.uml.ui.FigAssociationClass;
import org.tigris.gefdemo.uml.ui.InterfaceNodeFig;

/** 
 * This class defines a renderer object for UML Class Diagrams.
 */

public class ClassDiagramRenderer
    implements GraphNodeRenderer, GraphEdgeRenderer {

    private static Log LOG = LogFactory.getLog(ClassDiagramRenderer.class);

    /** Return a Fig that can be used to represent the given node */
    public FigNode getFigNodeFor(GraphModel gm, Layer lay, Object node) {
        LOG.debug("getFigNodeFor node " + node);
        if (node instanceof UmlClass) return new ClassNodeFig(node);
        else if (node instanceof UmlInterface) return new InterfaceNodeFig(node);
        else if (node instanceof UmlAssociation) return new AssociationNodeFig(node);
        LOG.error("Unable to create FigNode for " + node);
        return null;
    }

    /** Return a Fig that can be used to represent the given edge */
    public FigEdge getFigEdgeFor(GraphModel gm, Layer lay, Object edge) {
        LOG.debug("making figedge for " + edge);
        if (edge instanceof UmlAssociationEnd) {
            UmlAssociationEnd associationEnd = (UmlAssociationEnd) edge;
            AssociationEndEdgeFig ascEndFig = new AssociationEndEdgeFig(edge, lay);
            UmlAssociation association = associationEnd.getAssociation();
            UmlClassifier classifier = associationEnd.getClassifier();

            FigNode associationFN = (FigNode) lay.presentationFor(association);
            FigNode classifierFN = (FigNode) lay.presentationFor(classifier);

            ascEndFig.setSourcePortFig(associationFN);
            ascEndFig.setSourceFigNode(associationFN);
            ascEndFig.setDestPortFig(classifierFN);
            ascEndFig.setDestFigNode(classifierFN);
            return ascEndFig;
        }
        if (edge instanceof UmlDependency) {
            UmlDependency dep = (UmlDependency) edge;
            DependencyEdgeFig depFig = new DependencyEdgeFig(edge, lay);

            Object supplier = dep.getClient();
            Object client = dep.getSupplier();

            FigNode supFN = (FigNode) lay.presentationFor(supplier);
            FigNode cliFN = (FigNode) lay.presentationFor(client);

            depFig.setSourcePortFig(cliFN);
            depFig.setSourceFigNode(cliFN);
            depFig.setDestPortFig(supFN);
            depFig.setDestFigNode(supFN);
            return depFig;
        }
        if (edge instanceof UmlAssociationClass) {
            UmlAssociationClass association = (UmlAssociationClass)edge;
            FigAssociationClass associationClassFig = new FigAssociationClass(edge, lay);

            UmlAssociationEnd sourceEnd = (UmlAssociationEnd)association.getAssociationEnds().get(0);
            Object source = sourceEnd.getClassifier();
            UmlAssociationEnd targetEnd = (UmlAssociationEnd)association.getAssociationEnds().get(1);
            Object target = targetEnd.getClassifier();

            FigNode sourceFN = (FigNode) lay.presentationFor(source);
            FigNode targetFN = (FigNode) lay.presentationFor(target);

            associationClassFig.setSourcePortFig(sourceFN);
            associationClassFig.setSourceFigNode(sourceFN);
            associationClassFig.setDestPortFig(targetFN);
            associationClassFig.setDestFigNode(targetFN);
            System.out.println("Creating an AssociationFig between " + sourceFN + " and " + targetFN);
            return associationClassFig;
        }
        if (edge instanceof UmlAssociation) {
            UmlAssociation association = (UmlAssociation)edge;
            AssociationEdgeFig associationFig = new AssociationEdgeFig(edge, lay);

            UmlAssociationEnd sourceEnd = (UmlAssociationEnd)association.getAssociationEnds().get(0);
            Object source = sourceEnd.getClassifier();
            UmlAssociationEnd targetEnd = (UmlAssociationEnd)association.getAssociationEnds().get(1);
            Object target = targetEnd.getClassifier();

            FigNode sourceFN = (FigNode) lay.presentationFor(source);
            FigNode targetFN = (FigNode) lay.presentationFor(target);

            associationFig.setSourcePortFig(sourceFN);
            associationFig.setSourceFigNode(sourceFN);
            associationFig.setDestPortFig(targetFN);
            associationFig.setDestFigNode(targetFN);
            System.out.println("Creating an AssociationFig between " + sourceFN + " and " + targetFN);
            return associationFig;
        }
        LOG.error("Unable to create FigEdge for " + edge);
        return null;
    }
} /* end class ClassDiagramRenderer */