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

  JLabel boardlabel;
  JLabel cardlabel;
  JLabel playerlabel;
  JLabel mLabel;

  JLayeredPane bPane;

  private JScrollPane scroller;
  private JTextArea text;
  Board board = null;

  public BoardUI() {

    super("Deadwood");

    this.board = board.getInstance();

    initialize();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

  }

  private void initialize() {

    // JPanel toolbar = new JPanel();
    // add(toolbar, BorderLayout.NORTH);
    // addButton(toolbar, act, "Act");
    // addButton(toolbar, rehearse, "Rehearse");
    // addButton(toolbar, move, "Move");
    // addButton(toolbar, takeRole, "Take Role");
    // addButton(toolbar, rankUp, "Rank Up");
    // addButton(toolbar, endTurn, "End Turn");

    bPane = getLayeredPane();

    boardlabel = new JLabel();
    ImageIcon icon =  new ImageIcon("images/board.jpg");
    boardlabel.setIcon(icon);
    boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

       // Add the board to the lowest layer
    bPane.add(boardlabel, new Integer(0));

    cardlabel = new JLabel();
    ImageIcon cIcon =  new ImageIcon("images/01.png");
    cardlabel.setIcon(cIcon);
    cardlabel.setBounds(20,65,cIcon.getIconWidth()+2,cIcon.getIconHeight());
    cardlabel.setOpaque(true);

    // Add the card to the lower layer
    bPane.add(cardlabel, new Integer(1));

    playerlabel = new JLabel();
    ImageIcon pIcon = new ImageIcon("images/r2.png");
    playerlabel.setIcon(pIcon);
    //playerlabel.setBounds(114,227,pIcon.getIconWidth(),pIcon.getIconHeight());
    playerlabel.setBounds(114,227,46,46);
    bPane.add(playerlabel,new Integer(3));

       // Set the size of the GUI
    setSize(icon.getIconWidth()+200,icon.getIconHeight());

    // Create the Menu for action buttons
    mLabel = new JLabel("MENU");
    mLabel.setBounds(icon.getIconWidth()+40,0,130,30);
    bPane.add(mLabel,new Integer(2));

    // Create Action buttons
    act = new JButton("ACT");
    act.setBackground(Color.white);
    act.setBounds(icon.getIconWidth()+10, 30,130, 30);
    act.addMouseListener(new boardMouseListener());

    rehearse = new JButton("REHEARSE");
    rehearse.setBackground(Color.white);
    rehearse.setBounds(icon.getIconWidth()+10,60,130, 30);
    rehearse.addMouseListener(new boardMouseListener());

    move = new JButton("MOVE");
    move.setBackground(Color.white);
    move.setBounds(icon.getIconWidth()+10,90,130, 30);
    move.addMouseListener(new boardMouseListener());

    takeRole = new JButton("TAKE ROLE");
    takeRole.setBackground(Color.white);
    takeRole.setBounds(icon.getIconWidth()+10,120,130, 30);
    takeRole.addMouseListener(new boardMouseListener());

    rankUp = new JButton("RANK UP");
    rankUp.setBackground(Color.white);
    rankUp.setBounds(icon.getIconWidth()+10,150,130, 30);
    rankUp.addMouseListener(new boardMouseListener());

    endTurn = new JButton("END TURN");
    endTurn.setBackground(Color.white);
    endTurn.setBounds(icon.getIconWidth()+10,180,130, 30);
    endTurn.addMouseListener(new boardMouseListener());

// Place the action buttons in the top layer
    bPane.add(act, new Integer(2));
    bPane.add(rehearse, new Integer(2));
    bPane.add(move, new Integer(2));
    bPane.add(takeRole, new Integer(2));
    bPane.add(rankUp, new Integer(2));
    bPane.add(endTurn, new Integer(2));

  }

  class boardMouseListener implements MouseListener{

      // Code for the different button clicks
      public void mouseClicked(MouseEvent e) {

         if (e.getSource()== act){
            System.out.println("Acting is Selected\n");
         }
         else if (e.getSource()== rehearse){
            System.out.println("Rehearse is Selected\n");
         }
         else if (e.getSource()== move){
            System.out.println("Move is Selected\n");
         }
      }
      public void mousePressed(MouseEvent e) {
      }
      public void mouseReleased(MouseEvent e) {
      }
      public void mouseEntered(MouseEvent e) {
      }
      public void mouseExited(MouseEvent e) {
      }
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