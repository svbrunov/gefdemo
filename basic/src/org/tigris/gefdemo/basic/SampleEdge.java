package org.tigris.gefdemo.basic;

import java.awt.*;

import org.tigris.gef.base.*;
import org.tigris.gef.presentation.*;
import org.tigris.gef.graph.presentation.*;

/** A sample NetEdge subclass for use in the demos.  This edge is
 *  drawn with an arrowhead. */

public class SampleEdge extends NetEdge {
  /** Construct a new SampleEdge. */
  public SampleEdge() { } /* needs-more-work */

  public String getId() {
    return toString();
  }

  public FigEdge makePresentation(Layer lay) {
    //FigEdge foo = new FigEdgePoly();
    FigEdge foo = new FigEdgeLine();
    foo.setSourceArrowHead(new ArrowHeadTriangle());


    FigText mid = new FigText(10, 30, 90, 20);
    mid.setText("Midpoint");
    mid.setTextColor(Color.black);
    mid.setTextFilled(false);
    mid.setFilled(false);
    mid.setLineWidth(0);
    foo.addPathItem(mid, new PathConvPercent(foo, 50, 10));

    FigText start = new FigText(10, 30, 90, 20);
    start.setText("Start");
    start.setTextColor(Color.black);
    start.setTextFilled(false);
    start.setFilled(false);
    start.setLineWidth(0);
    foo.addPathItem(start,
		    new PathConvPercentPlusConst(foo, 0, 10, 10));

    return foo;
  }
} /* end class SampleEdge */
