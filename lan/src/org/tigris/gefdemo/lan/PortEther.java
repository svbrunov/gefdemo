package org.tigris.gefdemo.lan;

import java.io.*;

import org.tigris.gef.graph.*;
import org.tigris.gef.graph.presentation.*;

/** A port for ethernet connections only. */

public class PortEther extends NetPort implements Serializable {

    /** Construct a new PortEther as a port of the given NetNode. This
     * example includes the constraint that PortEther's can only be
     * part of NodeLAN's. */
    
    public PortEther(NetNode parent) {
	super(parent);
	if (!(parent instanceof NodeLAN)) {
	    // throw an exception
	}
    }
    
    protected Class defaultEdgeClass(NetPort otherPort) {
	try {
	    return Class.forName("org.tigris.gefdemo.lan.EdgeEther");
	}
	catch (java.lang.ClassNotFoundException ignore) {
	    return null;
	}
    }


    /** Add the constraint that PortEther's can only be connected to
     * other ports of the same type. */
    public boolean canConnectTo(GraphModel gm, Object anotherPort) {
	return (super.canConnectTo(gm, anotherPort)) &&
	    (anotherPort.getClass() == this.getClass());
    }

} /* end class PortEther */
