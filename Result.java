public interface Result <StateClass extends State, ActionClass extends Action> {
    StateClass Result(StateClass state, ActionClass action);
}
