package glgl.data;

import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;
import static glgl.GoLoPropertyType.GLGL_ITEMS_TABLE_VIEW;
import glgl.GoLogoLoApp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


/**
 *
 * @author McKillaGorilla, ChangruiZhou
 */
public class GoLoData implements AppDataComponent {
    GoLogoLoApp app;
    ObservableList<GoLoComponentPrototype> items;
    TableViewSelectionModel itemsSelectionModel;
    Pane workArea;
    
    public GoLoData(GoLogoLoApp initApp) {
        app = initApp;
        workArea =(Pane)((BorderPane)((BorderPane)app.getWorkspaceComponent().getWorkspace()).getCenter()).getCenter();
        
        // GET ALL THE THINGS WE'LL NEED TO MANIUPLATE THE TABLE
        TableView tableView = (TableView) app.getGUIModule().getGUINode(GLGL_ITEMS_TABLE_VIEW);
        items = tableView.getItems();
        itemsSelectionModel = tableView.getSelectionModel();
        itemsSelectionModel.setSelectionMode(SelectionMode.MULTIPLE);
        addComponent(new GoLoRectangle());
    }
    
    
    public Iterator<GoLoComponentPrototype> itemsIterator() {
        return this.items.iterator();
    }

    @Override
    public void reset() {
        AppGUIModule gui = app.getGUIModule();
        
        // CLEAR OUT THE TEXT FIELDS
        
        // CLEAR OUT THE ITEMS FROM THE TABLE
        TableView tableView = (TableView)gui.getGUINode(GLGL_ITEMS_TABLE_VIEW);
        items = tableView.getItems();
        items.clear();
    }

    public boolean isItemSelected() {
        ObservableList<GoLoComponentPrototype> selectedItems = this.getSelectedItems();
        return (selectedItems != null) && (selectedItems.size() == 1);
    }
    
    public boolean areItemsSelected() {
        ObservableList<GoLoComponentPrototype> selectedItems = this.getSelectedItems();
        return (selectedItems != null) && (selectedItems.size() > 1);        
    }

    
    public boolean isUpMovable(){
        ObservableList<GoLoComponentPrototype> selectedItems = this.getSelectedItems();
        if(selectedItems != null && selectedItems.size() == 1)  
            return items.indexOf(selectedItems.get(0)) != 0;
        return false;
    }
    
    public boolean isDownMovable(){
        ObservableList<GoLoComponentPrototype> selectedItems = this.getSelectedItems();
        if(selectedItems != null && selectedItems.size() == 1)  
            return items.indexOf(selectedItems.get(0)) != items.size() - 1;
        return false;
    }


    public void addComponent(GoLoComponentPrototype componentToAdd) {
        items.add(componentToAdd);
        workArea.getChildren().add(componentToAdd.goLoShape);
        
    }

    public void removeItem(GoLoComponentPrototype componentToDelete) {
        items.remove(componentToDelete);
    }
    
    public void replaceItem(GoLoComponentPrototype itemToEdit, GoLoComponentPrototype editedItem) {
        int pos = items.indexOf(itemToEdit);
        items.set(pos, editedItem);
        selectItem(editedItem);
    }
    
    public void moveItem(GoLoComponentPrototype initItem, int mode) {
        int pos = items.indexOf(initItem);
        items.set(pos, items.get(pos + mode));
        items.set(pos + mode, initItem);
        selectItem(initItem);

    }

    public GoLoComponentPrototype getSelectedItem() {
        if (!isItemSelected()) {
            return null;
        }
        return getSelectedItems().get(0);
    }
    public ObservableList<GoLoComponentPrototype> getSelectedItems() {
        return (ObservableList<GoLoComponentPrototype>)this.itemsSelectionModel.getSelectedItems();
    }

    public int getItemIndex(GoLoComponentPrototype item) {
        return items.indexOf(item);
    }

    public void addItemAt(GoLoComponentPrototype item, int itemIndex) {
        items.add(itemIndex, item);
    }

    public void moveItem(int oldIndex, int newIndex) {
        GoLoComponentPrototype itemToMove = items.remove(oldIndex);
        items.add(newIndex, itemToMove);
    }

    public int getNumItems() {
        return items.size();
    }

    public void selectItem(GoLoComponentPrototype itemToSelect) {
        this.itemsSelectionModel.select(itemToSelect);
    }

    public ArrayList<Integer> removeAll(ArrayList<GoLoComponentPrototype> itemsToRemove) {
        ArrayList<Integer> itemIndices = new ArrayList();
        for (GoLoComponentPrototype item: itemsToRemove) {
            itemIndices.add(items.indexOf(item));
        }
        for (GoLoComponentPrototype item: itemsToRemove) {
            items.remove(item);
        }
        return itemIndices;
    }

    public void addAll(ArrayList<GoLoComponentPrototype> itemsToAdd, ArrayList<Integer> addItemLocations) {
        for (int i = 0; i < itemsToAdd.size(); i++) {
            GoLoComponentPrototype itemToAdd = itemsToAdd.get(i);
            Integer location = addItemLocations.get(i);
            items.add(location, itemToAdd);
        }
    }

    public ArrayList<GoLoComponentPrototype> getCurrentItemsOrder() {
        ArrayList<GoLoComponentPrototype> orderedItems = new ArrayList();
        for (GoLoComponentPrototype item : items) {
            orderedItems.add(item);
        }
        return orderedItems;
    }

    public void clearSelected() {
        this.itemsSelectionModel.clearSelection();
    }

    public void sortItems(Comparator sortComparator) {
        Collections.sort(items, sortComparator);
    }

    public void rearrangeItems(ArrayList<GoLoComponentPrototype> oldListOrder) {
        items.clear();
        for (GoLoComponentPrototype item : oldListOrder) {
            items.add(item);
        }
    }
}