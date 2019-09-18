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
		if(boardSize == 4){
			//TODO: pass in 2D array.
		} else if(boardSize == 8){
			//TODO: pass in 2D array.
		} else {
			//TODO: raise error.
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
