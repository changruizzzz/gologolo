/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.transactions;

import glgl.data.GoLoComponentPrototype;
import glgl.data.GoLoData;
import java.util.ArrayList;
import jtps.jTPS_Transaction;

/**
 *
 * @author changruizhou
 */
public class RemoveComponents_Transaction implements jTPS_Transaction{
    GoLoData data;
    ArrayList<GoLoComponentPrototype> itemsToRemove;
    ArrayList<Integer> removedItemLocations;
    
    public RemoveComponents_Transaction(GoLoData initData, ArrayList<GoLoComponentPrototype> initItems) {
        data = initData;
        itemsToRemove = initItems;
    }

    @Override
    public void doTransaction() {
        removedItemLocations = data.removeAll(itemsToRemove);
    }

    @Override
    public void undoTransaction() {
        data.addAll(itemsToRemove, removedItemLocations);
    }
}
