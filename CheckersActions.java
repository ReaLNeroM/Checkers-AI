import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Math;
import java.util.Queue;

public class CheckersActions implements Actions <CheckersState, CheckersAction> {
    private boolean verifyJump(Board board, Jump jump, CheckersState state){
        CoordinatePair initialPosition = jump.getInitialPosition();
        CoordinatePair targetPosition = jump.getTargetPosition();

        if(!board.withinBounds(initialPosition) || !board.withinBounds(targetPosition)) {
            return false;
        }

        if(!board.hasPiece(initialPosition)){
            return false;
        }

        Piece jumpingPiece = board.getPiece(initialPosition);
        if(!jumpingPiece.getOwner().equals(state.getNextPlayer())){
            return false;
        }
        if(board.hasPiece(jump.getTargetPosition())){
            return false;
        }

        if(jump.isCapture()){
            CoordinatePair capturePosition = jump.getCapturePosition();

            if(!board.hasPiece(capturePosition)){
                return false;
            }
            if(board.getPiece(capturePosition).getOwner().equals(state.getNextPlayer())){
                return false;
            }

            int deltaY = targetPosition.getRowNumber() - initialPosition.getRowNumber();
            int deltaX = targetPosition.getColumnNumber() - initialPosition.getColumnNumber();

            // captures must be done two steps diagonally
            if(Math.abs(deltaY) != 2 || Math.abs(deltaX) != 2){
                return false;
            }

            // Non-king pieces can only capture forwards
            if(!jumpingPiece.getIsKing()) {
                if(jumpingPiece.getOwner().toInteger() == 1 && deltaY != -2){
                    return false;
                } else if(jumpingPiece.getOwner().toInteger() == 2 && deltaY != 2) {
                    return false;
                }
            }
        } else {
            int deltaY = targetPosition.getRowNumber() - initialPosition.getRowNumber();
            int deltaX = targetPosition.getColumnNumber() - initialPosition.getColumnNumber();

            // skips must be done one step diagonally
            if(Math.abs(deltaY) != 1 || Math.abs(deltaX) != 1){
                return false;
            }

            // Non-king pieces can only skip forwards
            if(!jumpingPiece.getIsKing()) {
                if(jumpingPiece.getOwner().toInteger() == 1 && deltaY != -1){
                    return false;
                } else if(jumpingPiece.getOwner().toInteger() == 2 && deltaY != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isAtPromotionLocation(Board board, CoordinatePair coordinatePair){
        if(coordinatePair.getRowNumber() == 0){
            return true;
        }
        if(coordinatePair.getRowNumber() + 1 == board.getSize()){
            return true;
        }

        return false;
    }

    private Board resultingBoardAfterJump(Board board, Jump jump){
        Board resultingBoard = new Board(board);

        Piece jumpingPiece = resultingBoard.getPiece(jump.getInitialPosition());

        resultingBoard.setPiece(jump.getInitialPosition(), new Piece(new Player(0)));
        if(jump.isCapture()){
            resultingBoard.setPiece(jump.getCapturePosition(), new Piece(new Player(0)));
        }

        // note the tricky promotion logic here: a black piece can exist on the first row without
        // getting promoted. It's once a piece jumps that the promotion logic triggers.
        Piece newPiece = null;
        newPiece = new Piece(new Player(jumpingPiece.getOwner()),
                             jumpingPiece.getIsKing() ||
                             isAtPromotionLocation(board, jump.getTargetPosition()));
        resultingBoard.setPiece(jump.getTargetPosition(), newPiece);

        return resultingBoard;
    }

    public Board resultingBoardAfterPartialAction(CheckersState state,
                                                  CheckersAction partialAction){
        Board currentBoard = state.getBoard();

        for(Jump jump : partialAction.getJumps()){
            if(!verifyJump(currentBoard, jump, state)){
                return null;
            }

            currentBoard = resultingBoardAfterJump(currentBoard, jump);
        }

        return currentBoard;
    }

    public CheckersAction[] getCaptures(CheckersState state, CheckersAction partialAction){
        if(partialAction.getNumberOfJumps() != partialAction.getNumberOfCaptures()) {
            // Captures can't be done after a skip
            return new CheckersAction[0];
        }

        Board currentBoard = resultingBoardAfterPartialAction(state, partialAction);
        ArrayList<CoordinatePair> captureInitialPositions = new ArrayList<CoordinatePair>();

        if(partialAction.getNumberOfJumps() != 0){
            Jump lastJump = partialAction.getJumps()[partialAction.getJumps().length - 1];
            CoordinatePair initialPosition = new CoordinatePair(lastJump.getTargetPosition());
            captureInitialPositions.add(initialPosition);
        } else {
            for(int i = 0; i < currentBoard.getSize(); i++){
                for(int j = 0; j < currentBoard.getSize(); j++){
                    CoordinatePair initialPosition = new CoordinatePair(i, j);
                    captureInitialPositions.add(initialPosition);
                }
            }
        }

        ArrayList<CheckersAction> validCaptures = new ArrayList<CheckersAction>();
        for(CoordinatePair initialPosition : captureInitialPositions){
            for(int deltaY = -2; deltaY <= 2; deltaY += 4){
                for(int deltaX = -2; deltaX <= 2; deltaX += 4){
                    CoordinatePair targetPosition = new CoordinatePair(
                        initialPosition.getRowNumber() + deltaY,
                        initialPosition.getColumnNumber() + deltaX
                    );
                    CoordinatePair capturePosition = new CoordinatePair(
                        initialPosition.getRowNumber() + deltaY / 2,
                        initialPosition.getColumnNumber() + deltaX / 2
                    );

                    Jump jumpAttempt = new Jump(
                        initialPosition, targetPosition, capturePosition
                    );
                    if(verifyJump(currentBoard, jumpAttempt, state)){
                        Jump[] resultingJumps = new Jump[partialAction.getNumberOfJumps() + 1];

                        Jump[] originalJumps = partialAction.getJumps();
                        for(int k = 0; k < originalJumps.length; k++){
                            resultingJumps[k] = originalJumps[k];
                        }
                        resultingJumps[partialAction.getNumberOfJumps()] = jumpAttempt;

                        validCaptures.add(new CheckersAction(resultingJumps));
                    }
                }
            }
        }

        return validCaptures.toArray(new CheckersAction[validCaptures.size()]);
    }

    public CheckersAction[] getSkips(CheckersState state, CheckersAction partialAction){
        if(partialAction.getNumberOfJumps() != 0){
            // Skips must be the first move
            return new CheckersAction[0];
        }

        Board currentBoard = resultingBoardAfterPartialAction(state, partialAction);

        ArrayList<CheckersAction> possibleSkips = new ArrayList<CheckersAction>();
        for(int i = 0; i < currentBoard.getSize(); i++){
            for(int j = 0; j < currentBoard.getSize(); j++){
                CoordinatePair initialPosition = new CoordinatePair(i, j);
                for(int deltaY = -1; deltaY <= 1; deltaY += 2){
                    for(int deltaX = -1; deltaX <= 1; deltaX += 2){
                        CoordinatePair targetPosition = new CoordinatePair(
                            i + deltaY, j + deltaX
                        );

                        Jump jumpAttempt = new Jump(initialPosition, targetPosition);
                        if(verifyJump(currentBoard, jumpAttempt, state)){
                            possibleSkips.add(new CheckersAction(new Jump[]{jumpAttempt}));
                        }
                    }
                }
            }
        }

        return possibleSkips.toArray(new CheckersAction[possibleSkips.size()]);
    }

    public CheckersAction[] Actions(CheckersState state){
        ArrayList<CheckersAction> validMoves = new ArrayList<CheckersAction>();
        Queue<CheckersAction> partialMoveFrontier = new LinkedList<CheckersAction>();
        partialMoveFrontier.add(new CheckersAction(new Jump[0]));

        int mostCapturesPossible = 0;
        while(!partialMoveFrontier.isEmpty()){
            CheckersAction currentPartialAction = partialMoveFrontier.remove();

            if(currentPartialAction.getNumberOfCaptures() > mostCapturesPossible){
                validMoves.clear();
                mostCapturesPossible = currentPartialAction.getNumberOfCaptures();
            }
            if(
                currentPartialAction.getNumberOfJumps() != 0 &&
                currentPartialAction.getNumberOfCaptures() >= mostCapturesPossible
            ){
                validMoves.add(currentPartialAction);
            }

            CheckersAction[] captures = getCaptures(state, currentPartialAction);
            CheckersAction[] skips = getSkips(state, currentPartialAction);

            for(CheckersAction captureAction : captures){
                partialMoveFrontier.add(captureAction);
            }

            if(captures.length == 0){
                for(CheckersAction skipAction : skips){
                    partialMoveFrontier.add(skipAction);
                }
            }
        }

        return validMoves.toArray(new CheckersAction[validMoves.size()]);
    }
}