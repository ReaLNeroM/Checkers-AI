public interface Action {
	String toString();
	State getStartState();
	State getEndState();
}
