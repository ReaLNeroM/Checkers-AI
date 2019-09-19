public class Board {
	private Piece[][] board;
	private Integer size;

	private boolean withinBounds(CoordinatePair c){
		if(0 <= c.getFirst() && c.getFirst() < 8 && 0 <= c.getSecond() && c.getSecond() < 8){
			return true;
		}
		return false;
	}

	public Board(Piece[][] b){
		size = b.length;
		board = b.clone();
	}

	public Board(Board b){
		this(b.getBoard());
	}

	public boolean hasPiece(CoordinatePair c) {
		if(!withinBounds(c)){
			return false;
		}

		return (board[c.getFirst()][c.getSecond()] != null);
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

	public void printToConsoleTest() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j]!=null) {
					System.out.print(board[i][j].getColor());
				}else {
					System.out.print("-");
				}
			}
			System.out.print("\n");
		}
		// System.out.println("testing BoardUtil functions: ");
		// ArrayList<int[]> output = util.getDiagonalPosition(2, 1);
		// for(int i = 0; i < output.size(); i++) {
		// 	int[] elem = output.get(i);
		// 	System.out.println( elem[0] + ", " + output.get(i)[1]);
		// }

		// System.out.println("testing validMovesFor() function: ");
		// ArrayList<Move> validMoves = validMovesFor(2, 1);
		// if(validMoves != null) {
		// 	for(int i = 0; i < validMoves.size(); i++) {
		// 		System.out.println(validMoves.get(i).toString());
		// 	}
		// }else {
		// 	System.out.println("validMoves returned null");
		// }
	}
}
