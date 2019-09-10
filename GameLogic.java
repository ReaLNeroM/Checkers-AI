public interface GameLogic {
	boolean isValidMove(Action a);
	Action[] getValidMoves();
	double computeHeuristic(State s);
	State getResultingState(State s, Action a);
}