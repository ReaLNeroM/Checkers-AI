public class CheckersState implements State {
	private Board board;
	private Player currentPlayer;
	private Integer numberOfMoves;

	public CheckersState(Board board, Player currentPlayer){
		this.board = board;
		this.currentPlayer = currentPlayer;
		this.numberOfMoves = 0;
	}

	public CheckersState(Board board, Player currentPlayer, Integer numberOfTurns){
		this.board = board;
		this.currentPlayer = currentPlayer;
		this.numberOfMoves = numberOfTurns;
	}

	public Board getBoard(){
		return board;
	}

	public Player getNextPlayer(){
		return currentPlayer;
	}

	public Integer getNumberOfMovesDone(){
		return numberOfMoves;
	}

	public String toString(){
		return "[Board=" + board.toString() + "," +
			   " currentPlayer=" + currentPlayer.toString() + "," +
			   " numberOfTurns=" + numberOfMoves.toString() + "]";
	}
}
