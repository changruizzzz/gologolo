package glgl.workspace.controllers;

import glgl.GoLogoLoApp;
import glgl.data.GoLoComponentPrototype;
import glgl.data.GoLoData;
import glgl.data.GoLoRectangle;
import glgl.data.GoLoText;
import glgl.transactions.AddComponent_Transaction;
import glgl.transactions.RemoveComponents_Transaction;
import glgl.transactions.ResizeBackground_Transaction;
import glgl.workspace.dialogs.EditDialog;
import java.util.ArrayList;

/**
 *
 * @author ChangruiZhou
 */
public class ComponentController {
    GoLogoLoApp app;
    EditDialog editDialog;
    
    public ComponentController(GoLogoLoApp initApp) {
        app = initApp;
        editDialog = new EditDialog(app);
    }
    
    public void processResize() {
        editDialog.showResizeDialog();
        double width = editDialog.getBackgroundWidth();
        double height = editDialog.getBackgroundHeight();
        if(width >= 0 && height >= 0) {
            GoLoData data = (GoLoData)app.getDataComponent();
            ResizeBackground_Transaction transaction = new ResizeBackground_Transaction(data, width, height);
            app.processTransaction(transaction);
            editDialog.reset();
        } 
//        ToDoItemPrototype newItem = itemDialog.getNewItem();        
//        if (newItem != null) {
//            // IF IT HAS A UNIQUE NAME AND COLOR
//            // THEN CREATE A TRANSACTION FOR IT
//            // AND ADD IT
//            ToDoData data = (ToDoData)app.getDataComponent();
//            AddItem_Transaction transaction = new AddItem_Transaction(data, newItem);
//            app.processTransaction(transaction);
//            //clean temporary obeject
//            itemDialog.reset();
//        }    
//        // OTHERWISE TELL THE USER WHAT THEY
//        // HAVE DONE WRONG
//        else {
//            
//            
//        }
    }
    
    public void processRemoveItems() {
        GoLoData data = (GoLoData)app.getDataComponent();
        if (data.isItemSelected() || data.areItemsSelected()) {
            ArrayList<GoLoComponentPrototype> itemsToRemove = new ArrayList(data.getSelectedItems());
            RemoveComponents_Transaction transaction = new RemoveComponents_Transaction(data, itemsToRemove);
            app.processTransaction(transaction);
        }
    }
    
    public void processEditItem() {
//        ToDoData data = (ToDoData)app.getDataComponent();
//        ToDoItemPrototype initEdit = data.getSelectedItem();
//        itemDialog.showEditDialog(initEdit);
//        ToDoItemPrototype editItem = itemDialog.getEditItem();
//        if (editItem != null) {
//            EditItem_Transaction transaction = new EditItem_Transaction(data, initEdit, editItem);
//            app.processTransaction(transaction);
//            itemDialog.reset();
//        }
    }
    
    public void processAddRectangle() {
        GoLoData data = (GoLoData)app.getDataComponent();
        GoLoRectangle component = new GoLoRectangle();
        AddComponent_Transaction add = new AddComponent_Transaction(data, component);
        app.processTransaction(add);
    }
    
    public void processAddText() {
        GoLoData data = (GoLoData)app.getDataComponent();
        GoLoText component = new GoLoText();
        AddComponent_Transaction add = new AddComponent_Transaction(data, component);
        app.processTransaction(add);
    }
    
    public void processRename() {
        editDialog.showRenameDialog();
    }
    
    public void processMoveItem(int movingType) {
//        ToDoData data = (ToDoData)app.getDataComponent();
//        ToDoItemPrototype selected = data.getSelectedItem();
//        MoveItem_Transaction transaction = new MoveItem_Transaction(data, selected, movingType);
//        app.processTransaction(transaction);
    }
}
