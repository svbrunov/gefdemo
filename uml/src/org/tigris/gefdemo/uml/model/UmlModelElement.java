package org.tigris.gefdemo.uml.model;

import java.beans.PropertyChangeListener;

/**
 * The base interface for all model elements
 * @author Bob Tarling
 * @since 03-Jun-2004
 */
public interface UmlModelElement {
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
