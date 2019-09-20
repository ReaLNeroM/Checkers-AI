
public class CheckersState implements State {
	Board board;
	Player nextPlayer;
	Integer numberOfTurns;

	public CheckersState(Board board, Player nextPlayer){
		this.board = board;
		this.nextPlayer = nextPlayer;
		this.numberOfTurns = 0;
	}

	public CheckersState(Board board, Player nextPlayer, Integer numberOfTurns){
		this.board = board;
		this.nextPlayer = nextPlayer;
		this.numberOfTurns = numberOfTurns;
	}

	public Board getBoard(){
		return board;
	}

	public Player getNextPlayer(){
		return nextPlayer;
	}

	public Integer getNumberOfMovesDone(){
		return numberOfTurns;
	}

	public String toString(){
		return "[ nextPlayer=" + nextPlayer.toString() + "," +
			    " numberOfTurns=" + numberOfTurns.toString() + "," +
			    " board=" + board.toString() + "]";
	}
}
