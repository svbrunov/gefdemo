package org.tigris.gefdemo;


/** Launch all GEF demos at the same time.
    TODO: Implement a new window to give a menu of demos.
*/

public class Main {
    
    ////////////////////////////////////////////////////////////////
    // main method
    
    public static void main(String args[]) {
	// Run the LAN demo
	org.tigris.gefdemo.lan.Main demo1 = new org.tigris.gefdemo.lan.Main();
    }
    
} /* end class Main */

