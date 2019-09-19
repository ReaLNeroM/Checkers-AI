public class CheckersState implements State {
	Board board;
	Color color;

	public CheckersState(Board board, Color color){
		this.board = board;
		this.color = color;
	}

	public Board getBoard(){
		return board;
	}

	public Integer getNumberOfMovesDone(){
		return null;
		// TODO
	}
	public Color getNextPlayerColor(){
		return color;
	}
}