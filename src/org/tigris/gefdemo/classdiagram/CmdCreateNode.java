// $Id$
// Copyright (c) 1996-99 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies. This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason. IN NO EVENT SHALL THE
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
package org.tigris.gefdemo.classdiagram;

import java.util.Hashtable;
import java.util.Vector;

import javax.swing.Action;

import org.apache.log4j.Logger;

/**
 * Command to create nodes with the appropriate modelelement. The modelelement
 * is initialized via the build method on the UmlFactory.
 */
public class CmdCreateNode extends org.tigris.gef.base.CmdCreateNode {

    private static Logger LOG = Logger.getLogger(CmdCreateNode.class);

    private static Hashtable cache = new Hashtable();

    private static Object[] emptyParam = new Object[] {};

    private static Vector factoryMethods = new Vector();

    private static final String ACTION_PREFIX_KEY = "action.new";

    /**
     * Constructor for CmdCreateNode.
     * 
     * @param args
     *            a hastable of arguments
     * @param resource
     * @param name
     *            the tooltip name
     */
    public CmdCreateNode(Hashtable args, String resource, String name) {
        super(args, resource, name);
        putToolTip(name);
    }

    /**
     * Constructor for CmdCreateNode.
     * 
     * @param args
     * @param name
     */
    public CmdCreateNode(Hashtable args, String name) {
        super(args, name);
        putToolTip(name);
    }

    /**
     * Constructor for CmdCreateNode.
     * 
     * @param nodeClass
     *            the class for which to create a node, and which to create
     *            itself
     * @param resource
     * @param name
     *            the tooltip name
     */
    public CmdCreateNode(Class nodeClass, String resource, String name) {
        super(nodeClass, resource, name);
        putToolTip(name);
    }

    /**
     * Constructor for CmdCreateNode.
     * 
     * @param nodeClass
     *            the class for which to create a node, and which to create
     *            itself
     * @param name
     *            the tooltip name
     */
    public CmdCreateNode(Object nodeClass, String name) {
        super((Class) nodeClass, name);
        putToolTip(name);
    }

    /**
     * Constructor for CmdCreateNode.
     * 
     * @param nodeClass
     *            the class for which to create a node, and which to create
     *            itself
     * @param sticky
     * @param resource
     * @param name
     *            the tooltip name
     */
    public CmdCreateNode(Class nodeClass, boolean sticky, String resource,
            String name) {
        super(nodeClass, sticky, resource, name);
        putToolTip(name);
    }

    /**
     * Constructor for CmdCreateNode.
     * 
     * @param nodeClass
     *            the class for which to create a node, and which to create
     *            itself
     * @param sticky
     * @param name
     *            the tooltip name
     */
    public CmdCreateNode(Object nodeClass, boolean sticky, String name) {
        super((Class) nodeClass, sticky, name);
        putToolTip(name);
    }

    /**
     * Creates a modelelement using the uml model factories. If it finds a
     * suitable match it will but the factory and method of this factory in a
     * cache, so that subsequent lookups do not have to iterate through all
     * methods again. If no match is found it will delegate to
     * <code>super.makeNode()</code>.
     * TODO exception
     * @return an object which represents in most cases a particular UML
     *         Element.
     * 
     * @see org.tigris.gef.graph.GraphFactory#makeNode()
     * @see org.tigris.gef.base.CmdCreateNode#makeNode()
     */
    public Object makeNode() {
        Class nodeClass = (Class) getArg("className", DEFAULT_NODE_CLASS);
        try {
            return ModelFacade.getInstance().createModelElement(nodeClass);
        } catch (IllegalArgumentException e) {
            LOG.error("Exception caught", e);
        }
        return null;
    }

    /**
     * returns the name of the uml modelelement without impl, M or the fullname
     * 
     * @return String
     */
    private String getCreateClassName() {
        String name = ((Class) _args.get("className")).getName();
        name = name.substring(name.lastIndexOf('.') + 2, name.length());
        if (name.endsWith("Impl")) {
            name = name.substring(0, name.lastIndexOf("Impl"));
        }
        return name;
    }

    /**
     * Adds tooltip text to the Action.
     */
    private void putToolTip(String name) {
        putValue(Action.SHORT_DESCRIPTION, name);
    }
}