public class CheckersUtility implements Utility<CheckersState, CheckersAction> {
	private CheckersActions checkersActionsImplementation = new CheckersActions();

	public int Utility(CheckersState s){
		CheckersAction[] possibleActions = checkersActionsImplementation.Actions(s);

		if(possibleActions.length == 0){
			if(s.getNextPlayerColor().toInteger() == 2){
				return 1;
			}
			if(s.getNextPlayerColor().toInteger() == 1){
				return -1;
			}
		}

		return 0;
	}
}
