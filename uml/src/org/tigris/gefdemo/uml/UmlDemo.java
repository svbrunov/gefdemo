package org.tigris.gefdemo.uml;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import org.tigris.gef.base.AlignAction;
import org.tigris.gef.base.CmdAdjustGrid;
import org.tigris.gef.base.CmdAdjustGuide;
import org.tigris.gef.base.CmdAdjustPageBreaks;
import org.tigris.gef.base.CmdCopy;
import org.tigris.gef.base.CmdDeleteFromModel;
import org.tigris.gef.base.CmdExit;
import org.tigris.gef.base.CmdGroup;
import org.tigris.gef.base.CmdOpen;
import org.tigris.gef.base.CmdPaste;
import org.tigris.gef.base.CmdPrint;
import org.tigris.gef.base.CmdReorder;
import org.tigris.gef.base.CmdSelectInvert;
import org.tigris.gef.base.CmdSelectNext;
import org.tigris.gef.base.CmdShowProperties;
import org.tigris.gef.base.CmdSpawn;
import org.tigris.gef.base.CmdUngroup;
import org.tigris.gef.base.CmdUseReshape;
import org.tigris.gef.base.CmdUseResize;
import org.tigris.gef.base.CmdUseRotate;
import org.tigris.gef.base.DistributeAction;
import org.tigris.gef.base.Globals;
import org.tigris.gef.base.ModeSelect;
import org.tigris.gef.event.ModeChangeEvent;
import org.tigris.gef.graph.GraphEdgeRenderer;
import org.tigris.gef.graph.GraphModel;
import org.tigris.gef.graph.GraphNodeRenderer;
import org.tigris.gef.graph.presentation.JGraph;
import org.tigris.gef.ui.PaletteFig;
import org.tigris.gef.ui.ToolBar;
import org.tigris.gef.undo.RedoAction;
import org.tigris.gef.undo.UndoAction;
import org.tigris.gef.util.Localizer;
import org.tigris.gef.util.ResourceLoader;
import org.tigris.gefdemo.uml.persistence.SaveAction;
import org.tigris.geflayout.base.LayoutAction;
import org.tigris.geflayout.sugiyama.SugiyamaLayouter;
import org.tigris.panelbeater.PanelManager;

/** 
 * An example of a complex application built using GEF.
 * This example demonstrates
 * <ul>
 * <li>Connection to an external model</li>
 * <li>Multiple diagrams in MDI view using panelbeater</li>
 * <li>Creating SVG from diagram using batik</li>
 * </ul>
 */

public class UmlDemo {

    /** The toolbar (shown at top of window). */
    private ToolBar _toolbar = new PaletteFig();
    /** The graph pane (shown in middle of window). */
    private JGraph _graph;

    private JPanel _mainPanel = new JPanel(new BorderLayout());

    private PanelManager panelManager = new PanelManager();
    
    private static UmlDemo instance;

    public UmlDemo getInstance() {
        return instance;
    }
    
    public UmlDemo() {

        instance = this;
        
        ResourceLoader.addResourceLocation("/org/tigris/gefdemo/uml/Images");
        ResourceLoader.addResourceExtension("gif");
        ResourceLoader.addResourceLocation("/org/tigris/gef/Images");
        
        panelManager.setMode(PanelManager.INTERNAL_FRAME_MODE, PanelManager.CENTER);
        
        panelManager.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent event) {
                    	panelManager.dispose();
                    }
                    public void windowClosed(WindowEvent event) {
                    	System.exit(0);
                    }
                }
            );
            
        panelManager.pack();

        JMenuBar menuBar = setUpMenus();

        panelManager.setJMenuBar(menuBar);
        
        DiagramPanel classDiagramPanel1 = null;
//        DiagramPanel classDiagramPanel2 = null;
//        DiagramPanel activityDiagramPanel1 = null;
//        DiagramPanel activityDiagramPanel2 = null;
//        DiagramPanel sequenceDiagramPanel1 = null;
//        DiagramPanel sequenceDiagramPanel2 = null;
        try {
            classDiagramPanel1 = new ClassDiagramPanel(
                ConnectionConstrainer.getInstance(),
                "Class Diagram 1");
//            classDiagramPanel2 = new ClassDiagramPanel(
//                ConnectionConstrainer.getInstance(),
//                "Class Diagram 2");
//            activityDiagramPanel1 = new ActivityDiagramPanel(
//                ConnectionConstrainer.getInstance(),
//                "Activity Diagram 1");
//            activityDiagramPanel2 = new ActivityDiagramPanel(
//                ConnectionConstrainer.getInstance(),
//                "Activity Diagram 2");
//            sequenceDiagramPanel1 = new SequenceDiagramPanel(
//                ConnectionConstrainer.getInstance(),
//                "Sequence Diagram 1");
//            sequenceDiagramPanel2 = new SequenceDiagramPanel(
//                ConnectionConstrainer.getInstance(),
//                "Sequence Diagram 2");
        } catch (Exception e) {
            System.out.println("Exception caught");
            e.printStackTrace();
        }
