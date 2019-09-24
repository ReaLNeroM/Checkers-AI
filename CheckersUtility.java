public class CheckersUtility implements Utility<CheckersState, CheckersAction> {
	private CheckersActions checkersActionsImplementation = new CheckersActions();
	private Integer numberOfMovesForTie = 200;

    public int Utility(CheckersState state){
    	CheckersAction[] possibleActions = checkersActionsImplementation.Actions(state);

    	if(possibleActions.length == 0){
    		if(state.getNextPlayer().toInteger() == 2){
    			return 1;
    		}
    		if(state.getNextPlayer().toInteger() == 1){
    			return -1;
    		}
    	}

    	return 0;
    }

	@Override
	public boolean isTerminal(CheckersState state) {
		if(state.getNumberOfMovesDone() >= numberOfMovesForTie) {
			return true;
		}
		CheckersAction[] possibleActions = checkersActionsImplementation.Actions(state);
		if(possibleActions.length == 0) {
			return true;
		}
		return false;
	}
}
