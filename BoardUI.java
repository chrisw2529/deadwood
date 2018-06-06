import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

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

  ArrayList<JLabel> playerLabels = new ArrayList<JLabel>();
  HashMap<Integer,JLabel> sMarkersMap = new HashMap<Integer,JLabel>();
<<<<<<< HEAD
  HashMap<Integer,JLabel> cardBacks = new HashMap<Integer,JLabel>();
  ArrayList<JLabel> cards = new ArrayList<JLabel>();

=======
>>>>>>> 74622a5f577dabb0c68130cdf05ed25d9c9954b1
  JLayeredPane bPane = getLayeredPane();

  private JScrollPane scroller;
  private JTextArea text;
  Board board = null;

  private BoardUI() {

    super("Deadwood");

    this.board = board.getInstance();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  //Holder for the board
  private static class LazyHolder
  {
    static final BoardUI INSTANCE = new BoardUI();
  }
  //getter for the board
  public static BoardUI getInstance()
  {
    return LazyHolder.INSTANCE;
  }

  public void initialize() {

    // JPanel toolbar = new JPanel();
    // add(toolbar, BorderLayout.NORTH);
    // addButton(toolbar, act, "Act");
    // addButton(toolbar, rehearse, "Rehearse");
    // addButton(toolbar, move, "Move");
    // addButton(toolbar, takeRole, "Take Role");
    // addButton(toolbar, rankUp, "Rank Up");
    // addButton(toolbar, endTurn, "End Turn");

    //bPane = getLayeredPane();

    boardlabel = new JLabel();
    ImageIcon icon =  new ImageIcon("images/board.jpg");
    boardlabel.setIcon(icon);
    boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

       // Add the board to the lowest layer
    bPane.add(boardlabel, new Integer(0));

    // cardlabel = new JLabel();
    // ImageIcon cIcon =  new ImageIcon("images/01.png");
    // cardlabel.setIcon(cIcon);
    // cardlabel.setBounds(20,65,cIcon.getIconWidth()+2,cIcon.getIconHeight());
    // cardlabel.setOpaque(true);
    //
    // // Add the card to the lower layer
    // bPane.add(cardlabel, new Integer(1));
    //
    // playerlabel = new JLabel();
    // ImageIcon pIcon = new ImageIcon("images/r2.png");
    // playerlabel.setIcon(pIcon);
    // //playerlabel.setBounds(114,227,pIcon.getIconWidth(),pIcon.getIconHeight());
    // playerlabel.setBounds(114,227,46,46);
    // bPane.add(playerlabel,new Integer(3));

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

        Player player = board.activePlayer();

         if (e.getSource()== act){
            System.out.println("Acting is Selected\n");
            player.act(player, board);

         }
         else if (e.getSource()== rehearse){
            System.out.println("Rehearse is Selected\n");
            player.rehearse(player, board);
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

  public void setCard(Card card, Set set, int i)
  {
    // System.out.println(set.getName());
    // System.out.println(set.getX() + " " + set.getY());
    String img = "images/cards/";

    if(i < 10)
        img += "0"+i+".png";
    else
        img += i+".png";

    //System.out.println("this is the string: "+img);

    cardlabel = new JLabel();
    ImageIcon cIcon =  new ImageIcon(img);
    cardlabel.setIcon(cIcon);
    cardlabel.setBounds(set.getX(),set.getY(),cIcon.getIconWidth()+2,cIcon.getIconHeight());
    cardlabel.setOpaque(true);

    // Add the card to the lower layer
    bPane.add(cardlabel, new Integer(1));
  }

  public void setCardBacks(Card card, Set set)
  {

    cardlabel = new JLabel();
    ImageIcon cIcon =  new ImageIcon("images/cards/back.png");
    cardlabel.setIcon(cIcon);
    cardlabel.setBounds(set.getX(),set.getY(),cIcon.getIconWidth()+2,cIcon.getIconHeight());
    cardlabel.setOpaque(true);
//FOR WHEN A PLAYER HOPS ON A SET    cardlabel.setVisible(false);
    sMarkersMap.put(set.getX()%set.getY(),cardlabel);
    bPane.add(cardlabel, new Integer(2));
  }

  public void removeBack(Set set)
  {
    cardBacks.get(set.getX()%set.getY()).setVisible(false);
    //bPane.repaint();
  }











  public void setPlayer(int id, int level){
    String img = "images/dice/";
    Player cp = board.getPlayers().get(id - 1);
    if(level < 1 || level > 6){
      System.out.println("error: level must be between 1, 6");
    }
    else{
      if(level == 1){
        if(cp.getID() == 1){
          img += "r" + level + ".png";
          System.out.println("player 1 x = " + cp.getX());

        }
        else if(cp.getID() == 2){
          img += "b" + level + ".png";
          cp.setX(cp.getX() + 42);
          System.out.println("player 2 x = " + cp.getX());
        }
        else if(cp.getID() == 3){
          img += "g" + level + ".png";
          cp.setX(cp.getX() + 84);
          System.out.println("player 3 x = " + cp.getX());

        }
        else{
          System.out.println("error: invalid player ID in setPlayer");
        }
      }
      else{
        if(cp.getID() == 1){
          img += "r" + level + ".png";
          //cp.setImageIcon(null);
          cp.getJLabel().setIcon(null);

        }
        else if(cp.getID() == 2){
          img += "b" + level + ".png";
        }
        else if(cp.getID() == 3){
          img += "g" + level + ".png";
        }
      }

      playerlabel = new JLabel();
      ImageIcon cIcon =  new ImageIcon(img);
      // if(level != 1){
      //   cp.getIcon().getImage().flush();
      // }
      System.out.println("img: " + img);
      System.out.println("player x: "+ cp.getX() + " y: " + cp.getY());
      cp.setImageIcon(cIcon);
      cp.setJLabel(playerlabel);
      playerlabel.setIcon(cIcon);
      playerlabel.setBounds(cp.getX(),cp.getY(),cIcon.getIconWidth()+2,cIcon.getIconHeight());
      playerlabel.setOpaque(true);

      // Add the card to the lower layer
      bPane.add(playerlabel, new Integer(5));


    }


  }
  public void movePlayerImage(Player player, int x , int y){
    player.setX(x);
    player.setY(y);
    player.getJLabel().setBounds(x, y,player.getIcon().getIconWidth()+2,player.getIcon().getIconHeight());

  }

  public void addShotMarkers(Set set, ShotMarker sm)
  {

    JLabel shotMs = new JLabel();
    ImageIcon cIcon =  new ImageIcon("images/shotMarker.png");
    shotMs.setIcon(cIcon);
    shotMs.setBounds(sm.getX(),sm.getY(),47,47);
    shotMs.setOpaque(false);

    // Add the card to the lower layer
    bPane.add(shotMs, new Integer(1));
    System.out.println(sm.getX()%sm.getY());
    sMarkersMap.put(sm.getX()%sm.getY(), shotMs);

  }

  public void removeShotMarkers(Set set, ShotMarker shot)
  {

    //bPane.remove(sMarkersMap.get(sm.getX()+sm.getY()));
    sMarkersMap.get(shot.getX()%shot.getY()).setVisible(false);
    bPane.repaint();
    set.decrementShotMarker();
    set.getShotMarkers().remove(0);
    System.out.println("SHOT MARKER REMOVED");

  }

  public void resetSM()
  {
    Iterator iterator =sMarkersMap.keySet().iterator();

    while (iterator.hasNext()) {
      Integer key = (Integer) iterator.next();
      sMarkersMap.get(key).setVisible(true);
    }
  }



}
