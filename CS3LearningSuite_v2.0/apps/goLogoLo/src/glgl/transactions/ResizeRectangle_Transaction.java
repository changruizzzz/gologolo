/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.transactions;

import glgl.data.GoLoNodeSelectionModel;
import glgl.data.GoLoRectangle;
import javafx.scene.shape.Rectangle;
import jtps.jTPS_Transaction;

/**
 *
 * @author changruizhou
 */
public class ResizeRectangle_Transaction implements jTPS_Transaction{
    
    private double oldX;
    private double oldWidth;
    private double oldY;
    private double oldHeight;    
    private double newX;
    private double newWidth;
    private double newY;
    private double newHeight;
    private Rectangle recNode;

    public ResizeRectangle_Transaction(GoLoRectangle rec) {
        recNode = (Rectangle)rec.getGoLoNode();
        oldX = rec.getOldX();
        oldY = rec.getOldY();
        newX = rec.getX();
        newY = rec.getY();
        oldWidth = rec.getOldWidth();
        oldHeight = rec.getOldHeight();
        newWidth = recNode.getWidth();
        newHeight = recNode.getHeight();
    }

    @Override
    public void doTransaction() {
        recNode.setX(newX);
        recNode.setY(newY);
        recNode.setWidth(newWidth);
        recNode.setHeight(newHeight);
    }

    @Override
    public void undoTransaction() {
        recNode.setX(oldX);
        recNode.setY(oldY);
        recNode.setWidth(oldWidth);
        recNode.setHeight(oldHeight);
    }
    
}
