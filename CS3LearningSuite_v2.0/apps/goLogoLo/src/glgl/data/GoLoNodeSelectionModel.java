/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.data;

import glgl.GoLogoLoApp;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

/**
 *
 * @author changruizhou
 */
public class GoLoNodeSelectionModel {
    
    GoLogoLoApp app;
    ObservableList<Node> nodes;
    TableViewSelectionModel componentsSelectionModel;
    int markedIndex = -1;
    
    GoLoNodeSelectionModel(GoLogoLoApp initApp) {
        app = initApp;
    }
    
    public void selectComponent(GoLoComponentPrototype component) {
        clearSelection();
        componentsSelectionModel.select(component);
        markNodesSelected(component);
        app.getFoolproofModule().updateAll();
    }
    
    public void setNodesList(ObservableList<Node> nodes) {
        this.nodes = nodes;
    }
    
    public void setTable(TableViewSelectionModel componentsSelectionModel) {
        this.componentsSelectionModel = componentsSelectionModel;
    }
    
    private void markNodesSelected(GoLoComponentPrototype component) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
        Node theNode = component.goLoNode;
        markedIndex = nodes.indexOf(theNode);
        theNode.setEffect(dropShadow);
    }
    
    public void clearSelection() {
        componentsSelectionModel.clearSelection();
        if(markedIndex >= 0) {
            nodes.get(markedIndex).setEffect(null);
        }
        markedIndex = -1;
        app.getFoolproofModule().updateAll();
    }
    
}
