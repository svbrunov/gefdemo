package org.tigris.panelbeater;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

/**
 * @author Bob Tarling
 */
class TabbedPanelStrategy extends PanelStrategy {
    
    private JTabbedPane tabbedPane;
    
    TabbedPanelStrategy(PanelContainer workbench) {
        this.workbench = workbench;
    }
    
    /**
     * Add a panel top this workbench panel. Depending on current mode the is either
     * added wrapped as a tabpage or as a JInternal
     */
    public Component add(Component comp) {
//        JInternalFrame jif = new JInternalFrame();
//        jif.getContentPane().add(comp);
//        jif.setVisible(true);
//        jif.setSize(400,400);
//        jif.setMaximizable(false);
//        jif.setIconifiable(false);
//        jif.setResizable(false);
//        jif.setClosable(false);
//        jif.setName(comp.getName());
//        comp = jif;
        if (workbench.panelList.size() == 0) {
            // For first component just add directly to pane
            workbench.panelList.add(comp);
            TitledBorder tb 
                = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), comp.getName());
            workbench.setBorder(tb);
            return workbench.superadd(comp);
        } else {
            if (workbench.panelList.size() == 1) {
                // For second component component create a tabbed
                // pane container.
                Component existingPane = (Component)workbench.panelList.get(0);
                workbench.superremove(existingPane);
                tabbedPane = new JTabbedPane();
                tabbedPane.add(existingPane);
                workbench.superadd(tabbedPane);
                workbench.setBorder(null);
            }
            // For second and subsequent add new pane to tabbed panel
            workbench.panelList.add(comp);
            tabbedPane.addTab(comp.getName(), comp);
            tabbedPane.setSelectedComponent(comp);
            return comp;
        }
    }

    public void remove(Component comp) {
      if (workbench.panelList.size() == 1) {
          // For first component just add directly to pane
          workbench.panelList.add(comp);
          TitledBorder tb 
              = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), comp.getName());
          workbench.setBorder(tb);
//          return workbench.superremove(comp);
      } else {
          workbench.panelList.remove(comp);
          tabbedPane.remove(comp);
      }
  }
    
    public void toTop(Component comp) {
        if (tabbedPane != null) {
            tabbedPane.setSelectedComponent(comp);
        }
    }
}
