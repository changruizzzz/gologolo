/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.workspace;

import static djf.AppPropertyType.RESET_VIEW_BUTTON;
import static djf.AppPropertyType.RESIZE_BUTTON;
import static djf.AppPropertyType.ZOOM_IN_BUTTON;
import static djf.AppPropertyType.ZOOM_OUT_BUTTON;
import djf.components.AppWorkspaceComponent;
import djf.modules.AppFoolproofModule;
import djf.modules.AppGUIModule;
import static djf.modules.AppGUIModule.ENABLED;
import static djf.modules.AppGUIModule.FOCUS_TRAVERSABLE;
import static djf.modules.AppGUIModule.HAS_KEY_HANDLER;
import djf.ui.AppNodesBuilder;
import static djf.ui.style.DJFStyle.CLASS_DJF_ICON_BUTTON;
import static djf.ui.style.DJFStyle.CLASS_DJF_TOOLBAR_PANE;
import static glgl.GoLoPropertyType.GLGL_ADD_CIRCLE_BUTTON;
import static glgl.GoLoPropertyType.GLGL_ADD_IMAGE_BUTTON;
import static glgl.GoLoPropertyType.GLGL_ADD_RECTANGLE_BUTTON;
import static glgl.GoLoPropertyType.GLGL_ADD_TEXT_BUTTON;
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
import static glgl.GoLoPropertyType.GLGL_FOOLPROOF_SETTINGS;
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
import static glgl.GoLoPropertyType.GLGL_TEXT_COLOR_PICKER;
import static glgl.GoLoPropertyType.GLGL_THICKNESS_SLIDER;
import static glgl.GoLoPropertyType.GLGL_TYPE_COLUMN;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_BOX;



