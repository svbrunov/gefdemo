package org.tigris.gefdemo.lan;

import java.awt.*;

import org.tigris.gef.base.*;
import org.tigris.gef.presentation.*;
import org.tigris.gef.graph.presentation.*;

/** A NetEdge subclass to represent printer (parallel) cables.  */

public class EdgePrinter extends NetEdge {
  /** Construct a new EdgePrinter. */
  public EdgePrinter() { } 

  public String getId() {
    return toString();
  }

  public FigEdge makePresentation(Layer lay) {
    return new FigEdgeLineDotted();
  }

} /* end class EdgePrinter */
