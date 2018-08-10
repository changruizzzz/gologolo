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
public class Rename_Transaction implements jTPS_Transaction{
    
    String oldName;
    String newName;
    GoLoComponentPrototype temp;
    
    public Rename_Transaction(GoLoComponentPrototype initComponent, String newName) {
        temp = initComponent;
        this.newName = newName;
        oldName = temp.getName();
    }
    
    @Override
    public void doTransaction() {
        temp.setName(newName);
        
    }
    @Override
    public void undoTransaction() {
        temp.setName(oldName);
    }
}
