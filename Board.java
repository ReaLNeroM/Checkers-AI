
public class Board {
	Piece[][] board;
	public Board(int size) {
		board = new Piece[size][size];
		for(int i = 0; i < (size/2)-1; i++) {
			for(int j = 0; j < size; j++) {
				if(i%2==0) {
					if(j%2!=0) {
						board[i][j] = new Piece("b");
					}
				}else {
					if(j%2==0) {
						board[i][j] = new Piece("b");
					}
				}
			}
		}//black side
		for(int i = size-1; i >= (size/2)+1; i--) {
			for(int j = 0; j < size; j++) {
				if(i%2!=0) {
					if(j%2==0) {
						board[i][j] = new Piece("w");
					}
				}else {
					if(j%2!=0) {
						board[i][j] = new Piece("w");
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
	}
}
