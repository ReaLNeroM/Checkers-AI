public class Piece {
	private Player Player;
	private boolean isKing;

	public Piece(Player Player) {
		this.Player = Player;
		isKing = false;
	}

	public Piece(Player Player, boolean king) {
		this.Player = Player;
		isKing = king;
	}

	public Player getPlayer() {
		return Player;
	}

	public boolean getIsKing() {
		return isKing;
	}

	public void promote() {
		isKing = true;
	}

	@Override
	public String toString() {
		return "Piece [Player=" + Player.toString() + ", isKing?=" + isKing + "]";
	}
}
