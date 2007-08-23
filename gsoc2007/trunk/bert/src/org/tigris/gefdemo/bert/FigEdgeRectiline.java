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



// File: FigEdgeRectiline.java
// Classes: FigEdgeRectiline
// Original Author: jrobbins@ics.uci.edu
// $Id$

package org.tigris.gefdemo.bert;

import java.awt.*;

import org.tigris.gef.presentation.Fig;
import org.tigris.gef.presentation.FigEdge;
import org.tigris.gef.presentation.FigPoly;

/**
 * A Fig that paints edges between ports. This version
 * automatically routes a rectilinear edge. The routing is not very
 * good. It avoids the source and sink nodes and no other nodes. It is
 * basically case-analysis, and some of the cases are wrong or
 * missing. Anyway, the user can edit the edge by dragging
 * handles. The 0th and last handles are fixed in position so that
 * they stay connected to ports. If the user drags a handle next to a
 * fixed handle, a new vertex is automatically inserted.
 *
 * @see FigPoly
 */

public class FigEdgeRectiline extends FigEdge {

    /** Instanciate a FigPoly with its rectilinear flag set. By default
     *  the FigPoly is black and the FigEdge has now ArrowHeads. */
    protected Fig makeEdgeFig() {
        FigPoly res = new FigPoly(Color.black);
        res.setRectilinear(true);
        res.setFixedHandles(1);
        res.setFilled(false);
        return res;
    }


  ////////////////////////////////////////////////////////////////
  // routing methods

    /** Find the route that the edge should follow.  Basically case
     *  analysis to route around source and destination nodes.
     *  Needs-More-Work: A better algorithm would really be useful.
     *  Needs-More-Work: Sometimes the edge can get non-rectilinear. */
    public void computeRouteImpl() {
        layoutEdge();
        FigPoly p = ((FigPoly) getFig());
    
        Point srcPt, dstPt;
        
        Fig sourcePortFig = getSourcePortFig();
        Fig destPortFig = getDestPortFig();
        
        if (_useNearest) {
            srcPt = sourcePortFig.connectionPoint(p.getPoint(1));
            dstPt = destPortFig.connectionPoint(p.getPoint(p.getNumPoints()-2));
        }
        else {
            srcPt = sourcePortFig.getCenter();
            dstPt = destPortFig.getCenter();
        }
        
        p.setEndPoints(srcPt, dstPt);
        calcBounds();
    } /* end computeRoute */

    /**
     * Internal function to actually compute the layout of the line if
     * it has never been done on that line before.
     */
    protected void layoutEdge() {
        int npoints = 4;
        int xpoints[] = new int[4];
        int ypoints[] = new int[4];
        
        Fig sourcePortFig = getSourcePortFig();
        Fig destPortFig = getDestPortFig();
        
        Point srcPt = sourcePortFig.getCenter();
        Point dstPt = destPortFig.getCenter();
        
        if (_useNearest) {
            srcPt = sourcePortFig.connectionPoint(dstPt);
            dstPt = destPortFig.connectionPoint(srcPt);
            srcPt = sourcePortFig.connectionPoint(dstPt);
            dstPt = destPortFig.connectionPoint(srcPt);
        }
        
        xpoints[0] = srcPt.x;
        ypoints[0] = srcPt.y;
        xpoints[1] = (srcPt.x + dstPt.x) / 2;
        ypoints[1] = srcPt.y;
        xpoints[2] = xpoints[1];
        ypoints[2] = dstPt.y;
        xpoints[3] = dstPt.x;
        ypoints[3] = dstPt.y;
        
        Polygon routePoly = new Polygon(xpoints, ypoints, npoints);
        ((FigPoly)getFig()).setPolygon(routePoly);
    }
} /* end class FigEdgeRectiline */
