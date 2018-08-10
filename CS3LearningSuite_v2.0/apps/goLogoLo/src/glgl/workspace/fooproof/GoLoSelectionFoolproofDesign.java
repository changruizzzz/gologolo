/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.workspace.fooproof;

import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;
import static glgl.GoLoPropertyType.GLGL_BOLD_BUTTON;
import static glgl.GoLoPropertyType.GLGL_DECREASE_FONT_SIZE_BUTTON;
import static glgl.GoLoPropertyType.GLGL_EDIT_ITEM_BUTTON;
import static glgl.GoLoPropertyType.GLGL_FONT_COMBO;
import static glgl.GoLoPropertyType.GLGL_FONT_SIZE_COMBO;
import static glgl.GoLoPropertyType.GLGL_INCREASE_FONT_SIZE_BUTTON;
import static glgl.GoLoPropertyType.GLGL_ITALIC_BUTTON;
import static glgl.GoLoPropertyType.GLGL_MOVE_ITEM_DOWN_BUTTON;
import static glgl.GoLoPropertyType.GLGL_MOVE_ITEM_UP_BUTTON;
import static glgl.GoLoPropertyType.GLGL_REMOVE_BUTTON;
import static glgl.GoLoPropertyType.GLGL_UNDERLINE_BUTTON;
import glgl.GoLogoLoApp;
import glgl.data.GoLoComponentPrototype;
import glgl.data.GoLoData;

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
        boolean itemsAreSelected = data.areItemsSelected();
        boolean itemUpMovable = data.isUpMovable();
        boolean itemDownMovable = data.isDownMovable();
        boolean isText = false;
        boolean isImage;
        boolean isCircle;
        if(itemIsSelected){
            GoLoComponentPrototype temp = data.getSelectedItem();
            isText = temp.isText();
        }            
        gui.getGUINode(GLGL_REMOVE_BUTTON).setDisable(!(itemIsSelected));
        gui.getGUINode(GLGL_EDIT_ITEM_BUTTON).setDisable(!itemIsSelected);
        gui.getGUINode(GLGL_MOVE_ITEM_UP_BUTTON).setDisable(!itemUpMovable);
        gui.getGUINode(GLGL_MOVE_ITEM_DOWN_BUTTON).setDisable(!itemDownMovable);
        
        //Text Related Control
        gui.getGUINode(GLGL_FONT_COMBO).setDisable(!isText);
        gui.getGUINode(GLGL_FONT_SIZE_COMBO).setDisable(!isText);
        gui.getGUINode(GLGL_BOLD_BUTTON).setDisable(!isText);
        gui.getGUINode(GLGL_ITALIC_BUTTON).setDisable(!isText);
        gui.getGUINode(GLGL_INCREASE_FONT_SIZE_BUTTON).setDisable(!isText);
        gui.getGUINode(GLGL_DECREASE_FONT_SIZE_BUTTON).setDisable(!isText);
        gui.getGUINode(GLGL_UNDERLINE_BUTTON).setDisable(!isText);

    }
}