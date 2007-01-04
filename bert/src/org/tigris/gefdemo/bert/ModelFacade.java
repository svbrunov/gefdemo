package org.tigris.gefdemo.bert;

import org.tigris.gefdemo.bert.model.Model;
import org.tigris.gefdemo.bert.model.ModelElement;

/**
 * A facade class to simplify access to the model.
 * In this case the model pretty mcuh fits our requirements
 * anyway but it is good practise to add another layer
 * of seperation here.
 * @author Bob Tarling
 * @since 09-Jun-2004
 */
public class ModelFacade {
    private static ModelFacade instance = new ModelFacade();
    
    public static ModelFacade getInstance() {
        return instance;
    }
    
    private Model umlModel = new Model();
    
    private ModelFacade() {
    }

    public Object createModelElement(Object identifier, Object fromPort, Object toPort) {
        // Spot any attempt to draw an association end between to classifiers
        return umlModel.createModelElement(identifier, (ModelElement)fromPort, (ModelElement)toPort);
    }
    
    public Object createModelElement(Object identifier) {
        return umlModel.createModelElement(identifier);
    }
    
    public void removeModelElement(Object element) {
        umlModel.removeModelElement((ModelElement)element);
    }
}
