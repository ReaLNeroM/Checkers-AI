public class CheckersUtility implements Utility<CheckersState, CheckersAction> {
	private CheckersActions checkersActionsImplementation = new CheckersActions();

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
}
