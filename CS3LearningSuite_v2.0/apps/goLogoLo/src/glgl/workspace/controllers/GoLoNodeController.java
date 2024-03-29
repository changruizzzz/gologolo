/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.workspace.controllers;

import glgl.GoLogoLoApp;
import glgl.data.GoLoCircle;
import glgl.data.GoLoComponentPrototype;
import glgl.data.GoLoRectangle;
import glgl.transactions.ChangeTextContent_Transaction;
import glgl.transactions.Moving_Transaction;
import glgl.transactions.ResizeRectangle_Transaction;
import glgl.workspace.dialogs.TextDialog;
import javafx.scene.text.Text;

/**
 *
 * @author changruizhou
 */
public class GoLoNodeController {
    GoLogoLoApp app;
    
    public GoLoNodeController(GoLogoLoApp app) {
        this.app = app;
    }
    
    public void processMove(GoLoComponentPrototype toMove) {
        Moving_Transaction mt = new Moving_Transaction(toMove);
        app.processTransaction(mt);
    }
    
    public void processChangeText(Text initTextNode) {
        TextDialog td = new TextDialog(app);
        td.showEditTextDialog(initTextNode.getText());
        String text = td.getString();
        if(text != null) {
            ChangeTextContent_Transaction ctt = new ChangeTextContent_Transaction(initTextNode, text);
            app.processTransaction(ctt);
        }
    }

    public void processResizeRectangle(GoLoRectangle rec) {
        ResizeRectangle_Transaction rrt= new ResizeRectangle_Transaction(rec);
        app.processTransaction(rrt);
    }

    public void processCircleResize(GoLoCircle goLoCircle) {
        ResizeCircle_Transaction rct = new ResizeCircle_Transaction(goLoCircle);
        app.processTransaction(rct);
        
    }
    
    
}
