/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.transactions;

import glgl.GoLogoLoApp;
import glgl.data.GoLoComponentPrototype;
import glgl.data.GoLoData;
import java.util.ArrayList;
import jtps.jTPS_Transaction;

/**
 *
 * @author changruizhou
 */
public class PasteComponents_Transaction implements jTPS_Transaction {
    GoLogoLoApp app;
    ArrayList<GoLoComponentPrototype> itemsToPaste;
    int pasteIndex;
    
    public PasteComponents_Transaction(  GoLogoLoApp initApp, 
                                    ArrayList<GoLoComponentPrototype> initItemsToPaste,
                                    int initPasteIndex) {
        app = initApp;
        itemsToPaste = initItemsToPaste;
        pasteIndex = initPasteIndex;
    }
    public PasteComponents_Transaction(  GoLogoLoApp initApp, 
                                    ArrayList<GoLoComponentPrototype> initItemsToPaste) {
        app = initApp;
        itemsToPaste = initItemsToPaste;
        GoLoData data = (GoLoData)app.getDataComponent();        
        pasteIndex = data.getNumItems() - 1;
    }

    @Override
    public void doTransaction() {
        GoLoData data = (GoLoData)app.getDataComponent();
        int index = pasteIndex+1;
        for (GoLoComponentPrototype itemToPaste : itemsToPaste) {
            data.addItemAt(itemToPaste, index);
            index++;
        }
    }

    @Override
    public void undoTransaction() {
        GoLoData data = (GoLoData)app.getDataComponent();
        for (GoLoComponentPrototype itemToPaste : itemsToPaste) {
            data.removeComponent(itemToPaste);
        }
    }   
}