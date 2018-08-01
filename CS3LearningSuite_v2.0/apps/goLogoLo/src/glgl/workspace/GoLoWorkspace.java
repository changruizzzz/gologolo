/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.workspace;

import static djf.AppPropertyType.RESET_VIEW_BUTTON;
import static djf.AppPropertyType.RESIZE_BUTTON;
import static djf.AppPropertyType.SNAP_BOX;
import static djf.AppPropertyType.SNAP_BUTTON;
import static djf.AppPropertyType.ZOOM_IN_BUTTON;
import static djf.AppPropertyType.ZOOM_OUT_BUTTON;
import djf.components.AppWorkspaceComponent;
import static djf.modules.AppGUIModule.DISABLED;
import static djf.modules.AppGUIModule.ENABLED;
import static djf.modules.AppGUIModule.FOCUS_TRAVERSABLE;
import static djf.modules.AppGUIModule.HAS_KEY_HANDLER;
import djf.ui.AppNodesBuilder;
import static djf.ui.style.DJFStyle.CLASS_DJF_ICON_BUTTON;
import static djf.ui.style.DJFStyle.CLASS_DJF_TOOLBAR_PANE;
import static djf.ui.style.DJFStyle.CLASS_DJF_TOOLBAR_TEXT;
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
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_PROMPT;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_SLIDER;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_SMALL_HEADER;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_TABLE;
import java.time.LocalDate;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
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
        AppNodesBuilder glglNodesBuilder = app.getGUIModule().getNodesBuilder();
        
        
        ToolBar zoomToolBar = new ToolBar();
        zoomToolBar.getStyleClass().add(CLASS_DJF_TOOLBAR_PANE);
