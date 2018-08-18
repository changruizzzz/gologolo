/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.workspace.controllers;

import glgl.data.GoLoCircle;
import javafx.scene.shape.Circle;
import jtps.jTPS_Transaction;

/**
 *
 * @author changruizhou
 */
public class ResizeCircle_Transaction implements jTPS_Transaction{

    double oldR;
    double newR;
    GoLoCircle initCircle;
    
    public ResizeCircle_Transaction(GoLoCircle goLoCircle) {
        initCircle = goLoCircle;
        oldR = initCircle.getOldRadius();
        newR = ((Circle)initCircle.getGoLoNode()).getRadius();
    }

    @Override
    public void doTransaction() {
        ((Circle)initCircle.getGoLoNode()).setRadius(newR);
        initCircle.setOldRadius(newR);
    }

    @Override
    public void undoTransaction() {
        ((Circle)initCircle.getGoLoNode()).setRadius(oldR);
        initCircle.setOldRadius(oldR);
    }
    
}
