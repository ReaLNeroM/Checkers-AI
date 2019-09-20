public class GameRunner {
	public static void main(String args[]) {
		// System.out.println("---number 4 test:");
		// Board b1 = new Board(4);
		// b1.printToConsoleTest();
		CheckersModel model = new CheckersModel();

		CheckersState currentState = model.getcurrentState(4);

		System.out.println(currentState.toString());

		// CheckersAction[] a = model.getActions(currentState);
		// for(CheckersAction bb : a){
		// 	CheckersState resultingState = model.getResult(currentState, bb);
		// 	if(resultingState != null) {
		// 		System.out.println(resultingState.toString());
		// 	}
		// }

		AI<CheckersState, CheckersAction> aiInstance = new AI<CheckersState, CheckersAction>();

		for(int i = 0; i < 200; i++){
			CheckersAction nextAction = aiInstance.randomPlay(model, currentState);
			CheckersState resultingState = model.getResult(currentState, nextAction);
			if(resultingState != null) {
				System.out.println(resultingState.toString());
			} else {
				break;
			}
			currentState = resultingState;
		}

		if(model.getUtility(currentState) == 1) {
			System.out.println("Win for the first player!");
		} else if(model.getUtility(currentState) == -1){
			System.out.println("Win for the second player!");
		} else {
			System.out.println("Tie!");
		}
	}
}
