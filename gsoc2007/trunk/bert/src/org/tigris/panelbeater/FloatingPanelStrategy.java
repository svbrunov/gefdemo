package org.tigris.panelbeater;

import java.awt.Component;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 * @author Bob Tarling
 */
class FloatingPanelStrategy extends PanelStrategy implements InternalFrameListener {
    
    private JDesktopPane desktopPane;
    private Map internalFrameByComponent = new HashMap();
    private Map componentByInternalFrame = new HashMap();
    
    FloatingPanelStrategy(PanelContainer workbench) {
        this.workbench = workbench;
        desktopPane = new JDesktopPane();
        workbench.superadd(desktopPane);
    }
    
    /**
     * Add a panel top this workbench panel. In floating page mode
     * the panel is wrapped in a JInternalFrame
     */
    public Component add(Component comp) {
        workbench.panelList.add(comp);
        JInternalFrame jif = new InternalFrame(comp.getName());
        jif.getContentPane().add(comp);
        jif.setVisible(true);
        jif.setSize(400,400);
        jif.setMaximizable(true);
        jif.setIconifiable(false);
        jif.setResizable(true);
        jif.setClosable(true);
        desktopPane.add(jif);
        internalFrameByComponent.put(comp, jif);
        componentByInternalFrame.put(jif, comp);
        toTop(comp);
        jif.addInternalFrameListener(this);
        return comp;
    }

    public void remove(Component comp) {
        workbench.panelList.remove(comp);
        JInternalFrame jif = (JInternalFrame)internalFrameByComponent.get(comp);
        //desktopPane.remove(jif);
        System.out.println("Disposing frame");
        jif.dispose();
        internalFrameByComponent.remove(comp);
        componentByInternalFrame.remove(jif);
        jif.removeInternalFrameListener(this);
    }

    public void toTop(Component comp) {
        try {
            JInternalFrame jif = (JInternalFrame)internalFrameByComponent.get(comp);
            JInternalFrame lastJif = desktopPane.getSelectedFrame();
            if (lastJif != null) {
                lastJif.setSelected(false);
            }
            jif.setSelected(true);
        } catch (PropertyVetoException e) {
            // This won't happen
            e.printStackTrace();
        }
    }

    
    public void internalFrameClosed(InternalFrameEvent e) {
        JInternalFrame frame = e.getInternalFrame();
        Component comp = (Component)componentByInternalFrame.get(frame);
        internalFrameByComponent.remove(comp);
        componentByInternalFrame.remove(frame);
    }

    public void internalFrameActivated(InternalFrameEvent e) {
    }

    public void internalFrameClosing(InternalFrameEvent e) {
    }

    public void internalFrameDeactivated(InternalFrameEvent e) {
    }

    public void internalFrameDeiconified(InternalFrameEvent e) {
    }

    public void internalFrameIconified(InternalFrameEvent e) {
    }

    public void internalFrameOpened(InternalFrameEvent e) {
    }
}
