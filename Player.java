import java.util.Random;
import java.util.ArrayList;


public class Player{

    int fame = 0;
    int cash = 0;
    int rank = 0;
    int rehearsalTok = 0;
    int ID = 0;
    Space currentSpace;
    Role currentRole = null;
    boolean isTurn = false;

    public Player(int id)
    {
        this.ID = id;

    }

    public void move(Player player, String destination, Board board)
    {

      if(player.isTurn == true) {
        ArrayList<String> neighbors = currentSpace.getNeighbors();
        for(int i = 0; i < neighbors.size(); i++){
          if(neighbors.get(i).equals(destination)){
            currentSpace = board.getSpaceMap().get(neighbors.get(i));
          }
        }

      }
      else{
        System.out.println("Not your turn!");
        return;
      }

    }

    public void act(Player player)
    {

      if(player.currentRole == null) {

        System.out.println("Not on a scene!");
        return;

      }

      int dieRoll = Board.roleDie();
      System.out.println(dieRoll);
      System.out.println("budget " + player.currentRole.getBudget());

      if(dieRoll >= player.currentRole.getBudget()) {

        if(player.currentRole.onScene == true) {
          player.fame += 2;
          System.out.println("Player " + player.ID + " has acted successfully and has gained 2 fames.");
        }

        else {
          player.fame++;
          player.cash++;
          System.out.println("Player " + player.ID + " has acted successfully and has gained 1 fame and 1 cash.");
        }

        player.currentRole.getSet().decrementShotMarker();

      }

      else {

        System.out.println("Player " + player.ID + " has acted unsuccessfully");

        if(player.currentRole.onScene == false) {
          player.cash++;
          System.out.println("Since player " + player.ID + " was off-scene, they have gained 1 cash.");
        }
      }
////////////////////////////////////////////////// Needs fixing (Wrap scene)
    //if(player.currentRole.getSet().getShotMarker() == 0)
//player.currentRole.getSet().wrapScene();
        ////////////////////////////////////////////////////////


      player.isTurn = false;
      System.out.println("Player " + player.ID + "'s turn has ended.");
    }

    public void rehearse(Player player)
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

    }

    public void endTurn(Player player) {

      System.out.println("Player " + player.ID + "'s turn has ended.");
      player.isTurn = false;

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
