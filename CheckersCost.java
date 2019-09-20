public class CheckersCost implements Cost <CheckersState, CheckersAction> {
	public Integer Cost(CheckersState state, CheckersAction action){
		return 1;
	}
}
