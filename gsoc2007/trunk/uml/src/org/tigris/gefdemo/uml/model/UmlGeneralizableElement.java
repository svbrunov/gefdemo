/*
 * Created on 03-Jun-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.tigris.gefdemo.uml.model;

/**
 * 
 * @author Bob Tarling
 * @since 18-Jun-2006
 */
public abstract interface UmlGeneralizableElement {
    void addGeneralization(UmlGeneralization gen);
    void removeGeneralization(UmlGeneralization gen);
    void addSpecialization(UmlGeneralization gen);
    void removeSpecialization(UmlGeneralization gen);
}