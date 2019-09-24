import javax.swing.*;
public class GameSetup {
    private static final String[] boardSizeOptions = { "small 4x4 Checkers", "Standard 8x8 Checkers" };
    private boolean setUpComplete = false;

    private int boardSize;//small 4x4 or standard 8x8
    private String AIType;//random agent, minmax, minmaxwith a-b pruning, or h-minmax with fixed depth cutoff and a-b pruning
    private int depthLimit;
    private boolean opening;//do you have the first move
    public GameSetup(){

    }

    public void promptPlayer() {
        JFrame frame = new JFrame("Game Setup");
        String user_gameSize = (String) JOptionPane.showInputDialog(frame,
            "What kind of game would you like to play?",
            "Game Setup",
            JOptionPane.QUESTION_MESSAGE,
            null,
            boardSizeOptions,
            boardSizeOptions[0]);

        // favoritePizza will be null if the user clicks Cancel
        System.out.printf("the game you chose is %s.\n", user_gameSize);
        if(user_gameSize.equals(boardSizeOptions[0])) {
            boardSize = 4;
        }else if(user_gameSize.equals(boardSizeOptions[1])) {
            boardSize = 8;
        }
        if(user_gameSize != null) {
            setUpComplete = true;
        }
        //System.exit(0);
    }

    public boolean setUpComplete() {
        return setUpComplete;
    }

    public int getGameSize() {
        return boardSize;
    }

    //TODO: complete toString method

}
