import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.text.DefaultCaret;

public class BoardUI extends JFrame {

  private JButton act;
  private JButton rehearse;
  private JButton move;
  private JButton takeRole;
  private JButton rankUp;
  private JButton endTurn;
  private JLabel boardlabel;
  private JLabel cardlabel;
  private JLabel playerlabel;
  private JLabel statPlayerLabel;
  private JLabel mLabel;
  private JLabel day;
  private JLabel activePlayer1;
  private JLabel activePlayer2;
  private JLabel fame;
  private JLabel cash;
  private JLabel space;
  private JLabel role;
  private JLabel rehearsalToken;

  private ImageIcon icon;

  private HashMap<Integer,JLabel> playerLabels = new HashMap<Integer,JLabel>();
  private HashMap<String,JLabel> sMarkersMap = new HashMap<String,JLabel>();
  private HashMap<String,JLabel> cardBacks = new HashMap<String,JLabel>();
  private HashMap<String,JLabel> cardsMap = new HashMap<String,JLabel>();
  private JLayeredPane bPane = getLayeredPane();

  private JScrollPane scroller;
  private JTextArea text;
  private Board board = null;

  private JPopupMenu moveTo;
  private JPopupMenu rankTo;
  private JPopupMenu roles;
  private Boolean moveToOpen = false;
  private Boolean rankUpOpen = false;
  private Boolean rolesOpen = false;
  private JMenuItem item;
  private ActionListener menuListener;


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

    boardlabel = new JLabel();
    icon =  new ImageIcon("images/board.jpg");
    boardlabel.setIcon(icon);
    boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

    // Add the board to the lowest layer
    bPane.add(boardlabel, new Integer(0));

    // Set the size of the GUI
    setSize(icon.getIconWidth()+400,icon.getIconHeight());

    // Create the Menu for action buttons
    mLabel = new JLabel("MENU");
    mLabel.setBounds(icon.getIconWidth()+40,0,150,30);
    bPane.add(mLabel,new Integer(2));

    // Create Action buttons
    act = new JButton("ACT");
    act.setBackground(Color.white);
    act.setBounds(icon.getIconWidth()+10, 30,150, 30);
    act.addMouseListener(new boardMouseListener());

    rehearse = new JButton("REHEARSE");
    rehearse.setBackground(Color.white);
    rehearse.setBounds(icon.getIconWidth()+10,60,150, 30);
    rehearse.addMouseListener(new boardMouseListener());

    move = new JButton("MOVE");
    move.setBackground(Color.white);
    move.setBounds(icon.getIconWidth()+10,90,150, 30);
    move.addMouseListener(new boardMouseListener());

    takeRole = new JButton("TAKE ROLE");
    takeRole.setBackground(Color.white);
    takeRole.setBounds(icon.getIconWidth()+10,120,150, 30);
    takeRole.addMouseListener(new boardMouseListener());

    rankUp = new JButton("RANK UP");
    rankUp.setBackground(Color.white);
    rankUp.setBounds(icon.getIconWidth()+10,150,150, 30);
    rankUp.addMouseListener(new boardMouseListener());

    endTurn = new JButton("END TURN");
    endTurn.setBackground(Color.white);
    endTurn.setBounds(icon.getIconWidth()+10,180,150, 30);
    endTurn.addMouseListener(new boardMouseListener());

    // Place the action buttons in the top layer
    bPane.add(act, new Integer(2));
    bPane.add(rehearse, new Integer(2));
    bPane.add(move, new Integer(2));
    bPane.add(takeRole, new Integer(2));
    bPane.add(rankUp, new Integer(2));
    bPane.add(endTurn, new Integer(2));

    //pop up menus for action buttons
    moveTo = new JPopupMenu();
    rankTo = new JPopupMenu();
    roles = new JPopupMenu();

    //Console
    text = new JTextArea("Game information\n");
    text.setEditable(false);
    scroller = new JScrollPane(text);
    scroller.setBounds(icon.getIconWidth()+10,550,350,300);
    bPane.add(scroller, new Integer(2));

