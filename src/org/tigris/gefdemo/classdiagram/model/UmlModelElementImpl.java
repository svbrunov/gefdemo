package org.tigris.gefdemo.classdiagram.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

abstract class UmlModelElementImpl implements java.io.Serializable, UmlModelElement {
    ////////////////////////////////////////////////////////////////
    // instance variables

    protected PropertyChangeSupport _changeSup =
        new PropertyChangeSupport(this);
    protected boolean _highlight = false;

    /** Construct a new net-level object, currently does nothing */
    public UmlModelElementImpl() {
    }

    /** Draw the user's attention to any and all visualizations of this
     *  net-level object. */
    public boolean getHighlight() {
        return _highlight;
    }

    public void setHighlight(boolean b) {
        boolean old = _highlight;
        _highlight = b;
        firePropertyChange("highlight", old, _highlight);
    }

    public abstract String getId();

    ////////////////////////////////////////////////////////////////
    // notifications and updates

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
            newV ? Boolean.TRUE : Boolean.FALSE);
    }

    public void firePropertyChange(String pName, int oldV, int newV) {
        _changeSup.firePropertyChange(
            pName,
            new Integer(oldV),
            new Integer(newV));
    }
} /* end class NetPrimitive */
