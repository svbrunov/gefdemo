package org.tigris.gefdemo.lan;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import org.tigris.gef.base.CmdAdjustGrid;
import org.tigris.gef.base.CmdAdjustGuide;
import org.tigris.gef.base.CmdAdjustPageBreaks;
import org.tigris.gef.base.CmdAlign;
import org.tigris.gef.base.CmdCopy;
import org.tigris.gef.base.CmdDeleteFromModel;
import org.tigris.gef.base.CmdDistribute;
import org.tigris.gef.base.CmdExit;
import org.tigris.gef.base.CmdGroup;
import org.tigris.gef.base.CmdNudge;
import org.tigris.gef.base.CmdOpen;
import org.tigris.gef.base.CmdOpenPGML;
import org.tigris.gef.base.CmdOpenSVG;
import org.tigris.gef.base.CmdOpenWindow;
import org.tigris.gef.base.CmdPaste;
import org.tigris.gef.base.CmdPrint;
import org.tigris.gef.base.CmdPrintPageSetup;
import org.tigris.gef.base.CmdReorder;
import org.tigris.gef.base.CmdSave;
import org.tigris.gef.base.CmdSavePGML;
import org.tigris.gef.base.CmdSaveSVG;
import org.tigris.gef.base.CmdSelectAll;
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
import org.tigris.gef.base.SelectionManager;
import org.tigris.gef.event.ModeChangeEvent;
import org.tigris.gef.event.ModeChangeListener;
import org.tigris.gef.graph.GraphEdgeRenderer;
import org.tigris.gef.graph.GraphModel;
import org.tigris.gef.graph.GraphNodeRenderer;
import org.tigris.gef.graph.presentation.JGraph;
import org.tigris.gef.ui.IStatusBar;
import org.tigris.gef.ui.PaletteFig;
import org.tigris.gef.ui.ToolBar;
import org.tigris.gef.util.Localizer;

/** A window that displays a toolbar, a connected graph editing pane,
 *  and a status bar. */

