package glgl.transactions;

import jtps.jTPS_Transaction;
import glgl.data.GoLoData;
import glgl.data.GoLoComponentPrototype;

/**
 *
 * @author McKillaGorilla
 */
public class AddComponent_Transaction implements jTPS_Transaction {
    GoLoData data;
    GoLoComponentPrototype componentToAdd;
    
    public AddComponent_Transaction(GoLoData initData, GoLoComponentPrototype initNewItem) {
        data = initData;
        componentToAdd = initNewItem;
    }

    @Override
    public void doTransaction() {
        data.addComponent(componentToAdd);        
    }

    @Override
    public void undoTransaction() {
        data.removeItem(componentToAdd);
    }
}
