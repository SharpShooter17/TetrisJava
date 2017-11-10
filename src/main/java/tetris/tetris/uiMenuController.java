package tetris.tetris;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class uiMenuController {
    @FXML
    private Button buttonStartNewGame;

    @FXML
    private TextField labelNick;

    @FXML
    private Button buttonEasy;

    @FXML
    private Button buttonMedium;

    @FXML
    private Button buttonHard;
    
    @FXML
    private Button buttonScores;
    
    @FXML
    private RadioButton radio16x32;
    
    @FXML
    private RadioButton radio8x16;
    
    @FXML
    void buttonExitClick(ActionEvent event) {
    	Platform.exit();
    }
    
    @FXML
    void buttonScoresClicked(ActionEvent event) {
    	SceneController.getSceneController().setScoresScene();
    }
    
    @FXML
    void buttonEasyClicked(ActionEvent event) {
		changeButtonToDefault();
		SceneController.getSceneController().getController().setDifficultLevel(Model.LEVEL.EASY);
		buttonEasy.setStyle("-fx-text-fill: white;");
    }

    @FXML
    void buttonHardClicked(ActionEvent event) {
		changeButtonToDefault();
		SceneController.getSceneController().getController().setDifficultLevel(Model.LEVEL.HARD);
		buttonHard.setStyle("-fx-text-fill: white;");
    }

    @FXML
    void buttonMediumClicked(ActionEvent event) {
		changeButtonToDefault();
		SceneController.getSceneController().getController().setDifficultLevel(Model.LEVEL.MEDIUM);
		buttonMedium.setStyle("-fx-text-fill: white;");
    }
    
    @FXML
    void buttonAboutClicked(ActionEvent event) {
    	SceneController.getSceneController().setAbout();
    }
    
    private void changeButtonToDefault(){
		switch ( SceneController.getSceneController().getController().getDifficultLevel() ){
			case EASY:
				buttonEasy.setStyle("-fx-text-fill: black;");
				break;
			case MEDIUM:
				buttonMedium.setStyle("-fx-text-fill: black;");
				break;
			case HARD:
				buttonHard.setStyle("-fx-text-fill: black;");
				break;
		}
    }
    
    @FXML
    void buttonStartNewGameClick(ActionEvent event) throws IOException {
    	if (radio16x32.isSelected()){
    		SceneController.getSceneController().getController().setBoardX(16);
    		SceneController.getSceneController().getController().setBoardY(32);
    		SceneController.getSceneController().getGameControl().setTileSize(16);
    	} else {
    		SceneController.getSceneController().getController().setBoardX(8);
    		SceneController.getSceneController().getController().setBoardY(16);
    		SceneController.getSceneController().getGameControl().setTileSize(32);
    	}
    	SceneController.getSceneController().getController().resetGame();
    	SceneController.getSceneController().setGameScene();
    }
    
    public String getNick(){
    	return labelNick.getText();
    }
}
