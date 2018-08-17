/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.transactions;

import glgl.data.GoLoComponentPrototype;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import jtps.jTPS_Transaction;

/**
 *
 * @author changruizhou
 */
public class ChangeBorderColor_Transaction implements jTPS_Transaction{
    
    GoLoComponentPrototype initItem;
    Color oldColor;
    Color newColor;

    public ChangeBorderColor_Transaction(GoLoComponentPrototype selected, Color value) {
        initItem = selected;
        oldColor = (Color)((Shape)selected.getGoLoNode()).getStroke();
        newColor = value;
    }

    @Override
    public void doTransaction() {
        ((Shape)initItem.getGoLoNode()).setStroke(newColor);
    }

    @Override
    public void undoTransaction() {
        ((Shape)initItem.getGoLoNode()).setStroke(oldColor);
    }
    
}
