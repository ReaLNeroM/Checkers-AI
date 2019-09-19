public class Jump {
	private CoordinatePair initialPosition;
	private CoordinatePair targetPosition;
	private CoordinatePair capturePosition;

	/**
	 *
	 * @param initialPosition coordinatePair describing the starting position of the jump
	 * @param capturePosition coordinatePair or null, describing where a piece was captured
	 * @param targetPosition coordinatePair describing where the jump ends at
	 */
	public Jump(CoordinatePair initialPosition, CoordinatePair targetPosition) {
		this.initialPosition = initialPosition;
		this.targetPosition = targetPosition;
		this.capturePosition = null;
	}

	public Jump(
		CoordinatePair initialPosition, CoordinatePair targetPosition,
		CoordinatePair capturePosition
	) {
		this.initialPosition = initialPosition;
		this.targetPosition = targetPosition;
		this.capturePosition = capturePosition;
	}

	public boolean isCapture() {
		return (capturePosition != null);
	}

	public CoordinatePair getInitialPosition() {
		return initialPosition;
	}

	public CoordinatePair getTargetPosition() {
		return targetPosition;
	}

	public CoordinatePair getCapturePosition() {
		return capturePosition;
	}

	@Override
	public String toString() {
		if(capturePosition == null){
			return "Jump [initialPosition=" + initialPosition.toString() +
					", targetPosition=" + targetPosition.toString() +
					", capturePosition=" + capturePosition.toString() + "]";
		} else {
			return "Jump [initialPosition=" + initialPosition.toString() +
					", targetPosition=" + targetPosition.toString() + "]";
		}
	}
}
