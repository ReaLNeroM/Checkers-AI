public interface Utility <StateClass extends State, ActionClass extends Action> {
    int Utility(StateClass state);
    boolean isTerminal(StateClass state);
}
