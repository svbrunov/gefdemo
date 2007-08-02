package org.tigris.gefdemo.basic;

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

import org.tigris.gef.base.*;
import org.tigris.gef.graph.presentation.DefaultGraphModel;
import org.tigris.gef.graph.presentation.GraphFrame;
import org.tigris.gef.util.*;
import org.tigris.gef.swing.*;
import org.tigris.gefdemo.basic.SampleNode;
import org.tigris.gefdemo.basic.SamplePalette;

import org.eclipse.swt.*;

/** A simple example of the minimum code needed to build an
 *  application using GEF. */

public class BasicApplication {

    private GraphFrame graphFrame;

    public BasicApplication() {
        SwingInit();
    }
    
    public void SwingInit(){
        // init localizer and resourceloader
        Localizer.addResource("GefBase","org.tigris.gef.base.BaseResourceBundle");
        Localizer.addResource("GefPres","org.tigris.gef.presentation.PresentationResourceBundle");
        Localizer.addLocale(Locale.getDefault());
        Localizer.switchCurrentLocale(Locale.getDefault());
        ResourceLoader.addResourceExtension("gif");
        ResourceLoader.addResourceLocation("/org/tigris/gef/Images");
        graphFrame = new JGraphFrame();

        graphFrame.setToolBar(new SamplePalette()); //needs-more-work
    
        // make the delete key remove elements from the underlying GraphModel
        //_jgf.getGraph().bindKey(new CmdDispose(), KeyEvent.VK_DELETE, 0);
    
        graphFrame.setBounds(10, 10, 300, 200);
        graphFrame.setVisible(true);
        LayerManager lm =  graphFrame.getGraph().getEditor().getLayerManager();
        LayerPerspective lay = (LayerPerspective) lm.getActiveLayer();
        lay.addNodeTypeRegion(SampleNode.class, new Rectangle(10, 10, 200, 200));
        DefaultGraphModel dgm = (DefaultGraphModel) graphFrame.getGraphModel();
        for (int i = 0; i < 1; i++) {
            SampleNode sn = new SampleNode();
            sn.initialize(null);
            dgm.addNode(sn);
        }
    }
    public void SWTInit() {
        // init localizer and resourceloader
     }   
    public BasicApplication(String mode) {
         if (mode=="SWT")
             SWTInit();
         else
             SwingInit();
    }

    public static void main(String args[]) {
    	BasicApplication demo;
    	if (args.length>0)
    		demo = new BasicApplication(args[1]);
    	else
    		demo = new BasicApplication("");
    		
    }
}