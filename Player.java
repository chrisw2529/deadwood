import java.util.Random;
import java.util.ArrayList;
import java.util.*;



public class Player{

    int fame = 0;
    int cash = 0;
    int rank = 1;
    int rehearsalTok = 0;
    int ID;
    Space currentSpace;
    Role currentRole = null;
    boolean isTurn = false;

    public Player(int id)
    {
        this.ID = id;

    }

    public void move(Player player, String destination, Board board)
    {
      if(player.currentRole == null){
        Boolean moved = false;

        if(player.isTurn == true) {
          ArrayList<String> neighbors = player.currentSpace.getNeighbors();
          for(int i = 0; i < neighbors.size(); i++){
            if(neighbors.get(i).equals(destination) && moved == false){
              player.currentSpace = board.getSpaceMap().get(neighbors.get(i));
              moved = true;
              System.out.println("player " + player.ID + " has moved to " + player.currentSpace.getName());

              if(player.currentSpace.getName() != "office" && player.currentSpace.getName() != "trailer") {
                takeRole(player, board, false);
              }

              break;
            }
          }
          if(moved == false){
            System.out.println("you can't move there!");
          }

        }
        else{
          System.out.println("Not your turn!");
          return;
        }
      }
      else{
        System.out.println("cannot move while on a role");
      }




    }
    public Set spaceToSet(Player player, Board board){
      ArrayList<Set> sets = board.getSetList();
      for (int i = 0 ; i < sets.size() ; i++) {
        if(sets.get(i).getName().equals(player.currentSpace.getName())){
          return sets.get(i);
        }
      }
      System.out.println("Error: set does not exist");
      return null;
    }

    public void takeRole(Player player, Board board, Boolean alreadyOnSet){
      Scanner sc = new Scanner(System.in);
      String c = "y";

      if(player.currentRole != null) {
        System.out.println("Already on a role!");
        return;
      }

      if(player.currentSpace.getName() == "office" || player.currentSpace.getName() == "trailer") {
        System.out.println("You cannot take a role on this space!");
        return;
      }



      if(spaceToSet(player, board).getIsWrapped() == false){

        if (alreadyOnSet == false) {
          System.out.println("would you like to take a role? (y/n)");
          c = sc.nextLine();
        }

        Boolean chooseWisely = true;

        while(chooseWisely == true){

          if(c.equals("y")){
            System.out.println("which role would you like to take? or press end to cancel");
            Set set = spaceToSet(player, board);
            ArrayList<Role> off = set.getRoles();
            ArrayList<Role> on = set.getCard().getRoles();
            System.out.println("off");
            //System.out.println("size of off is " + off.size());
            for (int  i = 0 ; i < off.size() ; i++ ) {
              System.out.println("( "+ (i+ 1) +" ) "+ off.get(i).getName() + " requires rank " + off.get(i).getLevel());
            }
            System.out.println("on");
            for (int  i = 0 ; i < on.size() ; i++ ) {
              System.out.println("( "+ (i+1) +" ) " + on.get(i).getName() + " requires rank " + on.get(i).getLevel());
            }

            Boolean notDone = true;

            while(notDone){
              c = sc.nextLine();
              if(c.equals("on 1")){
                notDone = (!roleQualificationCheck(player, on.get(0)));
                chooseWisely = false;
              }
              else if(c.equals("on 2")){
                if(on.get(1)!= null){
                  notDone = (!roleQualificationCheck(player, on.get(1)));
                  chooseWisely = false;
                }
                else{
                  System.out.println("role DNE!");
                }
              }
              else if(c.equals("on 3")){
                if(on.get(2)!= null){
                  notDone = (!roleQualificationCheck(player, on.get(2)));
                  chooseWisely = false;
                }
                else{
                  System.out.println("role DNE!");
                }
              }
              else if(c.equals("on 4")){
                if(on.get(3)!= null){
                  notDone = (!roleQualificationCheck(player, on.get(3)));
                  chooseWisely = false;
                }
                else{
                  System.out.println("role DNE!");
                }
              }
              else if(c.equals("off 1")){
                notDone = (!roleQualificationCheck(player, off.get(0)));
                chooseWisely = false;
              }
              else if(c.equals("off 2")){
                notDone = (!roleQualificationCheck(player, off.get(1)));
                chooseWisely = false;
              }
              else if(c.equals("off 3")){
                if(off.get(2)!= null){
                  notDone = (!roleQualificationCheck(player, on.get(2)));
                  chooseWisely = false;

                }
                else{
                  System.out.println("role DNE!");
                }
              }
              else if(c.equals("off 4")){
                if(off.get(3)!= null){
                  notDone = (!roleQualificationCheck(player, off.get(3)));
                  chooseWisely = false;

                }
                else{
                  System.out.println("role DNE!");
                }
              }
              else if(c.equals("end")){
                if(alreadyOnSet != true) {
                  endTurn(player, board);
                }
                else {
                  System.out.println("What would you like to do?");
                }
                notDone = false;
                chooseWisely = false;
              }

              else{
                System.out.println("Not a valid entry, use the format 'off (num)'");
                System.out.println("For example, 'off 1'");
                System.out.println("If you decide you don't want to take a role afterall, type 'end'");
              }
            }



          }
          else if(c.equals("n")){
            System.out.println("you have decided not to take a role ending turn");
            chooseWisely = false;
            endTurn(player, board);
          }

          else if(c.equals("end")){
            System.out.println("you have decided not to take a role ending turn");
            chooseWisely = false;
            endTurn(player, board);
          }

          else {
            System.out.println("Invalid entry, use 'y', 'n'");
            c = sc.nextLine();
          }

        }


      }

      else{
        System.out.println("scene is wrapped ending turn");
        endTurn(player, board);
      }

    }

