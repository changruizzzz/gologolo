/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.transactions;

import glgl.data.GoLoData;
import javafx.scene.paint.RadialGradient;
import jtps.jTPS_Transaction;

/**
 *
 * @author changruizhou
 */
public class CanvasRadient_Transaction  implements jTPS_Transaction{
    
    RadialGradient oldRG;
    RadialGradient newRG;
    GoLoData initData;
    
    public CanvasRadient_Transaction(GoLoData data){
        initData = data;
        oldRG = data.getFill();
        newRG = (RadialGradient)data.getBackground().getBackground().getFills().get(0).getFill();
    }

    @Override
    public void doTransaction() {
        initData.setFill(newRG);
    }

    @Override
    public void undoTransaction() {
        initData.setFill(oldRG);
    }
    
}
