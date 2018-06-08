import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class Player{

    int fame = 0;
    int cash = 0;
    int rank = 1;
    int rehearsalTok = 0;
    int ID;
    Space currentSpace;
    Role currentRole = null;
    boolean isTurn = false;
    boolean moved = false;
    int x = 991;
    int y = 248;
    Board board;
    ImageIcon icon;
    JLabel label;
    BoardUI boardUI;


    /*
    * Player Constructor
    * @param: Takes in an integer to indicate what number player you are
    * We decided not to give our players the chance to create their name because we felt it wasn't needed,
    so they are called from the player number
    */
    public Player(int id)
    {
        this.ID = id;
        this.boardUI = boardUI.getInstance();
        this.board = board.getInstance();

    }

    /*
    * move Method
    * @param: Player object, accesses the player in order to move to player who's turn it is
    * @param: String destination, allows us to iterate through an ArrayList to find the String that matches the SpaceMap, which holds all of our spaces
    * Allows our players to move to a destination, and also calls take role
    */
    public void move(Player player, String destination)
    {


      if(player.currentRole == null){
        Boolean moved = false;

        if(player.isTurn == true) {
          ArrayList<String> neighbors = player.currentSpace.getNeighbors();
          for(int i = 0; i < neighbors.size(); i++){
            if(neighbors.get(i).equals(destination) && moved == false){

              player.currentSpace = board.getSpaceMap().get(neighbors.get(i));
              moved = true;
              boardUI.updateConsole("Player " + player.ID + " has moved to " + player.currentSpace.getName());
              player.setX(player.currentSpace.getXPlayer());
              player.setY(player.currentSpace.getYPlayer());
              int offset = player.getX() -50 + (40 * player.ID);
              player.label.setBounds(offset, player.getY() -10, 40, 40);

              if(player.currentSpace.getName() != "office" && player.currentSpace.getName() != "trailer") {
                Set currSet = spaceToSet(player);
                boardUI.removeBack(currSet);
              }

              break;
            }
          }
          if(moved == false){
            boardUI.updateConsole("You can't move there!");
          }

        }
        else{
          boardUI.updateConsole("Not your turn!");
          return;
        }
      }
      else{
        boardUI.updateConsole("Cannot move while on a role");
      }

    }

    /*
    * takeRole Method
    * @param: Player object, accesses the player in order to give the player a role that they have chosen
    * @param: String, tells the method which role is being taken
    * Takes a role and calls roleQualificationCheck in order to determine if the player is allowed to take the role
    */
    public void takeRole(Player player, String whichRole){

      Set set = spaceToSet(player);
      ArrayList<Role> off = set.getRoles();
      ArrayList<Role> on = set.getCard().getRoles();
      Role roleChosen = null;

      if(whichRole.contains("*")) {

        for (int  i = 0 ; i < on.size() ; i++ ) {
          if(whichRole.contains(on.get(i).getName())){
            roleChosen = on.get(i);
          }
        }
      }
      else {
        for (int  i = 0 ; i < off.size() ; i++ ) {
          if(whichRole.contains(off.get(i).getName())){
            roleChosen = off.get(i);
          }
        }
      }
      roleQualificationCheck(player, roleChosen);
    }

    /*
    * roleQualificationCheck Method
    * @param: Player object, accesses the player in order to access their attributes to check if they're qualified for the role that they have chosen
    * @param: Board object, used to check if there's already a player on the chosen role
    * Checks the attributes of the current player and tells them if they're allowed to
    */
    public void roleQualificationCheck(Player player, Role role){
      if(role.getPlayer() != null){
        boardUI.updateConsole("Role is taken by another player");
      }
      else{
        if(player.rank >= role.getLevel()){
          player.currentRole = role;
          role.setPlayer(player);
          boardUI.updateConsole("You accepted the role of " + player.currentRole.getName());
          this.boardUI = boardUI.getInstance();
          if(role.onScene){
            boardUI.movePlayerImage(player, role.getCard().getSet().getX()  + role.getX(), role.getCard().getSet().getY() + role.getY());
          }
          else{
            boardUI.movePlayerImage(player, role.getX() + 3, role.getY()+ 3);
          }
          endTurn(player);
        }
        else{
          boardUI.updateConsole("You are not ranked high enough");
          boardUI.updateConsole("If you cannot take a role due to your low rank, you must end your turn.");
        }
      }

    }

    /*
    * act Method
    * @param: Player object, accesses the player in order to access their attributes and give them the correct amount when they choose to act
    * @param: Board object, used to access the SpaceMap
    * Allows our players to act if they are currently on a role
    */
    public void act(Player player)
    {
      boardUI = boardUI.getInstance();
      if(player.currentRole == null) {
        boardUI.updateConsole("Not on a scene!");
        return;
      }
      int dieRoll = Board.roleDie();
      if(player.rehearsalTok == 0){
        boardUI.updateConsole("You rolled a " + dieRoll);
      }
      else{
        boardUI.updateConsole("You rolled a " + dieRoll + " plus " + player.rehearsalTok +" for having " + player.rehearsalTok +" rehearsal Token(s) for a total of " + (dieRoll + player.rehearsalTok));

      }
      dieRoll += player.rehearsalTok;

      if(dieRoll >= spaceToSet(player).getCard().getBudget()) {
        if(player.currentRole.onScene == true) {
          player.fame += 2;
          boardUI.updateConsole("Player " + player.ID + " has acted successfully and has gained 2 fames.");
          boardUI.updateConsole("Player " + player.ID + "has " + player.fame + " fame(s), $" + player.cash + ", and is rank " + player.rank);
        }
        else {
          player.fame++;
          player.cash++;
          boardUI.updateConsole("Player " + player.ID + " has acted successfully and has gained 1 fame and 1 cash.");
          boardUI.updateConsole("Player " + player.ID + "has " + player.fame + " fame(s), $" + player.cash + ", and is rank " + player.rank);

        }
      int whichShotMarker = player.spaceToSet(player).getInitShotMarker() - player.spaceToSet(player).getShotMarker();
      boardUI.removeShotMarkers(player.spaceToSet(player), player.spaceToSet(player).getShotMarkers().get(whichShotMarker));
      }
      else {
        boardUI.updateConsole("Player " + player.ID + " has acted unsuccessfully");
        if(player.currentRole.onScene == false) {
          player.cash++;
          boardUI.updateConsole("Since player " + player.ID + " was off-scene, they have gained 1 cash.");
          boardUI.updateConsole("Player " + player.ID + " has " + player.fame + " fame(s), $" + player.cash + ", and is rank " + player.rank);
        }
      }
      endTurn(player);

      if(player.spaceToSet(player).getShotMarker() == 0){
        player.spaceToSet(player).getCard().wrapScene(board);
      }
    }

    /*
    * rehearse Method
    * @param: Player object, accesses the player in order to access their attributes and give them the correct amount when they choose to rehearse
    * @param: Board object, used to access the SpaceMap
    * Allows our players to rehearse if they are currently working on role
    */
    public void rehearse(Player player)
    {
      if(player.currentRole == null) {
        boardUI.updateConsole("Not on a scene!");
        return;
      }
      if(player.rehearsalTok == player.spaceToSet(player).getCard().getBudget() - 1) {
        boardUI.updateConsole("You have too many rehearsal tokens and you must act.");
        return;
      }
      player.rehearsalTok++;
      boardUI.updateConsole("Player " + player.ID + " has increased their rehearsal tokens to " + player.getRehearsal());
      endTurn(player);
    }


    /*
    * rankUpUsingCash Method
    * @param: Player object, accesses the player in order to access their attributes and give them the correct amount when they choose to rank up
    * @param: Integer indicating the desired amount
    * Allows our players to rank up using their cash if they are in the casting office
    */
    public void rankUpUsingCash(Player player, int desiredRank){
      if(!player.getSpace().getName().equals("office")){
        boardUI.updateConsole("You can only rank up while in the Casting Office!");
      }
      else if(desiredRank > 6){
        boardUI.updateConsole("The maximum rank you can be is 6");
      }
      else if(desiredRank <= player.rank){
        boardUI.updateConsole("You must rank UP");
        boardUI.updateConsole("You are currently rank " + player.rank);

      }

      else{
        int cash = player.getCash();
        if((desiredRank-1) * 5 <= cash){
          this.boardUI = boardUI.getInstance();
          player.setCash(cash - (desiredRank-1) * 5);
          player.setRank(desiredRank);
          boardUI.updateConsole("CONGRATULATIONS!! you are now rank " + desiredRank);
          boardUI.setPlayer(ID, desiredRank);
          boardUI.replaceDie(true);

        }
        else{
          boardUI.updateConsole("Don't have enough cash to rank up cash needed is: "+ (desiredRank-1)*5);

        }
      }

    }

    /*
    * rankUpUsingFame Method
    * @param: Player object, accesses the player in order to access their attributes and give them the correct amount when they choose to rank up
    * @param: Integer indicating the desired amount
    * Allows our players to rank up using their fame if they are in the casting office
    */
    public void rankUpUsingFame(Player player, int desiredRank){
      int fame = player.getFame();
      int reqFame = fameNeeded(desiredRank-1, 0);

      if(!player.getSpace().getName().equals("office")){
        boardUI.updateConsole("You can only rank up while in the Casting Office!");
      }
      else if(desiredRank > 6){
        boardUI.updateConsole("The maximum rank you can be is 6");
      }

      else if(desiredRank <= player.rank){
        boardUI.updateConsole("You must rank UP");
        boardUI.updateConsole("You are currently rank " + player.rank);

      }

      else{
        if(fame >= reqFame){
          this.boardUI = boardUI.getInstance();
          player.setFame(fame - reqFame);
          player.setRank(desiredRank);
          boardUI.updateConsole("CONGRATULATIONS!! you are now rank " + desiredRank);
          boardUI.setPlayer(ID, desiredRank);
          boardUI.replaceDie(true);
        }
        else{
          boardUI.updateConsole("Don't have enough fame to rank up fame needed is: "+ reqFame);
        }
      }

    }

    /*
    * fameNeeded method
    * @param: int rank
    * @param: int fame
    * Since the ranking system for the fame is non-linear, we needed a method in order to decide if our player was able to rank up
    */
    private int fameNeeded(int rank, int fame){
      int ret;
      if(rank <= 1){
        return 4;
      }
      else{
        ret =  fameNeeded(rank -1 , fame) +(rank * 2) + 2;
      }
      return ret;
    }

    /*
    * endTurn method
    * @param: Player object, ends the given players turn
    * @param: Board object, finds the next player and makes it their turn
    */
    public void endTurn(Player player) {

      boardUI.updateConsole("Player " + player.ID + "'s turn has ended.");
      player.isTurn = false;
      player.moved = false;
      ArrayList<Player> players = board.getPlayers();
      for(int i = 0; i < players.size(); i++){
        if(players.get(i).getID() == player.ID){
          if(i == players.size()- 1){
            players.get(0).setTurn(true);
            boardUI.updateConsole("It is now Player 1's turn ");
          }
          else{
            players.get(i+1).setTurn(true);
            boardUI.updateConsole("It is now Player " + (i + 2) +"'s turn ");

          }
        }
      }
      boardUI.replaceDie(true);
    }

    /*
    * spaceToSet method
    * @param: Player object, finds the space that the given player is on
    * @param: Board object, accesses the SpaceMap to convert it to the required object, (Set, CastingOffice, or Trailer)
    * This method allows us to access the set that the player is on, since we also have access to the Space that the player is on, we must look for the specific set (if they're on a set)
    */
    public Set spaceToSet(Player player){
      ArrayList<Set> sets = this.board.getSetList();
      for (int i = 0 ; i < sets.size() ; i++) {
        if(sets.get(i).getName().equals(player.currentSpace.getName())){
          return sets.get(i);
        }
      }
      System.out.println("Error: set does not exist");
      return null;
    }


    /*
    * getPlayerInfo method
    * @param: Player object, gives all info of the current player
    * Milestone 2 method
    *(helper method)
    */
    public void getPlayerInfo(Player player)
    {

        System.out.println("the active player is player " + player.ID + " they have $" + player.cash + " and " +player.fame+ " Fame and they are rank " + player.getRank());
        System.out.println("They are on " + player.currentSpace.getName());

        if(currentRole != null) {
          System.out.println("They are working on " + player.currentRole.getName()  + ", \"" + player.currentRole.getLine() + " \"" );
        }

    }


    public int getID(){
      return this.ID;
    }

    public Space getSpace()
    {
      return this.currentSpace;
    }
    public Role getRole(){
      return this.currentRole;
    }

    public int getFame(){
      return this.fame;
    }

    public int getCash(){
      return this.cash;
    }

    public int getRank(){
      return this.rank;
    }

    public int getRehearsal(){
      return this.rehearsalTok;
    }
    public int getX(){
      return this.x;
    }
    public int getY(){
      return this.y;
    }
    public ImageIcon getIcon(){
      return this.icon;
    }
    public JLabel getJLabel(){
      return this.label;
    }

    public boolean isTurn(){
      return this.isTurn;
    }

    public void setFame(int fame){
      this.fame = fame;
    }

    public void setCash(int cash){
      this.cash = cash;
    }

    public void setRank(int rank){
      this.rank = rank;
    }

    public void setRehearsal(int rehearsalTok){
      this.rehearsalTok = rehearsalTok;
    }

    public void setTurn(boolean turn){
      this.isTurn = turn;
    }

    public void setRole(Role role)
    {
      this.currentRole = role;
    }

    public void setSpace(Space space)
    {
      this.currentSpace = space;
    }
    public void setImageIcon(ImageIcon icon){
      this.icon = icon;
    }
    public void setJLabel(JLabel label){
      this.label = label;
    }
    public void setX(int x){
      this.x = x;
    }
    public void setY(int y){
      this.y = y;
    }
}
