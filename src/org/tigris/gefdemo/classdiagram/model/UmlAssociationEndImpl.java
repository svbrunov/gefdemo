package org.tigris.gefdemo.classdiagram.model;

/** A NetEdge subclass to represent a dependency between tagets.
 */

class UmlAssociationEndImpl
        extends UmlModelElementImpl
        implements UmlAssociationEnd {
    
    private UmlAssociation association;
    private UmlClassifier classifier;
    
    
    /** Construct a new Depends. */
    public UmlAssociationEndImpl(UmlAssociation association, UmlClassifier classifier) {
        this.association = association;
        this.classifier = classifier;
    }

    public String getId() {
        return toString();
    }
    /**
     * @return
     */
    public UmlAssociation getAssociation() {
        return association;
    }

    /**
     * @return
     */
    public UmlClassifier getClassifier() {
        return classifier;
    }

}
