public interface Model {
	
	//TODO: to be deleted
	//	Actions ActionsImplementation;
	//	Result ResultImplementation;
	//	Cost CostImplementation;
	//	Utility UtilityImplementation;
	//	Heuristic HeuristicImplementation;

	public Action[] getActions(State s);
	public State getResult(State s, Action a);
	public int getCost(State s, Action a);
	public int getHeuristic(State s);
	public int getUtility(State s);
}
