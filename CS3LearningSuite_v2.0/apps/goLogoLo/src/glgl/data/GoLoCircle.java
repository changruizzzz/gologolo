/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author changruizhou
 */
public class GoLoCircle extends GoLoComponentPrototype{
    
    public GoLoCircle() {
        name = new SimpleStringProperty("Default Circle");
        type = new SimpleStringProperty("Circle");
        goLoNode = new Circle(50, 50, 50);
        ((Circle)goLoNode).setFill(Color.CORAL);
        System.out.print(goLoNode.toString());
    }

    public GoLoCircle(double x, double y, double radius, Paint fill) {
        type = new SimpleStringProperty("Circle");
        goLoNode = new Circle(x, y , radius);
        ((Circle)goLoNode).setFill(fill);    
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
                .add("fill", ((Circle)goLoNode).getFill().toString());
        return jsonNode;
    }
    
}
