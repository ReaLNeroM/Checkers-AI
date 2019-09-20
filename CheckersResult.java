public class CheckersResult implements Result <CheckersState, CheckersAction> {
	private CheckersActions checkersActionsImplementation;

	public CheckersResult(){
		checkersActionsImplementation = new CheckersActions();
	}

	public CheckersState Result(CheckersState s, CheckersAction a){
		CheckersAction[] validActions = checkersActionsImplementation.Actions(s);

		boolean isValid = false;
		for(CheckersAction validAction : validActions){
			if(a.equals(validAction)){
				isValid = true;
			}
		}

		if(!isValid){
			return null;
		}

		Board resultingBoard = checkersActionsImplementation.resultingBoardAfterPartialAction(s, a);
		Integer numberOfMoves = s.getNumberOfMovesDone() + 1;
		Color nextPlayer = s.getNextPlayerColor().getOppositeColor();

		CheckersState resultingState = new CheckersState(resultingBoard, nextPlayer, numberOfMoves);
		return resultingState;
	}
}
