package glgl.workspace.dialogs;

import static djf.modules.AppGUIModule.ENABLED;
import static djf.modules.AppGUIModule.FOCUS_TRAVERSABLE;
import static djf.modules.AppGUIModule.HAS_KEY_HANDLER;
import static djf.modules.AppGUIModule.NO_KEY_HANDLER;
import djf.ui.AppNodesBuilder;
import static glgl.GoLoPropertyType.GLGL_ITEM_DIALOG_CANCEL_BUTTON;
import static glgl.GoLoPropertyType.GLGL_ITEM_DIALOG_HEADER_LABEL;
import static glgl.GoLoPropertyType.GLGL_ITEM_DIALOG_HEIGHT_LABEL;
import static glgl.GoLoPropertyType.GLGL_ITEM_DIALOG_HEIGHT_TEXT_FIELD;
import static glgl.GoLoPropertyType.GLGL_ITEM_DIALOG_OK_BUTTON;
import static glgl.GoLoPropertyType.GLGL_ITEM_DIALOG_RENAME_HEADER_TEXT;
import static glgl.GoLoPropertyType.GLGL_ITEM_DIALOG_RENAME_LABEL;
import static glgl.GoLoPropertyType.GLGL_ITEM_DIALOG_RENAME_TEXT_FIELD;
import static glgl.GoLoPropertyType.GLGL_ITEM_DIALOG_RESIZE_HEADER_TEXT;
import static glgl.GoLoPropertyType.GLGL_ITEM_DIALOG_TITLE_LABEL;
import static glgl.GoLoPropertyType.GLGL_ITEM_DIALOG_WIDTH_LABEL;
import static glgl.GoLoPropertyType.GLGL_ITEM_DIALOG_WIDTH_TEXT_FIELD;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import glgl.GoLogoLoApp;
import glgl.data.GoLoComponentPrototype;
import glgl.data.GoLoData;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_DIALOG_CANCEL_BUTTON;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_DIALOG_GRID;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_DIALOG_HEADER;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_DIALOG_OK_BUTTON;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_DIALOG_PANE;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_DIALOG_PROMPT;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_DIALOG_TEXT_FIELD;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_DIALOG_TITLE_PANE;
import javafx.event.EventType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


/**
 *
 * @author ChangruiZhou
 */
public class EditDialog extends Stage {
    GoLogoLoApp app;
    BorderPane dialogPane;
    GridPane resizeGridPane;
    GridPane renameGridPane;
    
    Label titleLabel;
    Label headerLabel;    
    VBox headerPane = new VBox();
    HBox okCancelPane = new HBox();
    Button okButton;
    Button cancelButton;
    Label heightLabel = new Label();
    TextField heightTextField = new TextField();        
    Label widthLabel = new Label();
    TextField widthTextField = new TextField();
    Label nameLabel = new Label();
    TextField nameTextField = new TextField();
    AppNodesBuilder nodesBuilder;
    
    GoLoComponentPrototype itemToEdit;
    GoLoComponentPrototype editedItem;
    double backgroundWidth = -1;
    double backgroundHeight = -1;
    String name;
    
    boolean finish = false;

    EventHandler cancelHandler;
    EventHandler addItemOkHandler;
    EventHandler editItemOkHandler;
    
    public EditDialog(GoLogoLoApp initApp) {
        // KEEP THIS FOR WHEN THE WORK IS ENTERED
        app = initApp;

        // EVERYTHING GOES IN HERE
        dialogPane = new BorderPane();
        resizeGridPane = new GridPane();
        renameGridPane = new GridPane();

        dialogPane.getStyleClass().add(CLASS_GLGL_DIALOG_PANE);
        resizeGridPane.getStyleClass().add(CLASS_GLGL_DIALOG_GRID);
        renameGridPane.getStyleClass().add(CLASS_GLGL_DIALOG_GRID);

        initDialog();
        
        // NOW PUT THE GRID IN THE SCENE AND THE SCENE IN THE DIALOG
        Scene scene = new Scene(dialogPane);
        this.setScene(scene);
        
        // SETUP THE STYLESHEET
        app.getGUIModule().initStylesheet(this);
        
        // MAKE IT MODAL
        this.initOwner(app.getGUIModule().getWindow());
        this.initModality(Modality.APPLICATION_MODAL);
    }

