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

public class NodeHub extends NodeLAN implements Serializable {

  public FigNode makePresentation(Layer lay) {
	//This is the code necessary to load the picture into the node so that you can paint onto the canvas
    ImageIcon hubIcon = org.tigris.gef.util.ResourceLoader.lookupIconResource("NodeHub");
    Image hubImage = hubIcon.getImage();

    //Fig(y coordinate, x coordinate, width, height)
    FigImage obj1 = new FigImage(-35, -5, hubImage);
    Fig obj2 = new FigRect( -50, -5, 10, 10, Color.blue, Color.white);
    Fig obj3 = new FigRect( -30,  -5, 10, 10, Color.blue, Color.white);
    Fig obj4 = new FigRect(-10,  -5, 10, 10, Color.blue, Color.white);
    Fig obj5 = new FigRect( 10,  -5, 10, 10, Color.blue, Color.white);
    Vector temp_list = new Vector();
    temp_list.addElement(obj1);
    temp_list.addElement(obj2);
    temp_list.addElement(obj3);
    temp_list.addElement(obj4);
    temp_list.addElement(obj5);
    FigNode fn = new FigNode(this, temp_list);
    fn.bindPort(ether1, obj2);
    fn.bindPort(ether2, obj3);
    fn.bindPort(ether3, obj4);
    fn.bindPort(ether4, obj5);
    fn.setBlinkPorts(true);
    return fn;
  }
}
