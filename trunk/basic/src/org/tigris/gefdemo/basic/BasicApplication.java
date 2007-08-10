package org.tigris.gefdemo.basic;

import java.awt.Rectangle;
import java.util.Locale;

import org.tigris.gef.base.LayerManager;
import org.tigris.gef.base.LayerPerspective;
import org.tigris.gef.graph.presentation.DefaultGraphModel;
import org.tigris.gef.graph.presentation.GraphFrame;
import org.tigris.gef.graph.presentation.Presentation;
import org.tigris.gef.graph.presentation.PresentationFactory;
import org.tigris.gef.ui.PaletteFig;
import org.tigris.gef.util.Localizer;
import org.tigris.gef.util.ResourceLoader;
import org.tigris.gefdemo.basic.SampleNode;
import org.tigris.gefdemo.basic.SamplePalette;

/** A simple example of the minimum code needed to build an
 *  application using GEF. */

public class BasicApplication {

    private GraphFrame graphFrame;

    public BasicApplication() {
        // init localizer and resourceloader
        Localizer.addResource("GefBase","org.tigris.gef.base.BaseResourceBundle");
        Localizer.addResource("GefPres","org.tigris.gef.presentation.PresentationResourceBundle");
        Localizer.addLocale(Locale.getDefault());
        Localizer.switchCurrentLocale(Locale.getDefault());
        ResourceLoader.addResourceExtension("gif");
        ResourceLoader.addResourceLocation("/org/tigris/gef/Images");
        graphFrame = PresentationFactory.getPresentation().createGraphFrame();

        //graphFrame.setToolBar(new SamplePalette()); //needs-more-work
        graphFrame.setToolBar(PresentationFactory.getPresentation().createPaletteFig()); //No Icons displayed
    
        graphFrame.setBounds(10, 10, 300, 300);
        graphFrame.setVisible(true);
        LayerManager lm =  graphFrame.getGraph().getEditor().getLayerManager();
        LayerPerspective lay = (LayerPerspective) lm.getActiveLayer();
        lay.addNodeTypeRegion(SampleNode.class, new Rectangle(10, 10, 200, 200));
        DefaultGraphModel dgm = (DefaultGraphModel) graphFrame.getGraphModel();
        for (int i = 0; i < 1; i++) {
            SampleNode sn1 = new SampleNode();
            SampleNode sn2 = new SampleNode();
            sn1.initialize(null);
            sn2.initialize(null);
            dgm.addNode(sn1);
            dgm.addNode(sn2);
        }
    }

    public static void main(String args[]) {
	
	if (args.length > 0) {
	    try {
                Class c = Class.forName(args[0]);
                PresentationFactory.setPresentation((Presentation) c.newInstance());
	    } catch (Exception e) {
		// fall back to swing if there's an exception
	    }
	}
    	BasicApplication demo = new BasicApplication();
    }
}