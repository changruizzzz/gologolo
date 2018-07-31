package tdlm.transactions;

import jtps.jTPS_Transaction;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;

/**
 *
 * @author ChangruiZhou
 */
public class EditItem_Transaction implements jTPS_Transaction {
    ToDoData data;
    ToDoItemPrototype itemToEdit;
    ToDoItemPrototype newItem;
   
    
    public EditItem_Transaction(ToDoData initData, ToDoItemPrototype initItem, ToDoItemPrototype initNewItem) {
        data = initData;
        itemToEdit = initItem;
        newItem = initNewItem;
    }

    @Override
    public void doTransaction() {
        data.replaceItem(itemToEdit, newItem);
    }

    @Override
    public void undoTransaction() {
        data.replaceItem(newItem, itemToEdit);
    }
}
