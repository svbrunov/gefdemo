package org.tigris.gefdemo.uml.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

abstract class UmlModelElementImpl implements UmlModelElement {
    
    private String name;

    private List supplierDependencies = new ArrayList();
    private List clientDependencies = new ArrayList();
    
    protected PropertyChangeSupport _changeSup = new PropertyChangeSupport(this);
    protected boolean _highlight = false;

    /** Construct a new net-level object, currently does nothing */
    public UmlModelElementImpl() {
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        _changeSup.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _changeSup.removePropertyChangeListener(l);
    }

    public void firePropertyChange(String pName, Object oldV, Object newV) {
        _changeSup.firePropertyChange(pName, oldV, newV);
    }

    public void firePropertyChange(String pName, boolean oldV, boolean newV) {
        _changeSup.firePropertyChange(
            pName,
            oldV ? Boolean.TRUE : Boolean.FALSE,
            newV ? Boolean.TRUE : Boolean.FALSE
        );
    }

    public void firePropertyChange(String pName, int oldV, int newV) {
        _changeSup.firePropertyChange(
            pName,
            new Integer(oldV),
            new Integer(newV)
        );
    }
    /**
     * Get the name of this model element
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this model element
     * @param string
     */
    public void setName(String string) {
        name = string;
    }
    
    public void addClientDependency(UmlDependency dep) {
        clientDependencies.add(dep);
    }

    public void removeClientDependency(UmlDependency dep) {
        clientDependencies.remove(dep);
    }

    public void addSupplierDependency(UmlDependency dep) {
        supplierDependencies.add(dep);
    }

    public void removeSupplierDependency(UmlDependency dep) {
        supplierDependencies.remove(dep);
    }

} /* end class NetPrimitive */
