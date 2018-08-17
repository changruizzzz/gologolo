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
import javax.swing.text.html.HTML;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import glgl.data.GoLoData;
import glgl.data.GoLoComponentPrototype;
import glgl.data.GoLoRectangle;
import glgl.data.GoLoText;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;

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
        goLoData.resize(width, height);	
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
            temp = new GoLoRectangle();
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
//        String toDoListName = savedFileName.substring(0, savedFileName.indexOf("."));
//        String fileToExport = toDoListName + ".html";
//        try {
//            // GET THE ACTUAL DATA
//            ToDoData toDoData = (ToDoData)data;
//            PropertiesManager props = PropertiesManager.getPropertiesManager();
//            String exportDirPath = props.getProperty(APP_PATH_EXPORT) + toDoListName + "/";
//            File exportDir = new File(exportDirPath);
//            if (!exportDir.exists()) {
//                exportDir.mkdir();
//            }
//
//            // NOW LOAD THE TEMPLATE DOCUMENT
//            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//            String htmlTemplatePath = props.getPropertiesDataPath() + props.getProperty(TDLM_EXPORT_TEMPLATE_FILE_NAME);
//            File file = new File(htmlTemplatePath);
//            System.out.println(file.getPath() + " exists? " + file.exists());
//            URL templateURL = file.toURI().toURL();
//            Document exportDoc = docBuilder.parse(templateURL.getPath());
//
//            // SET THE WEB PAGE TITLE
//            Node titleNode = exportDoc.getElementsByTagName(TITLE_TAG).item(0);
//            titleNode.setTextContent("No Name List");
//
//            // SET THE OWNER
//            Node ownerNode = getNodeWithId(exportDoc, HTML.Tag.TD.toString(), OWNER_TAG);
//            ownerNode.setTextContent(toDoData.getOwner());
//            
//            // ADD ALL THE ITEMS
//            Node tDataNode = getNodeWithId(exportDoc, "tdata", TABLE_DATA_TAG);
//            Iterator<ToDoItemPrototype> itemsIt = toDoData.itemsIterator();
//            while (itemsIt.hasNext()) {
//                ToDoItemPrototype item = itemsIt.next();
//                Element trElement = exportDoc.createElement(HTML.Tag.TR.toString());
//                tDataNode.appendChild(trElement);
//                addCellToRow(exportDoc, trElement, item.getCategory());
//                addCellToRow(exportDoc, trElement, item.getDescription());
//                addCellToRow(exportDoc, trElement, item.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
//                addCellToRow(exportDoc, trElement, "" + item.isCompleted());
//            }
//            
//            // CORRECT THE APP EXPORT PAGE
//            props.addProperty(APP_EXPORT_PAGE, exportDirPath + fileToExport);
//
//            // EXPORT THE WEB PAGE
//            saveDocument(exportDoc, props.getProperty(APP_EXPORT_PAGE));
//        }
//        catch(SAXException | ParserConfigurationException
//                | TransformerException exc) {
//            throw new IOException("Error loading " + fileToExport);
//        }
    }
    private void addCellToRow(Document doc, Node rowNode, String text) {
        Element tdElement = doc.createElement(HTML.Tag.TD.toString());
        tdElement.setTextContent(text);
        rowNode.appendChild(tdElement);
    }
    private Node getNodeWithId(Document doc, String tagType, String searchID) {
        NodeList testNodes = doc.getElementsByTagName(tagType);
        for (int i = 0; i < testNodes.getLength(); i++) {
            Node testNode = testNodes.item(i);
            Node testAttr = testNode.getAttributes().getNamedItem(HTML.Attribute.ID.toString());
            if ((testAttr != null) && testAttr.getNodeValue().equals(searchID)) {
                return testNode;
            }
        }
        return null;
    }
    private void saveDocument(Document doc, String outputFilePath)
            throws TransformerException, TransformerConfigurationException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        Result result = new StreamResult(new File(outputFilePath));
        Source source = new DOMSource(doc);
        transformer.transform(source, result);
    }

    /**
     * This method is provided to satisfy the compiler, but it
     * is not used by this application.
     */
    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        
    }
    
    public double getDataAsDouble(JsonObject json, String dataName) {
	JsonValue value = json.get(dataName);
	JsonNumber number = (JsonNumber)value;
	return number.bigDecimalValue().doubleValue();	
    }


}
