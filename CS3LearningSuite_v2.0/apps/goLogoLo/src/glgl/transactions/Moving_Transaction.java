/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.transactions;

import glgl.data.GoLoComponentPrototype;
import jtps.jTPS_Transaction;

/**
 *
 * @author changruizhou
 */
public class Moving_Transaction implements jTPS_Transaction {
    
    double oldX;
    double oldY;
    double newX;
    double newY;
    GoLoComponentPrototype item;
    
    public Moving_Transaction(GoLoComponentPrototype initItem) {
        oldX = initItem.getOldX();
        oldY = initItem.getOldY();
        newX = initItem.getX();
        newY = initItem.getY();
        item = initItem;
    }

    @Override
    public void doTransaction() {
        item.setCoordinate(newX, newY);
    }

    @Override
    public void undoTransaction() {
        item.setCoordinate(oldX, oldY);
    }
    
}
