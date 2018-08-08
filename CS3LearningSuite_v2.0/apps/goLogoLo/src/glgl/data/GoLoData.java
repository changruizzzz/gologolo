package glgl.data;

import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;
import static glgl.GoLoPropertyType.GLGL_DEFAULT_HEIGHT;
import static glgl.GoLoPropertyType.GLGL_DEFAULT_WIDTH;
import static glgl.GoLoPropertyType.GLGL_ITEMS_TABLE_VIEW;
import glgl.GoLogoLoApp;
import glgl.workspace.controllers.GoLoNodeController;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_RECTANGLE_BACK;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import properties_manager.PropertiesManager;

/**
 *
 * @author McKillaGorilla, ChangruiZhou
 */
public class GoLoData implements AppDataComponent {

    GoLogoLoApp app;
    ObservableList<GoLoComponentPrototype> components;
    TableViewSelectionModel componentsSelectionModel;
    Pane background;
    Rectangle rect;
    Rectangle clipper;
    StackPane workspacePane;
    GoLoNodeController nodeControl;
    GoLoNodeSelectionModel nodeSelectionModel;

    public GoLoData(GoLogoLoApp initApp) {
        app = initApp;
        // GET ALL THE THINGS WE'LL NEED TO MANIUPLATE THE TABLE
        TableView tableView = (TableView) app.getGUIModule().getGUINode(GLGL_ITEMS_TABLE_VIEW);
        components = tableView.getItems();
        componentsSelectionModel = tableView.getSelectionModel();
        componentsSelectionModel.setSelectionMode(SelectionMode.MULTIPLE);
        workspacePane = (StackPane) ((BorderPane) ((BorderPane) app.getWorkspaceComponent().getWorkspace()).getCenter()).getCenter();
        nodeControl = new GoLoNodeController(app);
        nodeSelectionModel = new GoLoNodeSelectionModel();
        nodeSelectionModel.setTable(componentsSelectionModel);
    }

    public void initBackground() {
        background = new Pane();
        clipper = new Rectangle();
        rect = new Rectangle();
        rect.setFill(Color.WHITE);
        rect.setOnMouseClicked(e->{
                    nodeSelectionModel.clearSelection();
        });
        workspacePane.getChildren().clear();
        workspacePane.getChildren().add(background);
        background.getStyleClass().add(CLASS_GLGL_RECTANGLE_BACK);
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        double width = Double.parseDouble(props.getProperty(GLGL_DEFAULT_WIDTH));
        double height = Double.parseDouble(props.getProperty(GLGL_DEFAULT_HEIGHT));
        resize(width, height);
        background.setClip(clipper);
        background.getChildren().add(rect);
        nodeSelectionModel.setNodesList(background.getChildren());

    }

    public boolean isItemSelected() {
        ObservableList<GoLoComponentPrototype> selectedItems = this.getSelectedItems();
        return (selectedItems != null) && (selectedItems.size() == 1);
    }

    public boolean areItemsSelected() {
        ObservableList<GoLoComponentPrototype> selectedItems = this.getSelectedItems();
        return (selectedItems != null) && (selectedItems.size() > 1);
    }

    public boolean isUpMovable() {
        ObservableList<GoLoComponentPrototype> selectedItems = this.getSelectedItems();
        if (selectedItems != null && selectedItems.size() == 1) {
            return components.indexOf(selectedItems.get(0)) != 0;
        }
        return false;
    }

    public boolean isDownMovable() {
        ObservableList<GoLoComponentPrototype> selectedItems = this.getSelectedItems();
        if (selectedItems != null && selectedItems.size() == 1) {
            return components.indexOf(selectedItems.get(0)) != components.size() - 1;
        }
        return false;
    }

    public void resize(double width, double height) {
        background.setMinSize(width, height);
        background.setMaxSize(width, height);
        clipper.setWidth(width);
        clipper.setHeight(height);
        rect.setWidth(width);
        rect.setHeight(height);
    }

    public void addComponent(GoLoComponentPrototype componentToAdd) {
        nodeSelectionModel.clearSelection();
        components.add(componentToAdd);
        background.getChildren().add(componentToAdd.goLoNode);
        updateIndicies();
        makeInteractable(componentToAdd);
        nodeSelectionModel.selectComponent(componentToAdd);

    }

    public void removeComponent(GoLoComponentPrototype componentToDelete) {
        components.remove(componentToDelete);
        nodeSelectionModel.clearSelection();
        background.getChildren().remove(componentToDelete.goLoNode);
        updateIndicies();
    }

    public void replaceItem(GoLoComponentPrototype componentToEdit, GoLoComponentPrototype editedItem) {
        int pos = components.indexOf(componentToEdit);
        components.set(pos, editedItem);
        selectItem(editedItem);
    }

    public void moveItem(GoLoComponentPrototype initItem, int mode) {
        int pos = components.indexOf(initItem);
        components.set(pos, components.get(pos + mode));
        components.set(pos + mode, initItem);
        selectItem(initItem);

    }

