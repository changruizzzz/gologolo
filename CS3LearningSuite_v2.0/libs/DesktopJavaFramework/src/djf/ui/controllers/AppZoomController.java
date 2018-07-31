package djf.ui.controllers;

import djf.AppTemplate;

public class AppZoomController {
    private AppTemplate app;
    
    public AppZoomController(AppTemplate initApp) {
        app = initApp;
    }  
    
    public void processZoomRequest() {
    }
    
    public void processResizeRequest() {
        app.getWorkspaceComponent();
    }  
   
}
