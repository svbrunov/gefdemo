package org.tigris.gefdemo.classdiagram;

import java.awt.event.*;
import java.util.Locale;

import org.tigris.gef.util.*;
import org.tigris.gef.graph.GraphModel;
import org.tigris.gef.graph.GraphModelException;
import org.tigris.gef.graph.presentation.*;

/** A simple example of the minimum code needed to build an
 *  application using GEF.
 */

public class ClassDiagramDemo {

    private JGraphFrame graphFrame;

    public ClassDiagramDemo() {
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
        ResourceLoader.addResourceExtension("gif");
        ResourceLoader.addResourceLocation("/org/tigris/gef/Images");
        GraphModel gm = new UmlGraphModel();

        graphFrame = new JGraphFrame(gm);
        graphFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                graphFrame.dispose();
            }
            public void windowClosed(WindowEvent event) {
                System.exit(0);
            }
        });
        graphFrame.setToolBar(new SamplePalette()); //needs-more-work

        // make the delete key remove elements from the underlying GraphModel
        //graphFrame.getGraph().bindKey(new CmdDispose(), KeyEvent.VK_DELETE, 0);
        ClassDiagramRenderer renderer = new ClassDiagramRenderer();
        graphFrame.getGraph().setGraphNodeRenderer(renderer);
        graphFrame.getGraph().setGraphEdgeRenderer(renderer);
        
        try {
            graphFrame.getGraphModel().setConnectionConstrainer(ConnectionConstrainer.getInstance());
        } catch (GraphModelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        graphFrame.setBounds(10, 10, 300, 200);
        graphFrame.setVisible(true);
    }

    ////////////////////////////////////////////////////////////////
    // main

    public static void main(String args[]) {
        ClassDiagramDemo demo = new ClassDiagramDemo();
    }

} /* end class BasicApplication */
