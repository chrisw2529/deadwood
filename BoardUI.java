import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

public class BoardUI extends JFrame implements ActionListener {

  private JButton act;
  private JButton rehearse;
  private JButton move;
  private JButton takeRole;
  private JButton rankUp;
  private JButton endTurn;


  private JScrollPane scroller;
  private JTextArea text;
  Board board = null;

  public BoardUI() {

    super("Testing BoardUI");

    this.board = board.getInstance();

    initialize();
    setSize(1000, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

  }

  private void initialize() {

    JPanel toolbar = new JPanel();
    add(toolbar, BorderLayout.NORTH);
    addButton(toolbar, act, "Act");
    addButton(toolbar, rehearse, "Rehearse");
    addButton(toolbar, move, "Move");
    addButton(toolbar, takeRole, "Take Role");
    addButton(toolbar, rankUp, "Rank Up");
    addButton(toolbar, endTurn, "End Turn");

  }

  public void actionPerformed (ActionEvent e) {

    System.out.println(e.getActionCommand() + " pressed");

    Player activePlayer = board.activePlayer();

    if (e.getActionCommand() == "Act"){
  //    activePlayer.act();
    }

    if (e.getActionCommand() == "Rehearse"){
    //  activePlayer.rehearse();
    }

    if (e.getActionCommand() == "Move"){
      //get the click to where
    //  activePlayer.move();
    }

    if (e.getActionCommand() == "Take Role"){
      //take roll click cmd
    //  activePlayer.takeRole();
    }

    if (e.getActionCommand() == "Rank Up"){
      //activePlayer
    }

    if (e.getActionCommand() == "End Turn"){
  //    activePlayer.endTurn();
    }

  }

  private void addButton(JPanel panel, JButton button, String label) {

    button = new JButton(label);
    panel.add(button);
    button.addActionListener(this);

  }



}
