/*
 * Created on 03-Jun-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.tigris.gefdemo.classdiagram.model;

import java.beans.PropertyChangeListener;

/**
 * 
 * @author Bob Tarling
 * @since 03-Jun-2004
 */
public interface UmlModelElement {
    /** Draw the user's attention to any and all visualizations of this
     *  net-level object. */
    public abstract boolean getHighlight();
    public abstract void setHighlight(boolean b);
    public abstract String getId();
    ////////////////////////////////////////////////////////////////
    public abstract void addPropertyChangeListener(PropertyChangeListener l);
    public abstract void removePropertyChangeListener(PropertyChangeListener l);
    public abstract void firePropertyChange(
        String pName,
        Object oldV,
        Object newV);
    public abstract void firePropertyChange(
        String pName,
        boolean oldV,
        boolean newV);
    public abstract void firePropertyChange(String pName, int oldV, int newV);
    
    public String getName();
    public void setName(String name);
}