import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class AI <StateClass, ActionClass> {
	private Random randomGenerator;

	public AI(){
		randomGenerator = new Random();
	}

	public ActionClass randomPlay(Model<StateClass, ActionClass> model, StateClass state){
		ActionClass[] validActions = model.getActions(state);

		if(validActions.length == 0){
			return null;
		}

		int randomIndex = randomGenerator.nextInt(validActions.length);
		System.out.println("computer has randomly played something");
		return validActions[randomIndex];
	}

	public ActionClass miniMax(Model<StateClass, ActionClass> model, StateClass state){
		ActionClass[] validActions = model.getActions(state);

		Pair<Double, Integer> score = new Pair<Double, Integer>(Double.MIN_VALUE, Integer.MAX_VALUE);
		ActionClass bestMove = null;
		for(int i = 0; i < validActions.length; i++) {
			StateClass currentState = model.getResult(state, validActions[i]);

			Pair<Double, Integer> opponentScore = miniMaxHelp(model, currentState);
			//evaluates the result based on score and cost
			//prioritizing score primarily and cost secondarily
			System.out.println("opponentScore: " + opponentScore.getFirst());
			if(utilCompare(opponentScore, score)) {
				score = opponentScore;
				bestMove = validActions[i];
			}
		}
		System.out.println("computer has played based on miniMax");
		return bestMove;
	}

	private Pair<Double, Integer> miniMaxHelp(Model<StateClass, ActionClass> model, StateClass state) {
		//check if is terminal state
		if(model.getIsTerminal(state)) {
			Pair<Double, Integer> output = new Pair<Double, Integer>(Double.valueOf(model.getUtility(state)), 0);
			return output;
		}

		ActionClass[] validActions = model.getActions(state);
		Pair<Double, Integer> score = new Pair<Double, Integer>(Double.MIN_VALUE, Integer.MAX_VALUE);
		for(int i = 0; i < validActions.length; i++) {
			StateClass currentState = model.getResult(state, validActions[i]);

			Pair<Double, Integer> opponentScore = miniMaxHelp(model, currentState);
			//evaluates the result based on score and cost
			//prioritizing score primarily and cost secondarily
			if(utilCompare(opponentScore, score)) {
				score = opponentScore;
			}
		}
		return score;
	}
	
	/**
	 * 
	 * @param firstUtil
	 * @param secondUtil
	 * @return true if the first is better than second
	 */
	private boolean utilCompare(Pair<Double, Integer> firstUtil, Pair<Double, Integer> secondUtil) {
		if(firstUtil.getFirst()>secondUtil.getFirst()) {
			return true;
		}
		if(firstUtil.getFirst() == secondUtil.getFirst() && firstUtil.getSecond() < secondUtil.getSecond()) {
			return true;
		}
		return false;
	}

	public ActionClass miniMax_a_b(Model<StateClass, ActionClass> model, StateClass state){
		boolean isMaxPlayer = ((CheckersState)state).getNextPlayer().toInteger()==1;		
		if(isMaxPlayer) {
			System.out.println("is Max Player");
		}else {
			System.out.println("is Min Player");
		}
		ActionClass[] validActions = model.getActions(state);
		Double score;
		ActionClass bestMove = null;
		if(isMaxPlayer) {
			score = Double.MIN_VALUE;
		}else {
			score = Double.MAX_VALUE;
		}
		for(int i = 0; i < validActions.length; i++) {
			StateClass currentState = model.getResult(state, validActions[i]);

			Double opponentScore = miniMaxAlphaBetaHelp(model, currentState, Double.MIN_VALUE, Double.MAX_VALUE, !isMaxPlayer);
			System.out.println("opponentUtil is: " + opponentScore);
			//evaluates the result based on score and cost
			//prioritizing score primarily and cost secondarily
			if(isMaxPlayer) {				
				if(opponentScore < score) {
					score = opponentScore;
					bestMove = validActions[i];
				}
			}else {
				if(score > opponentScore) {
					score = opponentScore;
					bestMove = validActions[i];
				}
			}
			System.out.println("score is currently: " + score + "(exploring " + i + "th option");
		}
		System.out.println("current player: " + ((CheckersState)state).getNextPlayer());
		if(bestMove != null) {
			System.out.println("util: " + score + ", depth: " + score);			
		}
		return bestMove;
	}
	
	private Double miniMaxAlphaBetaHelp(Model<StateClass, ActionClass> model, StateClass state, Double alpha, Double beta, boolean isMaxPlayer){
		System.out.println("a: " + alpha + ", b: " + beta);
		if(model.getIsTerminal(state)) {
			Double output = Double.valueOf(model.getUtility(state));
			System.out.println("found a terminal state of util: " + Double.valueOf(model.getUtility(state)));
			return output;
		}
		Double score;
		if(isMaxPlayer) {
			score = Double.MIN_VALUE;
			ActionClass[] validActions = model.getActions(state);
			for(ActionClass currentAction : validActions) {
				//score is the max of the ab of the child
				//score = max(score, ab(child, a, b, !isMaxPlayer))
				Double tempScore = miniMaxAlphaBetaHelp(model, model.getResult(state, currentAction), alpha, beta, !isMaxPlayer);
				if(score > tempScore) {
					//do nothing, score is still larger
				}else {
					score = tempScore;
				}
				if(alpha > score) {
					//do nothing, alpha is still the larger one
				}else {
					alpha = score;
				}
				if(alpha >= beta) {
					break;//beta cut-off
				}
			}
		}else {//is minP  layer
			score = Double.MAX_VALUE;
			ActionClass[] validActions = model.getActions(state);
			for(ActionClass currentAction : validActions) {
				//score is the max of the ab of the child
				//score = max(score, ab(child, a, b, !isMaxPlayer))
				Double tempScore = miniMaxAlphaBetaHelp(model, model.getResult(state, currentAction), alpha, beta, !isMaxPlayer);
				if(score < tempScore) {
					//do nothing, score is still larger
				}else {
					score = tempScore;
				}
				if(beta < score) {
					//do nothing, alpha is still the larger one
				}else {
					beta = score;
				}
				if(alpha >= beta) {
					break;//beta cut-off
				}
			}
		}
		return score;
	}

	public ActionClass h_miniMax_a_b(Model<StateClass, ActionClass> model, StateClass state){
		// TODO
		return null;
	}
}