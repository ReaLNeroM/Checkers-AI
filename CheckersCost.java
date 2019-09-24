public class CheckersCost implements Cost <CheckersState, CheckersAction> {
    public int Cost(CheckersState state, CheckersAction action){
    	return 1;
    }
}
