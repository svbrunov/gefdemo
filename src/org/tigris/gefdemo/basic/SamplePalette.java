package org.tigris.gefdemo.basic;

import java.awt.*;
import java.util.*;
import javax.swing.Action;

import org.tigris.gef.base.*;
import org.tigris.gef.ui.*;

/** A class to define a custom palette for use in some demos.
 *
 * @see org.tigris.gefdemo.basic.BasicApplication
 */

public class SamplePalette extends PaletteFig {

  /** Construct a new palette of example nodes for the Example application */
  public SamplePalette() { super(); }

  /** Define a button to make for the Example application */
  public void defineButtons() {
    super.defineButtons();

    add(new CmdCreateNode(org.tigris.gefdemo.basic.SampleNode.class, "NodeOne"));
    add(new CmdCreateNode(org.tigris.gefdemo.basic.SampleNode2.class, "NodeTwo"));
    addSeparator();
    Cmd image1 = new CmdSetMode(ModeCreateFigImage.class,
				"imageURL",
				"http://www.ics.uci.edu/~jrobbins/images/"+
				"new.gif");
    image1.putValue(Action.NAME, "Image1");
    Cmd image2 = new CmdSetMode(ModeCreateFigImage.class,
				"imageURL",
				"http://www.ics.uci.edu/~jrobbins/images/"+
				"gef_banner.gif");
    image2.putValue(Action.NAME, "Image2");

    if (Globals.getAppletContext() != null) {
      add(image1, "Image1", "Image1");
      add(image2, "Image2", "Image2");
    }
  }
} /* end class SamplePalette */
