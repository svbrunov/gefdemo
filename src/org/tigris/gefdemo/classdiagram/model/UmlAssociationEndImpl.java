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
        if (this.association == association) return;
        if (this.association != null) {
            this.association.removeAssociationEnd(this);
        }
        this.association = association;
        if (association != null) {
            association.addAssociationEnd(this);
        }
    }

    /**
     * @param classifier The classifier to set.
     */
    public void setClassifier(UmlClassifier classifier) {
        if (this.classifier == classifier) return;
        if (this.classifier != null) {
            this.classifier.removeAssociationEnd(this);
        }
        this.classifier = classifier;
        if (classifier != null) {
            classifier.addAssociationEnd(this);
        }
    }
}
