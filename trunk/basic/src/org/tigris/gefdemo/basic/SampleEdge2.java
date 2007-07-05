package org.tigris.gefdemo.basic;

import org.tigris.gef.base.*;
import org.tigris.gef.presentation.*;
import org.tigris.gef.graph.presentation.*;

/** A sample NetEdge subclass for use in the Example application. */

public class SampleEdge2 extends NetEdge {
  /** Construct a new SampleEdge2. */
  public SampleEdge2() { } /* needs-more-work */

  public String getId() {
    return toString();
  }

  public FigEdge makePresentation(Layer lay) {
    return new FigEdgeRectiline();
  }
} /* end class SampleEdge2 */
