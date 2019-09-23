import java.util.Scanner;

public class GameRunner {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("enter a game to simulate: ");
		System.out.println("First Player: 1 for random, 2 for miniMax, 3 for miniMaxAlphaBeta");
		String firstPlayer = scanner.nextLine();
		System.out.println("Second Player: 1 for random, 2 for miniMax, 3 for miniMaxAlphaBeta");
		String secondPlayer = scanner.nextLine();
		
		CheckersModel model = new CheckersModel();

		CheckersState currentState = model.getInitialState(4);

		System.out.println(currentState.toString());

		AI<CheckersState, CheckersAction> aiInstance = new AI<CheckersState, CheckersAction>();

		CheckersAction nextAction;
		CheckersState resultingState;
		boolean isFirstPlayerMove = true;
		do {
			if(isFirstPlayerMove) {
				nextAction = makeMove(model, currentState, aiInstance, firstPlayer);				
			}else {
				nextAction = makeMove(model, currentState, aiInstance, secondPlayer);
			}
			resultingState = model.getResult(currentState, nextAction);
			if(resultingState != null) {
				System.out.println(resultingState.toString());
			} else {
				break;
			}
			currentState = resultingState;
			isFirstPlayerMove = !isFirstPlayerMove;
		}while(resultingState != null);


		if(model.getUtility(currentState) == 1) {
			System.out.println("Win for the first player!");
		} else if(model.getUtility(currentState) == -1){
			System.out.println("Win for the second player!");
		} else {
			System.out.println("Tie!");
		}
	}
	
	public static CheckersAction makeMove(CheckersModel model, CheckersState state, AI<CheckersState, CheckersAction> ai, String AIType) {
		CheckersAction action = null;
		switch(Integer.valueOf(AIType)){
			case 1:
				action = ai.randomPlay(model, state);
				break;
				
			case 2:
				action = ai.miniMax(model, state);
				break;
				
			case 3:
				action = ai.miniMax_a_b(model, state);
				break;
				
		}
		return action;
	}
}
