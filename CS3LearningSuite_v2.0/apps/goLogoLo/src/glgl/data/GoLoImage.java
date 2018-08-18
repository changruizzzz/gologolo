/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author changruizhou
 */
public class GoLoImage extends GoLoComponentPrototype{
    
    String url;
    public GoLoImage(ImageView image, String fileName, String url) {
        goLoNode = image;
        type.set("Image");
        name.set(fileName);
        this.url = url;
        System.out.println(url);
        System.out.println(fileName);
    }
    
    public GoLoImage() {
        type.set("Image");
    }
    
    @Override
    public Object clone() {
        GoLoImage cloned = new GoLoImage();
        File file = new File(url);
        BufferedImage bufferedImage;
        ImageView iv = new ImageView();
        try {
            bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            iv.setImage(image);
            cloned.goLoNode = iv;
            cloned.setName(this.getName());
            cloned.setURL(this.getURL());
            cloned.setCoordinate(this.getX(), this.getY());
        } catch (IOException ex) {
            Logger.getLogger(GoLoImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cloned;
    }

    @Override
    public double getX() {
        return ((ImageView)goLoNode).getX();
    }

    @Override
    public double getY() {
        return ((ImageView)goLoNode).getY();
    }

    @Override
    public void setCoordinate(double x, double y) {
        ((ImageView)goLoNode).setX(x);
        ((ImageView)goLoNode).setY(y);
    }

    @Override
    public JsonObjectBuilder getJsonNode() {
        JsonObjectBuilder jsonNode = Json.createObjectBuilder()
                .add("x", ((ImageView)goLoNode).getX())
                .add("y", ((ImageView)goLoNode).getY())
                .add("url", url);
        return jsonNode;    
    }

    @Override
    public void loadFromJson(JsonObject jsonItem, String name) {
        double x = getDataAsDouble(jsonItem, "x");
        double y = getDataAsDouble(jsonItem, "y");
        url = jsonItem.getString("url");
        File file = new File(url);
        BufferedImage bufferedImage;
        ImageView iv = new ImageView();
        try {
            bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            iv.setImage(image);
            goLoNode = iv;
            setCoordinate(x, y);
            setName(name);
        } catch (IOException ex) {
            Logger.getLogger(GoLoImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getURL() {
        return url;
    }
    
    public void setURL(String url) {
        this.url = url;
    }
    
}
