package tetris.tetris;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
import org.xml.sax.SAXException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class uiScoresController {
	
	ObservableList<ModelScores> scoresList;
	
	public void addNewScore(ModelScores score){
		scoresList.add(score);
	}
	
   @FXML
   void initialize() {
	   try {
		   loadScores();
	   } catch (Exception e){
		   System.err.println(e.getMessage());
	   }
	   
	   colNick.setCellValueFactory(new PropertyValueFactory<>("nick"));
	   colLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
	   colScores.setCellValueFactory(new PropertyValueFactory<>("scores"));
	   colDifficulty.setCellValueFactory(new PropertyValueFactory<>("difficultLevel"));
	   
	   tableScores.setItems( scoresList );
   }

   @FXML
   private TableView<ModelScores> tableScores;
   
   @FXML
   private TableColumn<ModelScores, String> colNick;

   @FXML
   private TableColumn<ModelScores, Integer> colScores;

   @FXML
   private TableColumn<ModelScores, Integer> colLevel;
   
   @FXML
   private TableColumn<ModelScores, String> colDifficulty;
	
	@FXML
	void buttonBackMenuClicked(ActionEvent event) {
		SceneController.getSceneController().setMenuScene();
	}
    
    private void loadScores() throws ParserConfigurationException, SAXException, IOException, Exception {
    	scoresList = FXCollections.observableArrayList();
		
    	File file = new File("bestScores.xml");
    	if (!file.exists()){
    		return;
    	}
    	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse(file);
		 NodeList nodeList = document.getDocumentElement().getChildNodes();
		 
		 for (int i = 0; i < nodeList.getLength(); i++){
			 Node node = nodeList.item(i);
			 
			 if ( node instanceof Element ){
				 ModelScores score = new ModelScores();
				 NodeList childNodes = node.getChildNodes();
				 
				 for(int j = 0; j < childNodes.getLength(); j++){
					 Node cNode = childNodes.item(j);
					 if (cNode instanceof Element){
						 String content = cNode.getLastChild().getTextContent().trim();
						 switch(cNode.getNodeName()){
						 case "nick":
							 score.setNick(content);
							 break;
						 case "level": 
							 score.setLevel(Integer.parseInt(content));
							 break;
						 case "scores": 
							 score.setScores(Integer.parseInt(content));
							 break;
						 case "difficultLevel":
							 score.setDifficultLevel(content);
							 break;
						 default:
							 throw new Exception("Bad data - " + cNode.getNodeName());
						 }
					 }
				 }
				 scoresList.add(score);
			 }
		 }
	}
    
    public void saveToXML(){
		Document doc;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
	        doc = db.newDocument();
	        Element rootEle = doc.createElement("DB");
	        doc.appendChild(rootEle);
	        
	        for (ModelScores score : scoresList){
	        	
	        	Element eScore = doc.createElement("score");
	        	
	        	Element element = doc.createElement("nick");
	        	element.appendChild(doc.createTextNode(score.getNick()));
	        	eScore.appendChild(element);
	        	
	        	element = doc.createElement("level");
	        	element.appendChild(doc.createTextNode(  score.getLevel().toString() ));
	        	eScore.appendChild(element);

	        	element = doc.createElement("scores");
	        	element.appendChild(doc.createTextNode(score.getScores().toString()));
	        	eScore.appendChild(element);
	        	
	        	element = doc.createElement("difficultLevel");
	        	element.appendChild(doc.createTextNode(score.getDifficultLevel().toString()));
	        	eScore.appendChild(element);
	        	
	        	rootEle.appendChild(eScore);
	        }
	        	
        	TransformerFactory transformerFactory = TransformerFactory.newInstance();
    		Transformer transformer = transformerFactory.newTransformer();
    		DOMSource source = new DOMSource(doc);
    		StreamResult result = new StreamResult(new File("bestScores.xml"));
	    		
    		transformer.transform(source, result);
		}catch (ParserConfigurationException e){
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
