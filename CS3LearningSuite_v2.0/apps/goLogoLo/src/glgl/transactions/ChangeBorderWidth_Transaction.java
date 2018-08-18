/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.transactions;

import glgl.data.GoLoComponentPrototype;
import javafx.scene.shape.Shape;
import jtps.jTPS_Transaction;

/**
 *
 * @author changruizhou
 */
public class ChangeBorderWidth_Transaction implements jTPS_Transaction{
    
    Shape node;
    double oldWidth;
    double newWidth;
    GoLoComponentPrototype component;
    
    public ChangeBorderWidth_Transaction(GoLoComponentPrototype selected) {
        component = selected;
        node = (Shape)selected.getGoLoNode();
        oldWidth = selected.getOldStrokeWidth();
        newWidth = node.getStrokeWidth();
    }

    @Override
    public void doTransaction() {
        node.setStrokeWidth(newWidth);
        component.setOldStrokeWidth(newWidth);
    }

    @Override
    public void undoTransaction() {
        node.setStrokeWidth(oldWidth);
        component.setOldStrokeWidth(oldWidth);

    }
    
}
