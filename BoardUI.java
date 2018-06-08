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

  //JComboBox<String> moveTo;

  JLabel boardlabel;
  JLabel cardlabel;
  JLabel playerlabel;
  JLabel statPlayerLabel;
  JLabel mLabel;
  JLabel activePlayer1;
  JLabel activePlayer2;
  JLabel fame;
  JLabel cash;
  JLabel space;
  JLabel role;
  JLabel rehearsalToken;


  ImageIcon icon;

  HashMap<Integer,JLabel> playerLabels = new HashMap<Integer,JLabel>();
  HashMap<String,JLabel> sMarkersMap = new HashMap<String,JLabel>();
  HashMap<String,JLabel> cardBacks = new HashMap<String,JLabel>();
  HashMap<String,JLabel> cardsMap = new HashMap<String,JLabel>();
  JLayeredPane bPane = getLayeredPane();

  private JScrollPane scroller;
  private JTextArea text;
  Board board = null;

  JPopupMenu moveTo;
  JPopupMenu rankTo;
  JPopupMenu roles;
  Boolean moveToOpen = false;
  Boolean rankUpOpen = false;
  Boolean rolesOpen = false;
  JMenuItem item;
  ActionListener menuListener;
  private boolean clicked = false;


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

    //moveTo = new JComboBox<String>();

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

    //bPane.add(moveTo, new Integer(3));
  //  moveTo.setVisible(false);

   moveTo = new JPopupMenu();
   rankTo = new JPopupMenu();
   roles = new JPopupMenu();
   //menuListener = new ActionListener();





   text = new JTextArea("Game information\n");
   text.setEditable(false);
   scroller = new JScrollPane(text);

   scroller.setBounds(icon.getIconWidth()+10,550,350,300);
   bPane.add(scroller, new Integer(2));

  //  text.append("Hello\n" );
   //
  //  text.append("My name is\n" );
  //  text.append("Tony");

   statsInit();
   //JScrollPane scrPane = new JScrollPane(bPane);
  }

  public void statsInit(){

    activePlayer1 = new JLabel("Active Player: ");
    activePlayer2 = new JLabel("Player " + board.activePlayer().getID());
    fame = new JLabel("Fame: " + 0);
    cash = new JLabel("Cash: " + 0);
    space = new JLabel("Current Space: " + board.activePlayer().getSpace().getName());
    role = new JLabel("Current Role: Not on a role ");
    rehearsalToken = new JLabel("Number of rehearsal Tokens: 0");



    activePlayer1.setBounds(icon.getIconWidth()+10,400,130,20);
    activePlayer2.setBounds(icon.getIconWidth()+10,410,130,20);
    fame.setBounds(icon.getIconWidth()+10,460,130,20);
    cash.setBounds(icon.getIconWidth()+10,470,130,20);
    space.setBounds(icon.getIconWidth()+10,480,230,20);
    role.setBounds(icon.getIconWidth()+10,490,230,20);
    rehearsalToken.setBounds(icon.getIconWidth()+10,500,230,20);



    bPane.add(activePlayer1, new Integer(6));
    bPane.add(activePlayer2, new Integer(6));
    bPane.add(fame, new Integer(6));
    bPane.add(cash, new Integer(6));
    bPane.add(space, new Integer(6));
    bPane.add(role, new Integer(6));
    bPane.add(rehearsalToken, new Integer(6));





  }
  public void replaceDie(){

  }

  public void updateConsole(String update)
  {
    text.append(update + "\n");
    text.setCaretPosition(text.getDocument().getLength());
  }




  public void updateStats(){
    Player cp = board.activePlayer();
    int level = cp.getRank();
    String img = "";
    if(cp.getID() == 1){
      img += "r" + level + ".png";
    }
    else if(cp.getID() == 2){
      img += "b" + level + ".png";
    }
    else if(cp.getID() == 3){
      img += "g" + level + ".png";
    }


    statPlayerLabel = new JLabel();
    ImageIcon cIcon =  new ImageIcon(img);

    playerLabels.put(10, statPlayerLabel);
    statPlayerLabel.setIcon(cIcon);
    statPlayerLabel.setBounds(icon.getIconWidth()+10, 410,cIcon.getIconWidth()+2,cIcon.getIconHeight());
    playerlabel.setOpaque(true);

    // Add the card to the lower layer
    bPane.add(statPlayerLabel, new Integer(5));






    activePlayer2.setText("Player " + board.activePlayer().getID());
    fame.setText("Fame: " + board.activePlayer().getFame());
    cash.setText("Cash: " + board.activePlayer().getCash());
    space.setText("Current Space: " + board.activePlayer().getSpace().getName());
    if(board.activePlayer().getRole() == null){
      role.setText("Current Role: Not on a role ");
    }
    else{
      role.setText("current Role " + board.activePlayer().getRole().getName());
    }
    rehearsalToken.setText("Number of rehearsal Tokens: " + board.activePlayer().getRehearsal());


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
            player.act(player, board);

         }

         else if (e.getSource()== rehearse){

           if(player.currentRole == null) {

             updateConsole("Not on a scene!");

           }

          else
            player.rehearse(player, board);
         }

         else if (e.getSource()== move){

          // System.out.println("Move clicked");

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
             //System.out.println("Can't move twice!");
             updateConsole("Can't move twice!");
           }


            //Object selected = moveTo.getSelectedItem();
            //player.move(player, selected.toString(), board);
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
              //System.out.println("must be on casting Office");
              updateConsole("Must be on Casting Office");

            }
          }

          else if (e.getSource()== takeRole){

              rolesOpen = true;

              if(player.currentRole != null) {
              //  System.out.println("Already on a role!");
                updateConsole("Already on a role!");


              }

              else if(player.currentSpace.getName() == "office" || player.currentSpace.getName() == "trailer") {
              //  System.out.println("You cannot take a role on this space!");
                updateConsole("You cannot take a role on this space!");

                return;
              }

              else if(player.spaceToSet(player).getIsWrapped() == true) {
                //System.out.println("Scene already wrapped!");
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

           else if (e.getSource()== endTurn){

             player.endTurn(player);
           }

          updateStats();

      }
      public void mousePressed(MouseEvent e) {
      }
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
  //  @Override
  public void actionPerformed(ActionEvent e) {


    //clicked = true;
    System.out.println(e.getActionCommand() + " pressed");
          Player player = board.activePlayer();

    if(moveToOpen == true){
    //  System.out.println("Should be true;");
      player.move(player, e.getActionCommand());
      player.moved = true;
      //clicked = false;
      //System.out.println("Print");
    }
    if(rankUpOpen == true){
      //System.out.println("ranking up");
      String[] button = e.getActionCommand().split(" ");
      Player cp = board.activePlayer();
      if(button[1].equals("Fame")){
        //System.out.println("ru using fame to rank " + button[7]);
        player.rankUpUsingFame(cp, Integer.parseInt(button[7]));
      }
      else if(button[1].equals("Cash")){
        //System.out.println("ru using cash to rank " + button[7]);
        player.rankUpUsingCash(cp, Integer.parseInt(button[7]));
      }
    }
    if(rolesOpen == true){
      player.takeRole(player,e.getActionCommand());
      System.out.println("You have chosen..." + e.getActionCommand());
    }
    updateStats();

  }

}


