public interface Result <StateClass, ActionClass> {
	StateClass Result(StateClass state, ActionClass action);
}