    public boolean roleQualificationCheck(Player player, Role role){
    //  System.out.println("level is : " + role.getName()+ role.getLevel());
    //  System.out.println("p levle ; " + player.rank );
      if(role.takenBy != null){
        System.out.println("role is taken by another player");
        return false;
      }
      else{
        if(player.rank >= role.getLevel()){
          player.currentRole = role;
          role.setPlayer(player);
          System.out.println("you accepted the role of " + player.currentRole.getName());
          return true;
        }
        else{
          System.out.println("you are not ranked high enough");
          System.out.println("If you cannot take a role due to your low rank, you must end your turn by typing 'end'");
          return false;

        }
      }

    }

    public void act(Player player, Board board)
    {

      if(player.currentRole == null) {

        System.out.println("Not on a scene!");
        return;

      }

      int dieRoll = Board.roleDie();
      System.out.println(dieRoll);


      if(dieRoll >= spaceToSet(player, board).getCard().getBudget()) {
        if(player.currentRole.onScene == true) {
          player.fame += 2;
          System.out.println("Player " + player.ID + " has acted successfully and has gained 2 fames.");
          System.out.println("Player " + player.ID + "has " + player.fame + " fame(s), $" + player.cash + ", and is rank " + player.rank);
        }

        else {
          player.fame++;
          player.cash++;
          System.out.println("Player " + player.ID + " has acted successfully and has gained 1 fame and 1 cash.");
          System.out.println("Player " + player.ID + "has " + player.fame + " fame(s), $" + player.cash + ", and is rank " + player.rank);

        }
        System.out.println(player.spaceToSet(player, board).getName());
        player.spaceToSet(player,board).decrementShotMarker();

      }

      else {

        System.out.println("Player " + player.ID + " has acted unsuccessfully");

        if(player.currentRole.onScene == false) {
          player.cash++;
          System.out.println("Since player " + player.ID + " was off-scene, they have gained 1 cash.");
          System.out.println("Player " + player.ID + "has " + player.fame + " fame(s), $" + player.cash + ", and is rank " + player.rank);

        }
      }
//////////////////////////////////////////////// Needs fixing (Wrap scene)
    if(player.spaceToSet(player, board).getShotMarker() == 0)
      player.spaceToSet(player,board).getCard().wrapScene(board);
        //////////////////////////////////////////////////////
        endTurn(player, board);
    }
    //NOT working
    public void rehearse(Player player, Board board)
    {

      if(player.currentRole == null) {

        System.out.println("Not on a scene!");
        return;

      }

      //somthing is wrong here
      if(player.rehearsalTok == player.spaceToSet(player, board).getCard().getBudget() - 1) {
        System.out.println("You have too many rehearsal tokens and you must act.");
        return;
      }

      player.rehearsalTok++;
      System.out.println("Player " + player.ID + " has increased their rehearsal tokens to " + player.getRehearsal());
      endTurn(player, board);

    }


    /* rankUpUsingCash(Player player)
    * takes a player and ranks
    */
    public static void rankUpUsingCash(Player player, int desiredRank){

      if(!player.getSpace().getName().equals("office")){
        System.out.println("You can only rank up while in the Casting Office!");
      }
      else if(desiredRank > 6){
        System.out.println("the maximum rank you can be is 6");
      }

      else if(desiredRank <= player.rank){
        System.out.println("you must rank UP");
        System.out.println("you are current rank " + player.rank);
      }

      else{
        int cash = player.getCash();
        if((desiredRank-1) * 5 <= cash){
          player.setCash(cash - (desiredRank-1) * 5);
          player.setRank(desiredRank);
          System.out.println("CONGRATULATIONS!! you are now rank " + desiredRank);
        }
        else{
          System.out.println("don't have enough cash to rank up cash needed is: "+ (desiredRank-1)*5);
        }
      }

    }
    public static void rankUpUsingFame(Player player, int desiredRank){
      int fame = player.getFame();
      int reqFame = fameNeeded(desiredRank-1, 0);

      if(!player.getSpace().getName().equals("office")){
        System.out.println("You can only rank up while in the Casting Office!");
      }
      else if(desiredRank > 6){
        System.out.println("the maximum rank you can be is 6");
      }

      else if(desiredRank <= player.rank){
        System.out.println("you must rank UP");
        System.out.println("you are current rank " + player.rank);
      }

      else{
        if(fame >= reqFame){
          player.setFame(fame - reqFame);
          player.setRank(desiredRank);
          System.out.println("CONGRATULATIONS!! you are now rank " + desiredRank);

        }
        else{
          System.out.println("don't have enough fame to rank up fame needed is: "+ reqFame);
        }
      }

    }
    private static int fameNeeded(int rank, int fame){
      int ret;


      if(rank <= 1){
        return 4;
      }
      else{
        ret =  fameNeeded(rank -1 , fame) +(rank * 2) + 2;
      }
      return ret;
    }


    public void endTurn(Player player, Board board) {

      System.out.println("Player " + player.ID + "'s turn has ended.");
      player.isTurn = false;
      ArrayList<Player> players = board.getPlayers();
      for(int i = 0; i < players.size(); i++){
        if(players.get(i).getID() == player.ID){
          if(i == players.size()- 1){
            players.get(0).setTurn(true);
            System.out.println("it is now player 1's turn ");
          }
          else{
            players.get(i+1).setTurn(true);
            System.out.println("it is now player" + (i + 2) +"'s turn ");


          }
        }
      }

    }

    public int getID(){
      return this.ID;
    }

    public Space getSpace()
    {
      return this.currentSpace;
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

    public void getPlayerInfo(Player player, Board board)
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

}
