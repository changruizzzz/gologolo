/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.transactions;

import glgl.data.GoLoComponentPrototype;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import jtps.jTPS_Transaction;

/**
 *
 * @author changruizhou
 */
public class ChangeTextColor_Transaction implements jTPS_Transaction{
    
    GoLoComponentPrototype  init;
    Paint oldPaint;
    Paint newPaint;
    
    public ChangeTextColor_Transaction(GoLoComponentPrototype selected, Color value) {
        init = selected;
        oldPaint = ((Shape)selected.getGoLoNode()).getFill();
        newPaint = value;   
    }

    @Override
    public void doTransaction() {
        ((Shape)init.getGoLoNode()).setFill(newPaint);
    }

    @Override
    public void undoTransaction() {
        ((Shape)init.getGoLoNode()).setFill(oldPaint);    
    }
    
}
