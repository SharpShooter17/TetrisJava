package tetris.tetris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class uiGameOverController {

    @FXML
    private TextField textNick;

    @FXML
    private Label labelScores;

    @FXML
    private Label labelLevel;

    @FXML
    private Label labelDifficulty;

    @FXML
    private Button buttonOk;

    @FXML
    void buttonOkClicked(ActionEvent event) {
    	SceneController.getSceneController().getScoresControl().addNewScore(new ModelScores(getNick(), 
    		SceneController.getSceneController().getController().getLevel(), 
    		SceneController.getSceneController().getController().getScores(), 
    		SceneController.getSceneController().getController().getDifficultLevel().toString()));
    	SceneController.getSceneController().setScoresScene();
    }
    
    public void setNick(String nick){
    	if (nick.equals("")){
    		textNick.setText("Anonymous");
    	} else { 
    		textNick.setText(nick);
    	}
    }
    
    public void setScores(Integer val){
    	labelScores.setText(val.toString());
    }
    
    public void setDifficulty(String val){
    	labelDifficulty.setText(val);
    }
    
    public void setLevel(Integer value){
    	labelLevel.setText(value.toString());
    }
    
    public String getNick(){
    	return textNick.getText();
    }
}
