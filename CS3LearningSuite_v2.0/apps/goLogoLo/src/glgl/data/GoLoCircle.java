/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.data;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author changruizhou
 */
public class GoLoCircle extends GoLoComponentPrototype{
    
    DoubleProperty oldRadius = new SimpleDoubleProperty();
    public GoLoCircle() {
        name = new SimpleStringProperty("Default Circle");
        type = new SimpleStringProperty("Circle");
        goLoNode = new Circle(50, 50, 50);
        ((Circle)goLoNode).setStroke(Color.WHITE);
        ((Circle)goLoNode).setStrokeWidth(0);
        ((Circle)goLoNode).setStrokeType(StrokeType.INSIDE);
        ((Circle)goLoNode).setFill(fill);
        oldRadius.setValue(50);
    }

    public GoLoCircle(double x, double y, double radius, Paint fill) {
        type = new SimpleStringProperty("Circle");
        goLoNode = new Circle(x, y , radius);
        ((Circle)goLoNode).setStrokeType(StrokeType.INSIDE);
        ((Circle)goLoNode).setFill(fill);    
        oldRadius.setValue(radius);
    }

    @Override
    public double getX() {
        return ((Circle)goLoNode).getCenterX();
    }

    @Override
    public double getY() {
        return ((Circle)goLoNode).getCenterY();
    }

    @Override
    public void setCoordinate(double x, double y) {
        ((Circle)goLoNode).setCenterX(x);
        ((Circle)goLoNode).setCenterY(y); 
    }

    @Override
    public  JsonObjectBuilder getJsonNode() {
        JsonObjectBuilder jsonNode = Json.createObjectBuilder()
                .add("x", ((Circle)goLoNode).getCenterX())
                .add("y", ((Circle)goLoNode).getCenterY())
                .add("radius", ((Circle)goLoNode).getRadius())
                .add("fill", ((Circle)goLoNode).getFill().toString())                
                .add("strokeColor", ((Circle)goLoNode).getStroke().toString())
                .add("strokeWidth", ((Circle)goLoNode).getStrokeWidth());
        return jsonNode;
    }

    @Override
    public Object clone() {
        double x = ((Circle)goLoNode).getCenterX();
        double y = ((Circle)goLoNode).getCenterY();
        double r = ((Circle)goLoNode).getRadius();
        Paint rg = ((Circle)goLoNode).getFill();
        GoLoCircle cloned = new GoLoCircle(x, y, r, rg);
        Circle rc = (Circle)cloned.getGoLoNode();
        rc.setStroke(((Circle)goLoNode).getStroke());
        rc.setStrokeWidth(oldStrokeWidth.get());
        cloned.setOldStrokeWidth(oldStrokeWidth.get());
        cloned.setName(this.getName());
        cloned.setFill(fill);
        return cloned;
    }

    @Override
    public void loadFromJson(JsonObject jsonItem, String name) {
        double x = getDataAsDouble(jsonItem, "x");
        double y = getDataAsDouble(jsonItem, "y");
        double radius = getDataAsDouble(jsonItem, "radius");
        RadialGradient rg = RadialGradient.valueOf(jsonItem.getString("fill"));
        Color stroke = Color.valueOf(jsonItem.getString("strokeColor"));
        double strokeWidth = getDataAsDouble(jsonItem, "strokeWidth");
        goLoNode = new Circle(x, y , radius);
        ((Circle)goLoNode).setFill(rg); 
        ((Circle)goLoNode).setStroke(stroke);
        ((Circle)goLoNode).setStrokeWidth(strokeWidth);
        oldStrokeWidth.set(strokeWidth);
        ((Circle)goLoNode).setStrokeType(StrokeType.INSIDE);

        fill = rg;
        setName(name);
    }
    
    public void setOldRadius(double r) {
        oldRadius.set(r);
    }
    public double getOldRadius() {
        return oldRadius.get();
    }
}
