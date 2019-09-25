import java.util.Scanner;

public class CheckersGame {
    private enum Agent {
        Human,
        Random,
        Minimax,
        MinimaxAlphaBeta,
        MinimaxAlphaBetaHeuristic
    }

    private Agent firstPlayer = null;
    private Agent secondPlayer = null;

    private CheckersModel model = null;
    private CheckersState currentState = null;
    private AI<CheckersState, CheckersAction> aiInstance = null;
    private Scanner scanner = null;

    private Agent promptUserForAgent(){
        System.out.println("0 for human player, 1 for random, 2 for minimax," +
                           " 3 for minimax-AlphaBeta, 4 for minimax-AlphaBeta-Heuristic");

        Agent playerAgent = null;
        Integer playerNumber = scanner.nextInt();
        if(playerNumber == 0){
            playerAgent = Agent.Human;
        } else if(playerNumber == 1){
            playerAgent = Agent.Random;
        } else if(playerNumber == 2){
            playerAgent = Agent.Minimax;
        } else if(playerNumber == 3){
            playerAgent = Agent.MinimaxAlphaBeta;
        } else if(playerNumber == 4){
            playerAgent = Agent.MinimaxAlphaBetaHeuristic;
        } else {
            System.err.println("Received agent type " + playerNumber +
                               ", which is unsupported.");
            System.exit(-1);
        }

        return playerAgent;
    }

    public void setupNewGame(){
        System.out.println("Welcome to Checkers!");
        scanner = new Scanner(System.in);
        System.out.println("Enter a game to simulate: ");
        System.out.print("How many moves until a tie occurs: ");
        Integer numberOfMovesForTie = scanner.nextInt();

        System.out.println("Enter First Player.");
        firstPlayer = promptUserForAgent();

        System.out.println("Enter Second Player.");
        secondPlayer = promptUserForAgent();

        System.out.println("What size board would you like to play: size 4 or size 8");
        Integer boardSize = scanner.nextInt();
        if (boardSize != 4 && boardSize != 8){
            System.out.println("Received board size of " + boardSize + ", which is unsupported.");
            System.exit(0);
        }

        // read in a blank line, to ensure all input gets processed. nextInt() only consumes the
        // digits, not the \n character.
        @SuppressWarnings("unused")
        String blankLine = scanner.nextLine();

        model = new CheckersModel(numberOfMovesForTie);
        currentState = model.getInitialState(boardSize);
        if(boardSize == 4){
            // for 4x4 checkers, we can explore 15 moves deep in a second.
            aiInstance = new AI<CheckersState, CheckersAction>(15);
        } else {
            // for 8x8 checkers, we can explore 4 moves deep in a second.
            aiInstance = new AI<CheckersState, CheckersAction>(4);
        }
    }

    private CheckersAction queryUser(CheckersModel model, CheckersState state){
        CheckersAction userAction = null;

        while (userAction == null){
            System.out.print("Enter a move: ");
            String moveString = scanner.nextLine();
            userAction = new CheckersAction(moveString);

            if(model.getResult(state, userAction) == null){
                userAction = null;
                System.out.println("This move is not a valid checkers move for the current state." +
                                   " Is there a capture you missed?");
            }
        }

        return userAction;
    }

    public CheckersAction makeMove(CheckersModel model, CheckersState state,
                                   AI<CheckersState, CheckersAction> ai, Agent agentType) {
        if(model.getIsTerminal(state)){
            return null;
        }

        CheckersAction action = null;
        switch(agentType){
            case Human:
                action = queryUser(model, state);
                break;
            case Random:
                action = ai.randomPlay(model, state);
                break;
            case Minimax:
                action = ai.miniMax(model, state);
                break;
            case MinimaxAlphaBeta:
                action = ai.miniMax_a_b(model, state);
                break;
            case MinimaxAlphaBetaHeuristic:
                action = ai.hMiniMaxAlphaBeta(model, state);
                break;
            default:
                System.err.println("Received agent type " + agentType.name() +
                                   ", which is unsupported.");
                System.exit(-1);
        }
        return action;
    }

    public void playGame(){
        System.out.println("----------");
        System.out.println("sample input:");
        System.out.println("\t" + "D1-C2 for a move");
        System.out.println("\t" + "D1xB3 for a capture");
        System.out.println("----------");
        boolean isFirstPlayerMove = true;

        System.out.println("--Game Start!");
        System.out.println(currentState.toString());

        long firstPlayerTime = 0;
        long secondPlayerTime = 0;

        do {
            long moveStart = System.currentTimeMillis();
            CheckersAction nextAction = makeMove(model, currentState, aiInstance,
                                                 isFirstPlayerMove ? firstPlayer : secondPlayer);
            long moveEnd = System.currentTimeMillis();
            long moveDuration = moveEnd - moveStart;

            if(isFirstPlayerMove){
                firstPlayerTime += moveDuration;
            } else {
                secondPlayerTime += moveDuration;
            }
            if (nextAction == null) {
                break;
            }

            currentState = model.getResult(currentState, nextAction);
            isFirstPlayerMove = !isFirstPlayerMove;
            if(currentState == null || model.getIsTerminal(currentState)) {
                break;
            }

            System.out.println(currentState.toString());
            System.out.println(String.format("Current heuristic utility: %.5f",
                                             model.getHeuristic(currentState)));
        }while(currentState != null);

        System.out.println(currentState.toString());
        if(model.getUtility(currentState) == 1) {
            System.out.println("Win for the first player!");
        } else if(model.getUtility(currentState) == -1){
            System.out.println("Win for the second player!");
        } else {
            System.out.println("Tie!");
        }

        scanner.close();

        System.out.println(String.format("First player spent %.2f seconds thinking of a move.",
                                         firstPlayerTime / 1000.0));
        System.out.println(String.format("Second player spent %.2f seconds thinking of a move.",
                                         secondPlayerTime / 1000.0));
    }
}