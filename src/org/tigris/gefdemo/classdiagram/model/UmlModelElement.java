package org.tigris.gefdemo.classdiagram.model;

import java.beans.PropertyChangeListener;
import java.util.List;

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
    public void addSupplierDependency(UmlDependency dep);
    public void removeSupplierDependency(UmlDependency dep);
    public void addClientDependency(UmlDependency dep);
    public void removeClientDependency(UmlDependency dep);
}
