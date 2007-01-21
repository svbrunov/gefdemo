package org.tigris.gefdemo.uml.ui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

import org.tigris.swidgets.BorderSplitPane;
import org.tigris.toolbar.layouts.DockBorderLayout;

/**
 * A frame containing toolbar and component window support
 *
 * @author Bob Tarling
 */
public class ApplicationFrame extends JFrame {
    
    private static final long serialVersionUID = 3596142647436177638L;

    private JTabbedPane tabbedPane = new JTabbedPane();
    
    private BorderSplitPane workarea = new BorderSplitPane();
    private JPanel toolbarBoundry;

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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        toolbarBoundry = new JPanel();
        toolbarBoundry.setLayout(new DockBorderLayout());
        toolbarBoundry.add(workarea, DockBorderLayout.CENTER);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(toolbarBoundry);
        workarea.add(tabbedPane);
        workarea.add(new JPanel(), BorderSplitPane.WEST);
    }
        
    /**
     * Delegate any added components to the workarea
     */
    public Component add(Component comp) {
	tabbedPane.add(comp);
        return comp;
    }

    /**
     * @see java.awt.Container#remove(java.awt.Component)
     */
    public void remove(Component comp) {
        System.out.println("Trying to remove " + comp);
        tabbedPane.remove(comp);
    }
}
