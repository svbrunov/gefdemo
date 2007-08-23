package org.tigris.gefdemo.basic;

import java.util.*;
import java.io.*;

import org.tigris.gef.base.*;
import org.tigris.gef.base.KeyEvent;
import org.tigris.gef.base.MouseEvent;
import org.tigris.gef.presentation.*;
import org.tigris.gef.graph.presentation.*;

/** An example subclass of NetNode for use in the BasicApplication
 * application. */
public class SampleNode extends NetNode implements Serializable {

  /**
	 * 
	 */
	private static final long serialVersionUID = -2408765935522642669L;
public SamplePort north, east, west, south;
  protected int _number;
  
   /** Initialize a new SampleNode from the given default node and
    *  application specific model. <p>
    *
    *  Needs-More-Work: for now we construct the FigNode
    *  programatically, but eventually we will store it in a class
    *  variable and just refer to it, or copy it(?). That way the user
    *  can edit the FigNode(s) stored in the class variable and
    *  have those changes shown for all existing nodes, or for all
    *  future nodes. Maybe I should think about doing virtual copies?<p>
    */

  public void initialize(Hashtable args) {
    addPort(east = new SamplePort2(this));
    addPort(west = new SamplePort2(this));
    addPort(north = new SamplePort(this));
    addPort(south = new SamplePort(this));
    _number = _NextNumber++;
   }

  static int _NextNumber = 1;

  public int getNumber() { return _number; }
  public String getId() { return "" + _number; }
  
  public FigNode makePresentation(Layer lay) {
    FigSampleNode fn = new FigSampleNode();
    fn.setOwner(this);
    return fn;
  }

  /** Sample event handler: prints a message to the console. */
  public void mouseEntered(MouseEvent e) {
    //    System.out.println("sample node got mouseEnter");
  }

  /** Sample event handler: prints a message to the console. */
  public void mouseExited(MouseEvent e) {
    //    System.out.println("sample node got mouseExit");
  }

  /** Sample event handler: prints a message to the console. */
  public void mouseReleased(MouseEvent e) {
    //    System.out.println("sample node got mouseUp");
  }

  /** Sample event handler: prints a message to the console. */
  public void mousePressed(MouseEvent e) {
    //    System.out.println("sample node got mouseDown");
  }

  /** Sample event handler: prints a message to the console. */
  public void mouseClicked(MouseEvent e) {
    //    System.out.println("sample node got mouseDown");
  }

  /** Sample event handler: prints a message to the console. */
  public void mouseDragged(MouseEvent e) {
    //    System.out.println("sample node got mouseDrag");
  }

  /** Sample event handler: prints a message to the console. */
  public void mouseMoved(MouseEvent e) {
    //    System.out.println("sample node got mouseMove");
  }

  /** Sample event handler: prints a message to the console. */
  public void keyTyped(KeyEvent e) {
    //    System.out.println("sample node got keyUp");
  }

  /** Sample event handler: prints a message to the console. */
  public void keyReleased(KeyEvent e) {
    //    System.out.println("sample node got keyUp");
  }

  /** Sample event handler: prints a message to the console. */
  public void keyPressed(KeyEvent e) {
    //    System.out.println("sample node got keyDown");
  }
} /* end class SampleNode */
