package glgl.workspace.controllers;

import djf.AppTemplate;
import djf.modules.AppGUIModule;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import glgl.GoLogoLoApp;
import static glgl.GoLoPropertyType.GLGL_ITEMS_TABLE_VIEW;
import glgl.data.GoLoComponentPrototype;

/**
 *
 * @author ChangruiZhou
 */
public class ItemsTableController {
    GoLogoLoApp app;

    public ItemsTableController(AppTemplate initApp) {
        app = (GoLogoLoApp)initApp;
    }

    public void processChangeTableSize() {
        AppGUIModule gui = app.getGUIModule();
        TableView<GoLoComponentPrototype> itemsTable = (TableView)gui.getGUINode(GLGL_ITEMS_TABLE_VIEW);
        ObservableList columns = itemsTable.getColumns();
        for (int i = 0; i < columns.size(); i++) {
            TableColumn column = (TableColumn)columns.get(i);
            column.setMinWidth(itemsTable.widthProperty().getValue()/columns.size());
            column.setMaxWidth(itemsTable.widthProperty().getValue()/columns.size());
        }
    }    
}
