/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.VPos;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author changruizhou
 */
public class GoLoText extends GoLoComponentPrototype {
    
    public GoLoText(String text) {
        goLoNode = new Text(text);
        type = new SimpleStringProperty("Text");
        ((Text)goLoNode).setFont(new Font("Helvetica Light", 22));
        ((Text)goLoNode).setTextOrigin(VPos.TOP);
        name = new SimpleStringProperty("Default Text");
        goLoNode.setUserData(this);
    }
    
    public GoLoText(double x, double y, String text, TextAlignment alignment, VPos origin, 
            TextBoundsType boundsType, Font font, FontSmoothingType fontSmoothingType, Paint fill) {
        goLoNode = new Text(x, y, text);
        type = new SimpleStringProperty("Text");
        ((Text)goLoNode).setTextAlignment(alignment);
        ((Text)goLoNode).setTextOrigin(origin);
        ((Text)goLoNode).setBoundsType(boundsType);
        ((Text)goLoNode).setFont(font);
        ((Text)goLoNode).setFontSmoothingType(fontSmoothingType);
        ((Text)goLoNode).setFill(fill);   
        goLoNode.setUserData(this);

    }
    
    @Override
    public JsonObjectBuilder getJsonNode() {
        JsonObjectBuilder jsonNode = Json.createObjectBuilder()
                .add("x", ((Text)goLoNode).getX())
                .add("y", ((Text)goLoNode).getY())
                .add("text", ((Text)goLoNode).getText())
                .add("alignment", ((Text)goLoNode).getTextAlignment().toString())
                .add("origin", ((Text)goLoNode).getTextOrigin().toString())
                .add("boundsType", ((Text)goLoNode).getBoundsType().toString())
                .add("fontName",((Text)goLoNode).getFont().getName())
                .add("fontSize", ((Text)goLoNode).getFont().getSize())
                .add("fontSmoothingType", ((Text)goLoNode).getFontSmoothingType().toString())
                .add("fill", ((Text)goLoNode).getFill().toString());
        return jsonNode;
    }
    
    @Override
    public void setCoordinate(double x, double y) {
        ((Text)goLoNode).setX(x);
        ((Text)goLoNode).setY(y);
    }
    
    @Override
    public double getX() {
        return ((Text)goLoNode).getX();
    }
    
    @Override
    public double getY() {
        return ((Text)goLoNode).getY();
    }
    
    @Override
    public Object clone() {
        double x = ((Text)goLoNode).getX();
        double y = ((Text)goLoNode).getY();
        String text = ((Text)goLoNode).getText();
        TextAlignment alignment = ((Text)goLoNode).getTextAlignment();
        VPos origin = ((Text)goLoNode).getTextOrigin();
        TextBoundsType boundsType = ((Text)goLoNode).getBoundsType();
        String fontName = ((Text)goLoNode).getFont().getName();
        double size = ((Text)goLoNode).getFont().getSize();
        Font font = new Font(fontName, size);
        FontSmoothingType fontSmoothingType = ((Text)goLoNode).getFontSmoothingType();
        Paint fill = ((Text)goLoNode).getFill();
        GoLoText cloned = new GoLoText(x, y, text, alignment, origin, boundsType, font, fontSmoothingType, fill);
        cloned.setName(this.getName());
        return cloned;
    }
}
