package org.tigris.gefdemo.basic.swt;

import org.tigris.gef.graph.presentation.GraphFrame;

/** A simple example of the minimum code needed to build an
 *  application using GEF. */

public class BasicApplication {

    private GraphFrame graphFrame;

    public BasicApplication() {
	// Create a SWT instance of GraphFrame
    }
    
    public static void main(String args[]) {
    	BasicApplication demo = new BasicApplication();
    }
}