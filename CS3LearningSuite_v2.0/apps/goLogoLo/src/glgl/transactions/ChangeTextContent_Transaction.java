/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.transactions;

import javafx.scene.text.Text;
import jtps.jTPS_Transaction;

/**
 *
 * @author changruizhou
 */
public class ChangeTextContent_Transaction implements jTPS_Transaction {
    
    String oldString;
    String newString;
    Text textNode;
    
    public ChangeTextContent_Transaction(Text initNode, String newText) {
        textNode = initNode;
        oldString = initNode.getText();
        newString = newText;
    }

    @Override
    public void doTransaction() {
        textNode.setText(newString);
    }

    @Override
    public void undoTransaction() {
        textNode.setText(oldString);

    }
}