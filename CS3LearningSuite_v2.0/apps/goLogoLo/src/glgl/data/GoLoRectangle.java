/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author changruizhou
 */
public class GoLoRectangle extends GoLoComponentPrototype {
    
    public GoLoRectangle() {
        goLoShape = new Rectangle(xCoordinate, yCoordinate,750.0,750.0);
        goLoShape.setFill(Color.ALICEBLUE);
        type = new SimpleStringProperty("Rectangle");
        
    }
    
    
}