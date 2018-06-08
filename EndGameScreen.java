import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

public class EndGameScreen extends JFrame implements ActionListener {

  private JButton exit;
  private JLabel score;

  Board board = null;
  BoardUI boardUI;
  JLayeredPane bPane;

  // private static class LazyHolder
  // {
  //   static final EndGameScreen INSTANCE = new Board();
  // }
  // //getter for the board
  // public static EndGameScreen getInstance()
  // {
  //   return LazyHolder.INSTANCE;
  // }

  public EndGameScreen() {

    super("Game Over");

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

    score = new JLabel("<html>" + board.getEndGameLine() + "</html>");
    score.setBounds(150,165,350,200);

    bPane.add(score, new Integer(2));

    exit = new JButton("Exit");
    exit.setBackground(Color.white);
    exit.setBounds(235,10,120, 30);
    exit.addActionListener(this);
    bPane.add(exit, new Integer(2));

  }

  /*
  * actionPerformed method
  * @param: ActionEvent
  * Initializes the players that the user chooses
  */
  public void actionPerformed (ActionEvent e) {

    if (e.getActionCommand() == "Exit"){
      dispose();
      System.exit(1);
    }

  }


}
