public class Board {
	private Piece[][] board;
	private Integer size;

	public Board(Piece[][] b){
		size = b.length;
		board = new Piece[size][size];

		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				board[i][j] = b[i][j];
			}
		}
	}

	public Board(Board b){
		this(b.getBoard());
	}

	public boolean withinBounds(CoordinatePair c){
		if(
			0 <= c.getRowNumber() && c.getRowNumber() < size &&
			0 <= c.getColumnNumber() && c.getColumnNumber() < size
		){
			return true;
		}
		return false;
	}

	public boolean hasPiece(CoordinatePair c) {
		if(!withinBounds(c)){
			return false;
		}

		return (board[c.getRowNumber()][c.getColumnNumber()].getColor().toString() != "-");
	}

	public Piece[][] getBoard(){
		return board;
	}

	public Piece getPiece(CoordinatePair c){
		if(!withinBounds(c)){
			return null;
		}

		return board[c.getRowNumber()][c.getColumnNumber()];
	}

	public Integer getSize(){
		return size;
	}

	public void setPiece(CoordinatePair c, Piece p){
		if(!withinBounds(c)){
			return;
		}

		board[c.getRowNumber()][c.getColumnNumber()] = p;
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
