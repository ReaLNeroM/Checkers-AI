public class Board {
	private Piece[][] board;
	private Integer size;

	public Board(Piece[][] board){
		size = board.length;
		this.board = new Piece[size][size];

		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}

	public Board(Board board){
		this(board.getBoard());
	}

	public boolean withinBounds(CoordinatePair coordinatePair){
		if(
			0 <= coordinatePair.getRowNumber() && coordinatePair.getRowNumber() < size &&
			0 <= coordinatePair.getColumnNumber() && coordinatePair.getColumnNumber() < size
		){
			return true;
		}
		return false;
	}

	public boolean hasPiece(CoordinatePair coordinatePair) {
		if(!withinBounds(coordinatePair)){
			return false;
		}

		return (board[coordinatePair.getRowNumber()][coordinatePair.getColumnNumber()].getColor().toString() != "-");
	}

	public Piece[][] getBoard(){
		return board;
	}

	public Piece getPiece(CoordinatePair coordinatePair){
		if(!withinBounds(coordinatePair)){
			return null;
		}

		return board[coordinatePair.getRowNumber()][coordinatePair.getColumnNumber()];
	}

	public Integer getSize(){
		return size;
	}

	public void setPiece(CoordinatePair coordinatePair, Piece piece){
		if(!withinBounds(coordinatePair)){
			return;
		}

		board[coordinatePair.getRowNumber()][coordinatePair.getColumnNumber()] = piece;
	}

	public String toString(){
		StringBuilder boardString = new StringBuilder("[\n");

		for(Piece[] rowOfPieces : board){
			boardString.append('\t');
			for(Piece currentPiece : rowOfPieces){
				boardString.append(currentPiece.getColor());
			}
			boardString.append('\n');
		}

		boardString.append("]");
		return boardString.toString();
	}
}
