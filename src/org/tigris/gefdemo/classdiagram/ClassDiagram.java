package org.tigris.gefdemo.classdiagram;

import java.awt.event.*;
import java.util.Locale;

import org.tigris.gef.base.*;
import org.tigris.gef.util.*;
import org.tigris.gef.graph.presentation.*;

/** A simple example of the minimum code needed to build an
 *  application using GEF. */

public class ClassDiagram {

    private JGraphFrame graphFrame;

    public ClassDiagram() {
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
        graphFrame = new JGraphFrame();
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
        graphFrame.getGraph().bindKey(new CmdDispose(), KeyEvent.VK_DELETE, 0);

        graphFrame.setBounds(10, 10, 300, 200);
        graphFrame.setVisible(true);
        System.out.println("finished adding nodes");
    }

    ////////////////////////////////////////////////////////////////
    // main

    public static void main(String args[]) {
        ClassDiagram demo = new ClassDiagram();
    }

} /* end class BasicApplication */
