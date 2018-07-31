package tdlm.transactions;

import jtps.jTPS_Transaction;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;

/**
 *
 * @author ChangruiZhou
 */
public class MoveItem_Transaction implements jTPS_Transaction {
    ToDoData data;
    ToDoItemPrototype itemToMove;
    int mode;
   
    
    public MoveItem_Transaction(ToDoData initData, ToDoItemPrototype selected, int movingType) {
        data = initData;
        itemToMove = selected;
        mode = movingType;
    }

    @Override
    public void doTransaction() {
        data.moveItem(itemToMove, mode);
    }

    @Override
    public void undoTransaction() {
        data.moveItem(itemToMove, mode * (-1));
    }
}
