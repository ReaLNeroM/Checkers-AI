public interface Model <StateClass extends State, ActionClass extends Action> {
    public ActionClass[] getActions(StateClass state);
    public StateClass getResult(StateClass state, ActionClass action);
    public Integer getCost(StateClass state, ActionClass action);
    public Double getHeuristic(StateClass state);
    public Integer getUtility(StateClass state);
    public boolean getIsTerminal(StateClass state);
}
