import java.util.Pair;

public class CheckersAction implements Action {
	private Move[] intermediateMoves;

	private String coordinatesToString(Pair<Integer, Integer> coordinates){
		Character rowCharacter = 'A' + v
		return
	}

	private Move[] decodeMoveString(String moveDescription){
		String[] namesOfCellsInMove = moveDescription.split("x|-");

		Pair<Integer, Integer>[] coordinatesOfCellsInMove =
			new ArrayList<Pair<Integer, Integer>>();

		for(String nameOfCell : namesOfCellsInMove){
			int rowNumber = nameOfCell.get(0).getNumericValue() - 'A'.getNumericValue()
			int columnNumber = nameOfCell.get(0).getNumericValue() - '1'.getNumericValue();
			coordinatesOfCellsInMove.append(Pair(rowNumber, columnNumber))
		}

		return coordinatesOfCellsInMove;
	}

	CheckersAction(Move[] intermediateMoves){
		this.intermediateMoves = intermediateMoves;
	}

	CheckersAction(String moveDescription){
		this.intermediateMoves = decodeMoveString(moveDescription);
	}

	public String toString(){

	}

	public int getNumberOfCaptures(){
		return intermediateMoves.length;
	}
}
