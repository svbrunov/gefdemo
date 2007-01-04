package org.tigris.gefdemo.bert;

import org.tigris.gef.base.ModeCreatePolyEdge;
import org.tigris.gef.base.SetModeAction;
import org.tigris.gef.ui.PaletteFig;
import org.tigris.gefdemo.bert.model.Table;
import org.tigris.gefdemo.bert.model.Relationship;


/** A class to define a custom palette for use in some demos.
 *
 * @see org.tigris.gefdemo.basic.BasicApplication
 */

public class SamplePalette extends PaletteFig {

    private static final long serialVersionUID = 8319853722043556420L;

    /** Construct a new palette of example nodes for the Example application */
    public SamplePalette() {
        super();
    }

    /** Define a button to make for the Example application */
    public void defineButtons() {
        super.defineButtons();

        add(new CmdCreateNode(Table.class, "Class"));
        add(new SetModeAction(ModeCreatePolyEdge.class, "edgeClass", Relationship.class, "Dependency"));
    }
} /* end class SamplePalette */
