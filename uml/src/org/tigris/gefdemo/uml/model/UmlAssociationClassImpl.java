/*
 * Created on 06-Jan-2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.tigris.gefdemo.uml.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Bob Tarling
 * @since 06-Jan-2005
 */
public class UmlAssociationClassImpl extends UmlAssociationImpl implements UmlAssociationClass {

    private ArrayList umlAssociationEndRoles = new ArrayList();

    public UmlAssociationClassImpl(UmlNamespace ns) {
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
