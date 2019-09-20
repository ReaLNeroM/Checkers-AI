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
		
		Double score = Double.MIN_VALUE;
		ActionClass bestMove = null;
		for(int i = 0; i < validActions.length; i++) {
			StateClass currentState = model.getResult(state, validActions[i]);
			
			double opponentScore = miniMaxHelp(model, currentState);
			if(opponentScore>score) {
				score = opponentScore;
				bestMove = validActions[i];
			}
		}
		return bestMove;
	}
	
	private Double miniMaxHelp(Model<StateClass, ActionClass> model, StateClass state) {
		//check if is terminal state
		if(model.getIsTerminal(state)) {
			return Double.valueOf(model.getUtility(state));
		}
		
		ActionClass[] validActions = model.getActions(state);
		Double score = Double.MIN_VALUE;
		for(int i = 0; i < validActions.length; i++) {
			StateClass currentState = model.getResult(state, validActions[i]);
			
			double opponentScore = miniMaxHelp(model, currentState);
			if(opponentScore>score) {
				score = opponentScore;
			}
		}
		return score;
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