package org.tigris.panelbeater;

import java.awt.Component;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;

import org.tigris.gefdemo.uml.ui.ApplicationFrame;

public class PanelManager {
    private ApplicationFrame workbenchFrame;
    
    public PanelManager() {
        workbenchFrame = new ApplicationFrame();
    }

    public void addToolBar(JToolBar toolbar) {
        workbenchFrame.add(toolbar);
    }

    /**
     *  Delegate any added components to the workarea
     */
    public Component add(Component arg0, int arg1) {
        return workbenchFrame.add(arg0, arg1);
    }

    /**
     * Delegate any added components to the workarea
     */
    public void add(Component comp, Object position) {
        workbenchFrame.add(comp, position);
    }

    /**
     * Delegate any added components to the workarea
     */
    public Component add(Component comp) {
        return workbenchFrame.add(comp);
    }
    
    /**
     * Delegate any added components to the workarea
     */
    public void put(Object key, Component comp, Object position) {
        workbenchFrame.put(key, comp, position);
    }

    /**
     * Delegate any added components to the workarea
     */
    public Component put(Object key, Component comp) {
        workbenchFrame.put(key, comp);
        return comp;
    }
    
    public Component get(Object key) {
        return workbenchFrame.get(key);
    }
    
    public void remove(Component comp) {
        workbenchFrame.remove(comp);
    }
    
    /**
     * Delegate any added components to the workarea
     */
    public void setMode(int mode, String position) {
        workbenchFrame.setMode(mode, position);
    }

    public void pack() {
        workbenchFrame.pack();
    }
    
    public void setVisible(boolean visible) {
        workbenchFrame.setVisible(visible);
    }
    
    public void setJMenuBar(JMenuBar jmb) {
        workbenchFrame.setJMenuBar(jmb);
    }
    
    public void dispose() {
        workbenchFrame.dispose();
    }
    
    public JFrame getFrame() {
        return workbenchFrame;
    }

    public void setTitle(String title) {
        workbenchFrame.setTitle(title);
    }
    
    public void setBounds(int x, int y, int width, int height) {
        workbenchFrame.setBounds(x, y, width, height);
    }
    
    public void addWindowListener(WindowListener listener) {
        workbenchFrame.addWindowListener(listener);
    }
}