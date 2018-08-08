/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.transactions;

import static djf.AppPropertyType.APP_CLIPBOARD_FOOLPROOF_SETTINGS;
import glgl.GoLogoLoApp;
import glgl.data.GoLoComponentPrototype;
import glgl.data.GoLoData;
import java.util.ArrayList;
import jtps.jTPS_Transaction;

/**
 *
 * @author changruizhou
 */
public class CutComponents_Transaction implements jTPS_Transaction {
    GoLogoLoApp app;
    ArrayList<GoLoComponentPrototype> itemsToCut;
    ArrayList<Integer> cutItemLocations;
    
    public CutComponents_Transaction(GoLogoLoApp initApp, ArrayList<GoLoComponentPrototype> initItemsToCut) {
        app = initApp;
        itemsToCut = initItemsToCut;
    }

    @Override
    public void doTransaction() {
        GoLoData data = (GoLoData)app.getDataComponent();
        cutItemLocations = data.removeAll(itemsToCut);
        app.getFoolproofModule().updateControls(APP_CLIPBOARD_FOOLPROOF_SETTINGS);
    }

    @Override
    public void undoTransaction() {
        GoLoData data = (GoLoData)app.getDataComponent();
        data.addAll(itemsToCut, cutItemLocations);
        app.getFoolproofModule().updateControls(APP_CLIPBOARD_FOOLPROOF_SETTINGS);
    }   
}