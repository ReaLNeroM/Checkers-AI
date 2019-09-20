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
			if(utilCompare(opponentScore, score)) {
				score = opponentScore;
				bestMove = validActions[i];
			}
		}
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
		// TODO
		return null;
	}

	public ActionClass h_miniMax_a_b(Model<StateClass, ActionClass> model, StateClass state){
		// TODO
		return null;
	}
}