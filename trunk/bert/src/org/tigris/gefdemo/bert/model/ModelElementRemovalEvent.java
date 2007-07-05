/*
 * Created on 13-Jun-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.tigris.gefdemo.bert.model;

import java.util.EventObject;

/**
 * 
 * @author Bob Tarling
 * @since 13-Jun-2004
 */
public class ModelElementRemovalEvent extends EventObject {

    ModelElement modelElement;
    
    ModelElementRemovalEvent(Object source, ModelElement modelElement) {
        super(source);
        this.modelElement = modelElement;
    }
    
    /**
     * @return
     */
    public ModelElement getModelElement() {
        return modelElement;
    }

}
