public interface Heuristic <StateClass extends State, ActionClass extends Action> {
    Double Heuristic(StateClass state);
}
