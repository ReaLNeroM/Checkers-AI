public class Jump {
	private CoordinatePair initialPosition;
	private CoordinatePair targetPosition;
	boolean isCapture;

	/**
	 *
	 * @param initialPosition an int array with the row and col coordinates
	 * @param targetPosition also an int array
	 * @param isCapture true for capture move, false otherwise
	 * <h1>use isCapture() method to check if this move  is a capture move</h1>
	 * <h3>or I guess you could manually compute and see if the two locations are adjacent</h3>
	 */
	public Jump(CoordinatePair initialPosition, CoordinatePair targetPosition, boolean isCapture) {
		this.initialPosition = initialPosition;
		this.targetPosition = targetPosition;
		this.isCapture = isCapture;
	}

	public boolean isCapture() {
		return isCapture;
	}

	public CoordinatePair getInitialPosition() {
		return initialPosition;
	}

	public CoordinatePair getTargetPosition() {
		return targetPosition;
	}

	@Override
	public String toString() {
		return "Jump [initialPosition=" + initialPosition.toString() +
				", targetPosition=" + targetPosition.toString() +
				", isCapture=" + isCapture + "]";
	}
}
