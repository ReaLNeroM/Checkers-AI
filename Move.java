import java.util.Pair;

public class Move {
	private Pair<Integer, Integer> initialPosition;
	private Pair<Integer, Integer> targetPosition;
	boolean isCapture;

	/**
	 *
	 * @param initialPosition an int array with the row and col coordinates
	 * @param targetPosition also an int array
	 * @param isCapture true for capture move, false otherwise
	 * <h1>use isCapture() method to check if this move  is a capture move</h1>
	 * <h3>or I guess you could manually compute and see if the two locations are adjacent</h3>
	 */
	public Move(Pair<Integer, Integer> initialPosition, Pair<Integer, Integer> targetPosition, boolean isCapture) {
		this.initialPosition = initialPosition;
		this.targetPosition = targetPosition;
		this.isCapture = isCapture;
	}

	public boolean isCapture() {
		return isCapture;
	}

	public Pair<Integer, Integer> getInitialPosition() {
		return initialPosition;
	}

	public Pair<Integer, Integer> getTargetPosition() {
		return targetPosition;
	}

	@Override
	public String toString() {
		return "Move [initialPosition=" + Pair.toString(initialPosition) +
				", targetPosition=" + Pair.toString(targetPosition) +
				", isCapture=" + isCapture + "]";
	}
}
