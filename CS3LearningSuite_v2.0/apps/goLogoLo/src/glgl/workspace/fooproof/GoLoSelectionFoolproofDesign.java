/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.workspace.fooproof;

import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;
import static glgl.GoLoPropertyType.GLGL_BOLD_BUTTON;
import static glgl.GoLoPropertyType.GLGL_BORDER_COLOR_PICKER;
import static glgl.GoLoPropertyType.GLGL_CENTER_X_SLIDER;
import static glgl.GoLoPropertyType.GLGL_CENTER_Y_SLIDER;
import static glgl.GoLoPropertyType.GLGL_CGRADIENT_RADIUS_SLIDER;
import static glgl.GoLoPropertyType.GLGL_CYCLE_METHOD_COMBO;
import static glgl.GoLoPropertyType.GLGL_DECREASE_FONT_SIZE_BUTTON;
import static glgl.GoLoPropertyType.GLGL_EDIT_ITEM_BUTTON;
import static glgl.GoLoPropertyType.GLGL_FOCUS_ANGLE_SLIDER;
import static glgl.GoLoPropertyType.GLGL_FOCUS_DISTANCE_SLIDER;
import static glgl.GoLoPropertyType.GLGL_FONT_COMBO;
import static glgl.GoLoPropertyType.GLGL_FONT_SIZE_COMBO;
import static glgl.GoLoPropertyType.GLGL_INCREASE_FONT_SIZE_BUTTON;
import static glgl.GoLoPropertyType.GLGL_ITALIC_BUTTON;
import static glgl.GoLoPropertyType.GLGL_MOVE_ITEM_DOWN_BUTTON;
import static glgl.GoLoPropertyType.GLGL_MOVE_ITEM_UP_BUTTON;
import static glgl.GoLoPropertyType.GLGL_RADIUS_SLIDER;
import static glgl.GoLoPropertyType.GLGL_REMOVE_BUTTON;
import static glgl.GoLoPropertyType.GLGL_STOP_0_COLOR_PICKER;
import static glgl.GoLoPropertyType.GLGL_STOP_1_COLOR_PICKER;
import static glgl.GoLoPropertyType.GLGL_TEXT_COLOR_PICKER;
import static glgl.GoLoPropertyType.GLGL_THICKNESS_SLIDER;
import glgl.GoLogoLoApp;
import glgl.data.GoLoComponentPrototype;
import glgl.data.GoLoData;
import glgl.data.GoLoRectangle;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author changruizhou
 */
public class GoLoSelectionFoolproofDesign implements FoolproofDesign {
    GoLogoLoApp app;
    
    public GoLoSelectionFoolproofDesign(GoLogoLoApp initApp) {
        app = initApp;
    }

    @Override
    public void updateControls() {
        AppGUIModule gui = app.getGUIModule();
       
        // CHECK AND SEE IF A TABLE ITEM IS SELECTED
        GoLoData data = (GoLoData)app.getDataComponent();
        boolean itemIsSelected = data.isItemSelected();
        boolean itemUpMovable = data.isUpMovable();
        boolean itemDownMovable = data.isDownMovable();
        boolean isImage;
        boolean isCircle;
        if(itemIsSelected){
            GoLoComponentPrototype temp = data.getSelectedItem();
            if(temp.isText()) {
                enableTextControls(true, gui);
                enableBorderControls(false, gui);
                enableGradientControls(false, gui);
            }
            
            else if(temp.isRectangle()){
                data.getNodeSelectionModel().updateAnchors((GoLoRectangle)temp);
                enableTextControls(false, gui);
                enableBorderControls(true, gui);
                enableGradientControls(true, gui);
            } else if(temp.isCircle()) {
                enableTextControls(false, gui);
                enableBorderControls(true, gui);
                enableGradientControls(true, gui);
                gui.getGUINode(GLGL_RADIUS_SLIDER).setDisable(true);   
            } else {
                enableTextControls(false, gui);
                enableBorderControls(false, gui);
                enableGradientControls(false, gui);
            }
            updateControlValue(temp, data);
        } else {
                enableTextControls(false, gui);
                enableBorderControls(false, gui);
                enableGradientControls(true, gui);
        }            
        gui.getGUINode(GLGL_REMOVE_BUTTON).setDisable(!(itemIsSelected));
        gui.getGUINode(GLGL_EDIT_ITEM_BUTTON).setDisable(!itemIsSelected);
        gui.getGUINode(GLGL_MOVE_ITEM_UP_BUTTON).setDisable(!itemUpMovable);
        gui.getGUINode(GLGL_MOVE_ITEM_DOWN_BUTTON).setDisable(!itemDownMovable);     
    }
    
