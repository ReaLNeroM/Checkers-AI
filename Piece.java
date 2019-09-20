public class Piece {
	private Player owner;
	private boolean isKing;

	public Piece(Player owner) {
		this.owner = owner;
		this.isKing = false;
	}

	public Piece(Player owner, boolean isKing) {
		this.owner = owner;
		this.isKing = isKing;
	}

	public boolean isEmpty(){
		return (owner.toInteger() == 0);
	}

	public Player getOwner() {
		return owner;
	}

	public boolean getIsKing() {
		return isKing;
	}

	@Override
	public String toString() {
		if(owner.toInteger() == 1 && !isKing){
			return "w";
		} else if(owner.toInteger() == 1 && isKing){
			return "W";
		} else if(owner.toInteger() == 2 && !isKing){
			return "b";
		} else if(owner.toInteger() == 2 && isKing){
			return "B";
		}

		return "-";
	}
}
