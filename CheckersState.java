public class CheckersState implements State {
	Board board;
	Player currentPlayer;
	Integer numberOfTurns;

	public CheckersState(Board board, Player currentPlayer){
		this.board = board;
		this.currentPlayer = currentPlayer;
		this.numberOfTurns = 0;
	}

	public CheckersState(Board board, Player currentPlayer, Integer numberOfTurns){
		this.board = board;
		this.currentPlayer = currentPlayer;
		this.numberOfTurns = numberOfTurns;
	}

	public Board getBoard(){
		return board;
	}

	public Player getNextPlayer(){
		return currentPlayer;
	}

	public Integer getNumberOfMovesDone(){
		return numberOfTurns;
	}

	public String toString(){
		return "[Board=" + board.toString() + "," +
			   " currentPlayer=" + currentPlayer.toString() + "," +
			   " numberOfTurns=" + numberOfTurns.toString() + "]";
	}
}
