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
				boardPieces[i][j] = new Piece(new Color(0));
			}
		}

		for(int i = 0; i < (boardSize/2)-1; i++) {
			for(int j = 0; j < boardSize; j++) {
				if(i%2==0) {
					if(j%2!=0) {
						boardPieces[i][j] = new Piece(new Color(2));
					}
				}else {
					if(j%2==0) {
						boardPieces[i][j] = new Piece(new Color(2));
					}
				}
			}
		}//black side
		for(int i = boardSize-1; i >= (boardSize/2)+1; i--) {
			for(int j = 0; j < boardSize; j++) {
				if(i%2!=0) {
					if(j%2==0) {
						boardPieces[i][j] = new Piece(new Color(1));
					}
				}else {
					if(j%2!=0) {
						boardPieces[i][j] = new Piece(new Color(1));
					}
				}
			}
		}//white side

		if(boardSize == 4){
			return new CheckersState(new Board(boardPieces), new Color(1));
		} else if(boardSize == 8){
			return new CheckersState(new Board(boardPieces), new Color(1));
		}

		return null;
	}

	public CheckersAction[] getActions(CheckersState s){
		return this.ActionsImplementation.Actions(s);
	}
	public CheckersState getResult(CheckersState s, CheckersAction a){
		return this.ResultImplementation.Result(s, a);
	}
	public int getCost(CheckersState s, CheckersAction a){
		return this.CostImplementation.Cost(s, a);
	}
	public int getUtility(CheckersState s){
		return this.UtilityImplementation.Utility(s);
	}
	public int getHeuristic(CheckersState s){
		return this.HeuristicImplementation.Heuristic(s);
	}
}
