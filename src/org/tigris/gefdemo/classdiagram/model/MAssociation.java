package org.tigris.gefdemo.classdiagram.model;

import java.awt.Color;
import java.io.Serializable;
import java.util.Hashtable;

import org.tigris.gef.base.Layer;
import org.tigris.gef.graph.GraphModel;
import org.tigris.gef.graph.presentation.NetNode;
import org.tigris.gef.presentation.FigNode;

import org.tigris.gefdemo.classdiagram.ui.AssociationFig;

public class MAssociation extends NetNode implements Serializable {

    private AssociationPort port;
    
    private String name="";
    private String description="";
    private String depends="";
    private String ifCondition="";
    private String unlessCondition="";
    
    /**
     * A virtual TargetNode represents a target in a file other
     * than the file represented by the graph containing the fig.
     */
    private boolean virtual;

    public FigNode makePresentation(Layer lay) {
        
        AssociationFig figNode = new AssociationFig();
        port = new AssociationPort(this);
        addPort(port);
        figNode.bindPort(port, figNode.getBoundryFig());
        figNode.setOwner(this);
        if (virtual) {
            figNode.getBoundryFig().setLineColor(Color.red);
        }
        return figNode;
    }
    
    public void setName(String name) {
        this.name = name;
        this.firePropertyChange("name", null, name);
    }
    
    public String getName() {
        return name;
    }

    public void initialize(Hashtable args) {
    }

    public String getId() {
        return getName();
    }
    
    /** Add the constraint that PortEther's can only be connected to
     * other ports of the same type. */
    public boolean canConnectTo(GraphModel gm, Object otherNode, Object otherPort, Object myPort) {
        return (super.canConnectTo(gm, otherNode, otherPort, myPort)) &&
            (otherNode.getClass() == this.getClass());
    }
    
    public String toString() {
        return name;
    }
}
