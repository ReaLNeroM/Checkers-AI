import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class AI <StateClass extends State, ActionClass extends Action> {
    private Random randomGenerator;

    private class UtilityValue implements Comparable<UtilityValue> {
    	private Double utilityAchieved;
    	private Integer cost;

    	public UtilityValue(Double utilityAchieved, Integer cost){
    		this.utilityAchieved = utilityAchieved;
    		this.cost = cost;
    	}

    	public UtilityValue getOpponentUtilityValue(Integer actionCost){
    		return new UtilityValue(-utilityAchieved, cost + actionCost);
    	}

    	public Double getUtilityAchieved(){
    		return utilityAchieved;
    	}

    	public Integer getCost(){
    		return cost;
    	}

    	public int compareTo(UtilityValue comparisonUtilityValue){
    		// the higher the utilityAchieved, the better the UtilityValue.
    		if(utilityAchieved < comparisonUtilityValue.getUtilityAchieved()){
    			return -1;
    		} else if(utilityAchieved > comparisonUtilityValue.getUtilityAchieved()){
    			return 1;
    		}

    		// Since the utilityAchieved in both are equal, we prefer the one with the lower cost.
    		if(cost < comparisonUtilityValue.getCost()){
    			return 1;
    		} else if(cost > comparisonUtilityValue.getCost()){
    			return -1;
    		}

    		return 0;
    	}

    	public String toString(){
    		return "[ utilityAchieved: " + utilityAchieved.toString() +
    			   ", cost: " + cost.toString() + " ]";
    	}
    }

    private UtilityValue worstUtilityForMaximizingPlayer = new UtilityValue(Double.NEGATIVE_INFINITY, 0);
    private UtilityValue worstUtilityForMinimizingPlayer = new UtilityValue(Double.POSITIVE_INFINITY, 0);
    private UtilityValue tieUtilityValue = new UtilityValue(0.0, 0);
    private UtilityValue lossUtilityValue = new UtilityValue(-1.0, 0);

    public AI(){
    	randomGenerator = new Random();
    }

    public ActionClass randomPlay(Model<StateClass, ActionClass> model, StateClass state){
    	ActionClass[] validActions = model.getActions(state);

    	if(validActions.length == 0){
    		return null;
    	}

    	int randomIndex = randomGenerator.nextInt(validActions.length);
    	System.err.println("Computer has issued the random move: " +
    					   validActions[randomIndex].toString());
    	return validActions[randomIndex];
    }

    public ActionClass miniMax(Model<StateClass, ActionClass> model, StateClass state){
    	if(model.getIsTerminal(state)) {
    		return null;
    	}

    	ActionClass[] validActions = model.getActions(state);
    	UtilityValue bestUtility = worstUtilityForMaximizingPlayer;

    	ActionClass bestMove = null;
    	for(int i = 0; i < validActions.length; i++) {
    		StateClass currentState = model.getResult(state, validActions[i]);
    		UtilityValue opponentUtility = miniMaxHelp(model, currentState);
    		UtilityValue resultingUtilityOfAction = opponentUtility.getOpponentUtilityValue(model.getCost(state, validActions[i]));

    		if(bestUtility.compareTo(resultingUtilityOfAction) < 0) {
    			bestUtility = resultingUtilityOfAction;
    			bestMove = validActions[i];
    		}
    	}

    	if(bestMove != null){
    		System.err.println("Using Minimax, the computer has issued the following move: " +
    						   bestMove.toString());
    	}
    	return bestMove;
    }

    private UtilityValue miniMaxHelp(Model<StateClass, ActionClass> model,
    										  StateClass state) {
    	//check if is terminal state
    	if(model.getIsTerminal(state)) {
    		if(model.getUtility(state) == 0) {
    			return tieUtilityValue;
    		} else {
    			return lossUtilityValue;
    		}
    	}

    	ActionClass[] validActions = model.getActions(state);
    	UtilityValue bestUtility = worstUtilityForMaximizingPlayer;

    	for(int i = 0; i < validActions.length; i++) {
    		StateClass currentState = model.getResult(state, validActions[i]);

    		UtilityValue opponentUtility = miniMaxHelp(model, currentState);
    		UtilityValue resultingUtilityOfAction = opponentUtility.getOpponentUtilityValue(model.getCost(state, validActions[i]));

    		if(bestUtility.compareTo(resultingUtilityOfAction) < 0) {
    			bestUtility = resultingUtilityOfAction;
    		}
    	}
    	return bestUtility;
    }

    public ActionClass miniMax_a_b(Model<StateClass, ActionClass> model, StateClass state){
    	if(model.getIsTerminal(state)) {
    		return null;
    	}

    	boolean isMaxPlayer = state.getNextPlayer().toInteger()==1;
    	ActionClass[] validActions = model.getActions(state);
    	UtilityValue bestUtility = null;
    	if(isMaxPlayer) {
    		System.err.println("A-B Minimax is computing moves for the Maximizing player.");
    		bestUtility = worstUtilityForMaximizingPlayer;
    	} else {
    		System.err.println("A-B Minimax is computing moves for the Minimizing player.");
    		bestUtility = worstUtilityForMinimizingPlayer;
    	}

    	ActionClass bestMove = null;
    	for(int i = 0; i < validActions.length; i++) {
    		StateClass currentState = model.getResult(state, validActions[i]);

    		UtilityValue opponentUtility = miniMaxAlphaBetaHelp(
    			model, currentState,
    			worstUtilityForMaximizingPlayer, worstUtilityForMinimizingPlayer, !isMaxPlayer
    		);

    		if(isMaxPlayer && bestUtility.compareTo(opponentUtility) < 0) {
    			bestUtility = opponentUtility;
    			bestMove = validActions[i];
    		} else if (!isMaxPlayer && bestUtility.compareTo(opponentUtility) > 0){
    			bestUtility = opponentUtility;
    			bestMove = validActions[i];
    		}
    	}

    	if(bestMove != null){
    		System.err.println("A-B Minimax for player " + state.getNextPlayer().toString() +
    						   " has issued the following move: " + bestMove.toString());
    	}
    	return bestMove;
    }

    private UtilityValue miniMaxAlphaBetaHelp(Model<StateClass, ActionClass> model,
    										  StateClass state, UtilityValue alpha,
    										  UtilityValue beta, boolean isMaxPlayer){
    	if(model.getIsTerminal(state)) {
    		Double output = Double.valueOf(model.getUtility(state));
    		return new UtilityValue(output, 0);
    	}

    	UtilityValue bestUtility = null;

    	if(isMaxPlayer) {
    		bestUtility = worstUtilityForMaximizingPlayer;
    		ActionClass[] validActions = model.getActions(state);
    		for(ActionClass currentAction : validActions) {
    			//score is the max of the ab of the child
    			//score = max(score, ab(child, a, b, !isMaxPlayer))
    			UtilityValue opponentUtility = miniMaxAlphaBetaHelp(model,
    																model.getResult(state, currentAction),
    																alpha, beta, !isMaxPlayer);

    			if(bestUtility.compareTo(opponentUtility) < 0){
    				bestUtility = opponentUtility;

    				if(alpha.compareTo(bestUtility) < 0) {
    					//do nothing, alpha is still the larger one
    				}else {
    					alpha = bestUtility;
    				}
    			}

    			if(alpha.compareTo(beta) >= 0) {
    				break;//beta cut-off
    			}
    		}
    	}else {//is minP  layer
    		bestUtility = worstUtilityForMinimizingPlayer;
    		ActionClass[] validActions = model.getActions(state);
    		for(ActionClass currentAction : validActions) {
    			//score is the max of the ab of the child
    			//score = max(score, ab(child, a, b, !isMaxPlayer))
    			UtilityValue opponentUtility = miniMaxAlphaBetaHelp(model,
    																model.getResult(state, currentAction),
    																alpha, beta, !isMaxPlayer);

    			if(bestUtility.compareTo(opponentUtility) > 0){
    				bestUtility = opponentUtility;

    				if(beta.compareTo(bestUtility) > 0) {
    					//do nothing, alpha is still the larger one
    				}else {
    					alpha = bestUtility;
    				}
    			}

    			if(alpha.compareTo(beta) >= 0) {
    				break;//beta cut-off
    			}
    		}
    	}

    	return bestUtility;
    }
    
    public ActionClass hMiniMaxAlphaBeta(Model<StateClass, ActionClass> model, StateClass state){
    	if(model.getIsTerminal(state)) {
    		return null;
    	}

    	boolean isMaxPlayer = state.getNextPlayer().toInteger()==1;
    	ActionClass[] validActions = model.getActions(state);
    	UtilityValue bestUtility = null;
    	if(isMaxPlayer) {
    		System.err.println("A-B H-Minimax is computing moves for the Maximizing player.");
    		bestUtility = worstUtilityForMaximizingPlayer;
    	} else {
    		System.err.println("A-B H-Minimax is computing moves for the Minimizing player.");
    		bestUtility = worstUtilityForMinimizingPlayer;
    	}

    	ActionClass bestMove = null;
    	for(int i = 0; i < validActions.length; i++) {
    		StateClass currentState = model.getResult(state, validActions[i]);

    		UtilityValue opponentUtility = hMiniMaxAlphaBetaHelp(
    			model, currentState,
    			worstUtilityForMaximizingPlayer, worstUtilityForMinimizingPlayer, !isMaxPlayer, 0
    		);

    		if(isMaxPlayer && bestUtility.compareTo(opponentUtility) < 0) {
    			bestUtility = opponentUtility;
    			bestMove = validActions[i];
    		} else if (!isMaxPlayer && bestUtility.compareTo(opponentUtility) > 0){
    			bestUtility = opponentUtility;
    			bestMove = validActions[i];
    		}
    	}

    	if(bestMove != null){
    		System.err.println("A-B H-Minimax for player " + state.getNextPlayer().toString() +
    						   " has issued the following move: " + bestMove.toString());
    	}
    	return bestMove;
    }

    private UtilityValue hMiniMaxAlphaBetaHelp(Model<StateClass, ActionClass> model,
    										  StateClass state, UtilityValue alpha,
    										  UtilityValue beta, boolean isMaxPlayer, Integer searchDepth){
    	if(model.getIsTerminal(state)) {
    		Double output = Double.valueOf(model.getUtility(state));
    		return new UtilityValue(output, 0);
    	}
    	CheckersUtility utilImplementation = (CheckersUtility) ((CheckersModel)model).getUtilityImplementation();
    	if(utilImplementation.getMaxSearchDepth() <= searchDepth) {
    		Double currentHeuristic = Double.valueOf(model.getHeuristic(state));
    		return new UtilityValue(currentHeuristic, 0);
    	}

    	UtilityValue bestUtility = null;

    	if(isMaxPlayer) {
    		bestUtility = worstUtilityForMaximizingPlayer;
    		ActionClass[] validActions = model.getActions(state);
    		for(ActionClass currentAction : validActions) {
    			//score is the max of the ab of the child
    			//score = max(score, ab(child, a, b, !isMaxPlayer))
    			UtilityValue opponentUtility = hMiniMaxAlphaBetaHelp(model,
    																model.getResult(state, currentAction),
    																alpha, beta, !isMaxPlayer, searchDepth+1);

    			if(bestUtility.compareTo(opponentUtility) < 0){
    				bestUtility = opponentUtility;

    				if(alpha.compareTo(bestUtility) < 0) {
    					//do nothing, alpha is still the larger one
    				}else {
    					alpha = bestUtility;
    				}
    			}

    			if(alpha.compareTo(beta) >= 0) {
    				break;//beta cut-off
    			}
    		}
    	}else {//is minP  layer
    		bestUtility = worstUtilityForMinimizingPlayer;
    		ActionClass[] validActions = model.getActions(state);
    		for(ActionClass currentAction : validActions) {
    			//score is the max of the ab of the child
    			//score = max(score, ab(child, a, b, !isMaxPlayer))
    			UtilityValue opponentUtility = hMiniMaxAlphaBetaHelp(model,
    																model.getResult(state, currentAction),
    																alpha, beta, !isMaxPlayer, searchDepth+1);

    			if(bestUtility.compareTo(opponentUtility) > 0){
    				bestUtility = opponentUtility;

    				if(beta.compareTo(bestUtility) > 0) {
    					//do nothing, alpha is still the larger one
    				}else {
    					alpha = bestUtility;
    				}
    			}

    			if(alpha.compareTo(beta) >= 0) {
    				break;//beta cut-off
    			}
    		}
    	}

    	return bestUtility;
    }
}