//        zoomController = new AppZoomController(app);

        // THIS IS AN ALL OR NOTHING TOOLBAR
        Button resetButton = glglNodesBuilder.buildIconButton(RESET_VIEW_BUTTON, null, zoomToolBar, CLASS_DJF_ICON_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        resetButton.setOnAction(e -> {
//            zoomController.processZoomRequest();
        });
        Button zoomInButton = glglNodesBuilder.buildIconButton(ZOOM_IN_BUTTON, null, zoomToolBar, CLASS_DJF_ICON_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        zoomInButton.setOnAction(e -> {
//            zoomController.processZoomRequest();
        });
        Button zoomOutButton = glglNodesBuilder.buildIconButton(ZOOM_OUT_BUTTON, null, zoomToolBar, CLASS_DJF_ICON_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        zoomOutButton.setOnAction(e -> {
//            zoomController.processZoomRequest();
        });

        Button resizeButton = glglNodesBuilder.buildIconButton(RESIZE_BUTTON, null, zoomToolBar, CLASS_DJF_ICON_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        resizeButton.setOnAction(e -> {
//            zoomController.processResizeRequest();
        });
        
        CheckBox snapBox = glglNodesBuilder.buildCheckBox(SNAP_BOX, null, zoomToolBar, null, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label snapLabel = glglNodesBuilder.buildLabel(SNAP_BUTTON, null, zoomToolBar, CLASS_DJF_TOOLBAR_TEXT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        
    
        app.getGUIModule().getTopToolbarPane().getChildren().add(3, zoomToolBar);
                
                
                
                
                
	// THIS HOLDS ALL THE CONTROLS IN THE WORKSPACE
        BorderPane glglPane = new BorderPane();
        // THIS HAS THE ITEMS PANE COMPONENTS IN THE LEFT
        VBox itemsPane              = glglNodesBuilder.buildVBox(GLGL_ITEMS_PANE,                    null,       null,   CLASS_GLGL_ITEM_PANE, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        glglPane.setLeft(itemsPane);
        // AND NOW THE TABLE
        TableView<GoLoItemPrototype> itemsTable = glglNodesBuilder.buildTableView(GLGL_ITEMS_TABLE_VIEW,       itemsPane,          null,   CLASS_GLGL_TABLE, HAS_KEY_HANDLER,    FOCUS_TRAVERSABLE,  true);
        TableColumn orderColumn              = glglNodesBuilder.buildTableColumn(  GLGL_ORDER_COLUMN,    itemsTable,         CLASS_GLGL_COLUMN);
        TableColumn nameColumn           = glglNodesBuilder.buildTableColumn(  GLGL_NAME_COLUMN, itemsTable,         CLASS_GLGL_COLUMN);
        TableColumn typeColumn             = glglNodesBuilder.buildTableColumn(  GLGL_TYPE_COLUMN,  itemsTable,         CLASS_GLGL_COLUMN);
        itemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // SPECIFY THE TYPES FOR THE COLUMNS
        orderColumn.setCellValueFactory(     new PropertyValueFactory<String,    String>("Order"));
        nameColumn.setCellValueFactory(  new PropertyValueFactory<String,    String>("Name"));
        typeColumn.setCellValueFactory(    new PropertyValueFactory<LocalDate, String>("Type"));
        
        HBox itemButtonsPane        = glglNodesBuilder.buildHBox(GLGL_ITEM_BUTTONS_PANE,             itemsPane,          null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        Button moveItemUpButton     = glglNodesBuilder.buildIconButton(GLGL_MOVE_ITEM_UP_BUTTON,     itemButtonsPane,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button moveItemDownButton   = glglNodesBuilder.buildIconButton(GLGL_MOVE_ITEM_DOWN_BUTTON,   itemButtonsPane,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button editItemButton       = glglNodesBuilder.buildIconButton(GLGL_EDIT_ITEM_BUTTON,        itemButtonsPane,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);



        
        
        //This IS THE MAIN WORKING AREA IN THE MIDDLE
        VBox bodyPane                = glglNodesBuilder.buildVBox(GLGL_BODY_PANE,                    null,       null,   CLASS_GLGL_BODY, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        glglPane.setCenter(bodyPane);
        






        //THIS IS THE CONTROL PANEL AND ALL BUTTONS IN THE RIGHT 
        VBox functionPane               = glglNodesBuilder.buildVBox(GLGL_FUNCTION_PANE,                 null,   null,   CLASS_GLGL_FUNCTION_PANE, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        glglPane.setRight(functionPane);
        //ADD/DELETE ELEMENTS CONTROL
        HBox elementsBox                = glglNodesBuilder.buildHBox(GLGL_ELEMENTS_BOX,                  functionPane,   null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        Button addTextButton            = glglNodesBuilder.buildIconButton(GLGL_ADD_TEXT_BUTTON,         elementsBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addImageButton           = glglNodesBuilder.buildIconButton(GLGL_ADD_IMAGE_BUTTON,        elementsBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addRectangleButton       = glglNodesBuilder.buildIconButton(GLGL_ADD_RECTANGLE_BUTTON,    elementsBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addCircleButton          = glglNodesBuilder.buildIconButton(GLGL_ADD_CIRCLE_BUTTON,       elementsBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addTriangleButton        = glglNodesBuilder.buildIconButton(GLGL_ADD_TRIANGLE_BUTTON,     elementsBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button RemoveButton             = glglNodesBuilder.buildIconButton(GLGL_REMOVE_BUTTON,           elementsBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  DISABLED);
	//FONT SETTING CONTROL
        VBox fontSettingBox             = glglNodesBuilder.buildVBox(GLGL_FONT_SETTING_BOX,            functionPane,   null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        HBox fontSettingCombo           = glglNodesBuilder.buildHBox(GLGL_FONT_SETTING_COMBO,                  fontSettingBox,   null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        ComboBox fontCombo              = glglNodesBuilder.buildComboBox(GLGL_FONT_COMBO, GLGL_FONT_OPTIONS, GLGL_DEFAULT_FONT, fontSettingCombo, null, CLASS_GLGL_COMBO, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ComboBox fontSizeCombo          = glglNodesBuilder.buildComboBox(GLGL_FONT_SIZE_COMBO, GLGL_FONT_SIZE_OPTIONS, GLGL_DEFAULT_FONT_SIZE, fontSettingCombo, null, CLASS_GLGL_COMBO, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        HBox fontStyleBox                = glglNodesBuilder.buildHBox(GLGL_FONT_STYLE_BOX,                  fontSettingBox,   null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);        
        Button boldButton               = glglNodesBuilder.buildIconButton(GLGL_BOLD_BUTTON,         fontStyleBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button italicButton               = glglNodesBuilder.buildIconButton(GLGL_ITALIC_BUTTON,         fontStyleBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button increaseFontSizeButton               = glglNodesBuilder.buildIconButton(GLGL_INCREASE_FONT_SIZE_BUTTON,         fontStyleBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button DecreaseFontSizeButton               = glglNodesBuilder.buildIconButton(GLGL_DECREASE_FONT_SIZE_BUTTON,         fontStyleBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button underlineButton               = glglNodesBuilder.buildIconButton(GLGL_UNDERLINE_BUTTON,         fontStyleBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        
        //BORDER CONTROL
        VBox borderBox                  = glglNodesBuilder.buildVBox(GLGL_BORDER_BOX,                    functionPane,   null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        Label thicknessSliderLable      = glglNodesBuilder.buildLabel(GLGL_BORDER_THICKNESS_LABEL, borderBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider thicknessSlider          = glglNodesBuilder.buildSlider(GLGL_THICKNESS_SLIDER, borderBox, null, CLASS_GLGL_SLIDER, 0, 100, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label radiusSliderLable      = glglNodesBuilder.buildLabel(GLGL_BORDER_RADIUS_LABEL, borderBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider radiusSlider          = glglNodesBuilder.buildSlider(GLGL_RADIUS_SLIDER, borderBox, null, CLASS_GLGL_SLIDER, 0, 100, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label borderColorPickerLable      = glglNodesBuilder.buildLabel(GLGL_BORDER_COLOR_PICKER_LABEL, borderBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ColorPicker borderColorPicker =glglNodesBuilder.buildColorPicker(GLGL_BORDER_COLOR_PICKER, borderBox, null, CLASS_GLGL_COLOR_PICKER, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        //COLOR GRADIENT CONTROL
        VBox gradientBox                  = glglNodesBuilder.buildVBox(GLGL_COLOR_GRADIENT_BOX,                    functionPane,   null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        Label colorGradientLable      = glglNodesBuilder.buildLabel(GLGL_COLOR_GRADIENT_LABEL, gradientBox, null, CLASS_GLGL_SMALL_HEADER, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label focusAngleLable      = glglNodesBuilder.buildLabel(GLGL_FOCUS_ANGLE_LABEL, gradientBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider focusAngleSlider          = glglNodesBuilder.buildSlider(GLGL_FOCUS_ANGLE_SLIDER, gradientBox, null, CLASS_GLGL_SLIDER, 0, 100, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label focusDistanceLable      = glglNodesBuilder.buildLabel(GLGL_FOCUS_DISTANCE_LABEL, gradientBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider focusDistanceSlider          = glglNodesBuilder.buildSlider(GLGL_FOCUS_DISTANCE_SLIDER, gradientBox, null, CLASS_GLGL_SLIDER, 0, 100, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label centerXSliderLable      = glglNodesBuilder.buildLabel(GLGL_CENTER_X_LABEL, gradientBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider centerXSlider          = glglNodesBuilder.buildSlider(GLGL_CENTER_X_SLIDER, gradientBox, null, CLASS_GLGL_SLIDER, 0, 100, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label centerYSliderLable      = glglNodesBuilder.buildLabel(GLGL_CENTER_Y_LABEL, gradientBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider centerYSlider          = glglNodesBuilder.buildSlider(GLGL_CENTER_Y_SLIDER, gradientBox, null, CLASS_GLGL_SLIDER, 0, 100, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label gradientRadiusliderLable      = glglNodesBuilder.buildLabel(GLGL_GRADIENT_RADIUS_LABEL, gradientBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider gradientRadiusSlider          = glglNodesBuilder.buildSlider(GLGL_CGRADIENT_RADIUS_SLIDER, gradientBox, null, CLASS_GLGL_SLIDER, 0, 100, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label cycleMethodLable      = glglNodesBuilder.buildLabel(GLGL_CYCLE_METHOD_LABEL, gradientBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ComboBox cycleMehodCombo              = glglNodesBuilder.buildComboBox(GLGL_CYCLE_METHOD_COMBO, GLGL_CYCLE_METHOD_OPTIONS, GLGL_DEFAULT_CYCLE_METHOD, gradientBox, null, CLASS_GLGL_COMBO, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);



        Label stop0ColorPickerLable      = glglNodesBuilder.buildLabel(GLGL_STOP_0_COLOR_PICKER_LABEL, gradientBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ColorPicker stop0ColorPicker =glglNodesBuilder.buildColorPicker(GLGL_STOP_0_COLOR_PICKER, gradientBox, null, CLASS_GLGL_COLOR_PICKER, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label stop1ColorPickerLable      = glglNodesBuilder.buildLabel(GLGL_STOP_1_COLOR_PICKER_LABEL, gradientBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ColorPicker stop1ColorPicker =glglNodesBuilder.buildColorPicker(GLGL_STOP_1_COLOR_PICKER, gradientBox, null, CLASS_GLGL_COLOR_PICKER, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);

        
        
        
        
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