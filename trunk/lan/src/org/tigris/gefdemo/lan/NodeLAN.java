package org.tigris.gefdemo.lan;

import java.util.*;
import java.io.*;

import org.tigris.gef.graph.presentation.*;

/** An example subclass of NetNode for use in the BasicApplication
 * application. */
abstract public class NodeLAN extends NetNode implements Serializable {

    protected PortEther ether1, ether2, ether3, ether4;
    protected PortPrinter print1;
    protected PortPrinter2 print2;
    private int _number;

  //Added for tests
  private String name;
  private String type;
  private String status;
  private String url;

  public void setName( String s ){
      name = s;
  }
  public String getName(){
      return name;
  }

  public void setType( String s ){
      type = s;
  }
  public String getType(){
      return type;
  }

  public void setStatus( String s ){
      status = s;
  }
  public String getStatus(){
      return status;
  }

  public void setURL( String s ){
      url = s;
  }
  public String getURL(){
      return url;
  }



   /** Initialize a new NodeLAN from the given hashtable of arguments.
    */

  public void initialize(Hashtable args) {
    addPort(ether1 = new PortEther(this));
    addPort(ether2 = new PortEther(this));
    addPort(ether3 = new PortEther(this));
    addPort(ether4 = new PortEther(this));
    addPort(print1 = new PortPrinter(this));
    addPort(print2 = new PortPrinter2(this));
    _number = _NextNumber++;
   }

  static int _NextNumber = 1;

  public int getNumber() { return _number; }
  public String getId() { return "" + _number; }


} /* end class NodeLAN */
