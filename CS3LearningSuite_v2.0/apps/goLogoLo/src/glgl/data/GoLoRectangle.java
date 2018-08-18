/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.data;

import java.math.BigDecimal;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author changruizhou
 */
public class GoLoRectangle extends GoLoComponentPrototype {

    DoubleProperty oldWidth = new SimpleDoubleProperty();
    DoubleProperty oldHeight = new SimpleDoubleProperty();
    DoubleProperty oldArc = new SimpleDoubleProperty();
    
    public GoLoRectangle() {
        goLoNode = new Rectangle(300,150);
        ((Rectangle)goLoNode).setFill(fill);
        type = new SimpleStringProperty("Rectangle"); 
        name = new SimpleStringProperty("Default Rectangle");  
        ((Rectangle)goLoNode).setStroke(Color.WHITE);
        ((Rectangle)goLoNode).setStrokeWidth(0);
        ((Rectangle)goLoNode).setStrokeType(StrokeType.INSIDE);
    }
    
    public GoLoRectangle(double x, double y, double width, double height, Paint fill) {
        goLoNode = new Rectangle(x, y, width, height);
        ((Rectangle)goLoNode).setFill(fill);
        ((Rectangle)goLoNode).setStrokeType(StrokeType.INSIDE);
        type = new SimpleStringProperty("Rectangle");
        name = new SimpleStringProperty("Default Rectangle");  

    }
    
    @Override
    public  JsonObjectBuilder getJsonNode() {
        JsonObjectBuilder jsonNode = Json.createObjectBuilder()
                .add("x", ((Rectangle)goLoNode).getX())
                .add("y", ((Rectangle)goLoNode).getY())
                .add("width", ((Rectangle)goLoNode).getWidth())
                .add("height", ((Rectangle)goLoNode).getHeight())
                .add("fill", ((Rectangle)goLoNode).getFill().toString())
                .add("strokeColor", ((Rectangle)goLoNode).getStroke().toString())
                .add("arc", ((Rectangle)goLoNode).getArcHeight())
                .add("strokeWidth", ((Rectangle)goLoNode).getStrokeWidth());
        return jsonNode;
    }
    
    @Override
    public void loadFromJson(JsonObject jsonItem, String name) {
        double x = getDataAsDouble(jsonItem, "x");
        double y = getDataAsDouble(jsonItem, "y");
        double width = getDataAsDouble(jsonItem, "width");
        double height = getDataAsDouble(jsonItem, "height");
        RadialGradient rg = RadialGradient.valueOf(jsonItem.getString("fill"));
        Color stroke = Color.valueOf(jsonItem.getString("strokeColor"));
        double arc = getDataAsDouble(jsonItem, "arc");
        double strokeWidth = getDataAsDouble(jsonItem, "strokeWidth");
        goLoNode = new Rectangle(x, y, width, height);
        ((Rectangle)goLoNode).setArcWidth(arc);
        ((Rectangle)goLoNode).setArcHeight(arc);
        oldArc.set(arc);
        fill = rg;
        oldStrokeWidth.set(strokeWidth);
        ((Rectangle)goLoNode).setFill(rg);
        ((Rectangle)goLoNode).setStroke(stroke);
        ((Rectangle)goLoNode).setStrokeWidth(strokeWidth);
        ((Rectangle)goLoNode).setStrokeType(StrokeType.INSIDE);

        setName(name);
    }
    
    @Override
    public Object clone() {
        double x = ((Rectangle)goLoNode).getX();
        double y = ((Rectangle)goLoNode).getY();
        double width = ((Rectangle)goLoNode).getWidth();
        double height = ((Rectangle)goLoNode).getHeight();
        Paint rg = ((Rectangle)goLoNode).getFill();
        GoLoRectangle cloned = new GoLoRectangle(x, y, width, height, rg);
        Rectangle rc = (Rectangle)cloned.getGoLoNode();
        rc.setArcHeight(oldArc.get());
        rc.setArcWidth(oldArc.get());
        rc.setStroke(((Rectangle)goLoNode).getStroke());
        rc.setStrokeWidth(oldStrokeWidth.get());
        cloned.setOldArc(oldArc.get());
        cloned.setOldStrokeWidth(oldStrokeWidth.get());
        cloned.setName(this.getName());
        cloned.setFill(fill);
        return cloned;
    }
    
    
    @Override
    public void setCoordinate(double x, double y) {
        ((Rectangle)goLoNode).setX(x);
        ((Rectangle)goLoNode).setY(y);
    }
    
    @Override
    public double getX() {
        return ((Rectangle)goLoNode).getX();
    }
    
    @Override
    public double getY() {
        return ((Rectangle)goLoNode).getY();
    }
    
    public double getOldWidth() {
        return oldWidth.get();
    }
    
    public double getOldHeight() {
        return oldHeight.get();
    }

    public void setOldArc(double get) {
        oldArc.set(get);
    }
    
    public double getOldArc() {
        return oldArc.get();
    }
    
}

