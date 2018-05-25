import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

public class OpeningScreen extends JFrame implements ActionListener {

  private JButton twoPlayers;
  private JButton threePlayers;

  Board board = null;

  public OpeningScreen() {

    super("Testing OpeningScreen");

    this.board = board.getInstance();

    initialize();
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

  }

  private void initialize() {

    JPanel toolbar = new JPanel();
    add(toolbar, BorderLayout.NORTH);
    addButton(toolbar, twoPlayers, "2 Players");
    addButton(toolbar, threePlayers, "3 Players");
    //text = new JTextArea();
    //scroller = new JScrollPane(text);
    //add(scroller, BorderLayout.CENTER);

  }

  public void actionPerformed (ActionEvent e) {

    System.out.println(e.getActionCommand() + " pressed");

    if (e.getActionCommand() == "2 Players"){
      board.initializePlayers(2);
      BoardUI newBoardUI = new BoardUI();
      dispose();
    }


    if (e.getActionCommand() == "3 Players"){
      board.initializePlayers(3);
      BoardUI newBoardUI = new BoardUI();
      dispose();
    }


  }

  private void addButton(JPanel panel, JButton button, String label) {

    button = new JButton(label);
    panel.add(button);
    button.addActionListener(this);

  }

}
