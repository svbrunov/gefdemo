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
        UmlAssociationImpl newAssociation = (UmlAssociationImpl)association;
        UmlAssociationImpl oldAssociation = (UmlAssociationImpl)this.association;
        if (oldAssociation == association) return;
        if (oldAssociation != null) {
            oldAssociation.removeAssociationEnd(this);
        }
        this.association = association;
        if (newAssociation != null) {
            newAssociation.addAssociationEnd(this);
        }
    }

    /**
     * @param classifier The classifier to set.
     */
    public void setClassifier(UmlClassifier classifier) {
        UmlClassifierImpl newClassifier = (UmlClassifierImpl)classifier;
        UmlClassifierImpl oldClassifier = (UmlClassifierImpl)this.classifier;
        if (oldClassifier == classifier) return;
        if (oldClassifier != null) {
            oldClassifier.removeAssociationEnd(this);
        }
        this.classifier = newClassifier;
        if (newClassifier != null) {
            newClassifier.addAssociationEnd(this);
        }
    }
}
