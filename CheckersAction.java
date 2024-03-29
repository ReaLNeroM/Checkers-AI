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

            if(isCapture){
                // If a move is a capture, we know the capture happened in the cell between the
                // initialPosition and targetPosition.
                Integer rowNumberAverage = (jumpCoordinates.get(i).getRowNumber() +
                                            jumpCoordinates.get(i + 1).getRowNumber()) / 2;
                Integer columnNumberAverage = (jumpCoordinates.get(i).getColumnNumber() +
                                                jumpCoordinates.get(i + 1).getColumnNumber()) / 2;
                CoordinatePair captureCoordinates = new CoordinatePair(
                    rowNumberAverage, columnNumberAverage
                );

                intermediateJumps.add(new Jump(
                    jumpCoordinates.get(i), jumpCoordinates.get(i + 1), captureCoordinates
                ));
            } else {
                intermediateJumps.add(new Jump(
                    jumpCoordinates.get(i), jumpCoordinates.get(i + 1)
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

    public Integer getNumberOfCaptures(){
        int captures = 0;
        for(Jump currentJump : intermediateJumps){
            captures += currentJump.isCapture() ? 1 : 0;
        }

        return Integer.valueOf(captures);
    }

    public boolean equals(CheckersAction comparisonAction) {
        if(getNumberOfJumps() != comparisonAction.getNumberOfJumps()) {
            return false;
        }

        for(int i = 0; i < intermediateJumps.length; i++) {
            if (!intermediateJumps[i].equals(comparisonAction.getJumps()[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString(){
        StringBuilder actionString = new StringBuilder("");

        if(intermediateJumps.length >= 1) {
            actionString.append(intermediateJumps[0].getInitialPosition().toString());
        }

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
}
