public class CheckersAction implements Action {
	private State[] intermediateStates;

	private ArrayList<Pair<Integer, Integer>> decodeMoveString(String moveDescription){
		String[] namesOfCellsInMove = moveDescription.split("x|-");

		ArrayList<Pair<Integer, Integer>> coordinatesOfCellsInMove =
			new ArrayList<Pair<Integer, Integer>>();

		for(String nameOfCell : namesOfCellsInMove){
			int rowNumber = nameOfCell.get(0).getNumericValue() - 'A'.getNumericValue()
			int columnNumber = nameOfCell.get(0).getNumericValue() - '1'.getNumericValue();
			coordinatesOfCellsInMove.append(Pair(rowNumber, columnNumber))
		}

		return coordinatesOfCellsInMove;
	}

	CheckersAction(State[] intermediateStates){
		this.intermediateStates = intermediateStates;
	}

	CheckersAction(State startingState, String moveDescription){
		Board currentBoard = startingState.getBoard();

		ArrayList<Pair<Integer, Integer>> coordinatesOfCellsInMove = decodeMoveString(moveDescription);

		ArrayList<State> intermediateStates;
		for(int i = 0; i < coordinatesOfCellsInMove.length() - 1; i++){
			Pair<Integer, Integer>
		}

		this.intermediateStates = intermediateStates.toArray();
	}

	public String toString(){

	}

	boolean isValid(){

	}

	public State getStartState(){
		return intermediateStates[0];
	}

	public State getEndState(){
		return intermediateStates[intermediateStates.length() - 1];
	}
}
