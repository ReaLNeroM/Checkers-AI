public class Piece {
	private Color color;
	private boolean isKing;

	public Piece(Color color) {
		this.color = color;
		isKing = false;
	}

	public Piece(Color color, boolean isKing) {
		this.color = color;
		this.isKing = isKing;
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
		if(color.toInteger() == 1 && !isKing){
			return "w";
		} else if(color.toInteger() == 1 && isKing){
			return "W";
		} else if(color.toInteger() == 2 && !isKing){
			return "b";
		} else if(color.toInteger() == 2 && isKing){
			return "B";
		}

		return "-";
	}
}
