public interface Cost <StateClass extends State, ActionClass extends Action> {
    int Cost(StateClass state, ActionClass action);
}
