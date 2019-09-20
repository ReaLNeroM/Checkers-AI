public interface Model <StateClass, ActionClass> {

	//TODO: to be deleted
	//	Actions ActionsImplementation;
	//	Result ResultImplementation;
	//	Cost CostImplementation;
	//	Utility UtilityImplementation;
	//	Heuristic HeuristicImplementation;

	public ActionClass[] getActions(StateClass s);
	public StateClass getResult(StateClass s, ActionClass a);
	public int getCost(StateClass s, ActionClass a);
	public int getHeuristic(StateClass s);
	public int getUtility(StateClass s);
}