    private void enableTextControls(boolean enable, AppGUIModule gui) {
        gui.getGUINode(GLGL_FONT_COMBO).setDisable(!enable);
        gui.getGUINode(GLGL_FONT_SIZE_COMBO).setDisable(!enable);
        gui.getGUINode(GLGL_INCREASE_FONT_SIZE_BUTTON).setDisable(!enable);
        gui.getGUINode(GLGL_DECREASE_FONT_SIZE_BUTTON).setDisable(!enable);
        gui.getGUINode(GLGL_TEXT_COLOR_PICKER).setDisable(!enable); 
        gui.getGUINode(GLGL_BOLD_BUTTON).setDisable(true);
        gui.getGUINode(GLGL_ITALIC_BUTTON).setDisable(true);
        if(enable) {
            Button boldButton = (Button)gui.getGUINode(GLGL_BOLD_BUTTON);
            Button italicButton = (Button)gui.getGUINode(GLGL_ITALIC_BUTTON);
            String fontFamily = (String)((ComboBox)gui.getGUINode(GLGL_FONT_COMBO)).getSelectionModel().getSelectedItem();
            if(!(Font.font(fontFamily, FontWeight.NORMAL, 20)).equals(Font.font(fontFamily, FontWeight.BOLD, 20))) 
                boldButton.setDisable(!enable);            
            if(!(Font.font(fontFamily, FontPosture.REGULAR, 20)).equals(Font.font(fontFamily, FontPosture.ITALIC, 20))) 
                italicButton.setDisable(!enable);
        }
    }

    private void enableGradientControls(boolean enable, AppGUIModule gui) {
        gui.getGUINode(GLGL_FOCUS_ANGLE_SLIDER).setDisable(!enable);  
        gui.getGUINode(GLGL_FOCUS_DISTANCE_SLIDER).setDisable(!enable);  
        gui.getGUINode(GLGL_CENTER_X_SLIDER).setDisable(!enable);  
        gui.getGUINode(GLGL_CENTER_Y_SLIDER).setDisable(!enable);  
        gui.getGUINode(GLGL_CGRADIENT_RADIUS_SLIDER).setDisable(!enable);  
        gui.getGUINode(GLGL_CYCLE_METHOD_COMBO).setDisable(!enable);  
        gui.getGUINode(GLGL_STOP_0_COLOR_PICKER).setDisable(!enable);  
        gui.getGUINode(GLGL_STOP_1_COLOR_PICKER).setDisable(!enable);    
    }

    private void enableBorderControls(boolean enable, AppGUIModule gui) {
        gui.getGUINode(GLGL_RADIUS_SLIDER).setDisable(!enable);   
        gui.getGUINode(GLGL_THICKNESS_SLIDER).setDisable(!enable);   
        gui.getGUINode(GLGL_BORDER_COLOR_PICKER).setDisable(!enable);       }
   
    private void updateControlValue(GoLoComponentPrototype component, GoLoData data) {
        data.setBlockValueListener(true);
        if(component.isText()) {
            ((ComboBox)app.getGUIModule().getGUINode(GLGL_FONT_COMBO)).getSelectionModel().select(((Text)component.getGoLoNode()).getFont().getFamily());
            ((ComboBox)app.getGUIModule().getGUINode(GLGL_FONT_SIZE_COMBO)).getSelectionModel().select(new Integer((int)((Text)component.getGoLoNode()).getFont().getSize()));
            ((ColorPicker)app.getGUIModule().getGUINode(GLGL_TEXT_COLOR_PICKER)).setValue((Color)((Text)component.getGoLoNode()).getFill());
        } 
        data.setBlockValueListener(false);
    }
}