/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.shape.Shape;

/**
 *
 * @author changruizhou
 */
public class GoLoComponentPrototype {
    
    protected Shape goLoShape;
    protected StringProperty type;
    protected StringProperty name;
    protected IntegerProperty order;


    public GoLoComponentPrototype() {

        order = new SimpleIntegerProperty(0);
        name = new SimpleStringProperty("Layer " + order.get());
    }
    public Shape getShape() {
        return goLoShape;
    }
    
    public String getType() {
        return type.get();
    }
    
    public StringProperty typeProperty() {
        return type;
    }
    public StringProperty nameProperty() {
        return name;
    }
    public IntegerProperty orderProperty() {
        return order;
    }
    public String getName() {
        return name.get();
    }
    public void setName(String value) {
        name.set(value);
    }
    public void setType(String value) {
        type.set(value);
    }
    public void setOrder(int value) {
        order.set(value);
    }
    public int getOrder() {
        return order.get();
    }
}
