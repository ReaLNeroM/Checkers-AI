public interface Heuristic <StateClass extends State, ActionClass extends Action> {
	Integer Heuristic(StateClass state);
}
