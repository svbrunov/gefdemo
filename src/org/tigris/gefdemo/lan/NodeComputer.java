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

public class NodeComputer extends NodeLAN implements Serializable {

  public FigNode makePresentation(Layer lay) {
	//This is the code necessary to load the picture into the node so that you can paint onto the canvas
    ImageIcon computerIcon = org.tigris.gef.util.ResourceLoader.lookupIconResource("NodeComputer");
    Image computerImage = computerIcon.getImage();

    FigImage obj1 = new FigImage(-15, -15, computerImage);
    Fig obj4 = new FigRect(13,  -5, 10, 10, Color.blue, Color.white);
    Fig obj3 = new FigCircle(-22,  -5, 10, 10, Color.blue, Color.white);
    Vector temp_list = new Vector();
    temp_list.addElement(obj1);
    temp_list.addElement(obj3);
    temp_list.addElement(obj4);
    FigNode fn = new FigNode(this, temp_list);
    fn.bindPort(print1, obj3);
    fn.bindPort(ether1, obj4);
    fn.setBlinkPorts(true);
    return fn;
  }
}
