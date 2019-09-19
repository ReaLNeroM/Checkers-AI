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
			0 <= c.getFirst() && c.getFirst() < size &&
			0 <= c.getSecond() && c.getSecond() < size
		){
			return true;
		}
		return false;
	}

	public boolean hasPiece(CoordinatePair c) {
		if(!withinBounds(c)){
			return false;
		}

		return (board[c.getFirst()][c.getSecond()].getColor().toString() != "-");
	}

	public Piece[][] getBoard(){
		return board;
	}

	public Piece getPiece(CoordinatePair c){
		if(!withinBounds(c)){
			return null;
		}

		return board[c.getFirst()][c.getSecond()];
	}

	public Integer getSize(){
		return size;
	}

	public void setPiece(CoordinatePair c, Piece p){
		if(!withinBounds(c)){
			return;
		}

		board[c.getFirst()][c.getSecond()] = p;
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
