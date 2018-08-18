package glgl.data;

import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;
import static glgl.GoLoPropertyType.GLGL_DEFAULT_HEIGHT;
import static glgl.GoLoPropertyType.GLGL_DEFAULT_WIDTH;
import static glgl.GoLoPropertyType.GLGL_ITEMS_TABLE_VIEW;
import glgl.GoLogoLoApp;
import glgl.workspace.controllers.GoLoNodeController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import properties_manager.PropertiesManager;

/**
 *
 * @author ChangruiZhou
 */
public class GoLoData implements AppDataComponent {

    GoLogoLoApp app;
    ObservableList<GoLoComponentPrototype> components;
    TableViewSelectionModel componentsSelectionModel;
    Pane background;
    Rectangle clipper;
    StackPane workspacePane;
    GoLoNodeController nodeControl;
    GoLoNodeSelectionModel nodeSelectionModel;
    boolean isNodeClicked = false;
    boolean isCanvasClicked =false;
    boolean isNodeDragged = false;
    boolean blockValueListener = false;
    boolean isCircleScrolled = false;
    RadialGradient rg = new RadialGradient(0, 0, 0, 0, 0, true, CycleMethod.NO_CYCLE, new Stop[]{new Stop(0, Color.WHITE), new Stop(1, Color.WHITE)});;
    public GoLoData(GoLogoLoApp initApp) {
        app = initApp;
        // GET ALL THE THINGS WE'LL NEED TO MANIUPLATE THE TABLE
        TableView tableView = (TableView) app.getGUIModule().getGUINode(GLGL_ITEMS_TABLE_VIEW);
        components = tableView.getItems();
        componentsSelectionModel = tableView.getSelectionModel();
        componentsSelectionModel.setSelectionMode(SelectionMode.MULTIPLE);
        workspacePane = (StackPane)((Pane) ((BorderPane) ((BorderPane) app.getWorkspaceComponent().getWorkspace()).getCenter()).getCenter()).getChildren().get(0);
        nodeControl = new GoLoNodeController(app);
        nodeSelectionModel = new GoLoNodeSelectionModel(app);
    }

