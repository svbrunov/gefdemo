package org.tigris.gefdemo.uml;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import org.tigris.gef.base.CmdAdjustGrid;
import org.tigris.gef.base.CmdAdjustGuide;
import org.tigris.gef.base.CmdAdjustPageBreaks;
import org.tigris.gef.base.CmdAlign;
import org.tigris.gef.base.CmdCopy;
import org.tigris.gef.base.CmdDeleteFromModel;
import org.tigris.gef.base.CmdDistribute;
import org.tigris.gef.base.CmdExit;
import org.tigris.gef.base.CmdGroup;
import org.tigris.gef.base.CmdOpen;
import org.tigris.gef.base.CmdPaste;
import org.tigris.gef.base.CmdPrint;
import org.tigris.gef.base.CmdReorder;
import org.tigris.gef.base.CmdSave;
import org.tigris.gef.base.CmdSelectInvert;
import org.tigris.gef.base.CmdSelectNext;
import org.tigris.gef.base.CmdShowProperties;
import org.tigris.gef.base.CmdSpawn;
import org.tigris.gef.base.CmdUngroup;
import org.tigris.gef.base.CmdUseReshape;
import org.tigris.gef.base.CmdUseResize;
import org.tigris.gef.base.CmdUseRotate;
import org.tigris.gef.base.Globals;
import org.tigris.gef.base.ModeSelect;
import org.tigris.gef.event.ModeChangeEvent;
import org.tigris.gef.graph.GraphEdgeRenderer;
import org.tigris.gef.graph.GraphModel;
import org.tigris.gef.graph.GraphNodeRenderer;
import org.tigris.gef.graph.presentation.JGraph;
import org.tigris.gef.ui.PaletteFig;
import org.tigris.gef.ui.ToolBar;
import org.tigris.gef.util.Localizer;
import org.tigris.gef.util.ResourceLoader;
import org.tigris.panelbeater.PanelManager;

/** A simple example of the minimum code needed to build an
 *  application using GEF.
 */

public class UmlDemo {

    /** The toolbar (shown at top of window). */
    private ToolBar _toolbar = new PaletteFig();
    /** The graph pane (shown in middle of window). */
    private JGraph _graph;
    /** A statusbar (shown at bottom ow window). */
    private JLabel _statusbar = new JLabel(" ");

    private JPanel _mainPanel = new JPanel(new BorderLayout());
    private JPanel _graphPanel = new JPanel(new BorderLayout());
    private JMenuBar _menubar = new JMenuBar();

    private static UmlDemo instance;

    public UmlDemo getInstance() {
        return instance;
    }
    
    public UmlDemo() {

        instance = this;
        
        ResourceLoader.addResourceLocation("/org/tigris/gefdemo/uml/Images");
        ResourceLoader.addResourceExtension("gif");
        ResourceLoader.addResourceLocation("/org/tigris/gef/Images");
        
        PanelManager panelManager = new PanelManager();
        
        panelManager.setMode(PanelManager.INTERNAL_FRAME_MODE, PanelManager.CENTER);
        
        panelManager.pack();
        panelManager.setVisible(true);
 
        DiagramPanel diagramPanel = null;
        try {
            diagramPanel = new DiagramPanel(ConnectionConstrainer.getInstance());
        } catch (Exception e) {
            
        }
        panelManager.add(diagramPanel);
        panelManager.add(new JPanel(), PanelManager.WEST);
            
        // init localizer and resourceloader
        ////////////////////////////////////////////////////////////////
        // constructors

        Localizer.addResource(
            "GefBase",
            "org.tigris.gef.base.BaseResourceBundle");
        Localizer.addResource(
            "GefPres",
            "org.tigris.gef.presentation.PresentationResourceBundle");
        Localizer.addLocale(Locale.getDefault());
        Localizer.switchCurrentLocale(Locale.getDefault());
//        ResourceLoader.addResourceExtension("gif");
//        ResourceLoader.addResourceLocation("/org/tigris/gef/Images");
//        ResourceLoader.addResourceLocation("/org/tigris/gefdemo/uml/Images");
//        GraphModel gm = new UmlGraphModel();
//
//        graphFrame = new GefGraphFrame("Class Diagram", gm);
//        graphFrame.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent event) {
//                graphFrame.dispose();
//            }
//            public void windowClosed(WindowEvent event) {
//                System.exit(0);
//            }
//        });
//        graphFrame.setToolBar(new SamplePalette()); //needs-more-work

//        ClassDiagramRenderer renderer = new ClassDiagramRenderer();
//        graphFrame.getGraph().setGraphNodeRenderer(renderer);
//        graphFrame.getGraph().setGraphEdgeRenderer(renderer);
        
//        try {
//            graphFrame.getGraphModel().setConnectionConstrainer(ConnectionConstrainer.getInstance());
//        } catch (GraphModelException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        graphFrame.setBounds(10, 10, 300, 200);
//        graphFrame.setVisible(true);
    }
    
//    public void init() {
//        init(new JGraph());
//    }
//
//    public void init(JGraph jg) {
//        _graph = jg;
//        Container content = getContentPane();
//        setUpMenus();
//        content.setLayout(new BorderLayout());
//        content.add(_menubar, BorderLayout.NORTH);
//        _graphPanel.add(_graph, BorderLayout.CENTER);
//        _graphPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
//
//        _mainPanel.add(_toolbar, BorderLayout.NORTH);
//        _mainPanel.add(_graphPanel, BorderLayout.CENTER);
//        content.add(_mainPanel, BorderLayout.CENTER);
//        content.add(_statusbar, BorderLayout.SOUTH);
//        setSize(300, 250);
//        _graph.addModeChangeListener(this);
//    }
    