//        panelManager.add(activityDiagramPanel1);
//        panelManager.add(activityDiagramPanel2);
//        panelManager.add(sequenceDiagramPanel1);
//        panelManager.add(sequenceDiagramPanel2);
        panelManager.add(classDiagramPanel1);
//        panelManager.add(classDiagramPanel2);
        panelManager.add(new JPanel(), PanelManager.WEST);
        System.out.println("Panels added");
        
        panelManager.setBounds(10, 10, 700, 700);
        panelManager.setVisible(true);
            
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
    }
    
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
    
    public void setToolBar(ToolBar tb) {
        _toolbar = tb;
        _mainPanel.add(_toolbar, BorderLayout.NORTH);
    }
    /** Set up the menus and keystrokes for menu items. Subclasses can
     *  override this, or you can use setMenuBar(). */
    protected JMenuBar setUpMenus() {
        JMenuItem openItem,
            saveItem,
            printItem,
            exitItem;
        JMenuItem deleteItem, copyItem, pasteItem;
        JMenuItem groupItem, ungroupItem;
        JMenuItem toBackItem, backwardItem, toFrontItem, forwardItem;

        JMenuBar menubar = new JMenuBar();
        
        JMenu file = new JMenu(Localizer.localize("GefBase", "File"));
        file.setMnemonic('F');
        menubar.add(file);
        //file.add(new CmdNew());
        openItem = file.add(new CmdOpen());
        saveItem = file.add(new SaveAction("Save"));
        saveItem = file.add(new SaveSvgAction());
        saveItem = file.add(new SaveGraphicsAction());
        CmdPrint cmdPrint = new CmdPrint();
        printItem = file.add(cmdPrint);
        exitItem = file.add(new CmdExit());

        JMenu edit = new JMenu(Localizer.localize("GefBase", "Edit"));
        edit.setMnemonic('E');
        menubar.add(edit);

        UndoAction undoAction = new UndoAction("Undo");
        undoAction.setEnabled(false);
        JMenuItem undoItem = edit.add(undoAction);
        undoItem.setMnemonic('U');

        RedoAction redoAction = new RedoAction("Redo");
        redoAction.setEnabled(false);
        JMenuItem redoItem = edit.add(redoAction);
        redoItem.setMnemonic('R');

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
        menubar.add(view);
        view.setMnemonic('V');
        view.add(new CmdSpawn());
        view.add(new CmdShowProperties());
        view.addSeparator();
        view.add(new CmdAdjustGrid());
        view.add(new CmdAdjustGuide());
        view.add(new CmdAdjustPageBreaks());

        JMenu arrange = new JMenu(Localizer.localize("GefBase", "Arrange"));
        menubar.add(arrange);
        arrange.setMnemonic('A');
        groupItem = arrange.add(new CmdGroup());
        groupItem.setMnemonic('G');
        ungroupItem = arrange.add(new CmdUngroup());
        ungroupItem.setMnemonic('U');

        JMenu align = new JMenu(Localizer.localize("GefBase", "Align"));
        arrange.add(align);
        align.add(new AlignAction(AlignAction.ALIGN_TOPS));
        align.add(new AlignAction(AlignAction.ALIGN_BOTTOMS));
        align.add(new AlignAction(AlignAction.ALIGN_LEFTS));
        align.add(new AlignAction(AlignAction.ALIGN_RIGHTS));
        align.add(new AlignAction(AlignAction.ALIGN_H_CENTERS));
        align.add(new AlignAction(AlignAction.ALIGN_V_CENTERS));
        align.add(new AlignAction(AlignAction.ALIGN_TO_GRID));

        JMenu distribute =
            new JMenu(Localizer.localize("GefBase", "Distribute"));
        arrange.add(distribute);
        distribute.add(new DistributeAction(DistributeAction.H_SPACING));
        distribute.add(new DistributeAction(DistributeAction.H_CENTERS));
        distribute.add(new DistributeAction(DistributeAction.V_SPACING));
        distribute.add(new DistributeAction(DistributeAction.V_CENTERS));

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
        KeyStroke ctrlY = KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_MASK);
        KeyStroke ctrlZ = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK);
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
        
        arrange.add(new LayoutAction(new SugiyamaLayouter() {}));

        //newItem.setAccelerator(ctrlN);
        openItem.setAccelerator(ctrlO);
        saveItem.setAccelerator(ctrlS);
        printItem.setAccelerator(ctrlP);
        exitItem.setAccelerator(altF4);

        deleteItem.setAccelerator(delKey);
        redoItem.setAccelerator(ctrlY);
        undoItem.setAccelerator(ctrlZ);
        //cutItem.setAccelerator(ctrlX);
        copyItem.setAccelerator(ctrlC);
        pasteItem.setAccelerator(ctrlV);

        groupItem.setAccelerator(ctrlG);
        ungroupItem.setAccelerator(ctrlU);

        toBackItem.setAccelerator(sCtrlB);
        toFrontItem.setAccelerator(sCtrlF);
        backwardItem.setAccelerator(ctrlB);
        forwardItem.setAccelerator(ctrlF);

        return menubar;
    }

    ////////////////////////////////////////////////////////////////
    // main

    public static void main(String args[]) {
        new UmlDemo();
    }

} /* end class UmlDemo */
