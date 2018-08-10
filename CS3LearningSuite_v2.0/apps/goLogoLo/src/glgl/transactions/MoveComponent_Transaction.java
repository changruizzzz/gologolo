/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.transactions;

import glgl.data.GoLoComponentPrototype;
import glgl.data.GoLoData;
import jtps.jTPS_Transaction;

/**
 *
 * @author changruizhou
 */
public class MoveComponent_Transaction implements jTPS_Transaction {
    
    GoLoData data;
    GoLoComponentPrototype componentToMove;
    int mode;
    
    public MoveComponent_Transaction(GoLoData initData, GoLoComponentPrototype selected, int movingType) {
        data = initData;
        componentToMove = selected;
        mode = movingType;
    }

    @Override
    public void doTransaction() {
        data.moveItem(componentToMove, mode);
    }

    @Override
    public void undoTransaction() {
        data.moveItem(componentToMove, mode * (-1));
    }
}
    
