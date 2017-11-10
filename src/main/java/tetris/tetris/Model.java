package tetris.tetris;

public class Model {	
	enum LEVEL {EASY, MEDIUM, HARD}
	
	private int board_x;
	private int board_y;
	
	private TILE board[][];
	private Piece activePiece;
	private Piece nextPiece;
	private int scores;
	private int level;
	private Model.LEVEL difficultLevel;
	private long timeToNextFrame;
	
	
	Model(int x, int y){
		board_x = x;
		board_y = y;
		
		setDifficultLevel(Model.LEVEL.MEDIUM);
		resetGame();
	}
	
	public int getBoardX(){
		return board_x;
	}
	public void setBoardX(int x){
		board_x = x;
	}
	public int getBoardY(){
		return board_y;
	}
	public void setBoardY(int y){
		board_y = y;
	}	

	public void resetGame(){
		board = new TILE[board_y][board_x];
		
		for (int i = 0; i < board.length; i++){
			for (int  j = 0; j < board[i].length; j++){
				board[i][j] = TILE.EMPTY;
			}
		}
		
		activePiece = null;
		nextPiece = null;
		scores = 0;
		level = 0;
	}
	
	public Model.LEVEL getDifficultLevel(){
		return difficultLevel;
	}
	
	public void setDifficultLevel(Model.LEVEL difficultLevel){
		this.difficultLevel = difficultLevel;
		
		switch(difficultLevel){
		case EASY:
			timeToNextFrame = 700;
			break;
		case MEDIUM:
			timeToNextFrame = 450;
			break;
		case HARD:
			timeToNextFrame = 250;
			break;
		}
	}
	
	public int getLevel(){
		return level;
	}
	
	public TILE[][] getBoard() {
		return board;
	}
	
	public void newPiece() {
		
		if ( !(nextPiece instanceof Piece) ){
			setNextPiece(new Piece());
		}
		
		setActivePiece(nextPiece);
		setNextPiece(new Piece());
		nextPiece.setX(256);
		nextPiece.setY(128);
		activePiece.setX(board_x/2 - Shape.getWidthCurBlock(activePiece.getShape())/2 );
		activePiece.setY(-Shape.getHeightCurBlock(activePiece.getShape()));
	}

	public Piece getActivePiece() {
		return activePiece;
	}

	public void setActivePiece(Piece activePiece) {
		this.activePiece = activePiece;
	}
	
	public Piece getNextPiece() {
		return nextPiece;
	}

	public void setNextPiece(Piece nextPiece) {
		this.nextPiece = nextPiece;
	}
	
	public void downCurrentBlock(){
		if (!(activePiece instanceof Piece)){
			return;
		}
		
		if ( isColision() ){
			return;
		}
		activePiece.down();
	}
	
	public void leftCurrentBlock(){
		if (!(activePiece instanceof Piece)){
			return;
		}
		
		if (activePiece.getX() == 0){
			return;
		}
		activePiece.left();
		if (isColisionAfterTranslation()){
			activePiece.right();
		}
	}
	
	public void rightCurrentBlock(){
		if (!(activePiece instanceof Piece)){
			return;
		}
		
		if ((activePiece.getX() + Shape.getWidthCurBlock(activePiece.getShape())) == board_x){
			return;
		}
		activePiece.right();
		
		if (isColisionAfterTranslation()){
			activePiece.left();
		}
	}	

	public void rotateCurrentShape(){
		
		if (!(activePiece instanceof Piece)){
			return;
		}
		
		if (activePiece.getY() < 0){
			return;
		}
				
		if ((activePiece.getY() + Shape.getWidthCurBlock(activePiece.getShape())) > (board_y)){
			return;
		}
		
		if ((activePiece.getX() + Shape.getHeightCurBlock(activePiece.getShape())) > (board_x)){
			return;
		}
		
		TILE[][] tmp = activePiece.getShape().clone();
		activePiece.setShape( Shape.rotate(activePiece.getShape()) );
		if (isColisionAfterTranslation()){
			activePiece.setShape(tmp);
		}
	}
	
	public TILE [][] getactivePiece() {
		return activePiece.getShape();
	}
	
