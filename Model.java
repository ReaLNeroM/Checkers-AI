public interface Model {
	private Actions ActionsImplementation;
	private Result ResultImplementation;
	private Cost CostImplementation;
	private Utility UtilityImplementation;
	private Heuristic HeuristicImplementation;

	public Action[] getActions(State s);
	public State getResult(State s, Action a);
	public int getCost(State s, Action a);
	public int getHeuristic(State s);
	public int getUtility(State s);
}
