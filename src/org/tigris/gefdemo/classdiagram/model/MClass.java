package org.tigris.gefdemo.classdiagram.model;

import org.tigris.gef.base.Layer;
import org.tigris.gef.presentation.FigNode;

import org.tigris.gefdemo.classdiagram.ui.ClassFig;

public class MClass extends MClassifier {

    private ClassifierPort dependsPort;
    
    private String name="";
    
    public FigNode makePresentation(Layer lay) {
        
        ClassFig figNode = new ClassFig();
        dependsPort = new ClassifierPort(this);
        addPort(dependsPort);
        figNode.bindPort(dependsPort, figNode.getBoundryFig());
        figNode.setOwner(this);
        return figNode;
    }
}
