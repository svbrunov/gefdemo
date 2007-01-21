package org.tigris.panelbeater;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


/**
 * <p>A container for multiple JPanels with various display modes which can be alternated.</p>
 * <p>Child panels can be presented
 * <ul>
 * <li> wrapped by Tabpages inside a TabbedPane
 * <li> wrapped by JInternalFrames inside a JDesktopPane 
 * <li> placed side by side inside a MultipleSplitPane
 * </ul></p>
 * <p>It is envisioned that the application knows nothing about the PanelContainer and
 * access is only allowed via Workbench and WorkbenchFrame.</p>
 * TODO This is work in progress. At the moment this class just wraps a single JPanel.
 * @author Bob Tarling
 */
class PanelContainer extends JPanel {

    public static final int TABBED_MODE = 0;
    public static final int INTERNAL_FRAME_MODE = 1;
    
    List panelList = new ArrayList();
    private int mode = TABBED_MODE;
    private TabbedPanelStrategy strategy = null;
        
    PanelContainer() {
        super();
        setLayout(new BorderLayout());
        strategy = new TabbedPanelStrategy(this);
    }

    PanelContainer(int mode) {
        super();
        this.mode = mode;
        setLayout(new BorderLayout());
        strategy = new TabbedPanelStrategy(this);
    }

    /**
     * Add a panel top this workbench panel. Depending on current mode the is either
     * added wrapped as a tabpage or as a JInternal
     */
    public Component add(Component arg0, int arg1) {
        return super.add(arg0, arg1);
    }

    /**
     * Add a panel top this workbench panel. Depending on current mode the is either
     * added wrapped as a tabpage or as a JInternal
     */
    public void add(Component arg0, Object arg1, int arg2) {
        super.add(arg0, arg1, arg2);
    }

    /**
     * Add a panel top this workbench panel. Depending on current mode the is either
     * added wrapped as a tabpage or as a JInternal
     */
    public void add(Component arg0, Object arg1) {
        super.add(arg0, arg1);
    }

    protected final Component superadd(Component comp) {
        return super.add(comp);
    }
    
    protected final void superremove(Component comp) {
        super.remove(comp);
    }
    
    /**
     * Add a panel top this workbench panel. Depending on current mode the is either
     * added wrapped as a tabpage or as a JInternal
     */
    public Component add(Component comp) {
        System.out.println("Adding component");
        if (panelList.contains(comp)) return null;
        comp = strategy.add(comp);
        System.out.println("Component added");
        return comp;
    }

    /* (non-Javadoc)
     * @see java.awt.Container#remove(java.awt.Component)
     */
    public void remove(Component comp) {
        System.out.println("Removing component");
        if (!panelList.contains(comp)) return;
        strategy.remove(comp);
        System.out.println("Component removed");
    }
    
    /**
     * Add a panel top this workbench panel. Depending on current mode the is either
     * added wrapped as a tabpage or as a JInternal
     */
    public Component add(String arg0, Component arg1) {
        return super.add(arg0, arg1);
    }
}
