package org.tigris.gefdemo.classdiagram.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

abstract class UmlModelElementImpl implements UmlModelElement {
    
    private String name;

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

} /* end class NetPrimitive */