    public void initBackground() {
        background = new Pane();
        clipper = new Rectangle();
        background.setOnMouseClicked(e->{
            isCanvasClicked = true;
            if(isItemSelected() && !isNodeClicked) {
                clearSelected();
            } else {
                isNodeClicked = false;
            }
        });

        workspacePane.getChildren().clear();
        workspacePane.getChildren().add(background);
//        background.getStyleClass().add(CLASS_GLGL_RECTANGLE_BACK);
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        double width = Double.parseDouble(props.getProperty(GLGL_DEFAULT_WIDTH));
        double height = Double.parseDouble(props.getProperty(GLGL_DEFAULT_HEIGHT));
        resize(width, height);
        setFill(rg);
        background.setClip(clipper);        
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
    
    public GoLoNodeController getNdoeController() {
        return nodeControl;
    }

    public void resize(double width, double height) {
        background.setMinSize(width, height);
        background.setMaxSize(width, height);
        clipper.setWidth(width);
        clipper.setHeight(height);
//        rect.setWidth(width);
//        rect.setHeight(height);
    }

    public void addComponent(GoLoComponentPrototype componentToAdd) {
        clearSelected();
        components.add(componentToAdd);
        background.getChildren().add(componentToAdd.goLoNode);
        updateIndicies();
        makeInteractable(componentToAdd);
        select(componentToAdd);
    }

    public void removeComponent(GoLoComponentPrototype componentToDelete) {
        components.remove(componentToDelete);
        clearSelected();
        background.getChildren().remove(componentToDelete.goLoNode);
        updateIndicies();
    }

    public void replaceItem(GoLoComponentPrototype componentToEdit, GoLoComponentPrototype editedItem) {
        int pos = components.indexOf(componentToEdit);
        components.set(pos, editedItem);
        select(editedItem);
    }

    public void moveItem(GoLoComponentPrototype initItem, int mode) {
        int pos = components.indexOf(initItem);
        components.set(pos, components.get(pos + mode));
        components.set(pos + mode, initItem);
        Node temp = initItem.goLoNode;
        background.getChildren().remove(pos);
        background.getChildren().add(pos + mode, temp);
        select(initItem);
        updateIndicies();
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
        select(component);
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
        clearSelected();
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
            background.getChildren().add(location, componentToAdd.getGoLoNode());
            select(componentToAdd);
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

    public void clearSelected() {
        this.nodeSelectionModel.clearSelection();
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
        clearSelected();
        rg = new RadialGradient(0, 0, 0, 0, 0, true, CycleMethod.NO_CYCLE, new Stop[]{new Stop(0, Color.WHITE), new Stop(1, Color.WHITE)});
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
            isNodeClicked = true;
            nodeSelectionModel.selectComponent(component);
            if (component.isText()) 
                if (e.getClickCount() == 2) {
                    nodeControl.processChangeText((Text) component.goLoNode);}
        });
        component.goLoNode.setOnMousePressed(e -> {
            component.xDiff.set(e.getX() - component.getX());
            component.yDiff.set(e.getY() - component.getY());
            component.oldX.set(component.getX());
            component.oldY.set(component.getY());
            
        });
        component.goLoNode.setOnMouseDragged(e -> {
            isNodeDragged = true;
            component.setCoordinate(e.getX() - component.xDiff.get(), e.getY() - component.yDiff.get());
            if(component.isRectangle())
                nodeSelectionModel.updateAnchors((GoLoRectangle)component);
        });
        component.goLoNode.setOnMouseReleased(e -> {
            if (component.isMoved()) {
                nodeControl.processMove(component);
            }
        });
        if(component.isCircle()) {
            component.goLoNode.setOnScroll(e->{
                isCircleScrolled = true;
                if(e.getDeltaY() < 0)
                    ((Circle)component.goLoNode).setRadius(((Circle)component.goLoNode).getRadius()*1.1);
                else
                    ((Circle)component.goLoNode).setRadius(((Circle)component.goLoNode).getRadius()/1.1);

            });
            component.goLoNode.setOnScrollFinished(e->{
                nodeControl.processCircleResize((GoLoCircle)component);
            });
        }
    }
    
    public GoLoNodeSelectionModel getNodeSelectionModel() {
        return nodeSelectionModel;
    }
    
    
    public Iterator<GoLoComponentPrototype> componentsIterator() {
        return this.components.iterator();
    }
    
    public boolean isCanvasClicked() {
        return isCanvasClicked;
    }
    
    public void setCanvasClicked(boolean isClicked) {
        isCanvasClicked = isClicked;
    }
    
    public void select(GoLoComponentPrototype component) {        
        nodeSelectionModel.selectComponent(component);
    }
    
    public boolean isNodeDragged() {
        return isNodeDragged;
    }
    
    public void setIsNodeDragged(boolean dragged) {
        isNodeDragged = dragged;
    }
    
    public StackPane getWorkSpacePane() {
        return workspacePane;
    }
    
    public void setIsNodeClicked(boolean b) {
        isNodeClicked = b;
    }
    
    public boolean getBlockValueListener() {
        return blockValueListener;
    }
    
    public void setBlockValueListener(boolean b) {
        blockValueListener = b;
    } 
    
    public boolean getIsCircleScrolled() {
        return isCircleScrolled;
    }
    
    public void setIsCircleScrolled(boolean b) {
        isCircleScrolled = b;
    }
    
    public void changeFill(RadialGradient gradient) {
        BackgroundFill bf = new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY);
        background.setBackground(new Background(bf));
    }
    public void setFill(RadialGradient gradient) {
        BackgroundFill bf = new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY);
        background.setBackground(new Background(bf));
        rg = gradient;
    }
    
    public RadialGradient getFill() {
        return rg;
    }
}
