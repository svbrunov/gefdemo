package org.tigris.gefdemo.lan;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;

import org.tigris.gef.util.Localizer;
import org.tigris.gef.util.ResourceLoader;

/** A simple LAN-management application using GEF.  This demo shows
 * how to subclass GEF classes to define special types of nodes,
 * ports, and edges.    It defines it's own custom property sheets
 * for LAN equiptment.  And, it has a "Developer Dialog" tab that
 * explains what is happening internally as the user uses the
 * application.
 */

public class Main {

  ////////////////////////////////////////////////////////////////
  // instance variables

  protected GefGraphFrame _jgf;

  ////////////////////////////////////////////////////////////////
  // constructors

  public Main() {
      // init localizer and resourceloader
      //JR-TODO: localization of LAN demo
      Localizer.addResource("GefBase","org.tigris.gef.base.BaseResourceBundle");
      Localizer.addResource("GefPres","org.tigris.gef.presentation.PresentationResourceBundle");
      Localizer.addLocale(Locale.getDefault());
      Localizer.switchCurrentLocale(Locale.getDefault());

      ResourceLoader.addResourceExtension("gif");
      ResourceLoader.addResourceLocation("/org/tigris/gef/Images");
      ResourceLoader.addResourceLocation("/org/tigris/gefdemo/lan/Images");

      _jgf = new GefGraphFrame();
      _jgf.addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent event) {
		  _jgf.dispose();
	      }
	      public void windowClosed(WindowEvent event) {
		  System.exit(0);
	      }
	  });

      _jgf.setVisible(true);
  }

  ////////////////////////////////////////////////////////////////
  // main

  public static void main(String args[]) {
    Main lan_demo = new Main();
  }

}

