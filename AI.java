public interface AI <StateClass, ActionClass> {
	ActionClass randomPlay(Model<StateClass, ActionClass> model);

	ActionClass miniMax(Model<StateClass, ActionClass> model);

	ActionClass miniMax_a_b(Model<StateClass, ActionClass> model);

	ActionClass h_miniMax_a_b(Model<StateClass, ActionClass> model);
}