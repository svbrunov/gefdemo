package org.tigris.gefdemo.classdiagram;

import org.tigris.gef.base.*;
import org.tigris.gef.ui.*;
import org.tigris.gefdemo.classdiagram.model.MAssociationEnd;

/** A class to define a custom palette for use in some demos.
 *
 * @see org.tigris.gefdemo.basic.BasicApplication
 */

public class SamplePalette extends PaletteFig {

    /** Construct a new palette of example nodes for the Example application */
    public SamplePalette() {
        super();
    }

    /** Define a button to make for the Example application */
    public void defineButtons() {
        super.defineButtons();

        add(
            new CmdCreateNode(
                org.tigris.gefdemo.classdiagram.model.MClass.class,
                "Class"));
        add(
            new CmdCreateNode(
                org.tigris.gefdemo.classdiagram.model.MAssociation.class,
                "Association"));
        add(new CmdSetMode(ModeCreatePolyEdge.class, "edgeClass", MAssociationEnd.class, "Association"));
    }
} /* end class SamplePalette */