    //Adds stats to board
    statsInit();

  }


  /*
  * statsInit Method
  * makes all of the JLables and text for all of the player info on the screen
  *
  */
  public void statsInit(){

    day = new JLabel("Day: " + 1);
    activePlayer1 = new JLabel("Active Player: ");
    activePlayer2 = new JLabel("Player " + board.activePlayer().getID());
    fame = new JLabel("Fame: " + 0);
    cash = new JLabel("Cash: " + 0);
    space = new JLabel("Current Space: " + board.activePlayer().getSpace().getName());
    role = new JLabel("Current Role: Not on a role ");
    rehearsalToken = new JLabel("Number of rehearsal Tokens: 0");


    day.setBounds(icon.getIconWidth()+10,400,130,20);
    activePlayer1.setBounds(icon.getIconWidth()+10,410,130,20);
    activePlayer2.setBounds(icon.getIconWidth()+10,420,130,20);
    fame.setBounds(icon.getIconWidth()+10,480,130,20);
    cash.setBounds(icon.getIconWidth()+10,490,130,20);
    space.setBounds(icon.getIconWidth()+10,500,230,20);
    role.setBounds(icon.getIconWidth()+10,510,230,20);
    rehearsalToken.setBounds(icon.getIconWidth()+10,520,230,20);


    bPane.add(day, new Integer(6));
    bPane.add(activePlayer1, new Integer(6));
    bPane.add(activePlayer2, new Integer(6));
    bPane.add(fame, new Integer(6));
    bPane.add(cash, new Integer(6));
    bPane.add(space, new Integer(6));
    bPane.add(role, new Integer(6));
    bPane.add(rehearsalToken, new Integer(6));

    replaceDie(false);

  }

  /*
  * updateConsole Method
  * @param: String, updates the console with a String
  */
  public void updateConsole(String update)
  {
    text.append(update + "\n");
    text.setCaretPosition(text.getDocument().getLength());
  }


  /*
  * replaceDie Method
  * @param: Boolean (pass in false if you are initializing the die if not pass in true)
  * Replaces the dice to indicate which players turn it is
  */
  public void replaceDie(boolean notInit){

    Player cp = board.activePlayer();
    int level = cp.getRank();
    String img = "images/dice/";
    if(cp.getID() == 1){
      img += "r" + level + ".png";
    }
    else if(cp.getID() == 2){
      img += "b" + level + ".png";
    }
    else if(cp.getID() == 3){
      img += "g" + level + ".png";
    }

    if(notInit){
      bPane.remove(playerLabels.get(10));
      playerLabels.remove(10);
    }
    statPlayerLabel = new JLabel();
    ImageIcon cIcon =  new ImageIcon(img);

    playerLabels.put(10, statPlayerLabel);
    statPlayerLabel.setIcon(cIcon);
    statPlayerLabel.setBounds(icon.getIconWidth()+10, 440,cIcon.getIconWidth()+2,cIcon.getIconHeight());
    statPlayerLabel.setOpaque(true);
    bPane.add(statPlayerLabel, new Integer(7));
    bPane.repaint();
  }

  /*
  * updateStats Method
  * Updates all stats, this method is called after any MouseEvent or ActionEvent to make sure that
  * our display is updated.
  */
  public void updateStats(){

    day.setText("Day: " + board.getDay());
    activePlayer2.setText("Player " + board.activePlayer().getID());
    fame.setText("Fame: " + board.activePlayer().getFame());
    cash.setText("Cash: " + board.activePlayer().getCash());
    space.setText("Current Space: " + board.activePlayer().getSpace().getName());
    if(board.activePlayer().getRole() == null){
      role.setText("Current Role: Not on a role");
    }
    else{
      role.setText("Current Role: " + board.activePlayer().getRole().getName());
    }
    rehearsalToken.setText("Number of Rehearsal Tokens: " + board.activePlayer().getRehearsal());


    bPane.repaint();

  }

  class boardMouseListener implements MouseListener{

      // Code for the different button clicks
      public void mouseClicked(MouseEvent e) {

        Player player = board.activePlayer();
        moveTo.removeAll();

         if (e.getSource()== act){

           if(player.currentRole == null) {

             updateConsole("Not on a scene!");

           }

           else
            player.act(player);

         }

         else if (e.getSource()== rehearse){

           if(player.currentRole == null) {

             updateConsole("Not on a scene!");

           }

          else
            player.rehearse(player);
         }

         else if (e.getSource()== move){

           if(player.moved == false){
             ArrayList neighbors = player.getSpace().getNeighbors();

              for (int i = 0; i < neighbors.size(); i++) {
                item = new JMenuItem(neighbors.get(i).toString());
                item.addActionListener(new MenuActionListener());
                moveTo.add(item);

              }

              moveTo.show(move, move.getWidth()/2, move.getHeight()/2);
              moveToOpen = true;
           }

           else {
             updateConsole("Can't move twice!");
           }
          }

          else if (e.getSource()== rankUp){
            if(board.activePlayer().getSpace().getName().equals("office")){
                for(int i = 2; i <= 6; i++) {
                  item = new JMenuItem("use Fame to rank up to rank " + i);
                  item.addActionListener(new MenuActionListener());
                  rankTo.add(item);
               }
               for(int i = 2; i <= 6; i++) {
                 item = new JMenuItem("use Cash to rank up to rank " + i);
                 item.addActionListener(new MenuActionListener());
                 rankTo.add(item);
              }
              rankTo.show(rankUp, rankUp.getWidth()/2, rankUp.getHeight()/2);
              rankUpOpen = true;
            }
            else{
              updateConsole("Must be on Casting Office");

            }
          }

          else if (e.getSource()== takeRole){

              rolesOpen = true;

              if(player.currentRole != null) {
                updateConsole("Already on a role!");
              }

              else if(player.currentSpace.getName() == "office" || player.currentSpace.getName() == "trailer") {
                updateConsole("You cannot take a role on this space!");

                return;
              }

              else if(player.spaceToSet(player).getIsWrapped() == true) {
                updateConsole("Scene already wrapped!");

              }

              else {
                ArrayList<Role> offCard = player.spaceToSet(player).getRoles();
                ArrayList<Role> onCard = player.spaceToSet(player).getCard().getRoles();
                item = new JMenuItem("On card*");
                roles.add(item);
                for (int i = 0; i < offCard.size(); i++) {
                  item = new JMenuItem(offCard.get(i).getName() + ". Required rank: " + offCard.get(i).getLevel());
                  item.addActionListener(new MenuActionListener());
                  roles.add(item);

                }



                for (int i = 0; i < onCard.size(); i++) {
                  item = new JMenuItem(onCard.get(i).getName() + "* Required rank: " + onCard.get(i).getLevel());
                  item.addActionListener(new MenuActionListener());
                  roles.add(item);

                }

                  roles.show(takeRole, takeRole.getWidth(), takeRole.getHeight());
              }


           }

           else if (e.getSource()== endTurn) {
             player.endTurn(player);
           }

          updateStats();

      }
      public void mousePressed(MouseEvent e) {
      }

      /*
      * @param: MouseEvent
      * Resets all lists that popup on menus
      */
      public void mouseReleased(MouseEvent e) {
        moveTo.removeAll();
        rankTo.removeAll();
        roles.removeAll();
        moveToOpen = false;
        rankUpOpen = false;
        rolesOpen = false;
      }

      public void mouseEntered(MouseEvent e) {
      }

      public void mouseExited(MouseEvent e) {
      }
   }


