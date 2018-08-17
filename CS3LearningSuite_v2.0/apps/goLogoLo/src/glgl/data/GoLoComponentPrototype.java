/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.data;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

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
    protected final DoubleProperty oldStrokeWidth = new SimpleDoubleProperty();
    
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
    public abstract Object clone();
    
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
    
    public boolean isText() {
        return type.getValue().equals("Text");
    }
    
    public void markNode() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
        goLoNode.setEffect(dropShadow);
    }
    
    public void clearNode() {
        goLoNode.setEffect(null);
    }

    public boolean isRectangle() {
        return type.getValue().equals("Rectangle");
    }
    
    public abstract void loadFromJson(JsonObject jsonItem, String name);
    public double getDataAsDouble(JsonObject json, String dataName) {
	JsonValue value = json.get(dataName);
	JsonNumber number = (JsonNumber)value;
	return number.bigDecimalValue().doubleValue();	
    }

    public boolean isCircle() {
        return type.getValue().equals("Circle");
    }
    
    public boolean isIamge() {
        return type.getValue().equals("Image");
    }

    public void setOldStrokeWidth(double get) {
        oldStrokeWidth.set(get);
    }
    
    public double getOldStrokeWidth() {
        return oldStrokeWidth.get();
    }
}
