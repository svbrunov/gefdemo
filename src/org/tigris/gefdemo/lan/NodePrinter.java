package org.tigris.gefdemo.lan;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

import org.tigris.gef.base.*;
import org.tigris.gef.presentation.*;
import org.tigris.gef.util.*;

/** An example subclass of NetNode for use in the demos.
 *
 * @see BasicApplication */

public class NodePrinter extends NodeLAN implements Serializable {

  public FigNode makePresentation(Layer lay) {
	//This is the code necessary to load the picture into the node so that you can paint onto the canvas
    ImageIcon printerIcon = org.tigris.gef.util.ResourceLoader.lookupIconResource("NodePrinter");
    Image printerImage = printerIcon.getImage();

    FigImage obj1 = new FigImage(-15, -15, printerImage);
    Fig obj4 = new FigCircle(-20,  0, 10, 10, Color.blue, Color.blue);
    Vector temp_list = new Vector();
    temp_list.addElement(obj1);
    temp_list.addElement(obj4);
    FigNode fn = new FigNode(this, temp_list);
    fn.bindPort(print2, obj4);
    fn.setBlinkPorts(true);
    return fn;
  }
} /* end class NodePrinter */
