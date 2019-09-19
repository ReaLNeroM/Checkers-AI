import java.util.Random;

public class AI <StateClass, ActionClass> {
	private Random randomGenerator;

	public AI(){
		randomGenerator = new Random();
	}

	public ActionClass randomPlay(Model<StateClass, ActionClass> model, StateClass s){
		ActionClass[] validActions = model.getActions(s);

		if(validActions.length == 0){
			return null;
		}

		int randomIndex = randomGenerator.nextInt() % validActions.length;
		return validActions[randomIndex];
	}

	public ActionClass miniMax(Model<StateClass, ActionClass> model, StateClass s){
		// TODO
		return null;
	}

	public ActionClass miniMax_a_b(Model<StateClass, ActionClass> model, StateClass s){
		// TODO
		return null;
	}

	public ActionClass h_miniMax_a_b(Model<StateClass, ActionClass> model, StateClass s){
		// TODO
		return null;
	}
}