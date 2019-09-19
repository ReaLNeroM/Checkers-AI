import java.util.ArrayList;
import java.lang.StringBuilder;

public class CheckersAction implements Action {
	private Jump[] intermediateJumps;

	private Jump[] decodeJumpString(String JumpString){
		String[] namesOfCellsInJump = JumpString.split("x|-");

		ArrayList<CoordinatePair> jumpCoordinates = new ArrayList<CoordinatePair>();

		for(String coordinatesOfCell : namesOfCellsInJump){
			jumpCoordinates.add(new CoordinatePair(coordinatesOfCell));
		}

		ArrayList<Jump> intermediateJumps = new ArrayList<Jump>();
		for(int i = 0; i < jumpCoordinates.size() - 1; i++){
			// Every 3rd character starting from the second one is either '-' or 'x', indicating
			// either a normal jump or a capture jump respectively.
			Character isCaptureCharacter = JumpString.charAt(3 * i + 2);
			boolean isCapture = (isCaptureCharacter == 'x');
			CoordinatePair captureCoordinates = new CoordinatePair(
				(jumpCoordinates.get(i).getFirst() + jumpCoordinates.get(i + 1).getFirst()) / 2,
				(jumpCoordinates.get(i).getSecond() + jumpCoordinates.get(i + 1).getSecond()) / 2
			);

			Jump nextJump = null;
			if(isCapture){
				intermediateJumps.add(new Jump(
					jumpCoordinates.get(i),
					jumpCoordinates.get(i + 1),
					captureCoordinates
				));
			} else {
				intermediateJumps.add(new Jump(
					jumpCoordinates.get(i),
					jumpCoordinates.get(i + 1)
				));
			}
		}

		return intermediateJumps.toArray(new Jump[intermediateJumps.size()]);
	}

	CheckersAction(Jump[] intermediateJumps){
		this.intermediateJumps = intermediateJumps;
	}

	CheckersAction(String JumpString){
		this.intermediateJumps = decodeJumpString(JumpString);
	}

	public Jump[] getJumps(){
		return intermediateJumps;
	}

	public int getNumberOfJumps(){
		return intermediateJumps.length;
	}

	public String toString(){
		StringBuilder actionString = new StringBuilder("");

		actionString.append(intermediateJumps[0].getInitialPosition().toString());

		for(Jump currentJump : intermediateJumps){
			if(currentJump.isCapture()){
				actionString.append("x");
			} else {
				actionString.append("-");
			}

			actionString.append(currentJump.getTargetPosition().toString());
		}

		return actionString.toString();
	}

	public Integer getNumberOfCaptures(){
		int captures = 0;
		for(Jump currentJump : intermediateJumps){
			captures += currentJump.isCapture() ? 1 : 0;
		}

		return Integer.valueOf(captures);
	}
}
