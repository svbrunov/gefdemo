// $Id$
// Copyright (c) 1996-2002 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies.  This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason.  IN NO EVENT SHALL THE
// UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
// SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
// ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
// THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
// PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
// UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

/*
 * UMLDiagramGraphModel.java
 *
 * Created on November 14, 2002, 10:20 PM
 */

package org.tigris.gefdemo.uml;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.tigris.gef.graph.MutableGraphSupport;
import org.tigris.gefdemo.uml.model.UmlAssociation;
import org.tigris.gefdemo.uml.model.UmlClassifier;


/** UMLGraphModel is a helper class which extends
 * MutableGraphSupport to provide additional helper and common methods
 * for UML Diagrams.
 */
public class UmlGraphModel extends MutableGraphSupport {
    
    private static Log LOG = LogFactory.getLog(UmlGraphModel.class);
    
    /** contains all the nodes in the graphmodel/diagram. */    
    protected ArrayList nodes = new ArrayList();
    /** constains all the edges in the graphmodel/diagram. */    
    protected ArrayList edges = new ArrayList();
    
    
    /** constructor.
     * @see org.tigris.gef.graph.MutableGraphSupport
     */    
    public UmlGraphModel() {
        super();
    }
    
    /** get all the nodes from the graphmodel/diagram
     * @see org.tigris.gef.graph.MutableGraphSupport#getNodes()
     * @return Vector of nodes in the graphmodel/diagram
     */    
    public List getNodes() {
        return (ArrayList)nodes.clone();
    }
    
    /** get all the edges from the graphmodel/diagram
     * @return Vector of edges in the graphmodel/diagram
     */    
    public List getEdges() {
        return (ArrayList)edges.clone();
    }
    
    /** Return a valid node in this graph */
    //public Object createNode(String name, Hashtable args) {
    //    return null;
    //}

    /** Add the given node to the graph, if valid. */
    public void addNode(Object node) {
        nodes.add(node);
        fireNodeAdded(node);
    }

    /** Add the given edge to the graph, if valid. */
    public void addEdge(Object edge) {
        edges.add(edge);
        fireEdgeAdded(edge);
    }

    
    public boolean containsNode(Object node) {
	return nodes.contains(node);
    }
    
    public boolean constainsEdge(Object edge) {
	return edges.contains(edge);
    }
    
    /** remove a node from the diagram and notify GEF
     * @param node node to remove
     */    
    public void removeNode(Object node) {
        if (!containsNode(node)) return;
        nodes.remove(node);
        fireNodeRemoved(node);
    }
    
    /** remove an edge from the graphmodel and notify GEF
     * TODO IllegalArgumentException or GraphModelException if edge not contained
     * @param edge edge to remove
     */    
    public void removeEdge(Object edge) {
	if (!containsEdge(edge)) return;
        edges.remove(edge);
        fireEdgeRemoved(edge);
    }
    
    /** Assume that anything can be connected to anything unless overridden
     * in a subclass.
     */
    public boolean canConnect(Object fromP, Object toP) {
        return true;
    }


    /** The connect method without specifying a connection
     * type is unavailable by default
     */
    public Object connect(Object fromPort, Object toPort) {
        throw new UnsupportedOperationException("The connect method is not supported");
    }

    /** Contruct and add a new edge of the given kind and connect
     * the given ports.
     *
     * @param fromPort   The originating port to connect
     *
     * @param toPort     The destination port to connect
     *
     * @param edgeClass  The NSUML type of edge to create.
     *
     * @return           The type of edge created (the same as
     *                   <code>edgeClass</code> if we succeeded,
     *                   <code>null</code> otherwise)
     */
    public Object connect(Object fromPort, Object toPort,
			  java.lang.Class edgeClass)
    {
        Object connection = null;
        
        if (canConnect(fromPort, toPort, edgeClass)) {
            connection = ModelFacade.getInstance().createModelElement(
                edgeClass,
                fromPort,
                toPort);
        }
        
        if (connection == null) {
            LOG.debug("Cannot make a " + edgeClass.getName() +
		      " between a " + fromPort.getClass().getName() +
		      " and a " + toPort.getClass().getName());
            return null;
        }
        
        addEdge(connection);
        LOG.debug("Connection type" + edgeClass.getName() +
		  " made between a " + fromPort.getClass().getName() +
		  " and a " + toPort.getClass().getName());
        return connection;
    }
    
    /** Return all ports on node or edge */
    public List getPorts(Object nodeOrEdge) {
        Vector res = new Vector();
        if (nodeOrEdge instanceof UmlClassifier) res.addElement(nodeOrEdge);
        // TODO Check if this association only has 2 edges
        else if (nodeOrEdge instanceof UmlAssociation) res.addElement(nodeOrEdge);
        return res;
    }

    /** Return the node or edge that owns the given port */
    public Object getOwner(Object port) {
        return port;
    }

    public void addNodeRelatedEdges(Object node) {
    }

    /** Return true if the given object is a valid edge in this graph */
    public boolean canAddEdge(Object edge) {
        return true;
    }
    
    /** Return true if the given object is a valid node in this graph */
    public boolean canAddNode(Object node) {
        return true;
    }
    
    /** Return one end of an edge */
    public Object getSourcePort(Object edge) {
        LOG.error("TODO getSourcePort");
        return null;
    }

    /** Return  the other end of an edge */
    public Object getDestPort(Object edge) {
        LOG.error("TODO getSourcePort");
        return null;
    }

    /** Return all edges going to given port */
    public List getInEdges(Object port) {
        return null;
    }

    /** Return all edges going from given port */
    public List getOutEdges(Object port) {
        return null;
    }
}
