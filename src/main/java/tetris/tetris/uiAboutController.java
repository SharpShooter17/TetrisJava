package tetris.tetris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class uiAboutController {

    @FXML
    void buttonBackToMenuClicked(ActionEvent event) {
    	SceneController.getSceneController().setMenuScene();
    }

}
