/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.data;

import glgl.workspace.controllers.GoLoNodeController;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author changruizhou
 */
public abstract class GoLoComponentPrototype implements Cloneable{
    
    protected Node goLoNode;
    protected StringProperty type = new SimpleStringProperty();
    protected StringProperty name = new SimpleStringProperty();
    protected IntegerProperty order;
    protected final DoubleProperty xDiff = new SimpleDoubleProperty();
    protected final DoubleProperty yDiff = new SimpleDoubleProperty();
    protected final DoubleProperty oldX = new SimpleDoubleProperty();
    protected final DoubleProperty oldY = new SimpleDoubleProperty();

    public GoLoComponentPrototype() {
        order = new SimpleIntegerProperty(0);
    }
    
    public Node getGoLoNode() {
        return goLoNode;
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
    public Object clone() {
        return null;
    }
    public double getOldX() {
        return oldX.get();
    }
    public double getOldY() {
        return oldY.get();
    };
    
    public boolean isMoved() {
        return (oldX.get() != this.getX() || oldY.get()!= this.getY());
    }
    public abstract double getX();
    
    public abstract double getY();

    public abstract void setCoordinate(double x, double y);
    
    public abstract JsonObjectBuilder getJsonNode();
    
}
