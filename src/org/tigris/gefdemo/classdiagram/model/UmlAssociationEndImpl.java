package org.tigris.gefdemo.classdiagram.model;

class UmlAssociationEndImpl
        extends UmlModelElementImpl
        implements UmlAssociationEnd {
    
    private UmlAssociation association;
    private UmlClassifier classifier;
    
    /** Construct a new Depends. */
    public UmlAssociationEndImpl() {

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

    /**
     * @param association The association to set.
     */
    public void setAssociation(UmlAssociation association) {
        this.association = association;
        association.addAssociationEnd(this);
    }

    /**
     * @param classifier The classifier to set.
     */
    public void setClassifier(UmlClassifier classifier) {
        this.classifier = classifier;
        classifier.addAssociationEnd(this);
    }
}
