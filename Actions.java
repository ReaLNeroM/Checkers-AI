public interface Actions <StateClass extends State, ActionClass extends Action> {
    ActionClass[] Actions(StateClass state);
}
