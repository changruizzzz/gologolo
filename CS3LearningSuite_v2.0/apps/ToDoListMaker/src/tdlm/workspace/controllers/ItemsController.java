package tdlm.workspace.controllers;

import java.util.ArrayList;
import tdlm.ToDoListMakerApp;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;
import tdlm.workspace.dialogs.ToDoListItemDialog;
import tdlm.transactions.AddItem_Transaction;
import tdlm.transactions.EditItem_Transaction;
import tdlm.transactions.MoveItem_Transaction;
import tdlm.transactions.RemoveItems_Transaction;

/**
 *
 * @author McKillaGorilla, ChangruiZhou
 */
public class ItemsController {
    ToDoListMakerApp app;
    ToDoListItemDialog itemDialog;
    
    public ItemsController(ToDoListMakerApp initApp) {
        app = initApp;
        
        itemDialog = new ToDoListItemDialog(app);
    }
    
    public void processAddItem() {
        itemDialog.showAddDialog();
        ToDoItemPrototype newItem = itemDialog.getNewItem();        
        if (newItem != null) {
            // IF IT HAS A UNIQUE NAME AND COLOR
            // THEN CREATE A TRANSACTION FOR IT
            // AND ADD IT
            ToDoData data = (ToDoData)app.getDataComponent();
            AddItem_Transaction transaction = new AddItem_Transaction(data, newItem);
            app.processTransaction(transaction);
            //clean temporary obeject
            itemDialog.reset();
        }    
        // OTHERWISE TELL THE USER WHAT THEY
        // HAVE DONE WRONG
        else {
            
            
        }
    }
    
    public void processRemoveItems() {
        ToDoData data = (ToDoData)app.getDataComponent();
        if (data.isItemSelected() || data.areItemsSelected()) {
            ArrayList<ToDoItemPrototype> itemsToRemove = new ArrayList(data.getSelectedItems());
            RemoveItems_Transaction transaction = new RemoveItems_Transaction(app, itemsToRemove);
            app.processTransaction(transaction);
        }
    }
    
    public void processEditItem() {
        ToDoData data = (ToDoData)app.getDataComponent();
        ToDoItemPrototype initEdit = data.getSelectedItem();
        itemDialog.showEditDialog(initEdit);
        ToDoItemPrototype editItem = itemDialog.getEditItem();
        if (editItem != null) {
            EditItem_Transaction transaction = new EditItem_Transaction(data, initEdit, editItem);
            app.processTransaction(transaction);
            itemDialog.reset();
        }
    }
    
    public void processMoveItem(int movingType) {
        ToDoData data = (ToDoData)app.getDataComponent();
        ToDoItemPrototype selected = data.getSelectedItem();
        MoveItem_Transaction transaction = new MoveItem_Transaction(data, selected, movingType);
        app.processTransaction(transaction);
    }
}
