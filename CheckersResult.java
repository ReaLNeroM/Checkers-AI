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
		Color nextPlayer = Color.BLANK;
		if(s.getNextPlayerColor() == Color.W){
			nextPlayer = Color.B;
		} else {
			nextPlayer = Color.W;
		}

		CheckersState resultingState = new CheckersState(resultingBoard, nextPlayer, numberOfMoves);
		return resultingState;
	}
}
