public class GameRunner {
	public static void main(String args[]) {

		CheckersModel model = new CheckersModel();

		CheckersState currentState = model.getInitialState(4);

		System.out.println(currentState.toString());


		AI<CheckersState, CheckersAction> aiInstance = new AI<CheckersState, CheckersAction>();

		for(int i = 0; i < 200; i++){
			CheckersAction nextAction = aiInstance.miniMax(model, currentState);
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
