package org.tigris.gefdemo.classdiagram.model;

import java.awt.Color;
import java.io.Serializable;
import java.util.Hashtable;

import org.tigris.gef.base.Layer;
import org.tigris.gef.graph.GraphModel;
import org.tigris.gef.graph.presentation.NetNode;
import org.tigris.gef.presentation.FigNode;

import org.tigris.gefdemo.classdiagram.ui.ClassFig;

abstract public class MClassifier extends NetNode implements Serializable {

    protected ClassifierPort dependsPort;
    
    private String name="";
    
    public void setName(String name) {
        this.name = name;
        this.firePropertyChange("name", null, name);
    }
    
    public String getName() {
        return name;
    }

    public void initialize(Hashtable args) {
    }

    public String getId() {
        return getName();
    }
    
    public String toString() {
        return name;
    }
}