//   ActionListener menuActionListener = new ActionListener(){
//
//     @Override
//     public void actionPerformed(ActionEvent e) {
//         JLabel.setText(e.getActionCommand());
//     }
//
// };

  // private void addButton(JPanel panel, JButton button, String label) {
  //
  //   button = new JButton(label);
  //   panel.add(button);
  //   button.addActionListener(this);
  //
  // }

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
    cardsMap.put(set.getName(), cardlabel);
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
    cardBacks.put(set.getName(),cardlabel);
    bPane.add(cardlabel, new Integer(2));
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
        }
        else if(cp.getID() == 2){
          img += "b" + level + ".png";
        }
        else if(cp.getID() == 3){
          img += "g" + level + ".png";
        }

        bPane.remove(playerLabels.get(cp.getID()));

        playerLabels.remove(cp.getID());
        System.out.println("repainting");
        bPane.repaint();

      }

      playerlabel = new JLabel();
      ImageIcon cIcon =  new ImageIcon(img);

      playerLabels.put(cp.getID(), playerlabel);
      System.out.println("img: " + img);
      System.out.println("player x: "+ cp.getX() + " y: " + cp.getY());
      cp.setImageIcon(cIcon);
      cp.setJLabel(playerlabel);
      playerlabel.setIcon(cIcon);
      playerlabel.setBounds(cp.getX() -10,cp.getY()-10,cIcon.getIconWidth()+2,cIcon.getIconHeight());
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
    //System.out.println(sm.getID());
    sMarkersMap.put(sm.getID(), shotMs);

  }

  public void removeShotMarkers(Set set, ShotMarker shot)
  {

    //bPane.remove(sMarkersMap.get(sm.getX()+sm.getY()));
    sMarkersMap.get(shot.getID()).setVisible(false);
    System.out.println("Setting this to INVIS!!"+shot.getX()%shot.getY());
    //bPane.repaint();
    set.decrementShotMarker();
    System.out.println(set.getShotMarker());
    //set.getShotMarkers().remove(0);
    System.out.println("SHOT MARKER REMOVED");

  }

  public void removeBack(Set set)
  {

    System.out.println(set.getName());
    cardBacks.get(set.getName()).setVisible(false);
  }

  public void wrapSceneUI(Set set)
  {
    bPane.remove(cardsMap.get(set.getName()));
    bPane.revalidate();
    bPane.repaint();
  }

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
      //cardsMap.get(key).setVisible(true);
      bPane.remove(cardsMap.get(key));
      bPane.repaint();
    }
  }



}
