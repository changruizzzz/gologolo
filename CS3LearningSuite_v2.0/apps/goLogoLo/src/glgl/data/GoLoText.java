/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;

/**
 *
 * @author changruizhou
 */
public class GoLoText extends GoLoComponentPrototype {
    
    public GoLoText() {
        goLoShape = new Text("ENTER NEW TEXT");
        type = new SimpleStringProperty("Text");
        ((Text)goLoShape).setFont(new Font("Helvetica Light", 10));
    }
    
    public GoLoText(double x, double y, String text, TextAlignment alignment, VPos origin, 
            TextBoundsType boundsType, Font font, FontSmoothingType fontSmoothingType, Color fill) {
        goLoShape = new Text(x, y, text);
        type = new SimpleStringProperty("Text");
        ((Text)goLoShape).setTextAlignment(alignment);
        ((Text)goLoShape).setTextOrigin(origin);
        ((Text)goLoShape).setBoundsType(boundsType);
        ((Text)goLoShape).setFont(font);
        ((Text)goLoShape).setFontSmoothingType(fontSmoothingType);
        goLoShape.setFill(fill);

        
    }
}
