/*
 * Created on 03-Jun-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.tigris.gefdemo.classdiagram.model;

import org.tigris.gef.base.Layer;
import org.tigris.gef.presentation.FigEdge;

/**
 * 
 * @author Bob Tarling
 * @since 03-Jun-2004
 */
public interface UmlAssociationEnd {
    public abstract String getId();
    public abstract String getName();
    public abstract void setName(String name);
    public abstract FigEdge makePresentation(Layer lay);
}