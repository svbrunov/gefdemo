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

public class NodeLaptop extends NodeLAN implements Serializable {

  public FigNode makePresentation(Layer lay) {
	//This is the code necessary to load the picture into the node so that you can paint onto the canvas
    ImageIcon laptopIcon = org.tigris.gef.util.ResourceLoader.lookupIconResource("NodeLaptop");
    Image laptopImage = laptopIcon.getImage();

    FigImage obj1 = new FigImage(-15, -15, laptopImage);
    Fig obj4 = new FigRect(16,  -5, 10, 10, Color.blue, Color.white);
    Fig obj2 = new FigCircle( -5, -24, 10, 10, Color.blue, Color.white);
    Vector temp_list = new Vector();
    temp_list.addElement(obj1);
    temp_list.addElement(obj4);
    temp_list.addElement(obj2);
    FigNode fn = new FigNode(this, temp_list);
    fn.bindPort(ether1, obj4);
    fn.bindPort(print1, obj2);
    fn.setBlinkPorts(true);
    return fn;
  }
} /* end class NodeLaptop */