    private void initDialog() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        nodesBuilder = new AppNodesBuilder(app.getGUIModule(), app.getLanguageModule());
        titleLabel = nodesBuilder.buildLabel(GLGL_ITEM_DIALOG_TITLE_LABEL, headerPane, null, CLASS_GLGL_DIALOG_TITLE_PANE, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        headerLabel = nodesBuilder.buildLabel(GLGL_ITEM_DIALOG_HEADER_LABEL, headerPane, null, CLASS_GLGL_DIALOG_HEADER, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        headerLabel.setAlignment(Pos.CENTER);
        
        // THE NODES ABOVE GO DIRECTLY INSIDE THE GRID
        dialogPane.setTop(headerPane);
        okButton = nodesBuilder.buildTextButton(GLGL_ITEM_DIALOG_OK_BUTTON, okCancelPane, null, CLASS_GLGL_DIALOG_OK_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        cancelButton = nodesBuilder.buildTextButton(GLGL_ITEM_DIALOG_CANCEL_BUTTON, okCancelPane, null, CLASS_GLGL_DIALOG_CANCEL_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        okCancelPane.setAlignment(Pos.CENTER);
        dialogPane.setBottom(okCancelPane);
        heightLabel = nodesBuilder.buildLabel(GLGL_ITEM_DIALOG_HEIGHT_LABEL, null, null, CLASS_GLGL_DIALOG_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        heightTextField = nodesBuilder.buildTextField(GLGL_ITEM_DIALOG_HEIGHT_TEXT_FIELD, null, null, CLASS_GLGL_DIALOG_TEXT_FIELD, NO_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        widthLabel = nodesBuilder.buildLabel(GLGL_ITEM_DIALOG_WIDTH_LABEL, null, null, CLASS_GLGL_DIALOG_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        widthTextField = nodesBuilder.buildTextField(GLGL_ITEM_DIALOG_WIDTH_TEXT_FIELD, null, null, CLASS_GLGL_DIALOG_TEXT_FIELD, NO_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        resizeGridPane.add(heightLabel, 0, 0, 1, 1);
        resizeGridPane.add(heightTextField, 1, 0, 1, 1);
        resizeGridPane.add(widthLabel, 0, 1, 1, 1);
        resizeGridPane.add(widthTextField, 1, 1, 1, 1);
        nameLabel = nodesBuilder.buildLabel(GLGL_ITEM_DIALOG_RENAME_LABEL, null, null, CLASS_GLGL_DIALOG_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        nameTextField = nodesBuilder.buildTextField(GLGL_ITEM_DIALOG_RENAME_TEXT_FIELD, null, null, CLASS_GLGL_DIALOG_TEXT_FIELD, NO_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);  
        renameGridPane.add(nameLabel, 0, 0, 1, 1);
        renameGridPane.add(nameTextField, 1, 0, 1, 1);
        resizeGridPane.setAlignment(Pos.CENTER);
        renameGridPane.setAlignment(Pos.CENTER);


//         AND SETUP THE EVENT HANDLERS
        widthTextField.setOnAction(e->{
            processResizeWork();
        });
        heightTextField.setOnAction(e->{
            processResizeWork();
        });
        
        nameTextField.setOnAction(e->{
            processRenameWork();
        });
//        nameTextField.setOnAction(e->{
//            processRenameWork();
//        });
        
        cancelButton.setOnAction(e->{
            reset();
            this.hide();
        });   
    }
    
//    private void makeNewItem() {
//        String category = categoryTextField.getText();
//        String description = descriptionTextField.getText();
//        LocalDate startDate = startDatePicker.getValue();
//        LocalDate endDate = endDatePicker.getValue();
//        String assignedTo = assignedToTextField.getText();
//        boolean completed = completedCheckBox.selectedProperty().getValue();
//        newItem = new ToDoItemPrototype(category, description, startDate, endDate, assignedTo, completed);
//        this.hide();
//    }
    
    private void processResizeWork() {
        
        try {
            double newWidth = Double.parseDouble(widthTextField.getText());
            double newHeight = Double.parseDouble(heightTextField.getText());
            if(newWidth >= 0 && newHeight >= 0) {
                backgroundWidth = newWidth;
                backgroundHeight = newHeight;
                finish = true;
            } else {
                showErrorDialog(1);
            }
        } catch(NumberFormatException nfe) {
                showErrorDialog(1);
            
        } 
        if(finish) {
            this.hide();
//            okButton.removeEventHandler(EventType.ROOT, cancelHandler);
        }


    }

    public void showResizeDialog() {  
        dialogPane.setCenter(resizeGridPane);
        // USE THE TEXT IN THE HEADER FOR ADD
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String headerText = props.getProperty(GLGL_ITEM_DIALOG_RESIZE_HEADER_TEXT);
        setTitle(headerText);
        headerLabel.setText(headerText);
        widthTextField.setText("" + ((GoLoData)app.getDataComponent()).getBackground().getMaxWidth());
        heightTextField.setText("" + ((GoLoData)app.getDataComponent()).getBackground().getMaxHeight());
        okButton.setOnAction(e->{
            processResizeWork();
        });
         // AND OPEN THE DIALOG
        showAndWait();

    }
    
    public void showRenameDialog() {  
        dialogPane.setCenter(renameGridPane);
        // USE THE TEXT IN THE HEADER FOR ADD
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String headerText = props.getProperty(GLGL_ITEM_DIALOG_RENAME_HEADER_TEXT);
        setTitle(headerText);
        headerLabel.setText(headerText);
        nameTextField.setText(((GoLoData)app.getDataComponent()).getSelectedItem().getName());
        okButton.setOnAction(e->{
            processRenameWork();
        });
         // AND OPEN THE DIALOG
        showAndWait();

    }

    // CLEAN TEMP DATA
    public void reset(){
        backgroundWidth = -1;
        backgroundHeight = -1;
        finish = false;
        name = null;
    }
    

    private void showErrorDialog(int errorType) {
        if(errorType == 0)
            djf.ui.dialogs.AppDialogsFacade.showMessageDialog(app.getGUIModule().getWindow(), "NEW_ERROR_TITLE", "FIELD_EMPTY_ERROR");
        else
            djf.ui.dialogs.AppDialogsFacade.showMessageDialog(app.getGUIModule().getWindow(), "NEW_ERROR_TITLE", "FIELD_NEGETIVE_ERROR");

    }
    
    public double getBackgroundWidth() {
        return backgroundWidth;
    }
    public double getBackgroundHeight() {
        return backgroundHeight;
    }

    private void processRenameWork() {
        String tempName = nameTextField.getText();
        if(tempName.length() > 0) {
            name = tempName;
            finish = true;
        } else {
            showErrorDialog(0);
        }
        if(finish) {
            this.hide();
        }
    }
    
    public String getNewName() {
        return name;
    }
}  
