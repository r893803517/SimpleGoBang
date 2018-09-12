package Game;

public class Chess {
	private int color;//1-black£¬0-white
	private boolean isPlaced = false;
	public int matchCount = 1;
	
	public Chess(int color,boolean placed){
		this.color=color;
		this.isPlaced=placed;
	}
	
	public boolean ifPlaced() {
		return isPlaced;
	}

	public void Placed(boolean placed) {
		this.isPlaced = placed;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}
