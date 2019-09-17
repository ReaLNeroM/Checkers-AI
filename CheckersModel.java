public class CheckersModel implements Model {
	Actions ActionsImplementation;
	Result ResultImplementation;
	Cost CostImplementation;
	Utility UtilityImplementation;
	Heuristic HeuristicImplementation;

	public CheckersModel(){
		this.ActionsImplementation = new CheckersActions();
		this.ResultImplementation = new CheckersResult();
		this.CostImplementation = new CheckersCost();
		this.UtilityImplementation = new CheckersUtility();
		this.HeuristicImplementation = new CheckersHeuristic();
	}

	public State getInitialState(int boardSize){
		if(boardSize == 4){
			//TODO: pass in 2D array.
		} else if(boardSize == 8){
			//TODO: pass in 2D array.
		} else {
			//TODO: raise error.
		}
	}

	public Action[] getActions(State s){
		return this.ActionsImplementation.Actions(s);
	}
	public State getResult(State s, Action a){
		return this.ResultImplementation.Result(s, a);
	}
	public int getCost(State s, Action a){
		return this.CostImplementation.Cost(s, a);
	}
	public int getUtility(State s){
		return this.UtilityImplementation.Utility(s);
	}
	public int getHeuristic(State s){
		return this.HeuristicImplementation.Heuristic(s);
	}
}