import glgl.GoLogoLoApp;
import glgl.data.GoLoComponentPrototype;
import glgl.data.GoLoData;
import glgl.workspace.controllers.ComponentController;
import glgl.workspace.controllers.ItemsTableController;
import glgl.workspace.fooproof.GoLoSelectionFoolproofDesign;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_BODY;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_COLOR_PICKER;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_COLUMN;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_COMBO;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_FONT_COMBO;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_FUNCTION_PANE;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_ICON_BUTTON;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_ITEM_PANE;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_PROMPT;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_SLIDER;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_SMALL_HEADER;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_TABLE;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;



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
        
        
        
        ComponentController componentsController = new ComponentController((GoLogoLoApp)app);
                
        // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
        AppNodesBuilder glglNodesBuilder = app.getGUIModule().getNodesBuilder();
        
        //ADD ZOOM TOOL BAR TO THE TOP TOOL BAR
        ToolBar zoomToolBar = new ToolBar();
        zoomToolBar.getStyleClass().add(CLASS_DJF_TOOLBAR_PANE);
        Button resetButton = glglNodesBuilder.buildIconButton(RESET_VIEW_BUTTON, null, zoomToolBar, CLASS_DJF_ICON_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button zoomInButton = glglNodesBuilder.buildIconButton(ZOOM_IN_BUTTON, null, zoomToolBar, CLASS_DJF_ICON_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button zoomOutButton = glglNodesBuilder.buildIconButton(ZOOM_OUT_BUTTON, null, zoomToolBar, CLASS_DJF_ICON_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button resizeButton = glglNodesBuilder.buildIconButton(RESIZE_BUTTON, null, zoomToolBar, CLASS_DJF_ICON_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        resizeButton.setOnAction(e -> {
            componentsController.processResizeBackground();
        }); 
//        CheckBox snapBox = glglNodesBuilder.buildCheckBox(SNAP_BOX, null, zoomToolBar, null, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
//        Label snapLabel = glglNodesBuilder.buildLabel(SNAP_BUTTON, null, zoomToolBar, CLASS_DJF_TOOLBAR_TEXT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        app.getGUIModule().getTopToolbarPane().getChildren().add(3, zoomToolBar);
                
                
                
                
                
	// THIS HOLDS ALL THE CONTROLS IN THE WORKSPACE
        BorderPane glglPane = new BorderPane();
        // THIS HAS THE ITEMS PANE COMPONENTS IN THE LEFT
        VBox itemsPane              = glglNodesBuilder.buildVBox(GLGL_ITEMS_PANE,                    null,       null,   CLASS_GLGL_ITEM_PANE, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);

        // AND NOW THE TABLE
        TableView<GoLoComponentPrototype> itemsTable = glglNodesBuilder.buildTableView(GLGL_ITEMS_TABLE_VIEW,       itemsPane,          null,   CLASS_GLGL_TABLE, HAS_KEY_HANDLER,    FOCUS_TRAVERSABLE,  true);
        TableColumn orderColumn              = glglNodesBuilder.buildTableColumn(  GLGL_ORDER_COLUMN,    itemsTable,         CLASS_GLGL_COLUMN);
        TableColumn nameColumn           = glglNodesBuilder.buildTableColumn(  GLGL_NAME_COLUMN, itemsTable,         CLASS_GLGL_COLUMN);
        TableColumn typeColumn             = glglNodesBuilder.buildTableColumn(  GLGL_TYPE_COLUMN,  itemsTable,         CLASS_GLGL_COLUMN);
//        itemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // SPECIFY THE TYPES FOR THE COLUMNS
        orderColumn.setCellValueFactory(     new PropertyValueFactory("order"));
        nameColumn.setCellValueFactory(  new PropertyValueFactory("name"));
        typeColumn.setCellValueFactory(    new PropertyValueFactory("type"));
        
        HBox itemButtonsPane        = glglNodesBuilder.buildHBox(GLGL_ITEM_BUTTONS_PANE,             itemsPane,          null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        Button moveItemUpButton     = glglNodesBuilder.buildIconButton(GLGL_MOVE_ITEM_UP_BUTTON,     itemButtonsPane,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button moveItemDownButton   = glglNodesBuilder.buildIconButton(GLGL_MOVE_ITEM_DOWN_BUTTON,   itemButtonsPane,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button editItemButton       = glglNodesBuilder.buildIconButton(GLGL_EDIT_ITEM_BUTTON,        itemButtonsPane,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        editItemButton.setOnAction(e -> {
            componentsController.processRename();
        });
        moveItemUpButton.setOnAction(e->{
            componentsController.processMoveComponent(-1);
        });        
        moveItemDownButton.setOnAction(e->{
            componentsController.processMoveComponent(1);
        });

        
        
        //This IS THE MAIN WORKING AREA IN THE MIDDLE
        ScrollPane bodyPane = new ScrollPane();
        bodyPane.getStyleClass().add(CLASS_GLGL_BODY);
        glglPane.setCenter(bodyPane);
        glglPane.setLeft(itemsPane);
        bodyPane.setOnMouseClicked(e->{
            if(!((GoLoData)app.getDataComponent()).isCanvasClicked()) {
                ((GoLoData)app.getDataComponent()).clearSelected();
            } else {
                ((GoLoData)app.getDataComponent()).setCanvasClicked(false);
            }
        });
        Rectangle clipper = new Rectangle();
        clipper.widthProperty().bind(bodyPane.widthProperty());
        clipper.heightProperty().bind(bodyPane.heightProperty());
        bodyPane.setClip(clipper);
        StackPane workArea = new StackPane();
        workArea.setStyle("-fx-background-color: #eaeaea;");
        
        workArea.prefWidthProperty().bind(bodyPane.widthProperty());
        workArea.prefHeightProperty().bind(bodyPane.heightProperty());


//        bodyPane.getChildren().add(workArea);
        bodyPane.setContent(workArea);
//        workArea.setManaged(false);
        
        zoomInButton.setOnAction(e -> {
            workArea.setScaleX(2 * workArea.getScaleX());
            workArea.setScaleY(2 * workArea.getScaleY());

        });

        zoomOutButton.setOnAction(e -> {
            workArea.setScaleX(0.5 * workArea.getScaleX());
            workArea.setScaleY(0.5 * workArea.getScaleY());   
            if(workArea.getScaleX() == 1) {
                workArea.setTranslateX(0);
                workArea.setTranslateY(0);
            }
        });
        resetButton.setOnAction(e -> {
            workArea.setScaleX(1);
            workArea.setScaleY(1); 
            workArea.setTranslateX(0);
            workArea.setTranslateY(0);
        }); 
        
        DoubleProperty scroll = new SimpleDoubleProperty();        
        workArea.setOnScroll(e->{
            if(e.getDeltaY() != 0) {
                if(e.getDeltaY() > 0)
                    scroll.set(e.getDeltaY() / 12);
                else
                    scroll.set((-12 / e.getDeltaY()));

                workArea.setScaleX(workArea.getScaleX() * scroll.get());
                workArea.setScaleY(workArea.getScaleY() * scroll.get());
                if(workArea.getScaleX() <= 1) {
                    workArea.setTranslateX(0);
                    workArea.setTranslateY(0);                
                }
            }
        });
        DoubleProperty xOffSet = new SimpleDoubleProperty();
        DoubleProperty yOffSet = new SimpleDoubleProperty();
        workArea.setOnMousePressed(e->{
            xOffSet.set(e.getSceneX() - workArea.getTranslateX());
            yOffSet.set(e.getSceneY() - workArea.getTranslateY());
        });
        workArea.setOnMouseDragged(e->{
            if(!((GoLoData)app.getDataComponent()).isNodeDragged() && workArea.getScaleX() > 1) {
                workArea.setTranslateX(e.getSceneX() - xOffSet.get());
                workArea.setTranslateY(e.getSceneY() - yOffSet.get());
            } else {
                ((GoLoData)app.getDataComponent()).setIsNodeDragged(false);
            }
        });
        
        //THIS IS THE CONTROL PANEL AND ALL BUTTONS IN THE RIGHT 
        VBox functionPane               = glglNodesBuilder.buildVBox(GLGL_FUNCTION_PANE,                 null,   null,   CLASS_GLGL_FUNCTION_PANE, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        glglPane.setRight(functionPane);
        //ADD/DELETE ELEMENTS CONTROL
        HBox elementsBox                = glglNodesBuilder.buildHBox(GLGL_ELEMENTS_BOX,                  functionPane,   null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        Button addTextButton            = glglNodesBuilder.buildIconButton(GLGL_ADD_TEXT_BUTTON,         elementsBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addImageButton           = glglNodesBuilder.buildIconButton(GLGL_ADD_IMAGE_BUTTON,        elementsBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addRectangleButton       = glglNodesBuilder.buildIconButton(GLGL_ADD_RECTANGLE_BUTTON,    elementsBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addCircleButton          = glglNodesBuilder.buildIconButton(GLGL_ADD_CIRCLE_BUTTON,       elementsBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
//        Button addTriangleButton        = glglNodesBuilder.buildIconButton(GLGL_ADD_TRIANGLE_BUTTON,     elementsBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button RemoveButton             = glglNodesBuilder.buildIconButton(GLGL_REMOVE_BUTTON,           elementsBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
	addRectangleButton.setOnAction(e->{
            componentsController.processAddRectangle();});
	addTextButton.setOnAction(e->{
            componentsController.processAddText();});
        addCircleButton.setOnAction(e->{
            componentsController.processAddCircle();});

        RemoveButton.setOnAction(e -> {
            componentsController.processRemoveItems();});



        //FONT SETTING CONTROL
        
        VBox fontSettingBox             = glglNodesBuilder.buildVBox(GLGL_FONT_SETTING_BOX,            functionPane,   null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        HBox fontSettingCombo           = glglNodesBuilder.buildHBox(GLGL_FONT_SETTING_COMBO,                  fontSettingBox,   null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        ComboBox fontCombo              = glglNodesBuilder.buildComboBox(GLGL_FONT_COMBO, GLGL_FONT_OPTIONS, GLGL_DEFAULT_FONT, fontSettingCombo, null, CLASS_GLGL_FONT_COMBO, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ComboBox fontSizeCombo          = glglNodesBuilder.buildComboBox(GLGL_FONT_SIZE_COMBO, GLGL_FONT_SIZE_OPTIONS, GLGL_DEFAULT_FONT_SIZE, fontSettingCombo, null, CLASS_GLGL_COMBO, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ColorPicker textColorPicker =glglNodesBuilder.buildColorPicker(GLGL_TEXT_COLOR_PICKER, fontSettingCombo, null, CLASS_GLGL_COLOR_PICKER, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        textColorPicker.getStyleClass().add("button");
        List<String> l = Font.getFamilies();
        textColorPicker.setOnAction(e->{
            componentsController.processTextColor(textColorPicker.getValue());
        });
        
//        ObservableList<String> ol = fontCombo.getItems();
//        l.forEach((fontFamily) -> {
//            ol.add(fontFamily);
//        });
//        fontCombo.getSelectionModel().select("Helvetica");
        ObservableList<Integer> ol2 = fontSizeCombo.getItems();
        for(int i = 8; i <= 36; i+=2) {
            ol2.add(i);
        }
        fontSizeCombo.getSelectionModel().select(new Integer(22));
        
        HBox fontStyleBox                = glglNodesBuilder.buildHBox(GLGL_FONT_STYLE_BOX,                  fontSettingBox,   null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);        
        Button boldButton               = glglNodesBuilder.buildIconButton(GLGL_BOLD_BUTTON,         fontStyleBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button italicButton               = glglNodesBuilder.buildIconButton(GLGL_ITALIC_BUTTON,         fontStyleBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button increaseFontSizeButton               = glglNodesBuilder.buildIconButton(GLGL_INCREASE_FONT_SIZE_BUTTON,         fontStyleBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button decreaseFontSizeButton               = glglNodesBuilder.buildIconButton(GLGL_DECREASE_FONT_SIZE_BUTTON,         fontStyleBox,    null,   CLASS_GLGL_ICON_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
  
        fontCombo.getSelectionModel().selectedItemProperty().addListener(e->{
            if(!((GoLoData)app.getDataComponent()).getBlockValueListener()) {
                componentsController.processChangeFont((String)fontCombo.getSelectionModel().getSelectedItem(), 
                        (Integer)fontSizeCombo.getSelectionModel().getSelectedItem(), false, false);
            }
        });        
        fontSizeCombo.getSelectionModel().selectedItemProperty().addListener(e->{
            if(!((GoLoData)app.getDataComponent()).getBlockValueListener()) {
                componentsController.processChangeFont((String)fontCombo.getSelectionModel().getSelectedItem(), 
                        (Integer)fontSizeCombo.getSelectionModel().getSelectedItem(), false, false);
            }
        });
        increaseFontSizeButton.setOnAction(e->{
            int newInt = (Integer)fontSizeCombo.getSelectionModel().getSelectedItem() + 2;
            fontSizeCombo.getSelectionModel().select(new Integer(newInt));});        
        decreaseFontSizeButton.setOnAction(e->{
            int newInt = (Integer)fontSizeCombo.getSelectionModel().getSelectedItem() - 2;
            fontSizeCombo.getSelectionModel().select(new Integer(newInt));});
        boldButton.setOnAction(e->{
            componentsController.processChangeFont((String)fontCombo.getSelectionModel().getSelectedItem(), 
                    (Integer)fontSizeCombo.getSelectionModel().getSelectedItem(), true, false);
        });        
        italicButton.setOnAction(e->{
            componentsController.processChangeFont((String)fontCombo.getSelectionModel().getSelectedItem(), 
                    (Integer)fontSizeCombo.getSelectionModel().getSelectedItem(), false, true);
        });        
//        textColorButton.setOnAction(e->{
//            componentsController.processChangeFont((String)fontCombo.getSelectionModel().getSelectedItem(), 
//                    (Integer)fontSizeCombo.getSelectionModel().getSelectedItem(), false, false, true);
//        });
        
        
        
        //BORDER CONTROL
        VBox borderBox                  = glglNodesBuilder.buildVBox(GLGL_BORDER_BOX,                    functionPane,   null,   CLASS_GLGL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        Label thicknessSliderLable      = glglNodesBuilder.buildLabel(GLGL_BORDER_THICKNESS_LABEL, borderBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider thicknessSlider          = glglNodesBuilder.buildSlider(GLGL_THICKNESS_SLIDER, borderBox, null, CLASS_GLGL_SLIDER, 0, 100, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label radiusSliderLable      = glglNodesBuilder.buildLabel(GLGL_BORDER_RADIUS_LABEL, borderBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider radiusSlider          = glglNodesBuilder.buildSlider(GLGL_RADIUS_SLIDER, borderBox, null, CLASS_GLGL_SLIDER, 0, 100, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label borderColorPickerLable      = glglNodesBuilder.buildLabel(GLGL_BORDER_COLOR_PICKER_LABEL, borderBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ColorPicker borderColorPicker =glglNodesBuilder.buildColorPicker(GLGL_BORDER_COLOR_PICKER, borderBox, null, CLASS_GLGL_COLOR_PICKER, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        thicknessSlider.setOnMousePressed(e->{
            componentsController.setOldBorderWidth(thicknessSlider.valueProperty().get());
        });
        thicknessSlider.valueProperty().addListener(e->{
            if(!((GoLoData)app.getDataComponent()).getBlockValueListener()) {
                componentsController.changeBorderWidth(thicknessSlider.valueProperty().get());
            }            
        });
        thicknessSlider.setOnMouseReleased(e->{
            componentsController.processBorderWidth();
        });
        
        
        
        
        
        
        
        radiusSlider.valueProperty().addListener(e->{
            if(!((GoLoData)app.getDataComponent()).getBlockValueListener()) {
                componentsController.changeRectangleRadius(radiusSlider.valueProperty().get());
            }
        });
        
        
        borderColorPicker.setOnAction(e->{
            componentsController.processBorderColor(borderColorPicker.getValue());
        });
        
        
        
        
        
        
        
        
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
        ComboBox cycleMethodCombo              = glglNodesBuilder.buildComboBox(GLGL_CYCLE_METHOD_COMBO, GLGL_CYCLE_METHOD_OPTIONS, GLGL_DEFAULT_CYCLE_METHOD, gradientBox, null, CLASS_GLGL_COMBO, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);



        Label stop0ColorPickerLable      = glglNodesBuilder.buildLabel(GLGL_STOP_0_COLOR_PICKER_LABEL, gradientBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ColorPicker stop0ColorPicker =glglNodesBuilder.buildColorPicker(GLGL_STOP_0_COLOR_PICKER, gradientBox, null, CLASS_GLGL_COLOR_PICKER, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label stop1ColorPickerLable      = glglNodesBuilder.buildLabel(GLGL_STOP_1_COLOR_PICKER_LABEL, gradientBox, null, CLASS_GLGL_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ColorPicker stop1ColorPicker =glglNodesBuilder.buildColorPicker(GLGL_STOP_1_COLOR_PICKER, gradientBox, null, CLASS_GLGL_COLOR_PICKER, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);

        
        
        
        
        // AND PUT EVERYTHING IN THE WORKSPACE
	workspace = new BorderPane();
	((BorderPane)workspace).setCenter(glglPane);     

        
        itemsTable.setOnMouseClicked(e -> {
            ((GoLoData)app.getDataComponent()).getNodeSelectionModel().selectComponent(itemsTable.getSelectionModel().getSelectedItem());
            GoLoComponentPrototype temp = itemsTable.getSelectionModel().getSelectedItem();
            app.getFoolproofModule().updateAll();
            if(e.getClickCount() == 2) 
                if(temp.getType().equals("Text"))
                ((GoLoData)app.getDataComponent()).getNdoeController().processChangeText((Text)temp.getGoLoNode());                
        });

        ItemsTableController iTC = new ItemsTableController(app);
        itemsTable.widthProperty().addListener(e->{
            iTC.processChangeTableSize();
        });
    }
    
    public void initFoolproofDesign() {
        AppGUIModule gui = app.getGUIModule();
        AppFoolproofModule foolproofSettings = app.getFoolproofModule();
        foolproofSettings.registerModeSettings(GLGL_FOOLPROOF_SETTINGS, 
                new GoLoSelectionFoolproofDesign((GoLogoLoApp)app));
    }
    
    @Override
    public void processWorkspaceKeyEvent(KeyEvent ke) {
       // System.out.println("WORKSPACE REPONSE TO " + ke.getCharacter());
    }
    
    
}