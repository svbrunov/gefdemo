package org.tigris.gefdemo.uml;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.Locale;

import org.tigris.gef.graph.GraphModel;
import org.tigris.gef.graph.GraphModelException;
import org.tigris.gef.util.Localizer;
import org.tigris.gef.util.ResourceLoader;

/** A simple example of the minimum code needed to build an
 *  application using GEF.
 */

public class ClassDiagramDemo {

    private GefGraphFrame graphFrame;

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
        ResourceLoader.addResourceLocation("/org/tigris/gefdemo/uml/Images");
        GraphModel gm = new UmlGraphModel();

        graphFrame = new GefGraphFrame("Class Diagram", gm);
        graphFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                graphFrame.dispose();
            }
            public void windowClosed(WindowEvent event) {
                System.exit(0);
            }
        });
        graphFrame.setToolBar(new SamplePalette()); //needs-more-work

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
        new ClassDiagramDemo();
    }

} /* end class BasicApplication */
