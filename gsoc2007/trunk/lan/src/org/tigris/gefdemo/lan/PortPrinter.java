package org.tigris.gefdemo.lan;

import java.io.*;

import org.tigris.gef.graph.*;
import org.tigris.gef.graph.presentation.*;

/** A male parallel printer port. */

public class PortPrinter extends NetPort implements Serializable {

    /** Construct a new PortPrinter as a port of the given NetNode. This
     * example includes the constraint that PortPrinter's can only be
     * part of NodeLAN's. */
    
    public PortPrinter(NetNode parent) {
	super(parent);
	if (!(parent instanceof NodeLAN)) {
	    // throw an exception
	    System.out.println("PortPrinters are only to be used on NodeLAN's");
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


    /** Add the constraint that PortPrinter's can only be connected to
     * PortPrinter2's. */
    public boolean canConnectTo(GraphModel gm, Object anotherPort) {
	PortPrinter2 sp = new PortPrinter2(null);
	return (anotherPort.getClass() == sp.getClass());
    }

    // JR-TODO: public static final Class DESIRED_CLASS = new PortPrinter2(null).getClass();
    
} /* end class PortPrinter */
