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

package org.tigris.gefdemo.classdiagram;

import java.util.Collection;

import org.apache.log4j.Logger;

import org.tigris.gef.base.Layer;
import org.tigris.gef.graph.GraphEdgeRenderer;
import org.tigris.gef.graph.GraphModel;
import org.tigris.gef.graph.GraphNodeRenderer;
import org.tigris.gef.presentation.FigEdge;
import org.tigris.gef.presentation.FigNode;
import org.tigris.gefdemo.classdiagram.model.UmlAssociationEnd;
import org.tigris.gefdemo.classdiagram.model.UmlAssociation;
import org.tigris.gefdemo.classdiagram.model.UmlClass;
import org.tigris.gefdemo.classdiagram.model.UmlInterface;
import org.tigris.gefdemo.classdiagram.ui.AssociationEndFig;
import org.tigris.gefdemo.classdiagram.ui.AssociationFig;
import org.tigris.gefdemo.classdiagram.ui.ClassFig;
import org.tigris.gefdemo.classdiagram.ui.InterfaceFig;

/** This class defines a renderer object for UML Class Diagrams. In a
 *  Class Diagram the following UML objects are displayed with the
 *  following Figs: <p>
 * <pre>
 *  UML Object      ---  Fig
 *  ---------------------------------------
 *  Class         ---  FigClass
 *  Interface       ---  FigClass (TODO?)
 *  Generalization  ---  FigGeneralization
 *  Realization     ---  FigDependency (TODO)
 *  Association     ---  FigAssociation
 *  Dependency      ---  FigDependency
 *  </pre>
 */

public class ClassDiagramRenderer
    implements GraphNodeRenderer, GraphEdgeRenderer {

    protected static Logger cat = 
        Logger.getLogger(ClassDiagramRenderer.class);

    /** Return a Fig that can be used to represent the given node */
    public FigNode getFigNodeFor(GraphModel gm, Layer lay, Object node) {
        cat.debug("getFigNodeFor node " + node.getClass().getName());
        if (node instanceof UmlClass) return new ClassFig(gm, node);
        else if (node instanceof UmlInterface) return new InterfaceFig(gm, node);
        else if (node instanceof UmlAssociation) return new AssociationFig(gm, node);
        cat.error("TODO ClassDiagramRenderer getFigNodeFor " + node);
        return null;
    }

    /** Return a Fig that can be used to represent the given edge */
    public FigEdge getFigEdgeFor(GraphModel gm, Layer lay, Object edge) {
        cat.debug("making figedge for " + edge);
        if (edge instanceof UmlAssociationEnd) {
            AssociationEndFig ascFig = new AssociationEndFig(edge, lay);
            return ascFig;
        }
        cat.debug("TODO ClassDiagramRenderer getFigEdgeFor");
        return null;
    }
} /* end class ClassDiagramRenderer */