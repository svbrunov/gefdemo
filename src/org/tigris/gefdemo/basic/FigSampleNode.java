package org.tigris.gefdemo.basic;

import java.awt.*;
import java.util.*;

import org.tigris.gef.presentation.*;

public class FigSampleNode extends FigNode {
  Fig obj1, obj2, obj3, obj4, obj5, obj6;
  FigText obj7;

  public FigSampleNode() {
	super();
	obj1 = new FigRect(-25, -25, 50, 50, Color.black, Color.white);
	obj2 = new FigCircle(-20, -20, 40, 40, Color.red, null);
	obj3 = new FigCircle( -5, -30, 10, 10, Color.black, Color.blue);
	obj4 = new FigCircle( -5,  20, 10, 10, Color.black, Color.blue);
	obj5 = new FigRect(-30,  -5, 10, 10, Color.black, Color.green);
	obj6 = new FigRect( 20,  -5, 10, 10, Color.black, Color.green);
	obj7 = new FigText( -10,  -10, 20, 20);
	obj7.setLineWidth(0);
	obj7.setJustification(FigText.JUSTIFY_CENTER);

	addFig(obj1);
	addFig(obj2);
	addFig(obj3);
	addFig(obj4);
	addFig(obj5);
	addFig(obj6);
	addFig(obj7);

  }    
public String getPrivateData() {
	return "text=\"" + obj7.getText() + "\"";
}
  public void setOwner(Object own) {
	super.setOwner(own);
	if (!(own instanceof SampleNode)) return;
	SampleNode node = (SampleNode) own;
	obj7.setText(""+ node.getNumber());
	bindPort(node.north, obj3);
	bindPort(node.south, obj4);
	bindPort(node.east, obj5);
	bindPort(node.west, obj6);
  }  
public void setPrivateData(String data) {
	StringTokenizer tokenizer = new StringTokenizer(data,"=\"' ");
	
	while (tokenizer.hasMoreTokens()) {
		String tok = tokenizer.nextToken();
		if (tok.equals("text")) {
			String s = tokenizer.nextToken();
			obj7.setText( s );
		}
		else {
			/* Unknown value */
		}
	}
}
} /* end class FigSampleNode */
