public class CheckersHeuristic implements Heuristic <CheckersState, CheckersAction> {
    
    public Integer Heuristic(CheckersState state){
//    	parameters that could be useful:
//    		num pieces on each side
//    			included which one of those are kings
    	Board board = state.getBoard();
    	int numFirst = board.getNumWhite();
    	int numSecond = board.getNumBlack();
    	
    	if(numFirst > numSecond) {
    		return 1;
    	}else if(numSecond > numFirst) {
    		return -1;
    	}
    	return 0;
    }
    
}