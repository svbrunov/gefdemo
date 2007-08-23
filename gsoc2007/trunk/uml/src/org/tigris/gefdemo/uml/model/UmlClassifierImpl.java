package org.tigris.gefdemo.uml.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class UmlClassifierImpl extends UmlGeneralizableElementImpl implements UmlClassifier {

    private Collection umlAssociationEndRoles = new ArrayList();
    
    public UmlClassifierImpl(UmlNamespace ns) {
        super(ns);
    }
    
    
    /**
     * Return all the association ends of this association
     */
    public List getAssociationEndRoles() {
        return new ArrayList(umlAssociationEndRoles);
    }

    /**
     * Add a new association end to this association
     */
    public void addAssociationEndRole(UmlAssociationEnd associationEnd) {
        umlAssociationEndRoles.add(associationEnd);
    }

    /**
     * Remove an association end from this association
     */
    public void removeAssociationEndRole(UmlAssociationEnd associationEnd) {
        umlAssociationEndRoles.remove(associationEnd);
    }
}
