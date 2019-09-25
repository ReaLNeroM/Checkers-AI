public class CheckersResult implements Result <CheckersState, CheckersAction> {
    private CheckersActions checkersActionsImplementation;

    public CheckersResult(){
        checkersActionsImplementation = new CheckersActions();
    }

    public CheckersState Result(CheckersState state, CheckersAction action){
        CheckersAction[] validActions = checkersActionsImplementation.Actions(state);

        boolean isValid = false;
        for(CheckersAction validAction : validActions){
            if(action.equals(validAction)){
                isValid = true;
            }
        }

        if(!isValid){
            return null;
        }

        Board resultingBoard = checkersActionsImplementation.resultingBoardAfterPartialAction(
            state, action
        );
        Integer numberOfMoves = state.getNumberOfMovesDone() + 1;
        Player nextPlayer = state.getNextPlayer().getOppositePlayer();

        CheckersState resultingState = new CheckersState(resultingBoard, nextPlayer, numberOfMoves);
        return resultingState;
    }
}
