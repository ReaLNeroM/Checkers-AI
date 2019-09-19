public class CheckersState implements State {
	Board board;
	Color color;
	Integer numberOfTurns;

	public CheckersState(Board board, Color color){
		this.board = board;
		this.color = color;
		this.numberOfTurns = 0;
	}

	public CheckersState(Board board, Color color, Integer numberOfTurns){
		this.board = board;
		this.color = color;
		this.numberOfTurns = numberOfTurns;
	}

	public Board getBoard(){
		return board;
	}

	public Color getNextPlayerColor(){
		return color;
	}

	public Integer getNumberOfMovesDone(){
		return numberOfTurns;
	}
}