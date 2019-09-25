public class CheckersModel implements Model <CheckersState, CheckersAction> {
    Actions<CheckersState, CheckersAction> ActionsImplementation;
    Result<CheckersState, CheckersAction> ResultImplementation;
    Cost<CheckersState, CheckersAction> CostImplementation;
    Utility<CheckersState, CheckersAction> UtilityImplementation;
    Heuristic<CheckersState, CheckersAction> HeuristicImplementation;

    public CheckersModel(){
        this.ActionsImplementation = new CheckersActions();
        this.ResultImplementation = new CheckersResult();
        this.CostImplementation = new CheckersCost();
        this.UtilityImplementation = new CheckersUtility();
        this.HeuristicImplementation = new CheckersHeuristic();
    }

    public CheckersState getInitialState(int boardSize){
        Piece[][] boardPieces = new Piece[boardSize][boardSize];

        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                boardPieces[i][j] = new Piece(new Player(0));
            }
        }

        for(int i = 0; i < (boardSize/2)-1; i++) {
            for(int j = 0; j < boardSize; j++) {
                if(i%2==0) {
                    if(j%2!=0) {
                        boardPieces[i][j] = new Piece(new Player(2));
                    }
                }else {
                    if(j%2==0) {
                        boardPieces[i][j] = new Piece(new Player(2));
                    }
                }
            }
        }//black side
        for(int i = boardSize-1; i >= (boardSize/2)+1; i--) {
            for(int j = 0; j < boardSize; j++) {
                if(i%2!=0) {
                    if(j%2==0) {
                        boardPieces[i][j] = new Piece(new Player(1));
                    }
                }else {
                    if(j%2!=0) {
                        boardPieces[i][j] = new Piece(new Player(1));
                    }
                }
            }
        }//white side

        if(boardSize == 4){
            return new CheckersState(new Board(boardPieces), new Player(1));
        } else if(boardSize == 8){
            return new CheckersState(new Board(boardPieces), new Player(1));
        }

        return null;
    }

    public CheckersAction[] getActions(CheckersState state){
        return this.ActionsImplementation.Actions(state);
    }
    public CheckersState getResult(CheckersState state, CheckersAction action){
        return this.ResultImplementation.Result(state, action);
    }
    public Integer getCost(CheckersState state, CheckersAction action){
        return this.CostImplementation.Cost(state, action);
    }
    public Integer getUtility(CheckersState state){
        return this.UtilityImplementation.Utility(state);
    }
    public Double getHeuristic(CheckersState state){
        return this.HeuristicImplementation.Heuristic(state);
    }
    public boolean getIsTerminal(CheckersState state) {
        return UtilityImplementation.isTerminal(state);
    }

    public Actions<CheckersState, CheckersAction> getActionsImplementation() {
        return ActionsImplementation;
    }

    public Result<CheckersState, CheckersAction> getResultImplementation() {
        return ResultImplementation;
    }

    public Cost<CheckersState, CheckersAction> getCostImplementation() {
        return CostImplementation;
    }

    public Utility<CheckersState, CheckersAction> getUtilityImplementation() {
        return UtilityImplementation;
    }

    public Heuristic<CheckersState, CheckersAction> getHeuristicImplementation() {
        return HeuristicImplementation;
    }
}