	public boolean isColision(){
		
		if ((activePiece.getY() + Shape.getHeightCurBlock(activePiece.getShape())) >= board_y){
			return true;
		}
		
		for (int i = 0; i < activePiece.getShape().length; i++){
			for (int j = 0; j < activePiece.getShape()[i].length; j++){
				
				if ( activePiece.getShape()[i][j] == TILE.EMPTY || (activePiece.getY() + i) < 0){
					continue;
				}
				
				if ((activePiece.getY() + i) >= board_y){
					return true;
				}
				
				if ( (activePiece.getX() + j) >= board_x || (activePiece.getX() + j) < 0 ){
					return true;
				}
								
				if ( (activePiece.getY() + i + 1 > 0) && (board[activePiece.getY() + i + 1][activePiece.getX() + j] != TILE.EMPTY)){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isColisionAfterTranslation(){
		for (int i = 0; i < activePiece.getShape().length; i++){
			for (int j = 0; j < activePiece.getShape()[i].length; j++){
				if ( activePiece.getShape()[i][j] == TILE.EMPTY || (activePiece.getY() + i) < 0){
					continue;
				}
				
				if ( (activePiece.getX() + j) >= board_x || (activePiece.getX() + j) < 0 ){
					return true;
				}
				
				if ( board[activePiece.getY() + i][activePiece.getX() + j] != TILE.EMPTY ){
					return true;
				}
			}
		}
		
		return false;
	}
	
	//return false if piece is out of board
	public boolean writeCurrentShapeToBoard(){
		for (int i = 0; i < activePiece.getShape().length; i++){
			for (int j = 0; j < activePiece.getShape()[i].length; j++){
				if (activePiece.getY() < 0){
					return false;
				}
				if ( activePiece.getShape()[i][j] == TILE.EMPTY ){
					continue;
				}
				board[i + activePiece.getY()][j+activePiece.getX()] = activePiece.getShape()[i][j];
			}
		}
		return true;
	}
	
	private void removeLine(int index){
		for (int i = index; i > 0; i--){
			for (int j = 0; j < board_x; j++){
				board[i][j] = board[i-1][j];
			}
		}
		
		for (int j = 0; j < board_x; j++){
			board[0][j] = TILE.EMPTY;
		}
	}
	
	public int checkLines(){
		int res = 0;
		
		for (int i = 0; i < board_y; i++){
			int notEmpty = 0;
			for (int j = 0; j < board_x; j++){
				if (board[i][j] == TILE.EMPTY){
					break;
				}
				notEmpty++;
			}
			if (notEmpty == board_x ){
				removeLine(i);
				res++;
			}
		}
		
		return res;
	}

	public int getScores() {
		return scores;
	}

	public void setScores(int scores) {
		this.scores = scores;
	}
	
	public void addScores(int add){
		scores += add;
		
		int tmp = level;
		setLevel();	
		
		if (tmp != level){
			timeToNextFrame -= 10;
		
			if (timeToNextFrame < 50){
				timeToNextFrame = 50;
			}
		}
	}
	
	private void setLevel(){
		if (scores < 200){
			level = 1;
		} else if (scores < 500){
			level = 2;
		} else if (scores < 1000){
			level = 3;
		} else if (scores < 1500){
			level = 4;
		} else if (scores < 3000){
			level = 5;
		} else if (scores < 4500){
			level = 6;
		} else if (scores < 6000){
			level = 7;
		} else if (scores < 7500){
			level = 8;
		} else if (scores < 9000){
			level = 9;
		} else if (scores < 11500){
			level = 10;
		} else if (scores < 14000){
			level = 11;
		} else if (scores < 16000){
			level = 12;
		} else if (scores < 18000){
			level = 13;
		} else if (scores < 20000){
			level = 14;
		} else if (scores < 22500){
			level = 15;
		} else if (scores < 25000){
			level = 16;
		} else if (scores < 27500){
			level = 17;
		} else if (scores < 30000){
			level = 18;
		} else if (scores < 35000){
			level = 19;
		} else if (scores < 40000){
			level = 20;
		}
	}
	
	public long getTimeToNextFrame(){		
		return timeToNextFrame;
	}

}
