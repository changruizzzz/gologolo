package glgl.workspace.controllers;

import glgl.transactions.CanvasRadient_Transaction;
import glgl.transactions.ColorGradient_Transaction;
import glgl.transactions.ChangeArc_Transaction;
import glgl.GoLogoLoApp;
import glgl.data.GoLoCircle;
import glgl.data.GoLoComponentPrototype;
import glgl.data.GoLoData;
import glgl.data.GoLoImage;
import glgl.data.GoLoRectangle;
import glgl.data.GoLoText;
import glgl.transactions.AddComponent_Transaction;
import glgl.transactions.ChangeBorderColor_Transaction;
import glgl.transactions.ChangeBorderWidth_Transaction;
import glgl.transactions.ChangeFont_Transaction;
import glgl.transactions.ChangeTextColor_Transaction;
import glgl.transactions.MoveComponent_Transaction;
import glgl.transactions.RemoveComponents_Transaction;
import glgl.transactions.Rename_Transaction;
import glgl.transactions.ResizeBackground_Transaction;
import glgl.workspace.dialogs.EditDialog;
import glgl.workspace.dialogs.TextDialog;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 *
 * @author ChangruiZhou
 */
public class ComponentController {
    GoLogoLoApp app;
    EditDialog editDialog;
    TextDialog textDialog;
    
    public ComponentController(GoLogoLoApp initApp) {
        app = initApp;
        editDialog = new EditDialog(app);
        textDialog = new TextDialog(app);
    }
    
    public void processResizeBackground() {
        editDialog.showResizeDialog();
        double width = editDialog.getBackgroundWidth();
        double height = editDialog.getBackgroundHeight();
        if(width >= 0 && height >= 0) {
            GoLoData data = (GoLoData)app.getDataComponent();
            ResizeBackground_Transaction transaction = new ResizeBackground_Transaction(data, width, height);
            app.processTransaction(transaction);
            editDialog.reset();
        } 
    }
    
    public void processRemoveItems() {
        GoLoData data = (GoLoData)app.getDataComponent();
        if (data.isItemSelected() || data.areItemsSelected()) {
            ArrayList<GoLoComponentPrototype> itemsToRemove = new ArrayList(data.getSelectedItems());
            RemoveComponents_Transaction transaction = new RemoveComponents_Transaction(data, itemsToRemove);
            app.processTransaction(transaction);
        }
    }
    public void processAddImage() {
            GoLoData data = (GoLoData)app.getDataComponent();
            FileChooser fileChooser = new FileChooser();
            ImageView iv = new ImageView();
            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
              
            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
            if(file != null) {           
                try {
                    BufferedImage bufferedImage = ImageIO.read(file);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    iv.setImage(image);
                    GoLoImage component = new GoLoImage(iv, file.getName(), file.getPath());
                    AddComponent_Transaction add = new AddComponent_Transaction(data, component);
                    app.processTransaction(add);
                } catch (IOException ex) {
                    System.out.print("WRONG");
                }
            }
 
    }    

    public void processAddRectangle() {
        GoLoData data = (GoLoData)app.getDataComponent();
        GoLoRectangle component = new GoLoRectangle();
        AddComponent_Transaction add = new AddComponent_Transaction(data, component);
        app.processTransaction(add);
    }
    
    public void processAddText() {
        GoLoData data = (GoLoData)app.getDataComponent();
        textDialog.showAddTextDialog();
        GoLoText component = textDialog.getGoLoText();
        if(component != null) {
            AddComponent_Transaction add = new AddComponent_Transaction(data, component);
            app.processTransaction(add);
        }
    }
    
    public void processRename() {
        editDialog.showRenameDialog();
        GoLoComponentPrototype tempComponent = ((GoLoData)app.getDataComponent()).getSelectedItem();
        if(editDialog.getNewName() != null) {
            Rename_Transaction rename = new Rename_Transaction(tempComponent, editDialog.getNewName());
            app.processTransaction(rename);
        }    
    }
    
