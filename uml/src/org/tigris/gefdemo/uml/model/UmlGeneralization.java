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
 * @since 03-Jun-2004
 */
public interface UmlGeneralization extends UmlModelElement {
    public abstract String getId();
    public abstract void setParent(UmlGeneralizableElement gen);
    public abstract UmlGeneralizableElement getParent();
    public abstract void setChild(UmlGeneralizableElement gen);
    public abstract UmlGeneralizableElement getChild();
}