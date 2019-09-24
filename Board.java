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
    	return !getPiece(coordinatePair).isEmpty();
    }

    public Piece getPiece(CoordinatePair coordinatePair){
    	if(!withinBounds(coordinatePair)){
    		return null;
    	}

    	return board[coordinatePair.getRowNumber()][coordinatePair.getColumnNumber()];
    }

    public Piece[][] getBoard(){
    	return board;
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

    	boardString.append("\t ");
    	boardString.append("/");
    	for(int i = 0; i < size; i++){
    		boardString.append('|');
    		boardString.append(Integer.toString(i + 1));
    	}
    	boardString.append('\n');

    	boardString.append("\t ");
    	for(int j = 0; j < size; j++){
    		boardString.append("+—");
    	}
    	boardString.append("+\n");

    	for(int i = 0; i < size; i++){
    		Piece[] rowOfPieces = board[i];

    		boardString.append('\t');
    		boardString.append(' ' + Character.toString((char) ('A' + i)));
    		boardString.append('|');
    		for(Piece currentPiece : rowOfPieces){
    			boardString.append(currentPiece.toString());
    			boardString.append('|');
    		}
    		boardString.append('\n');

    		boardString.append("\t ");
    		for(int j = 0; j < size; j++){
    			boardString.append("+—");
    		}
    		boardString.append("+\n");
    	}

    	boardString.append("]");
    	return boardString.toString();
    }
    
    public int getNumWhite() {
    	int numWhite = 0;
    	for(int i = 0; i < size; i++) {
    		for(int j = 0; j < size; j++) {
    			if(board[i][j].getOwner().toInteger() == 1) {
    				numWhite++;
    			}
    		}
    	}
    	return numWhite;
    }
    
    public int getNumBlack() {
    	int numBlack = 0;
    	for(int i = 0; i < size; i++) {
    		for(int j = 0; j < size; j++) {
    			if(board[i][j].getOwner().toInteger() == 2) {
    				numBlack++;
    			}
    		}
    	}
    	return numBlack;
    }
}
