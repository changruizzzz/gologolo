package glgl.clipboard;

import djf.components.AppClipboardComponent;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import glgl.GoLogoLoApp;
import glgl.data.GoLoData;
import glgl.data.GoLoComponentPrototype;
import glgl.transactions.CutComponents_Transaction;
import glgl.transactions.PasteComponents_Transaction;

/**
 *
 * @author ChangruiZhou
 */
public class GoLoClipboard implements AppClipboardComponent {
    GoLogoLoApp app;
    ArrayList<GoLoComponentPrototype> clipboardCutComponents;
    ArrayList<GoLoComponentPrototype> clipboardCopiedComponents;
    
    public GoLoClipboard(GoLogoLoApp initApp) {
        app = initApp;
        clipboardCutComponents = null;
        clipboardCopiedComponents = null;
    }
    
    @Override
    public void cut() {
        GoLoData data = (GoLoData)app.getDataComponent();
        if (data.isItemSelected() || data.areItemsSelected()) {
            clipboardCutComponents = new ArrayList(data.getSelectedItems());
            clipboardCopiedComponents = null;
            CutComponents_Transaction transaction = new CutComponents_Transaction((GoLogoLoApp)app, clipboardCutComponents);
            app.processTransaction(transaction);
        }
    }

    @Override
    public void copy() {
        GoLoData data = (GoLoData)app.getDataComponent();
        if (data.isItemSelected() || data.areItemsSelected()) {
            ArrayList<GoLoComponentPrototype> tempComponents = new ArrayList(data.getSelectedItems());
            copyToCopiedClipboard(tempComponents);
        }
    }
    
    private void copyToCutClipboard(ArrayList<GoLoComponentPrototype> itemsToCopy) {
        clipboardCutComponents = copyComponents(itemsToCopy);
        clipboardCopiedComponents = null;        
        app.getFoolproofModule().updateAll();        
    }
    
    private void copyToCopiedClipboard(ArrayList<GoLoComponentPrototype> itemsToCopy) {
        clipboardCutComponents = null;
        clipboardCopiedComponents = copyComponents(itemsToCopy);
        app.getFoolproofModule().updateAll();        
    }
    
    private ArrayList<GoLoComponentPrototype> copyComponents(ArrayList<GoLoComponentPrototype> itemsToCopy) {
        ArrayList<GoLoComponentPrototype> tempCopy = new ArrayList();         
        for (GoLoComponentPrototype itemToCopy : itemsToCopy) {
            GoLoComponentPrototype copiedComponent = (GoLoComponentPrototype)itemToCopy.clone();
            tempCopy.add(copiedComponent);
        }        
        return tempCopy;
    }

    @Override
    public void paste() {
        GoLoData data = (GoLoData)app.getDataComponent();
        if ((clipboardCutComponents != null)
            && (!clipboardCutComponents.isEmpty())) {
                PasteComponents_Transaction transaction = new PasteComponents_Transaction((GoLogoLoApp)app, clipboardCutComponents);
                app.processTransaction(transaction);
                
                // NOW WE HAVE TO RE-COPY THE CUT ITEMS TO MAKE
                // SURE IF WE PASTE THEM AGAIN THEY ARE BRAND NEW OBJECTS
                copyToCutClipboard(clipboardCutComponents);
        } else if ((clipboardCopiedComponents != null) 
                && (!clipboardCopiedComponents.isEmpty())) {
                PasteComponents_Transaction transaction = new PasteComponents_Transaction((GoLogoLoApp)app, clipboardCopiedComponents);
                app.processTransaction(transaction);
            
                // NOW WE HAVE TO RE-COPY THE COPIED ITEMS TO MAKE
                // SURE IF WE PASTE THEM AGAIN THEY ARE BRAND NEW OBJECTS
                copyToCopiedClipboard(clipboardCopiedComponents);
            }
    }    


    @Override
    public boolean hasSomethingToCut() {
        return ((GoLoData)app.getDataComponent()).isItemSelected()
                || ((GoLoData)app.getDataComponent()).areItemsSelected();
    }

    @Override
    public boolean hasSomethingToCopy() {
        return ((GoLoData)app.getDataComponent()).isItemSelected()
                || ((GoLoData)app.getDataComponent()).areItemsSelected();
    }

    @Override
    public boolean hasSomethingToPaste() {
        if ((clipboardCutComponents != null) && (!clipboardCutComponents.isEmpty()))
            return true;
        else if ((clipboardCopiedComponents != null) && (!clipboardCopiedComponents.isEmpty()))
            return true;
        else
            return false;
    }
}