public class GefGraphFrame
    extends JFrame
    implements IStatusBar, Cloneable, ModeChangeListener {

    ////////////////////////////////////////////////////////////////
    // instance variables

    /** The toolbar (shown at top of window). */
    private ToolBar _toolbar = new PaletteFig();

    /** The toolbar shown at the bottom of the window */
    private PaletteLAN _toolbar2 = new PaletteLAN();

    /** The Propert Sheet tabbed pane */
    private JTabbedPane _tabPane = new JTabbedPane(JTabbedPane.TOP);

    /** The graph pane (shown in middle of window). */
    private JGraph _graph;
    /** A statusbar (shown at bottom of window). */
    private JLabel _statusbar = new JLabel(" ");

    private JPanel _mainPanel = new JPanel(new BorderLayout());
    private JPanel _graphPanel = new JPanel(new BorderLayout());
    private JPanel _bottomPanel = new JPanel(new BorderLayout());
    private JMenuBar _menubar = new JMenuBar();

    ////////////////////////////////////////////////////////////////
    // constructor

    /** Contruct a new JGraphFrame with the title "untitled" and a new
     *  DefaultGraphModel. */
    public GefGraphFrame() {
        this("GEF LAN Demo", new JGraph());
        SelectionManager sm = _graph.getEditor().getSelectionManager();
        sm.addGraphSelectionListener(new PropSheetListener());
    }

    /** Contruct a new JGraphFrame with the given title and given
     *  JGraph. All JGraphFrame contructors call this one. */
    public GefGraphFrame(String title, JGraph jg) {
        setTitle(title);
        _graph = jg;

        Container content = getContentPane();
        setUpMenus();
        content.setLayout(new BorderLayout());
        content.add(_menubar, BorderLayout.NORTH);
        _graphPanel.add(_graph, BorderLayout.CENTER);
        _graphPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        _mainPanel.add(_toolbar, BorderLayout.NORTH);
        _mainPanel.add(_graphPanel, BorderLayout.CENTER);
        _mainPanel.add(_toolbar2, BorderLayout.SOUTH);
        content.add(_mainPanel, BorderLayout.CENTER);
        content.add(_bottomPanel, BorderLayout.SOUTH);

        setPropSheet(new PSTable());

        // make the delete key remove elements from the underlying GraphModel
        //getGraph().bindKey(new CmdDispose(), KeyEvent.VK_DELETE, 0);

        setBounds(50, 50, 650, 650);
        _graph.addModeChangeListener(this);
    }

    ////////////////////////////////////////////////////////////////
    // Cloneable implementation

    public Object clone() {
        return null; //needs-more-work
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

    public void setJMenuBar(JMenuBar mb) {
        _menubar = mb;
        getContentPane().add(_menubar, BorderLayout.NORTH);
    }

    public void setPropSheet(PSTable ps) {
        _tabPane.add("Properties", ps);
        _bottomPanel.add(_tabPane, BorderLayout.CENTER);
    }

    /** Set up the menus and keystrokes for menu items. Subclasses can
     *  override this, or you can use setMenuBar(). */
    protected void setUpMenus() {

        JMenuItem openItem,
            openPGMLItem,
            openSVGItem,
            saveItem,
            savePGMLItem,
            saveSVGItem,
            printItem,
            printPageSetupItem,
            prefsItem,
            exitItem;
        JMenuItem selectAllItem;
        JMenuItem deleteItem, cutItem, copyItem, pasteItem;
        JMenuItem editNodeItem;
        JMenuItem groupItem, ungroupItem;
        JMenuItem toBackItem, backwardItem, toFrontItem, forwardItem;
        JMenuItem nudgeUpItem, nudgeDownItem, nudgeLeftItem, nudgeRightItem;

        JMenu file = new JMenu(Localizer.localize("GefBase", "File"));
        file.setMnemonic('F');
        _menubar.add(file);
        //file.add(new CmdNew());
        openItem = file.add(new CmdOpen());
        openPGMLItem = file.add(new CmdOpenPGML());
        openSVGItem = file.add(new CmdOpenSVG());
        saveItem = file.add(new CmdSave());
        savePGMLItem = file.add(new CmdSavePGML());
        saveSVGItem = file.add(new CmdSaveSVG());
        CmdPrint cmdPrint = new CmdPrint();
        printItem = file.add(cmdPrint);
        printPageSetupItem = file.add(new CmdPrintPageSetup(cmdPrint));
        prefsItem =
            file.add(
                new CmdOpenWindow(
                    "org.tigris.gef.base.PrefsEditor",
                    "Preferences..."));
        //file.add(new CmdClose());
        exitItem = file.add(new CmdExit());

        JMenu edit = new JMenu(Localizer.localize("GefBase", "Edit"));
        edit.setMnemonic('E');
        _menubar.add(edit);

        JMenu select = new JMenu(Localizer.localize("GefBase", "Select"));
        edit.add(select);
        selectAllItem = select.add(new CmdSelectAll());
        select.add(new CmdSelectNext(false));
        select.add(new CmdSelectNext(true));
        select.add(new CmdSelectInvert());

        edit.addSeparator();

        //edit.add(new CmdCut());
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
        //view.addSeparator();
        //view.add(new CmdZoomIn());
        //view.add(new CmdZoomOut());
        //view.add(new CmdZoomNormal());
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
        nudgeLeftItem = nudge.add(new CmdNudge(CmdNudge.LEFT));
        nudgeRightItem = nudge.add(new CmdNudge(CmdNudge.RIGHT));
        nudgeUpItem = nudge.add(new CmdNudge(CmdNudge.UP));
        nudgeDownItem = nudge.add(new CmdNudge(CmdNudge.DOWN));

        KeyStroke ctrlN =
            KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK);
        KeyStroke ctrlO =
            KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK);
        KeyStroke ctrlS =
            KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK);
        KeyStroke ctrlP =
            KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_MASK);
        KeyStroke altF4 =
            KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK);

        KeyStroke leftArrow  = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
        KeyStroke rightArrow = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
        KeyStroke upArrow    = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
        KeyStroke downArrow  = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);

        KeyStroke sLeftArrow, sRightArrow, sUpArrow, sDownArrow;
        sLeftArrow =
            KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, KeyEvent.SHIFT_MASK);
        sRightArrow =
            KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.SHIFT_MASK);
        sUpArrow = KeyStroke.getKeyStroke(KeyEvent.VK_UP, KeyEvent.SHIFT_MASK);
        sDownArrow =
            KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, KeyEvent.SHIFT_MASK);

        KeyStroke delKey,
            ctrlC,
            ctrlV,
            ctrlG,
            ctrlU,
            ctrlB,
            ctrlF,
            sCtrlB,
            sCtrlF;
        delKey = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, KeyEvent.CTRL_MASK);
        ctrlC = KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK);
        ctrlV = KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK);
        ctrlG = KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_MASK);
        ctrlU = KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_MASK);
        ctrlB = KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_MASK);
        ctrlF = KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_MASK);
        sCtrlB =
            KeyStroke.getKeyStroke(
                KeyEvent.VK_B,
                KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK);
        sCtrlF =
            KeyStroke.getKeyStroke(
                KeyEvent.VK_F,
                KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK);

        openItem.setAccelerator(ctrlO);
        saveItem.setAccelerator(ctrlS);
        printItem.setAccelerator(ctrlP);
        exitItem.setAccelerator(altF4);

        deleteItem.setAccelerator(delKey);
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

    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b) {
            Globals.setStatusBar(this);
        }
    }
    ////////////////////////////////////////////////////////////////
    // IStatusListener implementation

    /** Show a message in the statusbar. */
    public void showStatus(String msg) {
        if (_statusbar != null)
            _statusbar.setText(msg);
    }

}
