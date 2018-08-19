/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author changruizhou
 */
public class GoLoText extends GoLoComponentPrototype {
    
    boolean isBold = false;
    boolean isItalic = false;
    final FontWeight BOLD = FontWeight.BOLD;
    final FontWeight NORMAL = FontWeight.NORMAL;
    final FontPosture ITALIC = FontPosture.ITALIC;
    final FontPosture REGULAR = FontPosture.REGULAR;
    
    public GoLoText(String text) {
        goLoNode = new Text(text);
        type = new SimpleStringProperty("Text");
        ((Text)goLoNode).setFont(Font.font("Times New Roman", NORMAL, REGULAR, 22));
        ((Text)goLoNode).setTextOrigin(VPos.TOP);
        name = new SimpleStringProperty("Default Text");
    }
    
    public GoLoText(double x, double y, String text, TextAlignment alignment, VPos origin, 
            TextBoundsType boundsType, Font font, Paint fill) {
        goLoNode = new Text(x, y, text);
        ((Text)goLoNode).setTextAlignment(alignment);
        ((Text)goLoNode).setTextOrigin(origin);
        ((Text)goLoNode).setBoundsType(boundsType);
        ((Text)goLoNode).setFont(font);
        ((Text)goLoNode).setFill(fill);
        type = new SimpleStringProperty("Text");
        name = new SimpleStringProperty("Default Text");

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
                .add("fontFamily",((Text)goLoNode).getFont().getFamily())
                .add("fontSize", ((Text)goLoNode).getFont().getSize())
                .add("bold", isBold)
                .add("italic", isItalic)
                .add("fill", ((Text)goLoNode).getFill().toString());
        return jsonNode;
    }
    
    @Override
    public Object clone() {
        double x = ((Text)goLoNode).getX();
        double y = ((Text)goLoNode).getY();
        String text = ((Text)goLoNode).getText();
        TextAlignment alignment = ((Text)goLoNode).getTextAlignment();
        VPos origin = ((Text)goLoNode).getTextOrigin();
        TextBoundsType boundsType = ((Text)goLoNode).getBoundsType();
        String fontFamily = ((Text)goLoNode).getFont().getFamily();
        double size = ((Text)goLoNode).getFont().getSize();
        FontWeight tempW;
        FontPosture tempP;
        if(isBold)
            tempW = BOLD;
        else
            tempW =NORMAL;
        if(isItalic)
            tempP = ITALIC;
        else
            tempP =REGULAR;
        Font font = Font.font(fontFamily, tempW, tempP, size);
        Paint color = ((Text)goLoNode).getFill();
        GoLoText cloned = new GoLoText(x, y, text, alignment, origin, boundsType, font, color);
        cloned.setIsBold(isBold);
        cloned.setIsItalic(isItalic);
        cloned.setName(this.getName());
        return cloned;
    }
    
    @Override
    public void loadFromJson(JsonObject jsonItem, String name) {
        String text = jsonItem.getString("text");
        double x = getDataAsDouble(jsonItem, "x");
        double y = getDataAsDouble(jsonItem, "y");
        TextAlignment alignment = TextAlignment.valueOf(jsonItem.getString("alignment"));
        VPos origin = VPos.valueOf(jsonItem.getString("origin"));
        TextBoundsType boundsType = TextBoundsType.valueOf(jsonItem.getString("boundsType"));
        String fontFamily = jsonItem.getString("fontFamily");
        double fontSize = getDataAsDouble(jsonItem, "fontSize");
        boolean bold = jsonItem.getBoolean("bold");
        boolean italic = jsonItem.getBoolean("italic");
        setIsBold(bold);
        setIsItalic(italic);
        FontWeight weight= NORMAL;
        FontPosture posture = REGULAR;
        if(isBold)
            weight = BOLD;
        if(isItalic)
            posture = ITALIC;
        Font font = Font.font(fontFamily, weight, posture, fontSize);
        Color color = Color.valueOf(jsonItem.getString("fill"));
        goLoNode = new Text(x, y, text);
        type = new SimpleStringProperty("Text");
        ((Text)goLoNode).setTextAlignment(alignment);
        ((Text)goLoNode).setTextOrigin(origin);
        ((Text)goLoNode).setBoundsType(boundsType);
        ((Text)goLoNode).setFont(font);
        ((Text)goLoNode).setFill(color); 
        setName(name);
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
    
    public boolean isBold() {
        return isBold;
    }
    
    public boolean isItalic() {
        return isItalic;
        
    }    
    
    public void setIsBold(Boolean b) {
        isBold = b;
    }
    
    public void setIsItalic(Boolean b) {
        isItalic = b;
    }
}
