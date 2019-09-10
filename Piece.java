
public class Piece {
	private String color;
	private boolean isKing;
	
	public Piece(String color) {
		this.color = color;
		isKing = false;
	}
	
	public Piece(String color, boolean king) {
		this.color = color;
		isKing = king;
	}
	
	public String getColor() {
		return color;
	}
	
	public boolean getStatus() {
		return isKing;
	}
	
	public void promote() {
		isKing = true;
	}

	@Override
	public String toString() {
		return "Piece [color=" + color + ", isKing?=" + isKing + "]";
	}
}
