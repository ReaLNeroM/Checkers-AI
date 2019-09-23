import java.util.Scanner;

public class GameRunner {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("enter a game to simulate: ");
		System.out.println("First Player: 0 for human player, 1 for random, 2 for miniMax, 3 for miniMaxAlphaBeta");
		String firstPlayer = scanner.nextLine();
		System.out.println("Second Player: 0 for human player, 1 for random, 2 for miniMax, 3 for miniMaxAlphaBeta");
		String secondPlayer = scanner.nextLine();

		CheckersModel model = new CheckersModel();

		CheckersState currentState = model.getInitialState(4);
		System.out.println(currentState.toString());

		AI<CheckersState, CheckersAction> aiInstance = new AI<CheckersState, CheckersAction>();

		CheckersAction nextAction;
		CheckersState resultingState;
		boolean isFirstPlayerMove = true;
		do {
			nextAction = makeMove(model, currentState, aiInstance, isFirstPlayerMove ? firstPlayer : secondPlayer);
			if (nextAction == null) {
				break;
			}

			resultingState = model.getResult(currentState, nextAction);
			if(resultingState != null) {
				System.out.println(resultingState.toString());
				currentState = resultingState;
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

	private static CheckersAction queryUser(CheckersModel model, CheckersState state){
		CheckersAction userAction = null;

		while (userAction == null){
			System.out.println("Enter a move: ");
			Scanner userMoveScanner = new Scanner(System.in);
			String moveString = userMoveScanner.nextLine();
			userAction = new CheckersAction(moveString);

			if(userAction == null){
				System.out.println("Input is not in valid checkers move notation.");
			} else if(model.getResult(state, userAction) == null){
				userAction = null;
				System.out.println("This move is not a valid checkers move for the current state. Is there a capture you missed?");
			}
		}

		return userAction;
	}

	public static CheckersAction makeMove(CheckersModel model, CheckersState state,
										  AI<CheckersState, CheckersAction> ai, String AIType) {
		CheckersAction action = null;
		switch(Integer.valueOf(AIType)){
			case 0:
				action = queryUser(model, state);
				break;
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
