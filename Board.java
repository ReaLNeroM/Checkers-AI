import java.util.*;

public class Board {
	private Piece[][] board;
	// private BoardUtil util = new BoardUtil();
	private String WHITE = "w";
	private String BLACK = "b";
	public Board(int size) {
		board = new Piece[size][size];
		for(int i = 0; i < (size/2)-1; i++) {
			for(int j = 0; j < size; j++) {
				if(i%2==0) {
					if(j%2!=0) {
						board[i][j] = new Piece(BLACK);
					}
				}else {
					if(j%2==0) {
						board[i][j] = new Piece(BLACK);
					}
				}
			}
		}//black side
		for(int i = size-1; i >= (size/2)+1; i--) {
			for(int j = 0; j < size; j++) {
				if(i%2!=0) {
					if(j%2==0) {
						board[i][j] = new Piece(WHITE);
					}
				}else {
					if(j%2!=0) {
						board[i][j] = new Piece(WHITE);
					}
				}
			}
		}//white side
	}

	public boolean hasPiece() {
		return false;
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

	/**
	 *
	 * @param row
	 * @param col
	 * @return ArrayList<Move> only returns an ArrayList of the immediate moves
	 * <strong>i.e not including multi capture moves, just the immediate capture<strong>
	 */
	// public ArrayList<Move> validMovesFor(int row, int col) {
	// 	if(!util.existInBoard(row, col)) {
	// 		System.out.println("given coordinates does not exist on current board: " + row + ", " + col);
	// 		return null;
	// 	}
	// 	if(board[row][col] == null) {
	// 		System.out.println("Error: given coordinate has no piece present at row: " + row + " col: " + col);
	// 		return null;
	// 	}
	// 	Piece target = board[row][col];
	// 	ArrayList<int[]> validMoves = util.getDiagonalPosition(row, col);
	// 	if(target.getStatus()) {
	// 		//process all four directions because king can move in all directions
	// 	}else {
	// 		if(target.getColor().equals(WHITE)) {
	// 			//only process top two
	// 			//ARRAYLISTS AUTO SHIFT ELEMENTS DOWN!
	// 			validMoves.remove(2);
	// 			validMoves.remove(2);
	// 		}else
	// 		if(target.getColor().equals(BLACK)){
	// 			//only process bottom two
	// 			validMoves.remove(0);
	// 			validMoves.remove(0);
	// 		}else {
	// 			System.out.println("Error: target piece at " + row + ", " + col + " does not match known colors");
	// 			return null;
	// 		}
	// 	}
	// 	for(int i = 0; i < validMoves.size(); i++) {
	// 		//see the description for util.getDiagonalPosition(), -9 is the error coordinate substitute
	// 		if(validMoves.get(i)[0] == -9) {
	// 			validMoves.remove(i);
	// 		}
	// 	}
	// 	//after determining the direction we need to process
	// 	//we check if the diagonal is blacked
	// 	ArrayList<Move> output = new ArrayList<Move>();
	// 	for(int[] coordinate : validMoves) {
	// 		int x = coordinate[0];
	// 		int y = coordinate[1];
	// 		if(util.occupied(x, y)) {
	// 			//check for color
	// 			if(board[x][y].getColor().equals(target.getColor())) {
	// 				//no further jump available
	// 				//NO AVALIABLE MOVE!
	// 			}else {
	// 				//piece is opposite color thus could capture
	// 				//check for valid alternatives
	// 				int xDiff = row - x;
	// 				int yDiff = col - y;
	// 				int newX = x-xDiff;
	// 				int newY = y-yDiff;
	// 				if(!util.occupied(newX, newY)) {
	// 					//capture move is valid
	// 					//THIS MOVE IS A CAPTURE!
	// 					Move m = new Move(new int[] {row, col}, new int[] {newX, newY}, true);
	// 					output.add(m);
	// 				}
	// 			}
	// 		}else {
	// 			//THIS IS A MOVE!
	// 			Move m = new Move(new int[] {row, col}, coordinate, false);
	// 			output.add(m);
	// 		}
	// 	}
	// 	return output;
	// }

	// public boolean makeMove(Move move) {
	// 	return true;
	// }

	// private class BoardUtil {

	// 	/**
	// 	 * @param row row index
	// 	 * @param col column index
	// 	 * @return ArrayList<int[]> ArrayList of the four coordinates around the immediate diagonal
	// 	 *  of the input coordinates. in clockwise order starting from top left
	// 	 * <h1><strong>{-9, -9} is coordinate return for outside of board positions</strong></h1>
	// 	 */
	// 	public ArrayList<int[]> getDiagonalPosition(int row, int col) {
	// 		ArrayList<int[]> output = new ArrayList<int[]>();
	// 		//check top left
	// 		if(existInBoard(row-1, col-1)) {
	// 			int[] tl = {row-1, col-1};
	// 			output.add(tl);
	// 		}else {
	// 			output.add(new int[]{-9, -9});
	// 		}
	// 		//check top right
	// 		if(existInBoard(row-1, col+1)) {
	// 			int[] tr = {row-1, col+1};
	// 			output.add(tr);
	// 		}else {
	// 			output.add(new int[]{-9, -9});
	// 		}
	// 		//check bottom right
	// 		if(existInBoard(row+1, col+1)) {
	// 			int[] br = {row+1, col+1};
	// 			output.add(br);
	// 		}else {
	// 			output.add(new int[]{-9, -9});
	// 		}
	// 		//check bottom left
	// 		if(existInBoard(row+1, col-1)) {
	// 			int[] bl = {row+1, col-1};
	// 			output.add(bl);
	// 		}else {
	// 			output.add(new int[]{-9, -9});
	// 		}

	// 		return output;
	// 	}

	// 	private boolean existInBoard(int row, int col) {
	// 		if(row<0 || col<0) {
	// 			return false;
	// 		}
	// 		if(row>board.length-1 || col>board[0].length) {
	// 			return false;
	// 		}
	// 		return true;
	// 	}

	// 	private boolean occupied(int row, int col) {
	// 		if(board[row][col]!=null) {
	// 			return true;
	// 		}else {
	// 			return false;
	// 		}
	// 	}
	// }
}
