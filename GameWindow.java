import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

/**
 * @author Rowland Zhang
 *
 */
public class GameWindow extends JFrame
{  // Instantiate a textfield for input and a textarea for output.
   /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JTextField input = new JTextField(10);
   private JTextArea output = new JTextArea(10, 10);

   private JLabel info = new JLabel("press enter to submit input");
  
   GameSetup setup;
   public GameWindow(String title, GameSetup setup)
   {  // Register a listener with the textfield
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setTitle(title);
      this.getContentPane().setLayout(new BorderLayout());;
      this.setBackground(Color.LIGHT_GRAY);
      TextFieldListener tfListener = new TextFieldListener();
      input.addActionListener(tfListener);

      Font font = new Font("Courier", Font.BOLD, 30);
      output.setFont(font);
      // Don't let the user change the output.
      output.setEditable(false);
      
      // Add all the widgets to the applet
      this.getContentPane().add(input, BorderLayout.PAGE_END);
      this.getContentPane().add(output, BorderLayout.CENTER);
      
      this.getContentPane().add(info, BorderLayout.LINE_START);
      input.requestFocus();        // start with focus on this field
      this.pack();
      this.setVisible(true);
      
      this.setup = setup;
      
   }
   
   public void drawBlankBoard(int size) {
       String[][] board = new String[size+1][size+1];
       
       for(int i = 0; i < board.length; i++) {
    	   for(int j = 0; j < board[i].length; j++) {
    		   board[i][j] = "[-]";		   
    	   }
       }	
       
       for(int j = 1; j < board[0].length; j++) {
    	   board[0][j] = "{" + j + "}";
       }
       for(int i = 1; i < board.length; i++) {
    	   board[i][0] = "{" + String.valueOf((char)(i + 64)) + "}";
       }
       
       for(int i = 0; i < board.length; i++) {
    	   for(int j = 0; j < board[i].length; j++) {
    		   output.append(board[i][j]);			   
    	   }
    	   output.append("\n");
       }	
   input.requestFocus();
   }

   // The listener for the textfield.
   private class TextFieldListener implements ActionListener
   {  public void actionPerformed(ActionEvent evt)
      {  String inputString = input.getText();
         output.append(inputString + "\n");
         input.setText("");
      }
   }

}