/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.data;

import static glgl.GoLoPropertyType.GLGL_ITEMS_TABLE_VIEW;
import glgl.GoLogoLoApp;
import glgl.workspace.controllers.GoLoNodeController;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author changruizhou
 */
public class GoLoNodeSelectionModel {
    
    GoLogoLoApp app;
    ObservableList<GoLoComponentPrototype> components;
    TableView table;
    TableViewSelectionModel componentsSelectionModel;
    Node markedNode;
    Anchor[] anchors = new Anchor[4];
    Anchor topLeft = new Anchor();
    Anchor topRight = new Anchor();
    Anchor bottomLeft = new Anchor();
    Anchor bottomRight = new Anchor();
    GoLoNodeController nodeControl;
    
    GoLoNodeSelectionModel(GoLogoLoApp initApp) {
        app = initApp;
        table = (TableView) app.getGUIModule().getGUINode(GLGL_ITEMS_TABLE_VIEW);
        components = table.getItems();
        componentsSelectionModel = table.getSelectionModel();
        nodeControl = new GoLoNodeController(app);
    }
    
    public void selectComponent(GoLoComponentPrototype component) {
        clearSelection();
        componentsSelectionModel.select(component);
        markNodesSelected(component);
        app.getFoolproofModule().updateAll();
    }
    
    private void markNodesSelected(GoLoComponentPrototype component) {
        markedNode = component.goLoNode;
        component.markNode();
        if(component.isRectangle())
            addAnchors((GoLoRectangle)component);
    }
    
    public void clearSelection() {
        componentsSelectionModel.clearSelection();
        if(markedNode != null) {
            markedNode.setEffect(null);
        }
        markedNode = null;
        app.getFoolproofModule().updateAll();
        if(((GoLoData)app.getDataComponent()).getBackground() != null)
            removeAnchors();
    }
    
    private void addAnchors(GoLoRectangle rec) {
        updateAnchors(rec);
        ((GoLoData)app.getDataComponent()).getBackground().getChildren().add(topLeft);        
        ((GoLoData)app.getDataComponent()).getBackground().getChildren().add(topRight);
        ((GoLoData)app.getDataComponent()).getBackground().getChildren().add(bottomLeft);
        ((GoLoData)app.getDataComponent()).getBackground().getChildren().add(bottomRight);
        initAnchors(rec);
    }

    private void removeAnchors() {
        ((GoLoData)app.getDataComponent()).getBackground().getChildren().remove(topLeft);        
        ((GoLoData)app.getDataComponent()).getBackground().getChildren().remove(topRight);
        ((GoLoData)app.getDataComponent()).getBackground().getChildren().remove(bottomLeft);
        ((GoLoData)app.getDataComponent()).getBackground().getChildren().remove(bottomRight);
    }
    
    public void updateAnchors(GoLoRectangle rec) {
        Rectangle recNode = (Rectangle)rec.goLoNode;
        topLeft.setCenterX(recNode.getX());
        topLeft.setCenterY(recNode.getY());
        topRight.setCenterX(recNode.getX() + recNode.getWidth());
        topRight.setCenterY(recNode.getY());
        bottomLeft.setCenterX(recNode.getX());
        bottomLeft.setCenterY(recNode.getY() + recNode.getHeight());        
        bottomRight.setCenterX(recNode.getX() + recNode.getWidth());
        bottomRight.setCenterY(recNode.getY() + recNode.getHeight());
    }
    
    private void initAnchors(GoLoRectangle rec) {
        Rectangle recNode = (Rectangle)rec.goLoNode;
        //Setup anchor at top left
        topLeft.setOnMousePressed(e->{
            ((GoLoData)app.getDataComponent()).setIsNodeClicked(true);
            rec.oldX.set(rec.getX());
            rec.oldY.set(rec.getY());
            rec.oldHeight.set(recNode.getHeight());
            rec.oldWidth.set(recNode.getWidth());
        });
        topLeft.setOnMouseDragged(e->{
            ((GoLoData)app.getDataComponent()).setIsNodeDragged(true);
            recNode.setX(e.getX());
            recNode.setY(e.getY());
            recNode.setWidth(rec.oldWidth.get() - (e.getX() - rec.oldX.get()));
            recNode.setHeight(rec.oldHeight.get() - (e.getY() - rec.oldY.get()));
            updateAnchors(rec);
        });
        topLeft.setOnMouseReleased(e->{
            nodeControl.processResizeRectangle(rec);
        });
        
        //Setup anchor at top right
        topRight.setOnMousePressed(e->{
            ((GoLoData)app.getDataComponent()).setIsNodeClicked(true);
            rec.oldX.set(rec.getX());
            rec.oldY.set(rec.getY());
            rec.oldHeight.set(recNode.getHeight());
            rec.oldWidth.set(recNode.getWidth());
        });
        topRight.setOnMouseDragged(e->{
            ((GoLoData)app.getDataComponent()).setIsNodeDragged(true);
            recNode.setY(e.getY());
            recNode.setWidth(e.getX() - rec.oldX.get());
            recNode.setHeight(rec.oldHeight.get() - (e.getY() - rec.oldY.get()));
            updateAnchors(rec);
        });
        topRight.setOnMouseReleased(e->{
            nodeControl.processResizeRectangle(rec);
        });

        //Setup anchor at bottom left
        bottomLeft.setOnMousePressed(e->{
            ((GoLoData)app.getDataComponent()).setIsNodeClicked(true);
            rec.oldX.set(rec.getX());
            rec.oldY.set(rec.getY());
            rec.oldHeight.set(recNode.getHeight());
            rec.oldWidth.set(recNode.getWidth());
        });
        bottomLeft.setOnMouseDragged(e->{
            ((GoLoData)app.getDataComponent()).setIsNodeDragged(true);
            recNode.setX(e.getX());
            recNode.setWidth(rec.oldWidth.get() - (e.getX() - rec.oldX.get()));
            recNode.setHeight(e.getY() - rec.oldY.get());
            updateAnchors(rec);
        });
        bottomLeft.setOnMouseReleased(e->{
            nodeControl.processResizeRectangle(rec);
        });

        //Setup anchor at bottom right
        bottomRight.setOnMousePressed(e->{
            ((GoLoData)app.getDataComponent()).setIsNodeClicked(true);
            rec.oldX.set(rec.getX());
            rec.oldY.set(rec.getY());
            rec.oldHeight.set(recNode.getHeight());
            rec.oldWidth.set(recNode.getWidth());
        });
        bottomRight.setOnMouseDragged(e->{
            ((GoLoData)app.getDataComponent()).setIsNodeDragged(true);
            recNode.setWidth(e.getX() - rec.oldX.get());
            recNode.setHeight(e.getY() - rec.oldY.get());
            updateAnchors(rec);
        });
        bottomRight.setOnMouseReleased(e->{
            nodeControl.processResizeRectangle(rec);
        });
    }
}


class Anchor extends Circle {
    Anchor() {
        super(0, 0, 7);
        setFill(Color.BLACK);
        this.setOpacity(0.3);
        this.setManaged(false);
    }
}