package org.tigris.gefdemo.uml.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import org.tigris.panelbeater.PanelContainer;
import org.tigris.swidgets.BorderSplitPane;
import org.tigris.toolbar.layouts.DockBorderLayout;

/**
 * A frame containing toolbar and component window support
 *
 * @author Bob Tarling
 */
public class ApplicationFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 3596142647436177638L;
    public static final int TABBED_MODE = 0;
    public static final int INTERNAL_FRAME_MODE = 1;
    
    public static final String NORTHEAST = BorderSplitPane.NORTHEAST;
    public static final String NORTHWEST = BorderSplitPane.NORTHWEST;
    public static final String SOUTHEAST = BorderSplitPane.SOUTHEAST;
    public static final String SOUTHWEST = BorderSplitPane.SOUTHWEST;
    public static final String EAST = BorderSplitPane.EAST;
    public static final String WEST = BorderSplitPane.WEST;
    public static final String NORTH = BorderSplitPane.NORTH;
    public static final String SOUTH = BorderSplitPane.SOUTH;
    public static final String CENTER = BorderSplitPane.CENTER;
    
    private BorderSplitPane workarea = new BorderSplitPane();
    private JPanel toolbarBoundry;
    private boolean constructed = false;
    private Map workbenchPanelByPosition = new HashMap(9);

    private Map panelDataMap = new HashMap(9);

    private Map keyedPanels = new HashMap();
        
    private Map workbenchPanelsByComponent = new HashMap();
        
    public ApplicationFrame(String name) {
        super(name);
        init();
    }
        
    public ApplicationFrame() {
        super();
        init();
    }

    public void addToolBar(JToolBar toolbar) {
        toolbarBoundry.add(toolbar, DockBorderLayout.NORTH);
    }

    private void init() {
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        panelDataMap.put(NORTHEAST, new PanelData());
        panelDataMap.put(NORTHWEST, new PanelData());
        panelDataMap.put(SOUTHEAST, new PanelData());
        panelDataMap.put(SOUTHWEST, new PanelData());
        panelDataMap.put(EAST, new PanelData());
        panelDataMap.put(WEST, new PanelData());
        panelDataMap.put(NORTH, new PanelData());
        panelDataMap.put(SOUTH, new PanelData());
        panelDataMap.put(CENTER, new PanelData());
    
        toolbarBoundry = new JPanel();
        toolbarBoundry.setLayout(new DockBorderLayout());
        toolbarBoundry.add(workarea, DockBorderLayout.CENTER);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(toolbarBoundry);
        constructed = true;
    }
        
    /**
     * Delegate any added components to the workarea
     */
    public void add(Component comp, Object position) {
        put(comp, comp, position);
    }

    /**
     * Delegate any added components to the workarea
     */
    public Component add(Component comp) {
        put(comp, comp);
        return comp;
    }

    /**
     * @see java.awt.Container#remove(java.awt.Component)
     */
    public void remove(Component comp) {
        System.out.println("Trying to remove " + comp);
        if (constructed) {
            PanelContainer wp = (PanelContainer)workbenchPanelsByComponent.get(comp);
            workbenchPanelsByComponent.remove(comp);
            workbenchPanelByPosition.remove(comp);
            System.out.println("Removing from workbench");
            wp.remove(comp);
        } else {
            super.remove(comp);
        }
    }
    
    /**
     * Delegate any added components to the workarea
     */
    public void put(Object key, Component comp, Object position) {
        if (constructed) {
            keyedPanels.put(key, comp);
            // Wrap the component in a WorkbenchPanel
            PanelContainer wp = (PanelContainer)workbenchPanelByPosition.get(position);
            if (wp == null) {
                System.out.println(position);
                PanelData pd = (PanelData)panelDataMap.get(position);
                wp = new PanelContainer();
                wp.setPreferredSize(new Dimension(90,90));
                workarea.add(wp, position);
                workbenchPanelByPosition.put(position, wp);
            }
            wp.add(comp);
            workbenchPanelsByComponent.put(comp, wp);
        } else {
            super.add(comp, position);
        }
    }

    /**
     * Delegate any added components to the workarea
     */
    public void put(Object key, Component comp) {
        put(key, comp, BorderSplitPane.CENTER);
    }

    public Component get(Object key) {
        return (Component)keyedPanels.get(key);
    }
    
    public void setMode(int mode, String position) {
        panelDataMap.put(position, new PanelData());
        PanelContainer wp = (PanelContainer)workbenchPanelByPosition.get(position);
        if (wp == null) {
            wp = new PanelContainer();
            wp.setPreferredSize(new Dimension(90,90));
            workarea.add(wp, position);
            workbenchPanelByPosition.put(position, wp);
        }
    }

    private class PanelData {
        PanelData() {
        }
    }
}
