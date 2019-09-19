public class Piece {
	private Color color;
	private boolean isKing;

	public Piece(Color color) {
		this.color = color;
		isKing = false;
	}

	public Piece(Color color, boolean king) {
		this.color = color;
		isKing = king;
	}

	public Color getColor() {
		return color;
	}

	public boolean getIsKing() {
		return isKing;
	}

	public void promote() {
		isKing = true;
	}

	@Override
	public String toString() {
		return "Piece [color=" + color.toString() + ", isKing?=" + isKing + "]";
	}
}
