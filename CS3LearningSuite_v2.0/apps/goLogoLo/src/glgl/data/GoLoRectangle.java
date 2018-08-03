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
        goLoShape = new Rectangle(80,40);
        type = new SimpleStringProperty("Rectangle");
        
    }
    public GoLoRectangle(double width, double height) {
        goLoShape = new Rectangle(width, height);
        type = new SimpleStringProperty("Rectangle");
        
    }
    
    
}
