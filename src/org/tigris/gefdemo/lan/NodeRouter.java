package org.tigris.gefdemo.lan;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

import org.tigris.gef.base.*;
import org.tigris.gef.presentation.*;

/** An example subclass of NetNode for use in the demos.
 *
 * @see BasicApplication */

public class NodeRouter extends NodeLAN implements Serializable {

  public FigNode makePresentation(Layer lay) {
	//This is the code necessary to load the picture into the node so that you can paint onto the canvas
    ImageIcon routerIcon = org.tigris.gef.util.ResourceLoader.lookupIconResource("NodeRouter");
    Image routerImage = routerIcon.getImage();

    FigImage obj1 = new FigImage(-15, -15, routerImage);
    Fig obj2 = new FigRect( -5, -24, 10, 10, Color.blue, Color.white);
    Fig obj3 = new FigRect( -5,  14, 10, 10, Color.blue, Color.white);
    Fig obj4 = new FigRect(-24,  -5, 10, 10, Color.blue, Color.white);
    Fig obj5 = new FigRect( 14,  -5, 10, 10, Color.blue, Color.white);
    Collection temp_list = new Vector();
    temp_list.add(obj1);
    temp_list.add(obj2);
    temp_list.add(obj3);
    temp_list.add(obj4);
    temp_list.add(obj5);
    FigNode fn = new FigNode(this, temp_list);
    fn.bindPort(ether1, obj2);
    fn.bindPort(ether2, obj3);
    fn.bindPort(ether3, obj4);
    fn.bindPort(ether4, obj5);
    fn.setBlinkPorts(true);
    return fn;
  }
} /* end class NodeRouter */
