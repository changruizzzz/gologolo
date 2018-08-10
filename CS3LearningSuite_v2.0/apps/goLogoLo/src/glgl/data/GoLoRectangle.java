/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author changruizhou
 */
public class GoLoRectangle extends GoLoComponentPrototype {

    
    public GoLoRectangle() {
        goLoNode = new Rectangle(300,150);
        ((Rectangle)goLoNode).setFill(Color.ALICEBLUE);
        type = new SimpleStringProperty("Rectangle"); 
        name = new SimpleStringProperty("Default Rectangle");        
    }
    public GoLoRectangle(double x, double y, double width, double height, Paint fill) {
        goLoNode = new Rectangle(x, y, width, height);
        ((Rectangle)goLoNode).setFill(fill);
        type = new SimpleStringProperty("Rectangle"); 
    }
    
    @Override
    public  JsonObjectBuilder getJsonNode() {
        JsonObjectBuilder jsonNode = Json.createObjectBuilder()
                .add("x", ((Rectangle)goLoNode).getX())
                .add("y", ((Rectangle)goLoNode).getY())
                .add("width", ((Rectangle)goLoNode).getWidth())
                .add("height", ((Rectangle)goLoNode).getHeight())
                .add("fill", ((Rectangle)goLoNode).getFill().toString());
        return jsonNode;
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
    
    @Override
    public Object clone() {
        double x = ((Rectangle)goLoNode).getX();
        double y = ((Rectangle)goLoNode).getY();
        double width = ((Rectangle)goLoNode).getWidth();
        double height = ((Rectangle)goLoNode).getHeight();
        Paint fill = ((Rectangle)goLoNode).getFill();
        GoLoRectangle cloned = new GoLoRectangle(x, y, width, height, fill);
        cloned.setName(this.getName());
        return cloned;

        
    }
    
}
