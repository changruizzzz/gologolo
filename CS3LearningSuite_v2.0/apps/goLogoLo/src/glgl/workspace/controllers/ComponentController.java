package glgl.workspace.controllers;

import glgl.GoLogoLoApp;
import glgl.data.GoLoCircle;
import glgl.data.GoLoComponentPrototype;
import glgl.data.GoLoData;
import glgl.data.GoLoRectangle;
import glgl.data.GoLoText;
import glgl.transactions.AddComponent_Transaction;
import glgl.transactions.ChangeFont_Transaction;
import glgl.transactions.MoveComponent_Transaction;
import glgl.transactions.RemoveComponents_Transaction;
import glgl.transactions.Rename_Transaction;
import glgl.transactions.ResizeBackground_Transaction;
import glgl.workspace.dialogs.EditDialog;
import glgl.workspace.dialogs.TextDialog;
import java.util.ArrayList;
import javafx.scene.shape.Shape;

/**
 *
 * @author ChangruiZhou
 */
public class ComponentController {
    GoLogoLoApp app;
    EditDialog editDialog;
    TextDialog textDialog;
    
    public ComponentController(GoLogoLoApp initApp) {
        app = initApp;
        editDialog = new EditDialog(app);
        textDialog = new TextDialog(app);
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
        textDialog.showAddTextDialog();
        GoLoText component = textDialog.getGoLoText();
        if(component != null) {
            AddComponent_Transaction add = new AddComponent_Transaction(data, component);
            app.processTransaction(add);
        }
    }
    
    public void processRename() {
        editDialog.showRenameDialog();
        GoLoComponentPrototype tempComponent = ((GoLoData)app.getDataComponent()).getSelectedItem();
        if(editDialog.getNewName() != null) {
            Rename_Transaction rename = new Rename_Transaction(tempComponent, editDialog.getNewName());
            app.processTransaction(rename);
        }    
    }
    
    public void processMoveComponent(int movingType) {
        GoLoData data = (GoLoData)app.getDataComponent();
        GoLoComponentPrototype selected = data.getSelectedItem();
        MoveComponent_Transaction transaction = new MoveComponent_Transaction(data, selected, movingType);
        app.processTransaction(transaction);
    }

    public void processAddCircle() {
        GoLoData data = (GoLoData)app.getDataComponent();
        GoLoCircle component = new GoLoCircle();
        AddComponent_Transaction add = new AddComponent_Transaction(data, component);
        app.processTransaction(add);    
    }

    public void processChangeFont(String family, double size, boolean changeWeight, boolean changePosture, boolean changeUnderline) {
        GoLoData data = (GoLoData)app.getDataComponent();
        GoLoText selected = (GoLoText)data.getSelectedItem();    
        ChangeFont_Transaction ct = new ChangeFont_Transaction(selected, family, size, changeWeight, changePosture, changeUnderline);
        app.processTransaction(ct);
    }

    public void changeBorderWidth(double get) {
        GoLoData data = (GoLoData)app.getDataComponent();
        GoLoComponentPrototype selected = data.getSelectedItem();
        System.out.println(get);
        System.out.println(selected.getGoLoNode().toString());
        ((Shape)selected.getGoLoNode()).setStrokeWidth(get);
//        selected.getGoLoNode().getStyleClass().add("-fx-border-color: black;");
//        selected.getGoLoNode().getStyleClass().add("-fx-border-width:" + get + ";");
    }
}
