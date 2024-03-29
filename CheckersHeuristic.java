import java.lang.Math;

public class CheckersHeuristic implements Heuristic <CheckersState, CheckersAction> {
    private Double getPieceValue(CoordinatePair location, Piece piece, Integer boardSize){
        Double pieceValue = 0.0;
        if(piece.isEmpty()){
            return pieceValue;
        }

        if(piece.getIsKing()){
            // Kings have much better mobility, so this is factored in as a doubling of value.
            pieceValue = 2.0;

            Integer distancefromHorizontalBorder = Math.min(location.getColumnNumber(),
                                                            boardSize - location.getColumnNumber());
            Integer distancefromVerticalBorder = Math.min(location.getRowNumber(),
                                                          boardSize - location.getRowNumber());

            Double mobilityValue = ((double) distancefromVerticalBorder / boardSize +
                                    (double) distancefromHorizontalBorder / boardSize) / 2;

            // A King piece in the middle is at peak mobility, so we value it higher.
            pieceValue += 0.5 * mobilityValue;
        } else {
            pieceValue = 1.0;

            // The closer a non-king piece is to promotion, the more we value it.

            Integer distanceFromPromotionRow;
            if(piece.getOwner().toInteger() == 1){
                distanceFromPromotionRow = (boardSize - 1) - location.getRowNumber();
            } else {
                distanceFromPromotionRow = location.getRowNumber();
            }

            pieceValue += 0.1 * distanceFromPromotionRow;
        }

        // Pieces belonging to the Minimizing player reduce a value's estimated utility.
        if(piece.getOwner().toInteger() == 2){
            pieceValue *= -1;
        }

        return pieceValue;
    }

    public Double Heuristic(CheckersState state){
//        parameters that could be useful:
//            num pieces on each side
//                included which one of those are kings
        Board board = state.getBoard();
        Piece[][] boardPieces = board.getPieces();

        Double totalValue = 0.0;
        for(int i = 0; i < boardPieces.length; i++){
            for(int j = 0; j < boardPieces[i].length; j++){
                totalValue += getPieceValue(new CoordinatePair(i, j), boardPieces[i][j],
                                            board.getSize());
            }
        }

        // Since the heuristic needs to be within [-1, 1], we normalize the piece values by the board area.
        return totalValue / (2 * board.getSize() * board.getSize());
    }
}