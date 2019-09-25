import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CheckersRunner {
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
    AI<CheckersState, CheckersAction> aiInstance = null;

    private Agent promptUserForAgent(Scanner scanner){
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a game to simulate: ");
        System.out.print("How many moves until a tie occurs: ");
        Integer numberOfMovesForTie = scanner.nextInt();

        System.out.println("Enter First Player.");
        firstPlayer = promptUserForAgent(scanner);

        System.out.println("Enter Second Player.");
        secondPlayer = promptUserForAgent(scanner);

        System.out.println("What size board would you like to play: size 4 or size 8");
        Integer boardSize = scanner.nextInt();
        scanner.close();
        if (boardSize != 4 && boardSize != 8){
            System.out.println("Received board size of " + boardSize + ", which is unsupported.");
            System.exit(0);
        }

        model = new CheckersModel(numberOfMovesForTie);
        currentState = model.getInitialState(boardSize);
        aiInstance = new AI<CheckersState, CheckersAction>();
    }

    private CheckersAction queryUser(CheckersModel model, CheckersState state){
        CheckersAction userAction = null;

        Scanner userMoveScanner = new Scanner(System.in);

        while (userAction == null){
            System.out.print("Enter a move: ");
            String moveString = userMoveScanner.nextLine();
            userAction = new CheckersAction(moveString);

            if(userAction == null){
                System.out.println("Input is not in valid checkers move notation.");
            } else if(model.getResult(state, userAction) == null){
                userAction = null;
                System.out.println("This move is not a valid checkers move for the current state." +
                                   " Is there a capture you missed?");
            }
        }

        userMoveScanner.close();

        return userAction;
    }

    public CheckersAction makeMove(CheckersModel model, CheckersState state,
                                   AI<CheckersState, CheckersAction> ai, Agent agentType) {
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
        System.out.println(currentState.toString());
        boolean isFirstPlayerMove = true;

        System.out.println("--Game Start! time: ");
        DateTimeFormatter dtfStart = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(dtfStart.format(now1));

        do {
            CheckersAction nextAction = makeMove(model, currentState, aiInstance,
                                                 isFirstPlayerMove ? firstPlayer : secondPlayer);
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

        System.out.println("--Game End! time: ");
        DateTimeFormatter dtfEnd = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now2 = LocalDateTime.now();
        System.out.println(dtfEnd.format(now2));
    }
}