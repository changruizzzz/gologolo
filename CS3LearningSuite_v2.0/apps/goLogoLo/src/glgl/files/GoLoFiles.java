package glgl.files;

import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import glgl.data.GoLoCircle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import glgl.data.GoLoData;
import glgl.data.GoLoComponentPrototype;
import glgl.data.GoLoImage;
import glgl.data.GoLoRectangle;
import glgl.data.GoLoText;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.RadialGradient;
import javax.imageio.ImageIO;

/**
 *
 * @author Changrui Zhou
 */
public class GoLoFiles implements AppFileComponent {
    // FOR JSON SAVING AND LOADING


    static final String JSON_ITEMS = "items";
    static final String JSON_WIDTH = "width";
    static final String JSON_HEIGHT = "height";
    static final String JSON_TYPE = "type";
    static final String JSON_NAME = "name";
    static final String JSON_NODE = "node";
    static final String JSON_FILL = "fill";

    
    // FOR EXPORTING TO HTML
    static final String TITLE_TAG = "title";
    static final String OWNER_TAG = "list_owner_td";
    static final String TABLE_DATA_TAG = "to_do_list_table_data";
    
    /**
     * This method is for saving user work.
     * 
     * @param data The data management component for this application.
     * 
     * @param filePath Path (including file name/extension) to where
     * to save the data to.
     * 
     * @throws IOException Thrown should there be an error writing 
     * out data to the file.
     */
    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
	// GET THE DATA
	GoLoData goLoData = (GoLoData)data;
//	
	// FIRST THE Width and Height of background
        String width = "" + goLoData.getBackground().getWidth();
        String height = "" + goLoData.getBackground().getHeight();
        
        
	// NOW BUILD THE JSON ARRAY FOR THE LIST
	JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        Iterator<GoLoComponentPrototype> itemsIt = goLoData.componentsIterator();
	while (itemsIt.hasNext()) {	
            GoLoComponentPrototype item = itemsIt.next();
	    JsonObject itemJson = Json.createObjectBuilder()
		    .add(JSON_TYPE, item.getType())
                    .add(JSON_NAME, item.getName())
                    .add(JSON_NODE, item.getJsonNode())
                    .build();
	    arrayBuilder.add(itemJson);
	}
	JsonArray itemsArray = arrayBuilder.build();
	
	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject goLoDataJSO = Json.createObjectBuilder()
                .add(JSON_WIDTH, width)
                .add(JSON_HEIGHT, height)
                .add(JSON_FILL, goLoData.getFill().toString())
		.add(JSON_ITEMS, itemsArray)
		.build();
	
	// AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(goLoDataJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(goLoDataJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    /**
     * This method loads data from a JSON formatted file into the data 
     * management component and then forces the updating of the workspace
     * such that the user may edit the data.
     * 
     * @param data Data management component where we'll load the file into.
     * 
     * @param filePath Path (including file name/extension) to where
     * to load the data from.
     * 
     * @throws IOException Thrown should there be an error
     * reading
     * in data from the file.
     */
    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
	// CLEAR THE OLD DATA OUT
	GoLoData goLoData = (GoLoData)data;
	goLoData.reset();
	
	// LOAD THE JSON FILE WITH ALL THE DATA
	JsonObject json = loadJSONFile(filePath);
	
	// LOAD LIST NAME AND OWNER
	double width = Double.parseDouble(json.getString(JSON_WIDTH));
        double height = Double.parseDouble(json.getString(JSON_HEIGHT));
        RadialGradient rg = RadialGradient.valueOf(json.getString(JSON_FILL));
        goLoData.resize(width, height);
        goLoData.setFill(rg);
	// AND NOW LOAD ALL THE ITEMS
	JsonArray jsonItemArray = json.getJsonArray(JSON_ITEMS);
	for (int i = 0; i < jsonItemArray.size(); i++) {
	    JsonObject jsonItem = jsonItemArray.getJsonObject(i);
	    GoLoComponentPrototype item = loadItem(jsonItem);
	    goLoData.addComponent(item);
	}
    }
    
    
    public GoLoComponentPrototype loadItem(JsonObject jsonItem) {
	// GET THE DATA
        String name = jsonItem.getString(JSON_NAME);
        String type = jsonItem.getString(JSON_TYPE);
        JsonObject node = jsonItem.getJsonObject(JSON_NODE);
        GoLoComponentPrototype temp;
        if(type.equals("Rectangle")) {
            temp = new GoLoRectangle();
            temp.loadFromJson(node, name);
        }
        else if(type.equals("Text")) {
            temp = new GoLoText("");
            temp.loadFromJson(node, name);
        }
        else if(type.equals("Circle")) {
            temp = new GoLoCircle();
            temp.loadFromJson(node, name);
        } else {
            temp = new GoLoImage();
            temp.loadFromJson(node, name);
        }
        return temp;
        
    }

    // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
    
    /**
     * This method would be used to export data to another format,
     * which we're not doing in this assignment.
     */
    @Override
    public void exportData(AppDataComponent data, String savedFileName) throws IOException {
//        String logoName = savedFileName.substring(0, savedFileName.indexOf("."));
        String logoName = savedFileName;
        String fileToExport = logoName + ".png";
        try {
            // GET THE ACTUAL DATA
            GoLoData goLoData = (GoLoData)data;
            goLoData.clearSelected();
            WritableImage snapShot = goLoData.getBackground().snapshot(new SnapshotParameters(), null);
            File file = new File(fileToExport);
            ImageIO.write(SwingFXUtils.fromFXImage(snapShot, null), "png", file);        
        }
        catch(Exception e) {
            throw new IOException("Error loading " + fileToExport);
        }
    }

    /**
     * This method is provided to satisfy the compiler, but it
     * is not used by this application.
     */
    
    public double getDataAsDouble(JsonObject json, String dataName) {
	JsonValue value = json.get(dataName);
	JsonNumber number = (JsonNumber)value;
	return number.bigDecimalValue().doubleValue();	
    }

    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
