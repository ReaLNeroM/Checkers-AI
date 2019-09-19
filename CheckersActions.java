import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Math;
import java.util.Queue;

public class CheckersActions implements Actions <CheckersState, CheckersAction> {
	private boolean verifyJump(Board b, Jump j, CheckersState s){
		CoordinatePair initialPosition = j.getInitialPosition();
		CoordinatePair targetPosition = j.getTargetPosition();

		if(!b.hasPiece(initialPosition)){
			return false;
		}

		Piece jumpingPiece = b.getPiece(initialPosition);
		if(jumpingPiece.getColor() != s.getNextPlayerColor()){
			return false;
		}
		if(b.hasPiece(j.getTargetPosition())){
			return false;
		}

		if(j.isCapture()){
			CoordinatePair capturePosition = j.getCapturePosition();

			if(!b.hasPiece(capturePosition)){
				return false;
			}
			if(b.getPiece(capturePosition).getColor() == s.getNextPlayerColor()){
				return false;
			}

			int deltaY = targetPosition.getFirst() - initialPosition.getFirst();
			int deltaX = targetPosition.getSecond() - initialPosition.getSecond();

			// captures must be done two steps diagonally
			if(Math.abs(deltaY) != 2 || Math.abs(deltaX) != 2){
				return false;
			}

			// Non-king pieces can only capture forwards
			if(!jumpingPiece.getStatus() && deltaY != 2){
				return false;
			}
		} else {
			int deltaY = targetPosition.getFirst() - initialPosition.getFirst();
			int deltaX = targetPosition.getSecond() - initialPosition.getSecond();

			// skips must be done one step diagonally
			if(Math.abs(deltaY) != 1 || Math.abs(deltaX) != 1){
				return false;
			}

			// Non-king pieces can only skip forwards
			if(!jumpingPiece.getStatus() && deltaY != 1){
				return false;
			}
		}

		return true;
	}

	private Board resultingBoardAfterJump(Board b, Jump j){
		Board resultingBoard = new Board(b.getBoard());

		Piece jumpingPiece = b.getPiece(j.getInitialPosition());

		resultingBoard.setPiece(j.getInitialPosition(), null);
		if(j.isCapture()){
			resultingBoard.setPiece(j.getCapturePosition(), null);
		}
		//TODO: promote
		resultingBoard.setPiece(j.getTargetPosition(), jumpingPiece);

		return resultingBoard;
	}

	public Board resultingBoardAfterPartialAction(CheckersState s, CheckersAction partialAction){
		Board currentBoard = s.getBoard();

		for(Jump j : partialAction.getJumps()){
			if(!verifyJump(currentBoard, j, s)){
				return null;
			}

			currentBoard = resultingBoardAfterJump(currentBoard, j);
		}

		return currentBoard;
	}

	public CheckersAction[] getCaptures(CheckersState s, CheckersAction partialAction){
		Board currentBoard = resultingBoardAfterPartialAction(s, partialAction);

		ArrayList<CheckersAction> possibleCaptures = new ArrayList<CheckersAction>();
		for(int i = 0; i < currentBoard.getSize(); i++){
			for(int j = 0; j < currentBoard.getSize(); j++){
				CoordinatePair initialPosition = new CoordinatePair(i, j);
				for(int deltaY = -2; deltaY <= 2; deltaY += 4){
					for(int deltaX = -2; deltaX <= 2; deltaX += 4){
						CoordinatePair targetPosition = new CoordinatePair(
							i + deltaY, j + deltaX
						);
						CoordinatePair capturePosition = new CoordinatePair(
							i + deltaY / 2, j + deltaX / 2
						);

						Jump jumpAttempt = new Jump(
							initialPosition, targetPosition, capturePosition
						);
						if(verifyJump(currentBoard, jumpAttempt, s)){
							Jump[] resultingJumps = new Jump[partialAction.getNumberOfJumps() + 1];

							Jump[] originalJumps = partialAction.getJumps();
							for(int k = 0; k < originalJumps.length; k++){
								resultingJumps[k] = originalJumps[k];
							}
							resultingJumps[partialAction.getNumberOfJumps()] = jumpAttempt;

							possibleCaptures.add(new CheckersAction(resultingJumps));
						}
					}
				}
			}
		}

		return possibleCaptures.toArray(new CheckersAction[possibleCaptures.size()]);
	}

	public CheckersAction[] getSkips(CheckersState s, CheckersAction partialAction){
		if(partialAction.getNumberOfJumps() != 0){
			// Skips must be the first move
			return new CheckersAction[0];
		}

		Board currentBoard = resultingBoardAfterPartialAction(s, partialAction);

		ArrayList<CheckersAction> possibleCaptures = new ArrayList<CheckersAction>();
		for(int i = 0; i < currentBoard.getSize(); i++){
			for(int j = 0; j < currentBoard.getSize(); j++){
				CoordinatePair initialPosition = new CoordinatePair(i, j);
				for(int deltaY = -1; deltaY <= 1; deltaY += 2){
					for(int deltaX = -1; deltaX <= 1; deltaX += 2){
						CoordinatePair targetPosition = new CoordinatePair(
							i + deltaY, j + deltaX
						);

						Jump jumpAttempt = new Jump(initialPosition, targetPosition);
						if(verifyJump(currentBoard, jumpAttempt, s)){
							possibleCaptures.add(new CheckersAction(new Jump[]{jumpAttempt}));
						}
					}
				}
			}
		}

		return possibleCaptures.toArray(new CheckersAction[possibleCaptures.size()]);
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