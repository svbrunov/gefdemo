package org.tigris.gefdemo.classdiagram.model;

import java.io.*;

import org.apache.log4j.Logger;
import org.tigris.gef.graph.*;
import org.tigris.gef.graph.presentation.*;

/** A port for ethernet connections only. */

public class ClassifierPort extends NetPort implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(ClassifierPort.class);

    /** Construct a new DependsPort as a port of the given NetNode. This
     * example includes the constraint that PortEther's can only be
     * part of NodeLAN's. */
    
    public ClassifierPort(NetNode parent) {
	super(parent);
	if (!(parent instanceof MClass)) {
	    // throw an exception
	    LOG.error("DependencyPorts are only to be used on TargetNodes");
	}
    }
    
    public boolean isDragConnectable() {
        return false;
    }
    
    protected Class defaultEdgeClass(NetPort otherPort) {
        if (LOG.isDebugEnabled()) LOG.debug("Getting default edge for a DependsPort");
        return null;
    }
}
