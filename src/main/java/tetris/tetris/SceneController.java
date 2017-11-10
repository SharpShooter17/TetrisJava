package tetris.tetris;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SceneController {
	
	private Stage stage;
	private Scene menu;
	private Scene game;
	private Scene scores;
	private Scene gameOver;
	private Scene about;
	
	private AnimationTimer gameTimer;
	
	private uiMenuController menuControl;
	private uiGameController gameControl;
	private uiScoresController scoresControl;
	private uiGameOverController gameOverControl;
	private uiAboutController aboutControl;
	private Controller controller;
	
	static private SceneController sceneController;
	
	private SceneController() {
	
	}
	
	public void init(Stage stage, AnimationTimer timer, Controller controller) throws IOException{
		this.stage = stage;
		FXMLLoader loaderMenu = new FXMLLoader();
		loaderMenu.setLocation(this.getClass().getResource("/uiMenu.fxml"));
		StackPane paneMenu = loaderMenu.load();
		menuControl = loaderMenu.getController();
		
		menu = new Scene(paneMenu);
		
		FXMLLoader loaderGame = new FXMLLoader();
		loaderGame.setLocation(this.getClass().getResource("/uiGame.fxml"));
		StackPane paneGame = loaderGame.load();
		game = new Scene(paneGame);
		gameControl = loaderGame.getController();
		
		FXMLLoader loaderScores = new FXMLLoader();
		loaderScores.setLocation(this.getClass().getResource("/uiScores.fxml"));
		StackPane paneScores = loaderScores.load();
		scores = new Scene(paneScores);
		scoresControl = loaderScores.getController();
		
		FXMLLoader loaderGameOver = new FXMLLoader();
		loaderGameOver.setLocation(this.getClass().getResource("/uiGameOver.fxml"));
		StackPane paneGameOver = loaderGameOver.load();
		gameOver = new Scene(paneGameOver);
		gameOverControl = loaderGameOver.getController();
		
		FXMLLoader loaderAbout = new FXMLLoader();
		loaderAbout.setLocation(this.getClass().getResource("/uiAbout.fxml"));
		StackPane paneAbout = loaderAbout.load();
		about = new Scene(paneAbout);
		aboutControl = loaderAbout.getController();
		
		stage.setScene(menu);
		stage.setTitle("Tetris");
		stage.show();	
		
		this.gameTimer = timer;
		
		this.controller = controller;
	}
	
	public static SceneController getSceneController(){
		if ( !(sceneController instanceof SceneController))
			sceneController = new SceneController();
		return sceneController;			
	}
	
	public void setMenuScene(){
		stage.setScene(menu);
		stage.show();
		gameTimer.stop();
	}
	
	public void setGameScene(){
		stage.setScene(game);
		stage.show();
		gameTimer.start();
	}
	
	public void setScoresScene(){
		stage.setScene(scores);
		stage.show();
	}
	
	public void setGameOverScene(){
		gameTimer.stop();
		
		gameOverControl.setNick(menuControl.getNick());
		gameOverControl.setScores(controller.getScores());
		gameOverControl.setDifficulty(controller.getDifficultLevel().toString());
		gameOverControl.setLevel(controller.getLevel());
		
		stage.setScene(gameOver);
		stage.show();
	}

	public void setAbout(){
		stage.setScene(about);
		stage.show();
	}
	
	public uiMenuController getMenuControl() {
		return menuControl;
	}

	public uiGameController getGameControl() {
		return gameControl;
	}
	
	public Controller getController(){
		return controller;
	}

	public uiScoresController getScoresControl() {
		return scoresControl;
	}
}
