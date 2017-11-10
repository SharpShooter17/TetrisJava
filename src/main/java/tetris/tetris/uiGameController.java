package tetris.tetris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class uiGameController {
	private int tileSize;
	
    @FXML
    private Label scoreLabel;

    @FXML
    private Button buttonPause;

    @FXML
    private Button buttonReset;

    @FXML
    private Button buttonGoToMenu;

    @FXML
    private Canvas canvasBoard;
    
    @FXML
    private Canvas canvasHelp;

    @FXML
    void buttonGoToMenuClick(ActionEvent event) {
    	SceneController.getSceneController().setMenuScene();
    }
    
    public Canvas getBoard(){
    	return canvasBoard;
    }
    
    public void render(){
    	renderBoard(SceneController.getSceneController().getController().getBoard());
    	renderActivePiece(SceneController.getSceneController().getController().getActivePiece());
    	renderScores(SceneController.getSceneController().getController().getScores());
		renderNextPiece(SceneController.getSceneController().getController().getNextPiece());
    }
    
    void renderBackground(){
    	GraphicsContext g = canvasBoard.getGraphicsContext2D();
    	g.clearRect(0, 0, SceneController.getSceneController().getController().getBoardX()*tileSize, SceneController.getSceneController().getController().getBoardY()*tileSize);
    	g.setFill(Color.LIGHTSLATEGRAY);
    	g.setGlobalAlpha(0.8);
    	g.fillRect(0, 0, SceneController.getSceneController().getController().getBoardX()*tileSize, SceneController.getSceneController().getController().getBoardY()*tileSize);
    	g.setGlobalAlpha(1.0);
    }
    
    void renderBoard(TILE[][] board){
    	GraphicsContext g = canvasBoard.getGraphicsContext2D();
    	renderBackground();
    	for(int i = 0; i < board.length; i++){
    		for (int j = 0; j < board[i].length; j++){
    			setColor(g, board[i][j]);
    			g.fillRect(tileSize*j+1, tileSize*i+1, tileSize-2, tileSize-2);
    		}
    	}
    }
    
    void renderActivePiece(Piece piece){
    	if ( !(piece instanceof Piece) ) {
    		return;
    	}
    	
    	GraphicsContext g = canvasBoard.getGraphicsContext2D();
    	for (int i = 0; i < piece.getShape().length; i++){
    		for (int j = 0; j < piece.getShape()[i].length; j++){
    			setColor(g, piece.getShape()[i][j]);
    			g.fillRect(tileSize*(j + piece.getX()) + 1, tileSize*(i + piece.getY()) + 1, tileSize - 2, tileSize - 2);
    		}
    	}
    }
    
    void renderNextPiece(Piece piece){
    	GraphicsContext g = canvasHelp.getGraphicsContext2D();
    	
    	g.clearRect(0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());
    	
    	for (int i = 0; i < piece.getShape().length; i++){
    		for (int j = 0; j < piece.getShape()[i].length; j++){
    			setColor(g, piece.getShape()[i][j]);
    			g.fillRect(tileSize*j + 1 + g.getCanvas().getWidth()/2 - Shape.getWidthCurBlock(piece.getShape())*tileSize/2, 
    					tileSize*i+ 1 + g.getCanvas().getHeight()/2  - Shape.getHeightCurBlock(piece.getShape())*tileSize/2, tileSize - 2, tileSize - 2);
    		}
    	}
    } 
    
    void renderScores(Integer scores){
    	scoreLabel.setText(scores.toString());
    }
    
    private void setColor(GraphicsContext g, TILE color){
    	switch (color)
    	{
		case BLUE:
			g.setFill(Color.BLUE);
			break;
		case BROWN:
			g.setFill(Color.BROWN);
			break;
		case EMPTY:
			g.setFill(Color.TRANSPARENT);
			break;
		case GREEN:
			g.setFill(Color.GREEN);
			break;
		case ORANGE:
			g.setFill(Color.ORANGE);
			break;
		case PURPLE:
			g.setFill(Color.PURPLE);
			break;
		case RED:
			g.setFill(Color.RED);
			break;
		case TORQUISE:
			g.setFill(Color.TURQUOISE);
			break;
		case YELLOW:
			g.setFill(Color.YELLOW);
			break;
		default:
			break;
    	}
    }

    @FXML
    void buttonPauseClick(ActionEvent event) {
    	SceneController.getSceneController().getController().changePause();
    }

    @FXML
    void buttonResetClick(ActionEvent event) {
    	SceneController.getSceneController().getController().resetGame();
    	scoreLabel.setText("0");
    }
	
	@FXML
    void keyEvent(KeyEvent event) {
		
		switch (event.getCode()){
		case LEFT:
		case KP_LEFT:
		case A:
			SceneController.getSceneController().getController().left();
			break;
		case RIGHT:
		case KP_RIGHT:
		case D:
			SceneController.getSceneController().getController().right();
			break;
		case DOWN:
		case KP_DOWN:
		case S:
			SceneController.getSceneController().getController().down();
			break;
		case UP:
		case SPACE:
		case W:
			SceneController.getSceneController().getController().rotate();
			break;
		case P:
			SceneController.getSceneController().getController().changePause();
			break;
		case R:
			SceneController.getSceneController().getController().resetGame();
			break;
		default:
			break;
		}
		
		renderBoard(SceneController.getSceneController().getController().getBoard());
		renderActivePiece(SceneController.getSceneController().getController().getActivePiece());
    }

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

}
