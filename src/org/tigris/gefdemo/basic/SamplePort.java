package org.tigris.gefdemo.basic;

import java.io.*;

import org.tigris.gef.graph.*;
import org.tigris.gef.graph.presentation.*;


/** An example subclass of NetPort for the BasicApplication application.
 * As part of the example I constrain the ports to only be on SampleNodes
 * and only connect to SamplePorts. */

public class SamplePort extends NetPort implements Serializable{

   /** Construct a new SamplePort as a port of the given NetNode. This
    * example includes the constraint that SamplePort's can only be
    * part of SampleNode's. */

  public SamplePort(NetNode parent) {
    super(parent);
    if (!(parent instanceof SampleNode)) {
      // throw an exception
      System.out.println("SamplePorts are only to be used on SampleNodes");
    }
  }
  
  protected Class defaultEdgeClass(NetPort otherPort) {
    try { return Class.forName("org.tigris.gefdemo.basic.SampleEdge"); }
    catch (java.lang.ClassNotFoundException ignore) { return null; }
  }

  /** Add the constraint that SamplePort's can only be connected to
   * other ports of the same type. */
  public boolean canConnectTo(GraphModel gm, Object anotherPort) {
    return (super.canConnectTo(gm, anotherPort)) &&
      (anotherPort.getClass() == this.getClass());
    // needs-more-work: should work with subclasses too. This is
    // really a java.lang.Class method that is missing: isSubclass()
  }
} /* end class SamplePort */
