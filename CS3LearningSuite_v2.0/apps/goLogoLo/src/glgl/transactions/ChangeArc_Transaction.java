/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.transactions;

import glgl.data.GoLoRectangle;
import javafx.scene.shape.Rectangle;
import jtps.jTPS_Transaction;

/**
 *
 * @author changruizhou
 */
public class ChangeArc_Transaction implements jTPS_Transaction{
    
    Rectangle node;
    GoLoRectangle component;
    double oldArc;
    double newArc;
    
    public ChangeArc_Transaction(GoLoRectangle rec) {
        component = rec;
        node = (Rectangle)rec.getGoLoNode();
        oldArc = rec.getOldArc();
        newArc = node.getArcHeight();
    }
    @Override
    public void doTransaction() {
        node.setArcWidth(newArc);
        node.setArcHeight(newArc);
        component.setOldArc(newArc);
    }

    @Override
    public void undoTransaction() {
        node.setArcWidth(oldArc);
        node.setArcHeight(oldArc);
        component.setOldArc(oldArc);
    }
    
}
