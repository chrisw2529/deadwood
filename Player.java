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
    * @param: Board object, used to access the SpaceMap
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
              // System.out.println("player " + player.ID + " has moved to " + player.currentSpace.getName());
              // System.out.println("Xpos: "+ board.getSpaceMap().get(player.currentSpace.getName()).getXPlayer());
              boardUI.updateConsole("Player " + player.ID + " has moved to " + player.currentSpace.getName());
              player.setX(player.currentSpace.getXPlayer());
              player.setY(player.currentSpace.getYPlayer());
              int offset = player.getX() -50 + (40 * player.ID);
              player.label.setBounds(offset, player.getY() -10, 40, 40);
              System.out.println("player Xpos: " + player.getX() + "Ypos: " + player.getY());

              if(player.currentSpace.getName() != "office" && player.currentSpace.getName() != "trailer") {
                Set currSet = spaceToSet(player);
                boardUI.removeBack(currSet);
              }

              break;
            }
          }
          if(moved == false){
            boardUI.updateConsole("You can't move there!");
            //System.out.println("you can't move there!");
          }

        }
        else{
          boardUI.updateConsole("Not your turn!");
          return;
        }
      }
      else{
        boardUI.updateConsole("Cannot move while on a role");
        //
        // System.out.println("cannot move while on a role");
      }

    }

    /*
    * takeRole Method
    * @param: Player object, accesses the player in order to give the player a role that they have chosen
    * @param: Board object, to access the Sets in order to access the correct cards corresponding to each Set
    * @param: Boolean, this allows our player to take a role if they decide that would like to take one after not taking one the first time around
    * Takes a role and calls roleQualificationCheck in order to determine if the player is allowed to take the role
    */
    public void takeRole(Player player, String whichRole){
      System.out.println("taker called");
      Set set = spaceToSet(player);
      ArrayList<Role> off = set.getRoles();
      ArrayList<Role> on = set.getCard().getRoles();
      Role roleChosen = null;

      if(whichRole.contains("*")) {

        for (int  i = 0 ; i < on.size() ; i++ ) {
          if(whichRole.contains(on.get(i).getName())){
          //  System.out.println("Role on chosen");
            roleChosen = on.get(i);
          }

        }

      }

      else {

        for (int  i = 0 ; i < off.size() ; i++ ) {
          if(whichRole.contains(off.get(i).getName())){
            roleChosen = off.get(i);
        //    System.out.println("Role chosen");
          }
        }

      }


    //  if(roleChosen == null)
    //    System.out.println("It's null");

            //      System.out.println("tr");
      roleQualificationCheck(player, roleChosen);



      // Scanner sc = new Scanner(System.in);
      // String c = "y";
      //
      // if(player.currentRole != null) {
      //   System.out.println("Already on a role!");
      //   return;
      // }
      //
      // if(player.currentSpace.getName() == "office" || player.currentSpace.getName() == "trailer") {
      //   System.out.println("You cannot take a role on this space!");
      //   return;
      // }
      //
      //
      //
      // if(spaceToSet(player).getIsWrapped() == false){
      //
      //   if (alreadyOnSet == false) {
      //     System.out.println("would you like to take a role? (y/n)");
      //     c = sc.nextLine();
      //   }
      //
      //   Boolean chooseWisely = true;
      //
      //   while(chooseWisely == true){
      //
      //     if(c.equals("y")){
      //       System.out.println("which role would you like to take? or press end to cancel");
      //       Set set = spaceToSet(player);
      //       ArrayList<Role> off = set.getRoles();
      //       ArrayList<Role> on = set.getCard().getRoles();
      //       System.out.println("off");
      //       //System.out.println("size of off is " + off.size());
      //       for (int  i = 0 ; i < off.size() ; i++ ) {
      //         System.out.println("( "+ (i+ 1) +" ) "+ off.get(i).getName() + " requires rank " + off.get(i).getLevel());
      //       }
      //       System.out.println("on");
      //       for (int  i = 0 ; i < on.size() ; i++ ) {
      //         System.out.println("( "+ (i+1) +" ) " + on.get(i).getName() + " requires rank " + on.get(i).getLevel());
      //       }
      //
      //       Boolean notDone = true;
      //
      //       while(notDone){
      //         c = sc.nextLine();
      //         if(c.equals("on 1")){
      //           notDone = (!roleQualificationCheck(player, on.get(0), board));
      //           chooseWisely = false;
      //           // endTurn(player, board);
      //         }
      //         else if(c.equals("on 2")){
      //           try{
      //               notDone = (!roleQualificationCheck(player, on.get(1), board));
      //               chooseWisely = false;
      //               // endTurn(player, board);
      //           }
      //           catch(IndexOutOfBoundsException ex){
      //             System.out.println("role DNE!");
      //           }
      //         }
      //         else if(c.equals("on 3")){
      //           try{
      //               notDone = (!roleQualificationCheck(player, on.get(2), board));
      //               chooseWisely = false;
      //               // endTurn(player, board);
      //           }
      //           catch(IndexOutOfBoundsException ex){
      //             System.out.println("role DNE!");
      //           }
      //         }
      //         else if(c.equals("on 4")){
      //           try{
      //               notDone = (!roleQualificationCheck(player, on.get(3), board));
      //               chooseWisely = false;
      //               // endTurn(player, board);
      //           }
      //           catch(IndexOutOfBoundsException ex){
      //             System.out.println("role DNE!");
      //           }
      //         }
      //         else if(c.equals("off 1")){
      //           notDone = (!roleQualificationCheck(player, off.get(0), board));
      //           chooseWisely = false;
      //           // endTurn(player, board);
      //         }
      //         else if(c.equals("off 2")){
      //           notDone = (!roleQualificationCheck(player, off.get(1), board));
      //           chooseWisely = false;
      //           // endTurn(player, board);
      //         }
      //         else if(c.equals("off 3")){
      //           try{
      //               notDone = (!roleQualificationCheck(player, off.get(2), board));
      //               chooseWisely = false;
      //               // endTurn(player, board);
      //           }
      //           catch(IndexOutOfBoundsException ex){
      //             System.out.println("role DNE!");
      //           }
      //         }
      //         else if(c.equals("off 4")){
      //           try{
      //               notDone = (!roleQualificationCheck(player, off.get(3), board));
      //               chooseWisely = false;
      //               // endTurn(player, board);
      //           }
      //           catch(IndexOutOfBoundsException ex){
      //             System.out.println("role DNE!");
      //           }
      //         }
      //         else if(c.equals("end")){
      //           if(alreadyOnSet != true) {
      //             endTurn(player, board);
      //           }
      //           else {
      //             System.out.println("What would you like to do?");
      //           }if(!if(!
      //
      //         else{
      //           System.out.println("Not a valid entry, use the format 'off (num)'");
      //           System.out.println("For example, 'off 1'");
      //           System.out.println("If you decide you don't want to take a role afterall, type 'end'");
      //         }
      //       }
      //
      //
      //
      //     }
      //     else if(c.equals("n")){
      //       System.out.println("you have decided not to take a role ending turn");
      //       chooseWisely = false;
      //       endTurn(player, board);
      //     }
      //
      //     else if(c.equals("end")){
      //       System.out.println("you have decided not to take a role ending turn");
      //       chooseWisely = false;
      //       endTurn(player, board);
      //     }
      //
      //     else {
      //       System.out.println("Invalid entry, use 'y', 'n'");
      //       c = sc.nextLine();
      //     }
      //
      //   }
      //
      //
      // }
      //
      // else{
      //   System.out.println("scene is wrapped ending turn");
      //   endTurn(player, board);
      // }

    }

    /*
    * roleQualificationCheck Method
    * @param: Player object, accesses the player in order to access their attributes to check if they're qualified for the role that they have chosen
    * @param: Board object, used to check if there's already a player on the chosen role
    * Checks the attributes of the current player and tells them if they're allowed to
    */
    public void roleQualificationCheck(Player player, Role role){
    //  System.out.println("level is : " + role.getName()+ role.getLevel());
    //  System.out.println("p levle ; " + player.rank );
  //  System.out.println("inside rqc");
      if(role.getPlayer() != null){
        boardUI.updateConsole("Role is taken by another player");

      //  System.out.println("role is taken by another player");
        // return false;
      }
      else{
        if(player.rank >= role.getLevel()){
          player.currentRole = role;
          role.setPlayer(player);
          boardUI.updateConsole("You accepted the role of " + player.currentRole.getName());

        //  System.out.println("you accepted the role of " + player.currentRole.getName());
        //  System.out.println("role position X, Y: "+ role.getX()+ ",   " +role.getY());
        //  System.out.println("player is player: " + player.getID());
          this.boardUI = boardUI.getInstance();
          //System.out.println("get x: " + role.getSet().getName());
          if(role.onScene){
            boardUI.movePlayerImage(player, role.getCard().getSet().getX()  + role.getX(), role.getCard().getSet().getY() + role.getY());

          }
          else{
            boardUI.movePlayerImage(player, role.getX() + 3, role.getY()+ 3);
          }
          endTurn(player);
          // return true;
        }
        else{
          boardUI.updateConsole("You are not ranked high enough");
          boardUI.updateConsole("If you cannot take a role due to your low rank, you must end your turn.");

          // System.out.println("you are not ranked high enough");
          // System.out.println("If you cannot take a role due to your low rank, you must end your turn by typing 'end'");
          // return false;

        }
      }

    }

    /*
    * act Method
    * @param: Player object, accesses the player in order to access their attributes and give them the correct amount when they choose to act
    * @param: Board object, used to access the SpaceMap
    * Allows our players to act if they are currently on a role
    */
    public void act(Player player, Board board)
    {

      boardUI = boardUI.getInstance();

      if(player.currentRole == null) {

        //System.out.println("Not on a scene!");
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
      //System.out.println(dieRoll);
      //boardUI.updateConsole("You rolled a " + dieRoll);



      if(dieRoll >= spaceToSet(player).getCard().getBudget()) {
        if(player.currentRole.onScene == true) {
          player.fame += 2;
          // System.out.println("Player " + player.ID + " has acted successfully and has gained 2 fames.");
          // System.out.println("Player " + player.ID + "has " + player.fame + " fame(s), $" + player.cash + ", and is rank " + player.rank);
          boardUI.updateConsole("Player " + player.ID + " has acted successfully and has gained 2 fames.");
          boardUI.updateConsole("Player " + player.ID + "has " + player.fame + " fame(s), $" + player.cash + ", and is rank " + player.rank);
        }

        else {
          player.fame++;
          player.cash++;
          // System.out.println("Player " + player.ID + " has acted successfully and has gained 1 fame and 1 cash.");
          // System.out.println("Player " + player.ID + "has " + player.fame + " fame(s), $" + player.cash + ", and is rank " + player.rank);
          boardUI.updateConsole("Player " + player.ID + " has acted successfully and has gained 1 fame and 1 cash.");
          boardUI.updateConsole("Player " + player.ID + "has " + player.fame + " fame(s), $" + player.cash + ", and is rank " + player.rank);

        }

      //  int sm = player.spaceToSet(player).getShotMarkers().size();
      //  int ism = player.spaceToSet(player).getInitShotMarker();
       //player.spaceToSet(player).decrementShotMarker();
      //  board.decrementShotMarker();
      int whichShotMarker = player.spaceToSet(player).getInitShotMarker() - player.spaceToSet(player).getShotMarker();
      boardUI.removeShotMarkers(player.spaceToSet(player), player.spaceToSet(player).getShotMarkers().get(whichShotMarker));
        //player.spaceToSet(player).getShotMarkers().remove(sm-1);


      }

      else {

        //System.out.println("Player " + player.ID + " has acted unsuccessfully");
        boardUI.updateConsole("Player " + player.ID + " has acted unsuccessfully");


        if(player.currentRole.onScene == false) {
          player.cash++;
          boardUI.updateConsole("Since player " + player.ID + " was off-scene, they have gained 1 cash.");
          boardUI.updateConsole("Player " + player.ID + " has " + player.fame + " fame(s), $" + player.cash + ", and is rank " + player.rank);
          // System.out.println("Since player " + player.ID + " was off-scene, they have gained 1 cash.");
          // System.out.println("Player " + player.ID + "has " + player.fame + " fame(s), $" + player.cash + ", and is rank " + player.rank);

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
    public void rehearse(Player player, Board board)
    {

      if(player.currentRole == null) {

        //System.out.println("Not on a scene!");
        boardUI.updateConsole("Not on a scene!");

        return;

      }

      //somthing is wrong here
      if(player.rehearsalTok == player.spaceToSet(player).getCard().getBudget() - 1) {
      //  System.out.println("You have too many rehearsal tokens and you must act.");
        boardUI.updateConsole("You have too many rehearsal tokens and you must act.");

        return;
      }

      player.rehearsalTok++;
    //  System.out.println("Player " + player.ID + " has increased their rehearsal tokens to " + player.getRehearsal());
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
        // System.out.println("You can only rank up while in the Casting Office!");
        boardUI.updateConsole("You can only rank up while in the Casting Office!");

      }
      else if(desiredRank > 6){
        // System.out.println("the maximum rank you can be is 6");
        boardUI.updateConsole("The maximum rank you can be is 6");

      }

      else if(desiredRank <= player.rank){
        // System.out.println("you must rank UP");
        // System.out.println("you are current rank " + player.rank);
        boardUI.updateConsole("You must rank UP");
        boardUI.updateConsole("You are currently rank " + player.rank);

      }

      else{
        int cash = player.getCash();
        if((desiredRank-1) * 5 <= cash){
          this.boardUI = boardUI.getInstance();
          player.setCash(cash - (desiredRank-1) * 5);
          player.setRank(desiredRank);
        //  System.out.println("CONGRATULATIONS!! you are now rank " + desiredRank);
          boardUI.updateConsole("CONGRATULATIONS!! you are now rank " + desiredRank);

          boardUI.setPlayer(ID, desiredRank);
          boardUI.replaceDie(true);

        }
        else{
        //  System.out.println("Don't have enough cash to rank up cash needed is: "+ (desiredRank-1)*5);
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
        // System.out.println("You can only rank up while in the Casting Office!");
        boardUI.updateConsole("You can only rank up while in the Casting Office!");

      }
      else if(desiredRank > 6){
        // System.out.println("The maximum rank you can be is 6");
        boardUI.updateConsole("The maximum rank you can be is 6");

      }

      else if(desiredRank <= player.rank){
        // System.out.println("you must rank UP");
        // System.out.println("you are current rank " + player.rank);
        boardUI.updateConsole("You must rank UP");
        boardUI.updateConsole("You are currently rank " + player.rank);

      }

      else{
        if(fame >= reqFame){
          this.boardUI = boardUI.getInstance();

          player.setFame(fame - reqFame);
          player.setRank(desiredRank);
          // System.out.println("CONGRATULATIONS!! you are now rank " + desiredRank);
          boardUI.updateConsole("CONGRATULATIONS!! you are now rank " + desiredRank);

          boardUI.setPlayer(ID, desiredRank);
          boardUI.replaceDie(true);



        }
        else{
          //System.out.println("don't have enough fame to rank up fame needed is: "+ reqFame);
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

      //System.out.println("Player " + player.ID + "'s turn has ended.");
      boardUI.updateConsole("Player " + player.ID + "'s turn has ended.");

      player.isTurn = false;
      player.moved = false;
      //System.out.println("moved set to false");
      ArrayList<Player> players = board.getPlayers();
      for(int i = 0; i < players.size(); i++){
        if(players.get(i).getID() == player.ID){
          if(i == players.size()- 1){
            players.get(0).setTurn(true);
            //System.out.println("It is now Player 1's turn ");
            boardUI.updateConsole("It is now Player 1's turn ");

          }
          else{
            players.get(i+1).setTurn(true);
          //  System.out.println("It is now Player" + (i + 2) +"'s turn ");
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
  //  System.out.println("Here?");
      for (int i = 0 ; i < sets.size() ; i++) {
  //      System.out.println("123Here?");
        if(sets.get(i).getName().equals(player.currentSpace.getName())){
          return sets.get(i);
        }
      }
      System.out.println("Error: set does not exist");
      return null;
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

    /*
    * getPlayerInfo method
    * @param: Player object, gives all info of the current player
    */
    public void getPlayerInfo(Player player)
    {

        System.out.println("the active player is player " + player.ID + " they have $" + player.cash + " and " +player.fame+ " Fame and they are rank " + player.getRank());
        System.out.println("They are on " + player.currentSpace.getName());

        if(currentRole != null) {
          System.out.println("They are working on " + player.currentRole.getName()  + ", \"" + player.currentRole.getLine() + " \"" );
        }

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
