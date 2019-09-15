import java.util.Arrays;

public class Move {
//	private Board start;
//	private Board finish;
	private int[] start;
	private int[] finish;
	private Piece target;
	private boolean type;//true = capture, false = move
	
	/**
	 * 
	 * @param start an int array with the row and col coordinates
	 * @param finish also an int array
	 * @param type true for capture move, false otherwise
	 * <h1>use isCapture() method to check if this move is a capture move</h1>
	 * <h3>or I guess you could manually compute and see if the two locations are adjacent</h3>
	 */
	public Move(int[] start, int[] finish, boolean type) {
		this.start = start;
		this.finish = finish;
		this.type = type;
	}
	
	public Move(int xStart, int yStart, int xFinish, int yFinish, boolean type) {
		this(new int[] {xStart, yStart}, new int[] {xFinish, yFinish}, type);
	}
	
	public boolean isCapture() {
		return type;
	}
	
	public int[] getStart() {
		return start;
	}
	
	public int[] getFinish() {
		return finish;
	}

	@Override
	public String toString() {
		return "Move [start=" + Arrays.toString(start) + ", finish=" + Arrays.toString(finish) + ", type=" + type + "]";
	}
	
}
