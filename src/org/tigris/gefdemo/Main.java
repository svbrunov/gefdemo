package org.tigris.gefdemo;

import org.tigris.gefdemo.basic.BasicApplication;
import org.tigris.gefdemo.lan.LANDemo;
import org.tigris.gefdemo.classdiagram.ClassDiagramDemo;

/** Launch all GEF demos at the same time.
    TODO: Implement a new window to give a menu of demos.
*/

public class Main {
    
    ////////////////////////////////////////////////////////////////
    // main method
    
    public static void main(String args[]) {
	// Run the LAN demo
        BasicApplication demo1 = new BasicApplication();
        LANDemo demo2 = new LANDemo();
        ClassDiagramDemo demo3 = new ClassDiagramDemo();
    }
    
} /* end class Main */