    public void processMoveComponent(int movingType) {
        GoLoData data = (GoLoData)app.getDataComponent();
        GoLoComponentPrototype selected = data.getSelectedItem();
        MoveComponent_Transaction transaction = new MoveComponent_Transaction(data, selected, movingType);
        app.processTransaction(transaction);
    }

    public void processAddCircle() {
        GoLoData data = (GoLoData)app.getDataComponent();
        GoLoCircle component = new GoLoCircle();
        AddComponent_Transaction add = new AddComponent_Transaction(data, component);
        app.processTransaction(add);    
    }

    public void processChangeFont(String family, double size, boolean changeWeight, boolean changePosture) {
        GoLoData data = (GoLoData)app.getDataComponent();
        GoLoText selected = (GoLoText)data.getSelectedItem();    
        ChangeFont_Transaction ct = new ChangeFont_Transaction(selected, family, size, changeWeight, changePosture);
        app.processTransaction(ct);
    }

    public void changeBorderWidth(double get) {
        GoLoData data = (GoLoData)app.getDataComponent();
        GoLoComponentPrototype selected = data.getSelectedItem();
        ((Shape)selected.getGoLoNode()).setStrokeWidth(get);        

    }

    public void changeArc(double get) {
        GoLoData data = (GoLoData)app.getDataComponent();
        GoLoComponentPrototype selected = data.getSelectedItem();
        ((Rectangle)selected.getGoLoNode()).setArcHeight(get);
        ((Rectangle)selected.getGoLoNode()).setArcWidth(get);
    }

    public void processTextColor(Color value) {
        GoLoData data = (GoLoData)app.getDataComponent();
        GoLoComponentPrototype selected = data.getSelectedItem();
        Paint oldColor = ((Text)selected.getGoLoNode()).getFill();
        if(!oldColor.equals(value)) {
            ChangeTextColor_Transaction ctct = new ChangeTextColor_Transaction(selected, value);
            app.processTransaction(ctct);
        }
    }

    public void processBorderColor(Color value) {
        GoLoData data = (GoLoData)app.getDataComponent();
        GoLoComponentPrototype selected = data.getSelectedItem();
        ChangeBorderColor_Transaction cbct = new ChangeBorderColor_Transaction(selected, value);
        app.processTransaction(cbct);
    }

    public void processBorderWidth() {
        GoLoData data = (GoLoData)app.getDataComponent();
        GoLoComponentPrototype selected = data.getSelectedItem();
        ChangeBorderWidth_Transaction cbwt = new ChangeBorderWidth_Transaction(selected);
        app.processTransaction(cbwt);    
    }

    public void processArc() {
        GoLoData data = (GoLoData)app.getDataComponent();
        GoLoRectangle selected = (GoLoRectangle)data.getSelectedItem();
        if(selected.getOldArc() != ((Rectangle)selected.getGoLoNode()).getArcHeight()) {
            ChangeArc_Transaction cat = new ChangeArc_Transaction(selected);
            app.processTransaction(cat);
        }
    }

    public void processColorGradient() {
        GoLoData data = (GoLoData)app.getDataComponent();
        if(data.isItemSelected()) {
            GoLoComponentPrototype selected = data.getSelectedItem();
            ColorGradient_Transaction cgt = new ColorGradient_Transaction(selected);
            app.processTransaction(cgt);
        } else {
            CanvasRadient_Transaction crt = new CanvasRadient_Transaction(data);
            app.processTransaction(crt);
        }
    }

    public void changeColorGradient(RadialGradient rg) {
        GoLoData data = (GoLoData)app.getDataComponent();
        if(data.isItemSelected()) {
            GoLoComponentPrototype selected = data.getSelectedItem();
            ((Shape)selected.getGoLoNode()).setFill(rg);
        } else {
           data.changeFill(rg);
        }
    }


}
