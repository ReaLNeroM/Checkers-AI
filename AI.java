public interface AI {
	Action randomPlay(Model model);

	Action miniMax(Model model);

	Action miniMax_a_b(Model model);

	Action h_miniMax_a_b(Model model);
}