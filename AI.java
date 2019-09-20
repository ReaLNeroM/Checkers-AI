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
		if(model.getIsTerminal(state)) {
			return null;
		}

		ActionClass[] validActions = model.getActions(state);
		Pair<Double, Integer> bestUtility = new Pair<Double, Integer>(Double.NEGATIVE_INFINITY,
																	  Integer.MAX_VALUE);

		ActionClass bestMove = null;
		for(int i = 0; i < validActions.length; i++) {
			StateClass currentState = model.getResult(state, validActions[i]);

			Pair<Double, Integer> opponentUtility = miniMaxHelp(model, currentState);
			Pair<Double, Integer> resultingUtilityOfAction = new Pair<Double, Integer>(
				-opponentUtility.getFirst(),
				opponentUtility.getSecond() + model.getCost(state, validActions[i])
			);
			//evaluates the result based on bestUtility and cost
			//prioritizing bestUtility primarily and cost secondarily
			if(utilCompare(resultingUtilityOfAction, bestUtility)) {
				bestUtility = resultingUtilityOfAction;
				bestMove = validActions[i];
			}
		}
		return bestMove;
	}

	private Pair<Double, Integer> miniMaxHelp(Model<StateClass, ActionClass> model,
											  StateClass state) {
		//check if is terminal state
		if(model.getIsTerminal(state)) {
			if(model.getUtility() == 0) {
				return new Pair<Double, Integer>(0.0, 0);
			} else {
				return new Pair<Double, Integer>(-1.0, 0);
			}
		}

		ActionClass[] validActions = model.getActions(state);
		Pair<Double, Integer> bestUtility = new Pair<Double, Integer>(Double.NEGATIVE_INFINITY,
																	  Integer.MAX_VALUE);

		for(int i = 0; i < validActions.length; i++) {
			StateClass currentState = model.getResult(state, validActions[i]);

			Pair<Double, Integer> opponentUtility = miniMaxHelp(model, currentState);
			Pair<Double, Integer> resultingUtilityOfAction = new Pair<Double, Integer>(
				-opponentUtility.getFirst(),
				opponentUtility.getSecond() + model.getCost(state, validActions[i])
			);
			//evaluates the result based on bestUtility and cost
			//prioritizing bestUtility primarily and cost secondarily
			if(utilCompare(resultingUtilityOfAction, bestUtility)) {
				bestUtility = resultingUtilityOfAction;
			}
		}
		return bestUtility;
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
		if(
			firstUtil.getFirst() == secondUtil.getFirst() &&
			firstUtil.getSecond() < secondUtil.getSecond()
		) {
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