package glgl;

/**
 * This class provides the properties that are needed to be loaded for
 * setting up To Do List Maker workspace controls including language-dependent
 * text.
 * 
 * @author Changrui Zhou
 * @version 1.0
 */
public enum GoLoPropertyType {
   
    /* THESE ARE THE NODES IN OUR APP */
         
    // THIS IS THE TDLM WORKSPACE PANE
    GLGL_PANE,
    GOLOGOLO_LABEL,
    
    // THIS IS THE DETAILS PANE

       
    // ITEMS EDITING
    GLGL_BODY_PANE,
    GLGL_ITEMS_PANE,
    GLGL_FUNCTION_PANE,
    GLGL_ELEMENTS_BOX,
    GLGL_ITEM_BUTTONS_PANE,
    GLGL_EDIT_ITEM_BUTTON,
    GLGL_MOVE_ITEM_UP_BUTTON,
    GLGL_MOVE_ITEM_DOWN_BUTTON,
    GLGL_ITEMS_TABLE_VIEW,
    GLGL_ORDER_COLUMN,
    GLGL_NAME_COLUMN,
    GLGL_TYPE_COLUMN,
    GLGL_ADD_TEXT_BUTTON,
    GLGL_ADD_IMAGE_BUTTON,
    GLGL_ADD_RECTANGLE_BUTTON,
    GLGL_ADD_CIRCLE_BUTTON,
    GLGL_ADD_TRIANGLE_BUTTON,
    GLGL_REMOVE_BUTTON,
    GLGL_BORDER_BOX,
    GLGL_THICKNESS_SLIDER,
    GLGL_BORDER_THICKNESS_LABEL,
    GLGL_RADIUS_SLIDER,
    GLGL_BORDER_RADIUS_LABEL,
    GLGL_BORDER_COLOR_PICKER,    
    GLGL_BORDER_COLOR_PICKER_LABEL,
    
    // FONT
    GLGL_FONT_SETTING_BOX,
    GLGL_FONT_SETTING_COMBO,
    GLGL_FONT_COMBO,
    GLGL_FONT_OPTIONS,
    GLGL_DEFAULT_FONT,
    GLGL_FONT_SIZE_COMBO,
    GLGL_FONT_SIZE_OPTIONS,
    GLGL_DEFAULT_FONT_SIZE,
    GLGL_FONT_STYLE_BOX,
    GLGL_BOLD_BUTTON,
    GLGL_ITALIC_BUTTON,
    GLGL_INCREASE_FONT_SIZE_BUTTON,
    GLGL_DECREASE_FONT_SIZE_BUTTON,
    GLGL_UNDERLINE_BUTTON,
    
    //GRADIENT
    GLGL_COLOR_GRADIENT_BOX,
    GLGL_COLOR_GRADIENT_LABEL,
    GLGL_FOCUS_ANGLE_LABEL,
    GLGL_FOCUS_ANGLE_SLIDER,
    GLGL_FOCUS_DISTANCE_LABEL,
    GLGL_FOCUS_DISTANCE_SLIDER,
    GLGL_CENTER_X_LABEL,
    GLGL_CENTER_X_SLIDER,
    GLGL_CENTER_Y_LABEL,
    GLGL_CENTER_Y_SLIDER,
    GLGL_GRADIENT_RADIUS_LABEL,
    GLGL_CGRADIENT_RADIUS_SLIDER,
    GLGL_CYCLE_METHOD_LABEL,
    GLGL_CYCLE_METHOD_COMBO,
    GLGL_CYCLE_METHOD_OPTIONS,
    GLGL_DEFAULT_CYCLE_METHOD,
    GLGL_STOP_0_COLOR_PICKER_LABEL,
    GLGL_STOP_0_COLOR_PICKER,
    GLGL_STOP_1_COLOR_PICKER_LABEL,
    GLGL_STOP_1_COLOR_PICKER,

    
    


    // FOOLPROOF SETTINGS
    TDLM_FOOLPROOF_SETTINGS,
    
    // DIALOG CONTROLS
    TDLM_ITEM_DIALOG_HEADER,
    TDLM_ITEM_DIALOG_ADD_HEADER_TEXT,
    TDLM_ITEM_DIALOG_EDIT_HEADER_TEXT,
    TDLM_ITEM_DIALOG_CATEGORY_PROMPT,
    TDLM_ITEM_DIALOG_CATEGORY_TEXT_FIELD,
    TDLM_ITEM_DIALOG_DESCRIPTION_PROMPT,
    TDLM_ITEM_DIALOG_DESCRIPTION_TEXT_FIELD,
    TDLM_ITEM_DIALOG_START_DATE_PROMPT,
    TDLM_ITEM_DIALOG_START_DATE_PICKER,
    TDLM_ITEM_DIALOG_END_DATE_PROMPT,
    TDLM_ITEM_DIALOG_END_DATE_PICKER,
    TDLM_ITEM_DIALOG_COMPLETED_PROMPT,
    TDLM_ITEM_DIALOG_COMPLETED_CHECK_BOX,
    TDLM_ITEM_DIALOG_CONFIRM_PANE,
    TDLM_ITEM_DIALOG_OK_BUTTON,
    TDLM_ITEM_DIALOG_CANCEL_BUTTON,
    TDLM_ITEM_DIALOG_ASSIGNED_TO_PROMPT,
    TDLM_ITEM_DIALOG_ASSIGNED_TO_TEXT_FIELD,
    
    // THIS IS THE EXPORT TEMPLATE FILE NAME
    TDLM_EXPORT_TEMPLATE_FILE_NAME
}