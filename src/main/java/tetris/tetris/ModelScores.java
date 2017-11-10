package tetris.tetris;

public class ModelScores {
	private String nick;
	private Integer level;
	private Integer scores;
	private String difficultLevel;
	
	public ModelScores(String nick, int level, int scores, String difficultLevel){
		if (nick.equals("")){
			this.nick = new String("Anonymous");
		}else{
			this.nick = nick;
		}
		
		this.level = level;
		this.scores = scores;
		this.difficultLevel = difficultLevel;
	}
	
	public ModelScores(){
		
	}
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getScores() {
		return scores;
	}
	public void setScores(Integer scores) {
		this.scores = scores;
	}
	public String getDifficultLevel() {
		return difficultLevel;
	}
	public void setDifficultLevel(String difficultLevel) {
		this.difficultLevel = difficultLevel;
	}
}
