/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.transactions;

import glgl.data.GoLoData;
import jtps.jTPS_Transaction;

/**
 *
 * @author changruizhou
 */
public class ResizeBackground_Transaction implements jTPS_Transaction {
    GoLoData data;
    double width;
    double height;
    double oldWidth;
    double oldHeight;
    
    public ResizeBackground_Transaction(GoLoData initData, double width, double height) {
        data = initData;
        this.width = width;
        this.height = height;
        oldWidth = data.getBackground().getWidth();
        oldHeight = data.getBackground().getHeight();
    }

    @Override
    public void doTransaction() {
        data.resize(width, height);
    }

    @Override
    public void undoTransaction() {
        data.resize(oldWidth, oldHeight);
    }
}
