package org.tigris.gefdemo.basic;

import java.awt.*;
import java.io.*;
import java.util.*;

import org.tigris.gef.base.*;
import org.tigris.gef.presentation.*;

/** An example subclass of NetNode for use in the demos.
 *
 * @see BasicApplication */

public class SampleNode2 extends SampleNode implements Serializable {

  public FigNode makePresentation(Layer lay) {
    Fig obj1 = new FigRect(-25, -25, 50, 50, Color.black, Color.white);
    Fig obj2 = new FigRect(-13, -13, 26, 26, null, Color.pink);
    Fig obj3 = new FigCircle( -5, -24, 10, 10, Color.blue, Color.white);
    Fig obj4 = new FigCircle( -5,  14, 10, 10, Color.blue, Color.white);
    Fig obj5 = new FigRect(-24,  -5, 10, 10, Color.blue, Color.white);
    Fig obj6 = new FigRect( 14,  -5, 10, 10, Color.blue, Color.white);
    Vector temp_list = new Vector();
    temp_list.addElement(obj1);
    temp_list.addElement(obj2);
    temp_list.addElement(obj3);
    temp_list.addElement(obj4);
    temp_list.addElement(obj5);
    temp_list.addElement(obj6);
    FigNode fn = new FigNode(this, temp_list);
    fn.bindPort(north, obj3);
    fn.bindPort(south, obj4);
    fn.bindPort(east, obj5);
    fn.bindPort(west, obj6);
    fn.setBlinkPorts(true);
    return fn;
  }
} /* end class SampleNode2 */
