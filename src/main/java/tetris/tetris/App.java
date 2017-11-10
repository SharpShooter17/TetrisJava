package tetris.tetris;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application 
{
	static int board_x = 8;			//16
	static int board_y = 16;		//32
	static int tile_size = 32;		//16
	
	long time = 500000000;
	long lastNow = 0;
	
    public static void main( String[] args )
    {
        launch(args);
        SceneController.getSceneController().getScoresControl().saveToXML();
    }

	@Override
	public void start(Stage stage) throws Exception {		
		Controller controller = new Controller(board_x, board_y);
		
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				time += (now - lastNow);
				lastNow = now;
				if (time >= (controller.getTimeToNextFrame()*1000000)) {
						if (!controller.update()){
					 		SceneController.getSceneController().setGameOverScene();
					 	}
					 	SceneController.getSceneController().getGameControl().render();
	                    time = 0;
	                }				
			}
		};
		
		SceneController.getSceneController().init(stage, timer, controller);
		SceneController.getSceneController().getGameControl().setTileSize(tile_size);
	}
}
