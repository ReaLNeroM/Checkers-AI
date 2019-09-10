public interface GameLogic {
	boolean isValidMove();
	Action[] getValidMoves();
	double computeHeuristic(State s);
	State getResultingState(State s, Action a);
}