class MenuActionListener implements ActionListener {

  /*
  * @param: ActionEvent
  * This method is specifically used for the lists that appear when you click a menu with multiple
  * options, and inputs the chosen input
  */
  public void actionPerformed(ActionEvent e) {


    Player player = board.activePlayer();

    if(moveToOpen == true) {
      player.move(player, e.getActionCommand());
      player.moved = true;
    }

    if(rankUpOpen == true) {
      String[] button = e.getActionCommand().split(" ");
      Player cp = board.activePlayer();

      if(button[1].equals("Fame")) {
        player.rankUpUsingFame(cp, Integer.parseInt(button[7]));
      }

      else if(button[1].equals("Cash")) {
        player.rankUpUsingCash(cp, Integer.parseInt(button[7]));
      }

    }
    if(rolesOpen == true) {
      player.takeRole(player,e.getActionCommand());
    }

    updateStats();

  }

}


  /*
  * setCard Method
  * @param: Set object, needs to access the set for mapping into HashMap as well as x y coordinates
  * Adds the cards to the given set
  */
  public void setCard(Set set, int i)
  {
    String img = "images/cards/";
    if(i < 10)
        img += "0"+i+".png";
    else
        img += i+".png";

    cardlabel = new JLabel();
    ImageIcon cIcon =  new ImageIcon(img);
    cardlabel.setIcon(cIcon);
    cardlabel.setBounds(set.getX(),set.getY(),cIcon.getIconWidth()+2,cIcon.getIconHeight());
    cardlabel.setOpaque(true);

    // Add the card to the lower layer
    cardsMap.put(set.getName(), cardlabel);
    bPane.add(cardlabel, new Integer(1));
  }

  /*
  * setCardBacks Method
  * @param: Set object, accesses the x y to place the card
  * Adds card back to all of the card x y
  */
  public void setCardBacks(Set set)
  {

    cardlabel = new JLabel();
    ImageIcon cIcon =  new ImageIcon("images/cards/back.png");
    cardlabel.setIcon(cIcon);
    cardlabel.setBounds(set.getX(),set.getY(),cIcon.getIconWidth()+2,cIcon.getIconHeight());
    cardlabel.setOpaque(true);
    //FOR WHEN A PLAYER HOPS ON A SET
    cardBacks.put(set.getName(),cardlabel);
    bPane.add(cardlabel, new Integer(2));
  }

  /*
  * setPlayer Method
  * @param: id of the player
  * @param: level of player
  * initalized the player images and changes the images when a player ranks up then repaints
  */
  public void setPlayer(int id, int level){
    String img = "images/dice/";
    Player cp = board.getPlayers().get(id - 1);
    if(level < 1 || level > 6){
    }
    else{
      if(level == 1){
        if(cp.getID() == 1){
          img += "r" + level + ".png";
        }
        else if(cp.getID() == 2){
          img += "b" + level + ".png";
          cp.setX(cp.getX() + 42);
        }
        else if(cp.getID() == 3){
          img += "g" + level + ".png";
          cp.setX(cp.getX() + 84);

        }
        else{
          System.out.println("error: invalid player ID in setPlayer");
        }
      }
      else{
        if(cp.getID() == 1){
          img += "r" + level + ".png";
        }
        else if(cp.getID() == 2){
          img += "b" + level + ".png";
        }
        else if(cp.getID() == 3){
          img += "g" + level + ".png";
        }

        bPane.remove(playerLabels.get(cp.getID()));

        playerLabels.remove(cp.getID());
        bPane.repaint();

      }

      playerlabel = new JLabel();
      ImageIcon cIcon =  new ImageIcon(img);

      playerLabels.put(cp.getID(), playerlabel);
      cp.setImageIcon(cIcon);
      cp.setJLabel(playerlabel);
      playerlabel.setIcon(cIcon);
      playerlabel.setBounds(cp.getX() -10,cp.getY()-10,cIcon.getIconWidth()+2,cIcon.getIconHeight());
      playerlabel.setOpaque(true);

      // Add the card to the lower layer
      bPane.add(playerlabel, new Integer(5));


    }


  }

  /*
  * movePlayerImage Method
  * @param: Player object, changes the player x y attribute
  * @param: Int, x coord
  * @param: Int, y coord
  * Changes position of die for the given player
  */
  public void movePlayerImage(Player player, int x , int y){
    player.setX(x);
    player.setY(y);
    player.getJLabel().setBounds(x, y,player.getIcon().getIconWidth()+2,player.getIcon().getIconHeight());

  }

  /*
  * addShotMarkers Method
  * @param: ShotMarker object, each ShotMarker has a unique ID, since they are accessed by x y coordinates
  * Adds shot markers to the indicated position regarding the set
  */
  public void addShotMarkers(ShotMarker sm)
  {

    JLabel shotMs = new JLabel();
    ImageIcon cIcon =  new ImageIcon("images/shotMarker.png");
    shotMs.setIcon(cIcon);
    shotMs.setBounds(sm.getX(),sm.getY(),47,47);
    shotMs.setOpaque(false);

    // Add the card to the lower layer
    bPane.add(shotMs, new Integer(1));
    sMarkersMap.put(sm.getID(), shotMs);

  }

  /*
  * removeShotMarkers Method
  * @param: Set object, requires the set that the shot marker needs to be removed on
  * @param: ShotMarker object, each ShotMarker has a unique ID, since they are accessed by x y coordinates
  * Doesn't actually remove the shotmarker, just makes it invisible. Does this by accessing a HashMap that stores
  * all of the shot markers and making the one that matches the unique ID invisible
  */
  public void removeShotMarkers(Set set, ShotMarker shot)
  {
    sMarkersMap.get(shot.getID()).setVisible(false);
    set.decrementShotMarker();
  }

  /*
  * removeBack Method
  * @param: Set object, removes back on current Set object
  * Accesses the current card back by HashMap by using the set name as a key
  */
  public void removeBack(Set set)
  {
    cardBacks.get(set.getName()).setVisible(false);
  }

  /*
  * wrapSceneUI Method
  * @param: Set object, wraps the current set
  * Removes the card on the set to indicate that it has been wrapped
  */
  public void wrapSceneUI(Set set)
  {
    bPane.remove(cardsMap.get(set.getName()));
    bPane.revalidate();
    bPane.repaint();
  }


  /*
  * resetBoard Method
  * Iterates through all of the HashMaps containing the Jlabels and either resets their visibility, or in
  * the Card case, it removes the remaining cards, since we reinitialize all of the cards at the beginning
  * of a new day
  */
  public void resetBoard()
  {
    Iterator iterator = sMarkersMap.keySet().iterator();

    while (iterator.hasNext()) {
      String key = (String) iterator.next();
      sMarkersMap.get(key).setVisible(true);
    }

    Iterator iterator1 = cardBacks.keySet().iterator();

    while (iterator1.hasNext()) {
      String key = (String) iterator1.next();
      cardBacks.get(key).setVisible(true);
    }

    Iterator iterator2 = cardsMap.keySet().iterator();

    while (iterator2.hasNext()) {
      String key = (String) iterator2.next();
      bPane.remove(cardsMap.get(key));
      bPane.repaint();
    }
  }

  /*
  * disposeBoard Method
  * Exits the board game when the game ends
  */
  public void disposeBoard(){
    dispose();

  }



}
