package org.tigris.gefdemo.uml.model;

class UmlAssociationEndImpl
        extends UmlModelElementImpl
        implements UmlAssociationEnd {
    
    private UmlAssociation association;
    private UmlClassifier classifier;

    private boolean navigable = true;
    
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
        UmlClassifier newClassifier = classifier;
        UmlClassifier oldClassifier = this.classifier;
        if (oldClassifier == classifier) return;
        if (oldClassifier != null) {
            if (oldClassifier instanceof UmlAssociationClass) {
                ((UmlAssociationClassImpl)oldClassifier).removeAssociationEndRole(this);
            } else {
                ((UmlClassifierImpl)oldClassifier).removeAssociationEndRole(this);
            }
        }
        this.classifier = newClassifier;
        if (newClassifier != null) {
            if (newClassifier instanceof UmlAssociationClass) {
                ((UmlAssociationClassImpl)newClassifier).addAssociationEndRole(this);
            } else {
                ((UmlClassifierImpl)newClassifier).addAssociationEndRole(this);
            }
        }
    }
    
    public boolean isNavigable() {
        return navigable;
    }
}
