package org.tigris.gefdemo.lan;

import java.io.*;

import org.tigris.gef.graph.*;
import org.tigris.gef.graph.presentation.*;

/** A female parallel printer port. */

public class PortPrinter2 extends NetPort implements Serializable {

    /** Construct a new PortPrinter2 as a port of the given NetNode. This
     * example includes the constraint that PortPrinter2's can only be
     * part of NodeLAN's. */
    public PortPrinter2(NetNode parent) {
	super(parent);
	if (!(parent instanceof NodeLAN)) {
	    // throw an exception
	    System.out.println("PortPrinter2s are only to be used on PortPrinters");
	}
    }

  protected Class defaultEdgeClass(NetPort otherPort) {
    try {
	return Class.forName("org.tigris.gefdemo.lan.EdgePrinter");
    }
    catch (java.lang.ClassNotFoundException ignore) {
	return null;
    }
  }


    /** Add the constraint that PortPrinter2's can only be connected to
     * PortPrinter's. */
    public boolean canConnectTo(GraphModel gm, Object anotherPort) {
	PortPrinter sp = new PortPrinter(null);
	return (anotherPort.getClass() == sp.getClass());
    }

    // JR-TODO: public static final Class DESIRED_CLASS = new PortPrinter(null).getClass();

}
