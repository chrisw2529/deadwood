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
          ArrayList<String> neighbors = currentSpace.getNeighbors();
          for(int i = 0; i < neighbors.size(); i++){
            if(neighbors.get(i).equals(destination) && moved == false){
              currentSpace = board.getSpaceMap().get(neighbors.get(i));
              moved = true;
              System.out.println("player " + player.getID() + " has moved to " + currentSpace.getName());
              takeRole(player, board);
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
    public void takeRole(Player player, Board board){
      Scanner sc = new Scanner(System.in);
      String c = "";
      if(spaceToSet(player, board).getIsWrapped() == false){
        System.out.println("would you like to take a role?");
        c = sc.nextLine();
        if(c.equals("yes")){
          System.out.println("which role would you like to take? or press q to cancel");
          Set set = spaceToSet(player, board);
          ArrayList<Role> off = set.getRoles();
          ArrayList<Role> on = set.getCard().getRoles();
          System.out.println("off");
          System.out.println("size of off is " + off.size());
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
            }
            else if(c.equals("on 2")){
              if(on.get(1)!= null){
                notDone = (!roleQualificationCheck(player, on.get(1)));
              }
              else{
                System.out.println("role DNE!");
              }
            }
            else if(c.equals("on 3")){
              if(on.get(2)!= null){
                notDone = (!roleQualificationCheck(player, on.get(2)));
              }
              else{
                System.out.println("role DNE!");
              }
            }
            else if(c.equals("on 4")){
              if(on.get(3)!= null){
                notDone = (!roleQualificationCheck(player, on.get(3)));
              }
              else{
                System.out.println("role DNE!");
              }
            }
            else if(c.equals("off 1")){
              notDone = (!roleQualificationCheck(player, off.get(0)));
            }
            else if(c.equals("off 2")){
              notDone = (!roleQualificationCheck(player, off.get(1)));
            }
            else if(c.equals("off 3")){
              if(off.get(2)!= null){
                notDone = (!roleQualificationCheck(player, on.get(2)));

              }
              else{
                System.out.println("role DNE!");
              }
            }
            else if(c.equals("off 4")){
              if(off.get(3)!= null){
                notDone = (!roleQualificationCheck(player, on.get(3)));

              }
              else{
                System.out.println("role DNE!");
              }
            }
            else if(c.equals("q")){
              endTurn(player, board);
              notDone = false;
            }
            else{
              System.out.println("invalid entry");
            }
          }



        }
        else{
          System.out.println("scene is wrapped");
        }
      }

    }
    public boolean roleQualificationCheck(Player player, Role role){
      System.out.println("level is : " + role.getName()+ role.getLevel());
      System.out.println("p levle ; " + player.rank );
      if(role.takenBy != null){
        System.out.println("role is takenBy another player");
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

      if(dieRoll >= spaceToSet(player, board).getBudget()) {

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
      player.spaceToSet(player,board).getCard().wrapScene();
        //////////////////////////////////////////////////////
        endTurn(player, board);
    }

    public void rehearse(Player player, Board board)
    {

      if(player.currentRole == null) {

        System.out.println("Not on a scene!");
        return;

      }

      if(player.rehearsalTok == player.currentRole.getLevel() - 1) {
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
      if(!player.getSpace().getName().equals("Casting Office")){
        System.out.println("You can only rank up while in the Casting Office!");
      }
      else{
        int cash = player.getCash();
        if(desiredRank * 5 <= cash){
          player.setCash(cash - desiredRank * 5);
          player.setRank(desiredRank);
          System.out.println("CONGRATULATIONS!! you are now rank " + desiredRank);
        }
        else{
          System.out.println("don't have enough cash to rank up cash needed is: "+ desiredRank*5);
        }
      }

    }
    public static void rankUpUsingFame(Player player, int desiredRank){
      int fame = player.getFame();
      int reqFame = fameNeeded(desiredRank, 0);

      if(!player.getSpace().getName().equals("Casting Office")){
        System.out.println("You can only rank up while in the Casting Office!");
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
