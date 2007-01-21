package org.tigris.panelbeater;

import java.awt.Component;
import java.util.ArrayList;

/**
 * @author Bob Tarling
 */
abstract class PanelStrategy {
    
    PanelContainer workbench;
    private ArrayList pageListeners = new ArrayList();
    
    /**
     * Add a panel top this workbench panel. Depending on current mode the is either
     * added wrapped as a tabpage or as a JInternal
     */
    public abstract Component add(Component comp);

    public abstract void remove(Component comp);

    public abstract void toTop(Component comp);
}
