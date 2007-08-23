package org.tigris.gefdemo.uml.model;

import java.util.ArrayList;
import java.util.List;

class UmlAssociationImpl 
        extends UmlGeneralizableElementImpl
        implements UmlAssociation {

    private List umlAssociationEnds = new ArrayList();
    
    public UmlAssociationImpl(UmlNamespace ns) {
        super(ns);
    }
    
    /**
     * Return all the association ends of this association
     */
    public List getAssociationEnds() {
        return new ArrayList(umlAssociationEnds);
    }

    /**
     * Add a new association end to this association
     */
    public void addAssociationEnd(UmlAssociationEnd associationEnd) {
        umlAssociationEnds.add(associationEnd);
    }

    /**
     * Remove an association end from this association
     */
    public void removeAssociationEnd(UmlAssociationEnd associationEnd) {
        System.out.println("Removing " + associationEnd + " from " + this);
        if (!umlAssociationEnds.remove(associationEnd)) {
            throw new IllegalArgumentException("Attempt to remove a non-existent association end");
        }
    }
}
