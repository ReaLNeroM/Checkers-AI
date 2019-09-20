public interface Model <StateClass, ActionClass> {

	//TODO: to be deleted
	//	Actions ActionsImplementation;
	//	Result ResultImplementation;
	//	Cost CostImplementation;
	//	Utility UtilityImplementation;
	//	Heuristic HeuristicImplementation;

	public ActionClass[] getActions(StateClass state);
	public StateClass getResult(StateClass state, ActionClass action);
	public int getCost(StateClass state, ActionClass action);
	public int getHeuristic(StateClass state);
	public int getUtility(StateClass state);
}
