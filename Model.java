public interface Model <StateClass extends State, ActionClass extends Action> {

	//TODO: to be deleted
	//	Actions ActionsImplementation;
	//	Result ResultImplementation;
	//	Cost CostImplementation;
	//	Utility UtilityImplementation;
	//	Heuristic HeuristicImplementation;

	public ActionClass[] getActions(StateClass state);
	public StateClass getResult(StateClass state, ActionClass action);
	public Integer getCost(StateClass state, ActionClass action);
	public Integer getHeuristic(StateClass state);
	public Integer getUtility(StateClass state);
	public boolean getIsTerminal(StateClass state);
}
