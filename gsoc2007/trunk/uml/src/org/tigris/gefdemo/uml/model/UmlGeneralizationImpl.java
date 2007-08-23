package org.tigris.gefdemo.uml.model;

/** A NetEdge subclass to represent a dependency between tagets.
 */

class UmlGeneralizationImpl 
        extends UmlModelElementImpl
        implements UmlGeneralization {
    
    UmlGeneralizableElement parent;
    UmlGeneralizableElement child;
    
    private String name;
    
    /** Construct a new Depends. */
    public UmlGeneralizationImpl(UmlNamespace ns) {
        super(ns);
    }

    public String getId() {
        return toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public UmlGeneralizableElement getChild() {
        return child;
    }
    public UmlGeneralizableElement getParent() {
        return parent;
    }
    /**
     * @param element
     */
    public void setChild(UmlGeneralizableElement child) {
        UmlGeneralizableElement oldChild = (UmlGeneralizableElement)this.child;
        UmlGeneralizableElement newChild = (UmlGeneralizableElement)child;
        if (oldChild == newChild) return;
        if (oldChild != null) {
            oldChild.removeGeneralization(this);
        }
        this.child = newChild;
        if (newChild != null) {
            newChild.addGeneralization(this);
        }
    }

    /**
     * @param element
     */
    public void setParent(UmlGeneralizableElement parent) {
        UmlGeneralizableElement oldParent = (UmlGeneralizableElement)this.parent;
        UmlGeneralizableElement newParent = (UmlGeneralizableElement)parent;
        if (oldParent == newParent) return;
        if (oldParent != null) {
            oldParent.removeSpecialization(this);
        }
        this.parent = newParent;
        if (newParent != null) {
            newParent.addSpecialization(this);
        }
    }

}
