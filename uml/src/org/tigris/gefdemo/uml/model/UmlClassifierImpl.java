package org.tigris.gefdemo.uml.model;

import java.util.ArrayList;
import java.util.Collection;

class UmlClassifierImpl extends UmlModelElementImpl implements UmlClassifier {

    private Collection umlAssociationEnds = new ArrayList();
    
    /**
     * Return all the association ends of this association
     */
    public Collection getAssociationEnds() {
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
