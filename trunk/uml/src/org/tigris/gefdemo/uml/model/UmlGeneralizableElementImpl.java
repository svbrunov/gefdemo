package org.tigris.gefdemo.uml.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class UmlGeneralizableElementImpl extends UmlModelElementImpl implements UmlGeneralizableElement {

    private Collection umlGeneralizations = new ArrayList();
    private Collection umlSpecializations = new ArrayList();
    
    public UmlGeneralizableElementImpl(UmlNamespace ns) {
        super(ns);
    }
    
    
    /**
     * Return all the generalizations of this generalizable element
     */
    public List getGeneralizations() {
        return new ArrayList(umlGeneralizations);
    }

    /**
     * Return all the specializations of this generalizable element
     */
    public List getSpecializations() {
        return new ArrayList(umlSpecializations);
    }

    /**
     * Add a new generalization end to this generalizable element
     */
    public void addGeneralization(UmlGeneralization gen) {
        umlGeneralizations.add(gen);
    }

    /**
     * Remove a generalization end from this generalizable element
     */
    public void removeGeneralization(UmlGeneralization gen) {
        umlGeneralizations.remove(gen);
    }

    /**
     * Add a new specialization end to this generalizable element
     */
    public void addSpecialization(UmlGeneralization gen) {
        umlSpecializations.add(gen);
    }

    /**
     * Remove a specialization end from this generalizable element
     */
    public void removeSpecialization(UmlGeneralization gen) {
        umlSpecializations.remove(gen);
    }
}
