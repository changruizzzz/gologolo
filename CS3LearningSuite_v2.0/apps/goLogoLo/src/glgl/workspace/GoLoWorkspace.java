/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.workspace;

import djf.components.AppWorkspaceComponent;
import static djf.modules.AppGUIModule.DISABLED;
import static djf.modules.AppGUIModule.ENABLED;
import static djf.modules.AppGUIModule.FOCUS_TRAVERSABLE;
import static djf.modules.AppGUIModule.HAS_KEY_HANDLER;
import djf.ui.AppNodesBuilder;
import static glgl.GoLoPropertyType.GLGL_ADD_CIRCLE_BUTTON;
import static glgl.GoLoPropertyType.GLGL_ADD_IMAGE_BUTTON;
import static glgl.GoLoPropertyType.GLGL_ADD_RECTANGLE_BUTTON;
import static glgl.GoLoPropertyType.GLGL_ADD_TEXT_BUTTON;
import static glgl.GoLoPropertyType.GLGL_ADD_TRIANGLE_BUTTON;
import static glgl.GoLoPropertyType.GLGL_BODY_PANE;
import static glgl.GoLoPropertyType.GLGL_BOLD_BUTTON;
import static glgl.GoLoPropertyType.GLGL_BORDER_BOX;
import static glgl.GoLoPropertyType.GLGL_BORDER_COLOR_PICKER;
import static glgl.GoLoPropertyType.GLGL_BORDER_COLOR_PICKER_LABEL;
import static glgl.GoLoPropertyType.GLGL_BORDER_RADIUS_LABEL;
import static glgl.GoLoPropertyType.GLGL_BORDER_THICKNESS_LABEL;
import static glgl.GoLoPropertyType.GLGL_CENTER_X_LABEL;
import static glgl.GoLoPropertyType.GLGL_CENTER_X_SLIDER;
import static glgl.GoLoPropertyType.GLGL_CENTER_Y_LABEL;
import static glgl.GoLoPropertyType.GLGL_CENTER_Y_SLIDER;
import static glgl.GoLoPropertyType.GLGL_CGRADIENT_RADIUS_SLIDER;
import static glgl.GoLoPropertyType.GLGL_COLOR_GRADIENT_BOX;
import static glgl.GoLoPropertyType.GLGL_COLOR_GRADIENT_LABEL;
import static glgl.GoLoPropertyType.GLGL_CYCLE_METHOD_COMBO;
import static glgl.GoLoPropertyType.GLGL_CYCLE_METHOD_LABEL;
import static glgl.GoLoPropertyType.GLGL_CYCLE_METHOD_OPTIONS;
import static glgl.GoLoPropertyType.GLGL_DECREASE_FONT_SIZE_BUTTON;
import static glgl.GoLoPropertyType.GLGL_DEFAULT_CYCLE_METHOD;
import static glgl.GoLoPropertyType.GLGL_DEFAULT_FONT;
import static glgl.GoLoPropertyType.GLGL_DEFAULT_FONT_SIZE;
import static glgl.GoLoPropertyType.GLGL_EDIT_ITEM_BUTTON;
import static glgl.GoLoPropertyType.GLGL_ELEMENTS_BOX;
import static glgl.GoLoPropertyType.GLGL_FOCUS_ANGLE_LABEL;
import static glgl.GoLoPropertyType.GLGL_FOCUS_ANGLE_SLIDER;
import static glgl.GoLoPropertyType.GLGL_FOCUS_DISTANCE_LABEL;
import static glgl.GoLoPropertyType.GLGL_FOCUS_DISTANCE_SLIDER;
import static glgl.GoLoPropertyType.GLGL_FONT_COMBO;
import static glgl.GoLoPropertyType.GLGL_FONT_OPTIONS;
import static glgl.GoLoPropertyType.GLGL_FONT_SETTING_BOX;
import static glgl.GoLoPropertyType.GLGL_FONT_SETTING_COMBO;
import static glgl.GoLoPropertyType.GLGL_FONT_SIZE_COMBO;
import static glgl.GoLoPropertyType.GLGL_FONT_SIZE_OPTIONS;
import static glgl.GoLoPropertyType.GLGL_FONT_STYLE_BOX;
import static glgl.GoLoPropertyType.GLGL_FUNCTION_PANE;
import static glgl.GoLoPropertyType.GLGL_GRADIENT_RADIUS_LABEL;
import static glgl.GoLoPropertyType.GLGL_INCREASE_FONT_SIZE_BUTTON;
import static glgl.GoLoPropertyType.GLGL_ITALIC_BUTTON;
import static glgl.GoLoPropertyType.GLGL_ITEMS_PANE;
import static glgl.GoLoPropertyType.GLGL_ITEMS_TABLE_VIEW;
import static glgl.GoLoPropertyType.GLGL_ITEM_BUTTONS_PANE;
import static glgl.GoLoPropertyType.GLGL_MOVE_ITEM_DOWN_BUTTON;
import static glgl.GoLoPropertyType.GLGL_MOVE_ITEM_UP_BUTTON;
import static glgl.GoLoPropertyType.GLGL_NAME_COLUMN;
import static glgl.GoLoPropertyType.GLGL_ORDER_COLUMN;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

