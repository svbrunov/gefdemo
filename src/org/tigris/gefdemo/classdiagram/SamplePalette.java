package org.tigris.gefdemo.classdiagram;

import org.tigris.gef.base.CmdSetMode;
import org.tigris.gef.base.ModeCreatePolyEdge;
import org.tigris.gef.ui.PaletteFig;
import org.tigris.gefdemo.classdiagram.model.UmlAssociationEnd;
import org.tigris.gefdemo.classdiagram.model.UmlAssociation;
import org.tigris.gefdemo.classdiagram.model.UmlClass;
import org.tigris.gefdemo.classdiagram.model.UmlDependency;
import org.tigris.gefdemo.classdiagram.model.UmlInterface;


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

        add(new CmdCreateNode(UmlClass.class, "Class"));
        add(new CmdCreateNode(UmlInterface.class, "Interface"));
        add(new CmdCreateNode(UmlAssociation.class, "Association"));
        add(new CmdSetMode(ModeCreateAssociationEnd.class, "edgeClass", UmlAssociationEnd.class, "AssociationEnd"));
        add(new CmdSetMode(ModeCreatePolyEdge.class, "edgeClass", UmlDependency.class, "Dependency"));
    }
} /* end class SamplePalette */
