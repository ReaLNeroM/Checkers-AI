public interface Utility <StateClass, ActionClass> {
	int Utility(StateClass state);
	boolean isTerminal(StateClass state);
}
