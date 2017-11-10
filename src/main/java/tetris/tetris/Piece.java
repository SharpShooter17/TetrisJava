package tetris.tetris;

public class Piece {
	private TILE[][] shape;
	private int x, y;
	Piece(){
		this.shape = Shape.getShape(Shape.randomShape()).clone();
		setX(0);
		setY(0);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public TILE[][] getShape() {
		return shape;
	}
	public void setShape(TILE[][] shape) {
		this.shape = shape;
	}
	public void down(){
		y++;
	}
	public void left(){
		x--;
	}
	public void right(){
		x++;
	}
	public void up(){
		y --;
	}
}
