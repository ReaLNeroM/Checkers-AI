public class GameRunner {
	public static void main(String args[]) {
		// System.out.println("---number 4 test:");
		// Board b1 = new Board(4);
		// b1.printToConsoleTest();
		CheckersModel m = new CheckersModel();

		CheckersState initialState = m.getInitialState(4);

		System.out.println(initialState.toString());

		CheckersAction[] a = m.getActions(initialState);
		for(CheckersAction bb : a){
			CheckersState resultingState = m.getResult(initialState, bb);
			if(resultingState != null) {
				System.out.println(resultingState.toString());
			}
		}

		AI<CheckersState, CheckersAction> aiInstance = new AI<CheckersState, CheckersAction>();

		CheckersAction aa = aiInstance.randomPlay(m, initialState);
		CheckersState resultingState = m.getResult(initialState, aa);
		if(resultingState != null) {
			System.out.println(resultingState.toString());
		}
	}
}
