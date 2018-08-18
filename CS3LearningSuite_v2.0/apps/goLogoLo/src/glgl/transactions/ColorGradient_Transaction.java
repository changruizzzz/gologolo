/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.transactions;

import glgl.data.GoLoComponentPrototype;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Shape;
import jtps.jTPS_Transaction;

/**
 *
 * @author changruizhou
 */
public class ColorGradient_Transaction implements jTPS_Transaction{
    
    
    GoLoComponentPrototype item;
    Shape shape;
    RadialGradient oldRG;
    RadialGradient newRG;
    
    public ColorGradient_Transaction(GoLoComponentPrototype selected) {
        item = selected;
        shape = (Shape)item.getGoLoNode();
        oldRG = selected.getFill();
        newRG = (RadialGradient)shape.getFill();
    }

    @Override
    public void doTransaction() {
        shape.setFill(newRG);
        item.setFill(newRG);
    }

    @Override
    public void undoTransaction() {
        shape.setFill(oldRG);
        item.setFill(oldRG);
    }
    
}
