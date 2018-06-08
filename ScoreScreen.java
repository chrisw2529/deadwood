import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

public class ScoreScreen extends JFrame implements ActionListener {

  private JButton playAgain2Player;
  private JButton playAgain3Player;


  JLabel logo;
  JLabel Score;


  JLayeredPane bPane;

  Board board = null;
  BoardUI boardUI;

  public scoreScreen() {

    super("Deadwood");

    this.board = board.getInstance();

    initialize();
    setSize(590, 370);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);

  }

  /*
  * initialize method
  * Creates buttons, imports logo for opening screen
  */
  private void initialize() {
    bPane = getLayeredPane();

    logo = new JLabel();
    // ImageIcon icon =  new ImageIcon("images/openingLogo.jpg");
    // logo.setIcon(icon);
    // logo.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

    // Add the board to the lowest layer
    //bPane.add(logo, new Integer(0));


    twoPlayers = new JButton("Play Again 2 Players");
    twoPlayers.setBackground(Color.white);
    twoPlayers.setBounds(147,10,120, 30);
    twoPlayers.addActionListener(this);
    bPane.add(twoPlayers, new Integer(2));

    threePlayers = new JButton("Play Again 3 Players");
    threePlayers.setBackground(Color.white);
    threePlayers.setBounds(295,10,120, 30);
    threePlayers.addActionListener(this);
    bPane.add(threePlayers, new Integer(2));

  }

  public void displayScore(){
    
  }

  /*
  * actionPerformed method
  * @param: ActionEvent
  * Initializes the players that the user chooses
  */

  public void actionPerformed (ActionEvent e) {

    System.out.println(e.getActionCommand() + " pressed");
    if (e.getActionCommand() == "Play Again 2 Players"){
      board.initializePlayers(2);
      this.boardUI = boardUI.getInstance();
      boardUI.initialize();
      dispose();
    }


    if (e.getActionCommand() == "Play Again 3 Players"){
      board.initializePlayers(3);
      this.boardUI = boardUI.getInstance();
      boardUI.initialize();
      dispose();
    }

  }

}
