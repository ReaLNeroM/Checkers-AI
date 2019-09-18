import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class CheckersActions implements Actions <CheckersState, CheckersAction> {
	private Board resultingBoardAfterPartialAction(CheckersState s, CheckersAction partialAction){
		Board currentBoard = s.getBoard();

		for(Jump a : partialAction.getJumps()){
			//TODO

		}

		return currentBoard;
	}

	public CheckersAction[] getCaptures(CheckersState s, CheckersAction partialAction){
		return null;
		//TODO
	}

	public CheckersAction[] getSkips(CheckersState s, CheckersAction partialAction){
		if(partialAction.getNumberOfJumps() != 0){
			// Skips must be the first move
			return new CheckersAction[0];
		}

		return null;
		//TODO
	}

	public CheckersAction[] Actions(CheckersState s){
		ArrayList<CheckersAction> validMoves = new ArrayList<CheckersAction>();

		Queue<CheckersAction> partialMoveFrontier = new LinkedList<CheckersAction>();
		partialMoveFrontier.add(new CheckersAction(new Jump[0]));

		int mostCapturesPossible = 0;
		while(!partialMoveFrontier.isEmpty()){
			CheckersAction currentPartialAction = partialMoveFrontier.remove();

			if(currentPartialAction.getNumberOfCaptures() > mostCapturesPossible){
				validMoves.clear();
			}
			if(currentPartialAction.getNumberOfCaptures() >= mostCapturesPossible){
				validMoves.add(currentPartialAction);
			}

			CheckersAction[] captures = getCaptures(s, currentPartialAction);
			CheckersAction[] skips = getSkips(s, currentPartialAction);

			if(captures.length != 0){
				for(CheckersAction a : captures){
					partialMoveFrontier.add(a);
				}
			} else if(skips.length == 0 && captures.length == 0){
				for(CheckersAction a : skips){
					partialMoveFrontier.add(a);
				}
			}
		}

		return validMoves.toArray(new CheckersAction[validMoves.size()]);
	}
}