import static glgl.GoLoPropertyType.GLGL_PANE;
import static glgl.GoLoPropertyType.GLGL_RADIUS_SLIDER;
import static glgl.GoLoPropertyType.GLGL_REMOVE_BUTTON;
import static glgl.GoLoPropertyType.GLGL_STOP_0_COLOR_PICKER;
import static glgl.GoLoPropertyType.GLGL_STOP_0_COLOR_PICKER_LABEL;
import static glgl.GoLoPropertyType.GLGL_STOP_1_COLOR_PICKER;
import static glgl.GoLoPropertyType.GLGL_STOP_1_COLOR_PICKER_LABEL;
import static glgl.GoLoPropertyType.GLGL_THICKNESS_SLIDER;
import static glgl.GoLoPropertyType.GLGL_TYPE_COLUMN;
import static glgl.GoLoPropertyType.GLGL_UNDERLINE_BUTTON;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_BOX;



import glgl.GoLogoLoApp;
import glgl.data.GoLoItemPrototype;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_BODY;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_COLOR_PICKER;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_COLUMN;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_COMBO;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_FUNCTION_PANE;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_ICON_BUTTON;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_ITEM_PANE;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_PANE;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_PROMPT;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_SLIDER;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_SMALL_HEADER;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_TABLE;
import java.time.LocalDate;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;



/**
 *
 * @author Changrui Zhou
 */
public class GoLoWorkspace extends AppWorkspaceComponent {

    public GoLoWorkspace(GoLogoLoApp app) {
        super(app);

        // LAYOUT THE APP
        initLayout();
        
        // 
        initFoolproofDesign();
    }
        
