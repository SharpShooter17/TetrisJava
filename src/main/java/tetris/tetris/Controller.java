package tetris.tetris;

public class Controller {
	
	enum STATE {IN_GAME, PAUSE, GAME_OVER };
	STATE state;
	private boolean isBlock;
	
	private Model model;
			
	public Controller(int board_x, int board_y) {
		model = new Model(board_x, board_y);
		state = STATE.IN_GAME;
		isBlock = false;
	}
	
	public Controller(){
		state = STATE.IN_GAME;
		isBlock = false;
		model = null;
	}
	
	public void resetGame(){
		state = STATE.IN_GAME;
		isBlock = false;
		model.resetGame();
	}
	
	public boolean update(){
		 if (isBlock == false){
			model.newPiece();
			isBlock = true;
			model.addScores(20);
		} else if (state == STATE.IN_GAME) {
			isBlock = !model.isColision();
			if (!isBlock){
				if (!model.writeCurrentShapeToBoard()){
					state = STATE.GAME_OVER;
				}
				model.addScores( model.checkLines() * 200 );
				model.setActivePiece(null);
			} else {
				model.downCurrentBlock();
			}
		} else if (state == STATE.GAME_OVER){
			return false;
		} else if (state == STATE.PAUSE){
			//Do nothing
		}
		 
		return true;
	}
	
	public void changePause(){
		if (state == STATE.PAUSE){
			state = STATE.IN_GAME;
		} else if (state == STATE.IN_GAME){
			state = STATE.PAUSE;
		}
	}
	
	public TILE[][] getBoard() {
		return model.getBoard();
	}
	
	public Piece getActivePiece(){
		return model.getActivePiece();
	}
	
	public Piece getNextPiece(){
		return model.getNextPiece();
	}
	
	public void left(){
		if ( state == STATE.IN_GAME ){
		  model.leftCurrentBlock();
		}
	}
	
	public void right(){
		if ( state == STATE.IN_GAME ){
		  model.rightCurrentBlock();
		}
	}
	
	public void down(){
	if ( state == STATE.IN_GAME ){
		  model.downCurrentBlock();
		}
	}
	
	public void rotate(){
		if ( state == STATE.IN_GAME ){
		  model.rotateCurrentShape();
		}
	}
	
	public int getScores(){
		return model.getScores();
	}
	
	public int getBoardX(){
		return model.getBoardX();
	}
	
	public void setBoardX(int x){
		model.setBoardX(x);
	}
	
	public void setBoardY(int y){
		model.setBoardY(y);
	}
	
	public int getBoardY(){
		return model.getBoardY();
	}
	
	public int getLevel(){
		return model.getLevel();
	}
	
	public long getTimeToNextFrame(){
		return model.getTimeToNextFrame();
	}
	
	public void setDifficultLevel(Model.LEVEL difficultLevel){
		model.setDifficultLevel(difficultLevel);
	}
	
	public Model.LEVEL getDifficultLevel(){
		return model.getDifficultLevel();
	}
}
