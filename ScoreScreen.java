import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

public class ScoreScreen extends JFrame implements ActionListener {

  private JButton twoPlayers;
  private JButton threePlayers;


  JLabel logo;
  JLabel Score;
  private JScrollPane scroller;

  private JTextArea text;


  JLayeredPane bPane;

  Board board = null;
  BoardUI boardUI;

  private ScoreScreen() {

    super("Deadwood");

    this.board = board.getInstance();

    initialize();
    setSize(590, 370);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);

  }

  //Holder for the scoreScreen
  private static class LazyHolder
  {
    static final ScoreScreen INSTANCE = new ScoreScreen();
  }
  //getter for the scoreScreen
  public static ScoreScreen getInstance()
  {
    return LazyHolder.INSTANCE;
  }


  /*
  * initialize method
  * Creates buttons, imports logo for opening screen
  */
  public void initialize() {
    bPane = getLayeredPane();

    logo = new JLabel();
    // ImageIcon icon =  new ImageIcon("images/openingLogo.jpg");
    // logo.setIcon(icon);
    // logo.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

    // Add the board to the lowest layer
    //bPane.add(logo, new Integer(0));
    text = new JTextArea("Game information\n");
    text.setEditable(false);
    scroller = new JScrollPane(text);
    scroller.setBounds(10,20,200,300);
    bPane.add(scroller, new Integer(2));


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

  public void updateConsole(String update)
  {
    System.out.println("update is: " + update);
    text.append(update + "\n");
    text.setCaretPosition(text.getDocument().getLength());
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
