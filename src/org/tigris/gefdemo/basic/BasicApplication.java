package org.tigris.gefdemo.basic;

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

import org.tigris.gef.base.*;
import org.tigris.gef.util.*;
import org.tigris.gef.graph.presentation.*;

/** A simple example of the minimum code needed to build an
 *  application using GEF. */

public class BasicApplication {

  ////////////////////////////////////////////////////////////////
  // instance variables

  protected JGraphFrame _jgf;

  ////////////////////////////////////////////////////////////////
  // constructors

  public BasicApplication() {
	  // init localizer and resourceloader
	  Localizer.addResource("GefBase","org.tigris.gef.base.BaseResourceBundle");
	  Localizer.addResource("GefPres","org.tigris.gef.presentation.PresentationResourceBundle");
	  Localizer.addLocale(Locale.getDefault());
	  Localizer.switchCurrentLocale(Locale.getDefault());
	  ResourceLoader.addResourceExtension("gif");
	  ResourceLoader.addResourceLocation("/org/tigris/gef/Images");
    _jgf = new JGraphFrame();
    _jgf.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent event) {
          _jgf.dispose();
        }
        public void windowClosed(WindowEvent event) {
          System.exit(0);
        }
      });

    _jgf.setToolBar(new SamplePalette()); //needs-more-work

    // make the delete key remove elements from the underlying GraphModel
    _jgf.getGraph().bindKey(new CmdDispose(), KeyEvent.VK_DELETE, 0);

    _jgf.setBounds(10, 10, 300, 200);
    _jgf.setVisible(true);
    LayerManager lm =  _jgf.getGraph().getEditor().getLayerManager();
    LayerPerspective lay = (LayerPerspective) lm.getActiveLayer();
    lay.addNodeTypeRegion(SampleNode.class, new Rectangle(10, 10, 200, 200));
    //lay.addNodeTypeRegion(SampleNode2.class, new Rectangle(250, 10, 200, 200));
    DefaultGraphModel dgm = (DefaultGraphModel) _jgf.getGraphModel();
    for (int i = 0; i < 1; i++) {
      SampleNode sn = new SampleNode();
      sn.initialize(null);
      dgm.addNode(sn);
      //      SampleNode2 sn2 = new SampleNode2();
      //      sn2.initialize(null);
      //      dgm.addNode(sn2);
    }
  }

  ////////////////////////////////////////////////////////////////
  // main

  public static void main(String args[]) {
    BasicApplication demo = new BasicApplication();
  }

} /* end class BasicApplication */