    ////////////////////////////////////////////////////////////////
    // accessors

    public JGraph getGraph() {
        return _graph;
    }
    public GraphEdgeRenderer getGraphEdgeRenderer() {
        return _graph.getEditor().getGraphEdgeRenderer();
    }
    public GraphModel getGraphModel() {
        return _graph.getGraphModel();
    }
    public GraphNodeRenderer getGraphNodeRenderer() {
        return _graph.getEditor().getGraphNodeRenderer();
    }
    public JMenuBar getJMenuBar() {
        return _menubar;
    }
    public ToolBar getToolBar() {
        return _toolbar;
    }
    ////////////////////////////////////////////////////////////////
    // ModeChangeListener implementation
    public void modeChange(ModeChangeEvent mce) {
        //System.out.println("TabDiagram got mode change event");
        if (!Globals.getSticky() && Globals.mode() instanceof ModeSelect)
            _toolbar.unpressAllButtons();
    }
    public void setGraph(JGraph g) {
        _graph = g;
    }
    public void setGraphEdgeRenderer(GraphEdgeRenderer rend) {
        _graph.getEditor().setGraphEdgeRenderer(rend);
    }
    public void setGraphModel(GraphModel gm) {
        _graph.setGraphModel(gm);
    }
    public void setGraphNodeRenderer(GraphNodeRenderer rend) {
        _graph.getEditor().setGraphNodeRenderer(rend);
    }
//    public void setJMenuBar(JMenuBar mb) {
//        _menubar = mb;
//        getContentPane().add(_menubar, BorderLayout.NORTH);
//    }
    public void setToolBar(ToolBar tb) {
        _toolbar = tb;
        _mainPanel.add(_toolbar, BorderLayout.NORTH);
    }
    /** Set up the menus and keystrokes for menu items. Subclasses can
     *  override this, or you can use setMenuBar(). */
    protected void setUpMenus() {
        JMenuItem openItem,
            saveItem,
            printItem,
            exitItem;
        JMenuItem deleteItem, copyItem, pasteItem;
        JMenuItem groupItem, ungroupItem;
        JMenuItem toBackItem, backwardItem, toFrontItem, forwardItem;

        JMenu file = new JMenu(Localizer.localize("GefBase", "File"));
        file.setMnemonic('F');
        _menubar.add(file);
        //file.add(new CmdNew());
        openItem = file.add(new CmdOpen());
        saveItem = file.add(new CmdSave());
        CmdPrint cmdPrint = new CmdPrint();
        printItem = file.add(cmdPrint);
        exitItem = file.add(new CmdExit());

        JMenu edit = new JMenu(Localizer.localize("GefBase", "Edit"));
        edit.setMnemonic('E');
        _menubar.add(edit);

        JMenu select = new JMenu(Localizer.localize("GefBase", "Select"));
        edit.add(select);
        select.add(new CmdSelectNext(false));
        select.add(new CmdSelectNext(true));
        select.add(new CmdSelectInvert());

        edit.addSeparator();

        copyItem = edit.add(new CmdCopy());
        copyItem.setMnemonic('C');
        pasteItem = edit.add(new CmdPaste());
        pasteItem.setMnemonic('P');

        deleteItem = edit.add(new CmdDeleteFromModel());
        edit.addSeparator();
        edit.add(new CmdUseReshape());
        edit.add(new CmdUseResize());
        edit.add(new CmdUseRotate());

        JMenu view = new JMenu(Localizer.localize("GefBase", "View"));
        _menubar.add(view);
        view.setMnemonic('V');
        view.add(new CmdSpawn());
        view.add(new CmdShowProperties());
        view.addSeparator();
        view.add(new CmdAdjustGrid());
        view.add(new CmdAdjustGuide());
        view.add(new CmdAdjustPageBreaks());

        JMenu arrange = new JMenu(Localizer.localize("GefBase", "Arrange"));
        _menubar.add(arrange);
        arrange.setMnemonic('A');
        groupItem = arrange.add(new CmdGroup());
        groupItem.setMnemonic('G');
        ungroupItem = arrange.add(new CmdUngroup());
        ungroupItem.setMnemonic('U');

        JMenu align = new JMenu(Localizer.localize("GefBase", "Align"));
        arrange.add(align);
        align.add(new CmdAlign(CmdAlign.ALIGN_TOPS));
        align.add(new CmdAlign(CmdAlign.ALIGN_BOTTOMS));
        align.add(new CmdAlign(CmdAlign.ALIGN_LEFTS));
        align.add(new CmdAlign(CmdAlign.ALIGN_RIGHTS));
        align.add(new CmdAlign(CmdAlign.ALIGN_H_CENTERS));
        align.add(new CmdAlign(CmdAlign.ALIGN_V_CENTERS));
        align.add(new CmdAlign(CmdAlign.ALIGN_TO_GRID));

        JMenu distribute =
            new JMenu(Localizer.localize("GefBase", "Distribute"));
        arrange.add(distribute);
        distribute.add(new CmdDistribute(CmdDistribute.H_SPACING));
        distribute.add(new CmdDistribute(CmdDistribute.H_CENTERS));
        distribute.add(new CmdDistribute(CmdDistribute.V_SPACING));
        distribute.add(new CmdDistribute(CmdDistribute.V_CENTERS));

        JMenu reorder = new JMenu(Localizer.localize("GefBase", "Reorder"));
        arrange.add(reorder);
        toBackItem = reorder.add(new CmdReorder(CmdReorder.SEND_TO_BACK));
        toFrontItem = reorder.add(new CmdReorder(CmdReorder.BRING_TO_FRONT));
        backwardItem = reorder.add(new CmdReorder(CmdReorder.SEND_BACKWARD));
        forwardItem = reorder.add(new CmdReorder(CmdReorder.BRING_FORWARD));

        JMenu nudge = new JMenu(Localizer.localize("GefBase", "Nudge"));
        arrange.add(nudge);

        KeyStroke ctrlO = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK);
        KeyStroke ctrlS = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK);
        KeyStroke ctrlP = KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_MASK);
        KeyStroke altF4 = KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK);

        KeyStroke delKey = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);
        KeyStroke ctrlC = KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK);
        KeyStroke ctrlV = KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK);
        KeyStroke ctrlG = KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_MASK);
        KeyStroke ctrlU = KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_MASK);
        KeyStroke ctrlB = KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_MASK);
        KeyStroke ctrlF = KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_MASK);
        KeyStroke sCtrlB =
            KeyStroke.getKeyStroke(
                KeyEvent.VK_B,
                KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK);
        KeyStroke sCtrlF =
            KeyStroke.getKeyStroke(
                KeyEvent.VK_F,
                KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK);

        //newItem.setAccelerator(ctrlN);
        openItem.setAccelerator(ctrlO);
        saveItem.setAccelerator(ctrlS);
        printItem.setAccelerator(ctrlP);
        exitItem.setAccelerator(altF4);

        deleteItem.setAccelerator(delKey);
        //undoItem.setAccelerator(ctrlZ);
        //cutItem.setAccelerator(ctrlX);
        copyItem.setAccelerator(ctrlC);
        pasteItem.setAccelerator(ctrlV);

        groupItem.setAccelerator(ctrlG);
        ungroupItem.setAccelerator(ctrlU);

        toBackItem.setAccelerator(sCtrlB);
        toFrontItem.setAccelerator(sCtrlF);
        backwardItem.setAccelerator(ctrlB);
        forwardItem.setAccelerator(ctrlF);

    }
    ////////////////////////////////////////////////////////////////
    // display related methods

//    public void setVisible(boolean b) {
//        super.setVisible(b);
//        if (b) {
//            Globals.setStatusBar(this);
//        }
//    }
    ////////////////////////////////////////////////////////////////
    // IStatusListener implementation

//    /** Show a message in the statusbar. */
//    public void showStatus(String msg) {
//        if (_statusbar != null) {
//            _statusbar.setText(msg);
//        }
//    }

    ////////////////////////////////////////////////////////////////
    // main

    public static void main(String args[]) {
        new UmlDemo();
    }

} /* end class BasicApplication */
