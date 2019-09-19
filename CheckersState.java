
public class CheckersState implements State {
	private Board board;
	private String player;
	public CheckersState(Board board, String player) {
		this.board = board;
		this.player = player;
	}

	@Override
	public State getInitialState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWinState() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String getPlayer() {
		return player;
	}
	
	public Board getBoard() {
		return board;
	}
	
	/**
	 * 
	 * @return returns the player the turn changed into in string
	 */
	public String changeSides() {
		if(player.endsWith("W")) {
			player = "B";
			return "B";
		}else {
			player = "W";
			return "W";
		}
	}

}
