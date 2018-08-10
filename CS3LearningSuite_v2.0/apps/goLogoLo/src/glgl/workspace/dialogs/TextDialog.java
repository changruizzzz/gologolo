package glgl.workspace.dialogs;

import static djf.modules.AppGUIModule.ENABLED;
import static djf.modules.AppGUIModule.FOCUS_TRAVERSABLE;
import static djf.modules.AppGUIModule.HAS_KEY_HANDLER;
import static djf.modules.AppGUIModule.NO_KEY_HANDLER;
import djf.ui.AppNodesBuilder;
import static glgl.GoLoPropertyType.GLGL_ITEM_DIALOG_CANCEL_BUTTON;
import static glgl.GoLoPropertyType.GLGL_ITEM_DIALOG_OK_BUTTON;
import static glgl.GoLoPropertyType.GLGL_TEXT_DIALOG_ADD_HEADER_TEXT;
import static glgl.GoLoPropertyType.GLGL_TEXT_DIALOG_EDIT_HEADER_TEXT;
import static glgl.GoLoPropertyType.GLGL_TEXT_DIALOG_HEADER;
import static glgl.GoLoPropertyType.GLGL_TEXT_DIALOG_TEXT_FIELD;
import static glgl.GoLoPropertyType.GLGL_TEXT_DIALOG_TEXT_LABEL;
import static glgl.GoLoPropertyType.GLGL_TEXT_DIALOG_TEXT_LABEL_TEXT;

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
import glgl.data.GoLoText;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_DIALOG_CANCEL_BUTTON;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_DIALOG_GRID;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_DIALOG_HEADER;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_DIALOG_OK_BUTTON;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_DIALOG_PANE;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_DIALOG_PROMPT;
import static glgl.workspace.style.GLGLStyle.CLASS_GLGL_DIALOG_TEXT_FIELD;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;


/**
 *
 * @author ChangruiZhou
 */
public class TextDialog extends Stage {
    GoLogoLoApp app;
    
    BorderPane dialogPane;
    Label headerLabel;    
    HBox okCancelPane = new HBox();
    Button okButton;
    Button cancelButton;
    GridPane textGrid;
    Label textLabel = new Label();
    TextField textTextField = new TextField();
    AppNodesBuilder nodesBuilder;
    String text;
    GoLoText newText;
    GoLoText toEditText;
    
    boolean finish = false;
    boolean editing = false;
    
    EventHandler cancelHandler;
    EventHandler addItemOkHandler;
    EventHandler editItemOkHandler;
    
    public TextDialog(GoLogoLoApp initApp) {
        // KEEP THIS FOR WHEN THE WORK IS ENTERED
        app = initApp;

        // EVERYTHING GOES IN HERE
        dialogPane = new BorderPane();
        textGrid = new GridPane();
        dialogPane.getStyleClass().add(CLASS_GLGL_DIALOG_PANE);
        textGrid.getStyleClass().add(CLASS_GLGL_DIALOG_GRID);
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
        headerLabel = nodesBuilder.buildLabel(GLGL_TEXT_DIALOG_HEADER, null, null, CLASS_GLGL_DIALOG_HEADER, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        headerLabel.setAlignment(Pos.CENTER);

        // THE NODES ABOVE GO DIRECTLY INSIDE THE GRID
        dialogPane.setTop(headerLabel);
        okButton = nodesBuilder.buildTextButton(GLGL_ITEM_DIALOG_OK_BUTTON, okCancelPane, null, CLASS_GLGL_DIALOG_OK_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        cancelButton = nodesBuilder.buildTextButton(GLGL_ITEM_DIALOG_CANCEL_BUTTON, okCancelPane, null, CLASS_GLGL_DIALOG_CANCEL_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        okCancelPane.setAlignment(Pos.CENTER);
        dialogPane.setBottom(okCancelPane);
        textLabel = nodesBuilder.buildLabel(GLGL_TEXT_DIALOG_TEXT_LABEL, null, null, CLASS_GLGL_DIALOG_PROMPT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        textTextField = nodesBuilder.buildTextField(GLGL_TEXT_DIALOG_TEXT_FIELD, null, null, CLASS_GLGL_DIALOG_TEXT_FIELD, NO_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        textGrid.add(textLabel, 0, 0, 1, 1);
        textGrid.add(textTextField, 1, 0, 1, 1);
        dialogPane.setCenter(textGrid);
        textGrid.setAlignment(Pos.CENTER);


//         AND SETUP THE EVENT HANDLERS
        textTextField.setOnAction(e->{
            processWork();
        });

        okButton.setOnAction(e->{
            processWork();
        });
        
        cancelButton.setOnAction(e->{
            reset();
            this.hide();
        });   
    }
    
    private void processWork() {
        text = textTextField.getText();
        if(text.length() > 0 ) {
            newText = new GoLoText(text);
            finish = true;
        } else {
            showErrorDialog();
        }
        if(finish) 
            this.hide();
    }

    public void showAddTextDialog() {  

        // USE THE TEXT IN THE HEADER FOR ADD
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String headerText = props.getProperty(GLGL_TEXT_DIALOG_ADD_HEADER_TEXT);
        setTitle(headerText);
        headerLabel.setText(headerText);
        textTextField.setText("");
        
        editing = false;
        toEditText = null;
         // AND OPEN THE DIALOG
        showAndWait();

    }
    
    public void showEditTextDialog(String initString) {  
        // USE THE TEXT IN THE HEADER FOR Edit
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String headerText = props.getProperty(GLGL_TEXT_DIALOG_EDIT_HEADER_TEXT);
        setTitle(headerText);
        textLabel.setText(props.getProperty(GLGL_TEXT_DIALOG_TEXT_LABEL_TEXT));
        headerLabel.setText(headerText);
        textTextField.setText(initString);
         // AND OPEN THE DIALOG
        showAndWait();

    }


    // CLEAN TEMP DATA
    public void reset(){
        newText = null;
        toEditText = null;
        text = null;
    }
    

    private void showErrorDialog() {
        djf.ui.dialogs.AppDialogsFacade.showMessageDialog(app.getGUIModule().getWindow(), "NEW_ERROR_TITLE", "FIELD_EMPTY_ERROR");
    }
    
    public String getString() {
        return text;
    }
    public GoLoText getGoLoText() {
        return newText;
    }
}  
