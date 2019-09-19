import javax.swing.*;

public class GameRunner {
	public static void main(String args[]) 
	{ 
		GameSetup setup = new GameSetup();
		setup.promptPlayer();
		
		if(setup.setUpComplete()) {
			System.out.println("setup complete");
		}else {
			JFrame f = new JFrame();
			JOptionPane.showMessageDialog(f, "setup incomplete");
			System.out.println("setup incomplete");
		}
		GameWindow window = new GameWindow("checkers game", setup);
	} 
}
