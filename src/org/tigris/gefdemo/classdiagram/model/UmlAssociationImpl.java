package org.tigris.gefdemo.classdiagram.model;

import java.util.ArrayList;
import java.util.List;

class UmlAssociationImpl extends UmlModelElementImpl implements UmlAssociation {

    private List umlAssociationEnds = new ArrayList();
    
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
        umlAssociationEnds.remove(associationEnd);
    }
}
