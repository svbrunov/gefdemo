package org.tigris.gefdemo.lan;

import java.awt.*;
import java.util.*;
import javax.swing.Action;

import org.tigris.gef.base.*;
import org.tigris.gef.ui.*;

/** A class to define a custom palette for LAN equipment.
 */

public class PaletteLAN extends ToolBar {

    /** Construct a new palette of LAN nodes. */
    public PaletteLAN() {
	super();
	defineButtons();
    }
    
    /** Add toolbar buttons to make LAN nodes. */
    public void defineButtons() {
	add(new CmdCreateNode(org.tigris.gefdemo.lan.NodeComputer.class, "NodeComputer"));
	add(new CmdCreateNode(org.tigris.gefdemo.lan.NodePrinter.class, "NodePrinter"));
	add(new CmdCreateNode(org.tigris.gefdemo.lan.NodeLaptop.class, "NodeLaptop"));
    addSeparator();
	add(new CmdCreateNode(org.tigris.gefdemo.lan.NodeHub.class, "NodeHub"));
	add(new CmdCreateNode(org.tigris.gefdemo.lan.NodeServer.class, "NodeServer"));
	add(new CmdCreateNode(org.tigris.gefdemo.lan.NodeRouter.class, "NodeRouter"));
    }

}