    public GoLoComponentPrototype getSelectedItem() {
        if (!isItemSelected()) {
            return null;
        }
        return getSelectedItems().get(0);
    }

    public ObservableList<GoLoComponentPrototype> getSelectedItems() {
        return (ObservableList<GoLoComponentPrototype>) this.componentsSelectionModel.getSelectedItems();
    }

    public int getItemIndex(GoLoComponentPrototype component) {
        return components.indexOf(component);
    }

    public void addItemAt(GoLoComponentPrototype component, int componentIndex) {
        components.add(componentIndex, component);
        background.getChildren().add(componentIndex, component.goLoNode);
        makeInteractable(component);
        updateIndicies();
        nodeSelectionModel.selectComponent(component);
    }

    public void moveItem(int oldIndex, int newIndex) {
        GoLoComponentPrototype componentToMove = components.remove(oldIndex);
        components.add(newIndex, componentToMove);
    }

    public ArrayList<Integer> removeAll(ArrayList<GoLoComponentPrototype> componentsToRemove) {
        ArrayList<Integer> componentIndices = new ArrayList();
        for (GoLoComponentPrototype component : componentsToRemove) {
            componentIndices.add(components.indexOf(component));
        }
        nodeSelectionModel.clearSelection();
        for (GoLoComponentPrototype componentToRemove : componentsToRemove) {
            components.remove(componentToRemove);
            background.getChildren().remove(componentToRemove.getGoLoNode());
        }
        updateIndicies();
        return componentIndices;
    }

    public void addAll(ArrayList<GoLoComponentPrototype> componentsToAdd, ArrayList<Integer> addComponentLocations) {
        for (int i = 0; i < componentsToAdd.size(); i++) {
            GoLoComponentPrototype componentToAdd = componentsToAdd.get(i);
            Integer location = addComponentLocations.get(i);
            components.add(location, componentToAdd);
            background.getChildren().add(location + 1, componentToAdd.getGoLoNode());
            nodeSelectionModel.selectComponent(componentToAdd);
        }
        updateIndicies();
    }

    public ArrayList<GoLoComponentPrototype> getCurrentItemsOrder() {
        ArrayList<GoLoComponentPrototype> orderedItems = new ArrayList();
        for (GoLoComponentPrototype component : components) {
            orderedItems.add(component);
        }
        return orderedItems;
    }

    public int getNumItems() {
        return components.size();
    }

    public void selectItem(GoLoComponentPrototype componentToSelect) {
        this.componentsSelectionModel.select(componentToSelect);
    }

    public void clearSelected() {
        this.componentsSelectionModel.clearSelection();
    }

    public void sortItems(Comparator sortComparator) {
        Collections.sort(components, sortComparator);
    }

    public void rearrangeItems(ArrayList<GoLoComponentPrototype> oldListOrder) {
        components.clear();
        for (GoLoComponentPrototype component : oldListOrder) {
            components.add(component);
        }
    }

    @Override
    public void reset() {
        AppGUIModule gui = app.getGUIModule();

        // CLEAR OUT THE TEXT FIELDS
        // CLEAR OUT THE ITEMS FROM THE TABLE
        TableView tableView = (TableView) gui.getGUINode(GLGL_ITEMS_TABLE_VIEW);
        components = tableView.getItems();
        components.clear();
        nodeSelectionModel.clearSelection();
        initBackground();
    }

    public Pane getBackground() {
        return background;
    }

    private void updateIndicies() {
        for (int i = 0; i < components.size(); i++) {
            components.get(i).setOrder(i + 1);
        }
    }

    private void makeInteractable(GoLoComponentPrototype component) {
        component.goLoNode.setOnMouseClicked(e-> {
            nodeSelectionModel.selectComponent(component);
            if (component.getType().equals("Text")) 
                                if (e.getClickCount() == 2) {
                    nodeControl.processChangeText((Text) component.goLoNode);}
            app.getFoolproofModule().updateAll();
        });
        component.goLoNode.setOnMousePressed(e -> {
            component.xDiff.set(e.getX() - component.getX());
            component.yDiff.set(e.getY() - component.getY());
            component.oldX.set(component.getX());
            component.oldY.set(component.getY());
        });
        component.goLoNode.setOnMouseDragged(e -> {
            component.setCoordinate(e.getX() - component.xDiff.get(), e.getY() - component.yDiff.get());
        });
        component.goLoNode.setOnMouseReleased(e -> {
            if (component.isMoved()) {
                nodeControl.processMove(component);
            }
        });
//        if (component.getType().equals("Text")) {
//            component.goLoNode.setOnMouseClicked(e -> {
//                                    nodeSelectionModel.selectComponent(component);
//
//                if (e.getClickCount() == 2) {
//                    nodeControl.processChangeText((Text) component.goLoNode);}
//            });
//        }
    }
    
    public GoLoNodeSelectionModel getNodeSelectionModel() {
        return nodeSelectionModel;
    }
    
    public Iterator<GoLoComponentPrototype> componentsIterator() {
        return this.components.iterator();
    }
}
