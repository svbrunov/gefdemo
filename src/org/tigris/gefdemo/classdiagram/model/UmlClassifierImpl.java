package org.tigris.gefdemo.classdiagram.model;

import java.util.Hashtable;

class UmlClassifierImpl extends UmlModelElementImpl implements UmlClassifier {

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