    // THIS HELPER METHOD INITIALIZES ALL THE CONTROLS IN THE WORKSPACE
    private void initLayout() {
        // FIRST LOAD THE FONT FAMILIES FOR THE COMBO BOX
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
        AppNodesBuilder glglBuilder = app.getGUIModule().getNodesBuilder();
        
        
//        app.getGUIModule().getTopToolbarPane().getChildren().add(3, workspace);
                
                
                
                
                
	// THIS HOLDS ALL THE CONTROLS IN THE WORKSPACE
        BorderPane glglPane = new BorderPane();
        // THIS HAS THE ITEMS PANE COMPONENTS IN THE LEFT
        VBox itemsPane              = glglBuilder.buildVBox(GLGL_ITEMS_PANE,                    null,       null,   CLASS_GLGL_ITEM_PANE, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        glglPane.setLeft(itemsPane);
        // AND NOW THE TABLE
        TableView<GoLoItemPrototype> itemsTable = glglBuilder.buildTableView(GLGL_ITEMS_TABLE_VIEW,       itemsPane,          null,   CLASS_GLGL_TABLE, HAS_KEY_HANDLER,    FOCUS_TRAVERSABLE,  true);
        TableColumn orderColumn              = glglBuilder.buildTableColumn(  GLGL_ORDER_COLUMN,    itemsTable,         CLASS_GLGL_COLUMN);
        TableColumn nameColumn           = glglBuilder.buildTableColumn(  GLGL_NAME_COLUMN, itemsTable,         CLASS_GLGL_COLUMN);
        TableColumn typeColumn             = glglBuilder.buildTableColumn(  GLGL_TYPE_COLUMN,  itemsTable,         CLASS_GLGL_COLUMN);
        itemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // SPECIFY THE TYPES FOR THE COLUMNS
        orderColumn.setCellValueFactory(     new PropertyValueFactory<String,    String>("Order"));
        nameColumn.setCellValueFactory(  new PropertyValueFactory<String,    String>("Name"));
        typeColumn.setCellValueFactory(    new PropertyValueFactory<LocalDate, String>("Type"));
        
        HBox itemButtonsPane        = glglBuilder.buildHBox(GLGL_ITEM_BUTTONS_PANE,             itemsPane,          null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        Button moveItemUpButton     = glglBuilder.buildIconButton(GLGL_MOVE_ITEM_UP_BUTTON,     itemButtonsPane,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button moveItemDownButton   = glglBuilder.buildIconButton(GLGL_MOVE_ITEM_DOWN_BUTTON,   itemButtonsPane,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button editItemButton       = glglBuilder.buildIconButton(GLGL_EDIT_ITEM_BUTTON,        itemButtonsPane,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);



        
        
        //This IS THE MAIN WORKING AREA IN THE MIDDLE
        VBox bodyPane                = glglBuilder.buildVBox(GLGL_BODY_PANE,                    null,       null,   CLASS_GLGL_BODY, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        glglPane.setCenter(bodyPane);
        






        //THIS IS THE CONTROL PANEL AND ALL BUTTONS IN THE RIGHT 
        VBox functionPane               = glglBuilder.buildVBox(GLGL_FUNCTION_PANE,                 null,   null,   CLASS_GLGL_FUNCTION_PANE, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        glglPane.setRight(functionPane);
        //ADD/DELETE ELEMENTS CONTROL
        HBox elementsBox                = glglBuilder.buildHBox(GLGL_ELEMENTS_BOX,                  functionPane,   null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        Button addTextButton            = glglBuilder.buildIconButton(GLGL_ADD_TEXT_BUTTON,         elementsBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addImageButton           = glglBuilder.buildIconButton(GLGL_ADD_IMAGE_BUTTON,        elementsBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addRectangleButton       = glglBuilder.buildIconButton(GLGL_ADD_RECTANGLE_BUTTON,    elementsBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addCircleButton          = glglBuilder.buildIconButton(GLGL_ADD_CIRCLE_BUTTON,       elementsBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addTriangleButton        = glglBuilder.buildIconButton(GLGL_ADD_TRIANGLE_BUTTON,     elementsBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button RemoveButton             = glglBuilder.buildIconButton(GLGL_REMOVE_BUTTON,           elementsBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  DISABLED);
	//FONT SETTING CONTROL
        VBox fontSettingBox             = glglBuilder.buildVBox(GLGL_FONT_SETTING_BOX,            functionPane,   null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        HBox fontSettingCombo           = glglBuilder.buildHBox(GLGL_FONT_SETTING_COMBO,                  fontSettingBox,   null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        ComboBox fontCombo              = glglBuilder.buildComboBox(GLGL_FONT_COMBO, GLGL_FONT_OPTIONS, GLGL_DEFAULT_FONT, fontSettingCombo, null, CLASS_GLGL_COMBO, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ComboBox fontSizeCombo          = glglBuilder.buildComboBox(GLGL_FONT_SIZE_COMBO, GLGL_FONT_SIZE_OPTIONS, GLGL_DEFAULT_FONT_SIZE, fontSettingCombo, null, CLASS_GLGL_COMBO, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        HBox fontStyleBox                = glglBuilder.buildHBox(GLGL_FONT_STYLE_BOX,                  fontSettingBox,   null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);        
        Button boldButton               = glglBuilder.buildIconButton(GLGL_BOLD_BUTTON,         fontStyleBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button italicButton               = glglBuilder.buildIconButton(GLGL_ITALIC_BUTTON,         fontStyleBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button increaseFontSizeButton               = glglBuilder.buildIconButton(GLGL_INCREASE_FONT_SIZE_BUTTON,         fontStyleBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button DecreaseFontSizeButton               = glglBuilder.buildIconButton(GLGL_DECREASE_FONT_SIZE_BUTTON,         fontStyleBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button underlineButton               = glglBuilder.buildIconButton(GLGL_UNDERLINE_BUTTON,         fontStyleBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        
        //BORDER CONTROL
        VBox borderBox                  = glglBuilder.buildVBox(GLGL_BORDER_BOX,                    functionPane,   null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        Label thicknessSliderLable      = glglBuilder.buildLabel(GLGL_BORDER_THICKNESS_LABEL, borderBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider thicknessSlider          = glglBuilder.buildSlider(GLGL_THICKNESS_SLIDER, borderBox, null, CLASS_GLGL_SLIDER, 0, 100, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label radiusSliderLable      = glglBuilder.buildLabel(GLGL_BORDER_RADIUS_LABEL, borderBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider radiusSlider          = glglBuilder.buildSlider(GLGL_RADIUS_SLIDER, borderBox, null, CLASS_GLGL_SLIDER, 0, 100, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label borderColorPickerLable      = glglBuilder.buildLabel(GLGL_BORDER_COLOR_PICKER_LABEL, borderBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ColorPicker borderColorPicker =glglBuilder.buildColorPicker(GLGL_BORDER_COLOR_PICKER, borderBox, null, CLASS_GLGL_COLOR_PICKER, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        //COLOR GRADIENT CONTROL
        VBox gradientBox                  = glglBuilder.buildVBox(GLGL_COLOR_GRADIENT_BOX,                    functionPane,   null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        Label colorGradientLable      = glglBuilder.buildLabel(GLGL_COLOR_GRADIENT_LABEL, gradientBox, null, CLASS_GLGL_SMALL_HEADER, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label focusAngleLable      = glglBuilder.buildLabel(GLGL_FOCUS_ANGLE_LABEL, gradientBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider focusAngleSlider          = glglBuilder.buildSlider(GLGL_FOCUS_ANGLE_SLIDER, gradientBox, null, CLASS_GLGL_SLIDER, 0, 100, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label focusDistanceLable      = glglBuilder.buildLabel(GLGL_FOCUS_DISTANCE_LABEL, gradientBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider focusDistanceSlider          = glglBuilder.buildSlider(GLGL_FOCUS_DISTANCE_SLIDER, gradientBox, null, CLASS_GLGL_SLIDER, 0, 100, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label centerXSliderLable      = glglBuilder.buildLabel(GLGL_CENTER_X_LABEL, gradientBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider centerXSlider          = glglBuilder.buildSlider(GLGL_CENTER_X_SLIDER, gradientBox, null, CLASS_GLGL_SLIDER, 0, 100, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label centerYSliderLable      = glglBuilder.buildLabel(GLGL_CENTER_Y_LABEL, gradientBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider centerYSlider          = glglBuilder.buildSlider(GLGL_CENTER_Y_SLIDER, gradientBox, null, CLASS_GLGL_SLIDER, 0, 100, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label gradientRadiusliderLable      = glglBuilder.buildLabel(GLGL_GRADIENT_RADIUS_LABEL, gradientBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider gradientRadiusSlider          = glglBuilder.buildSlider(GLGL_CGRADIENT_RADIUS_SLIDER, gradientBox, null, CLASS_GLGL_SLIDER, 0, 100, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label cycleMethodLable      = glglBuilder.buildLabel(GLGL_CYCLE_METHOD_LABEL, gradientBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ComboBox cycleMehodCombo              = glglBuilder.buildComboBox(GLGL_CYCLE_METHOD_COMBO, GLGL_CYCLE_METHOD_OPTIONS, GLGL_DEFAULT_CYCLE_METHOD, gradientBox, null, CLASS_GLGL_COMBO, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);



        Label stop0ColorPickerLable      = glglBuilder.buildLabel(GLGL_STOP_0_COLOR_PICKER_LABEL, gradientBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ColorPicker stop0ColorPicker =glglBuilder.buildColorPicker(GLGL_STOP_0_COLOR_PICKER, gradientBox, null, CLASS_GLGL_COLOR_PICKER, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label stop1ColorPickerLable      = glglBuilder.buildLabel(GLGL_STOP_1_COLOR_PICKER_LABEL, gradientBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ColorPicker stop1ColorPicker =glglBuilder.buildColorPicker(GLGL_STOP_1_COLOR_PICKER, gradientBox, null, CLASS_GLGL_COLOR_PICKER, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);

        
        
        
        
        // AND PUT EVERYTHING IN THE WORKSPACE
	workspace = new BorderPane();
	((BorderPane)workspace).setCenter(glglPane);
//
//        // AND NOW SETUP ALL THE EVENT HANDLING CONTROLLERS
//        ownerTextField.textProperty().addListener(e->{
//            app.getFileModule().markAsEdited(true);
//        });
//        ItemsController itemsController = new ItemsController((ToDoListMakerApp)app);
//        addItemButton.setOnAction(e->{
//            itemsController.processAddItem();
//        });
//        removeItemButton.setOnAction(e->{
//            itemsController.processRemoveItems();
//        });
//        editItemButton.setOnAction(e->{
//            itemsController.processEditItem();
//        });
//        moveItemUpButton.setOnAction(e->{
//            itemsController.processMoveItem(-1);
//        });
//        moveItemDownButton.setOnAction(e->{
//            itemsController.processMoveItem(1);
//        });       
//        
//        
//        itemsTable.setOnMouseClicked(e -> {
//            app.getFoolproofModule().updateAll();
//            if(e.getClickCount() == 2) 
//                itemsController.processEditItem();                
//        });
//
//        ItemsTableController iTC = new ItemsTableController(app);
//        itemsTable.widthProperty().addListener(e->{
//            iTC.processChangeTableSize();
//        });
//        itemsTable.setOnSort(new EventHandler<SortEvent<TableView<ToDoItemPrototype>>>(){
//            @Override
//            public void handle(SortEvent<TableView<ToDoItemPrototype>> event) {
//                ToDoData data = (ToDoData)app.getDataComponent();
//                ArrayList<ToDoItemPrototype> oldListOrder = data.getCurrentItemsOrder();
//                TableView view = event.getSource();
//                ObservableList sortOrder = view.getSortOrder();
//                if ((sortOrder != null) && (sortOrder.size() == 1)) {
//                    TableColumn sortColumn = event.getSource().getSortOrder().get(0);
//                    String columnText = sortColumn.getText();
//                    SortType sortType = sortColumn.getSortType();
//                    System.out.println("Sort by " + columnText);
//                    event.consume();
//                    SortItems_Transaction transaction = new SortItems_Transaction(data, oldListOrder, columnText, sortType);
//                    app.processTransaction(transaction);
//                    app.getFoolproofModule().updateAll();
//                }
//            }            
//        });
    }
    
    public void initFoolproofDesign() {
//        AppGUIModule gui = app.getGUIModule();
//        AppFoolproofModule foolproofSettings = app.getFoolproofModule();
//        foolproofSettings.registerModeSettings(GLGL_FOOLPROOF_SETTINGS, 
//                new ToDoSelectionFoolproofDesign((ToDoListMakerApp)app));
    }

    @Override
    public void processWorkspaceKeyEvent(KeyEvent ke) {
       // System.out.println("WORKSPACE REPONSE TO " + ke.getCharacter());
    }
}