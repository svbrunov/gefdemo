package org.tigris.gefdemo.lan;

import org.tigris.gef.base.*;
import org.tigris.gef.presentation.*;
import org.tigris.gef.graph.presentation.*;

/** A NetEdge subclass to represent ethernet cables.  Ethernet cables
 *  will be displayed with a rectiliniear FigEdge.
 */

public class EdgeEther extends NetEdge {
  /** Construct a new EdgeEther. */
  public EdgeEther() { }

  public String getId() {
    return toString();
  }

  public FigEdge makePresentation(Layer lay) {
    return new FigEdgePoly();
  }

} /* end class EdgeEther */
