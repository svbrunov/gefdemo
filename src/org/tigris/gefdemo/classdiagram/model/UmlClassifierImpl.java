package org.tigris.gefdemo.classdiagram.model;

class UmlClassifierImpl extends UmlModelElementImpl implements UmlClassifier {

    private String name="";
    
    public String getId() {
        return getName();
    }
    
    public String toString() {
        return name;
    }
}
