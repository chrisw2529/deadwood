import java.util.Random;

public class Player{

    int fame = 0;
    int cash = 0;
    int rank = 0;
    int rehearsalTok = 0;
    int ID = 0;
    Space currentSpace = 0;
    Role currentRole = null;
    boolean isTurn = false;

    public Player(int id)
    {
        this.ID = id;
    }

    public void move(Player player)
    {

      if(player.isTurn == false) {

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

      Random rand = new Random();
      int dieRoll = (rand.nextInt(6) + 1) + player.rehearsalTok;

      if(dieRoll >= player.currentRole.getScene().getBudget()) {

        if(player.currentRole.onScene == true) {
          player.fame += 2;
          System.out.println("Player " + player.ID + " has acted successfully and has gained 2 fames.");
        }

        else {
          player.fame++;
          player.cash++;
          System.out.println("Player " + player.ID + " has acted successfully and has gained 1 fame and 1 cash.");
        }

        currentRole.getScene().decrementShotMarker();

      }

      else {

        System.out.println("Player " + player.ID + " has acted unsuccessfully");

        if(player.currentRole.onScene == false) {
          player.cash++;
          System.out.println("Since player " + player.ID + " was off-scene, they have gained 1 cash.");
        }
      }

      if(player.currentRole.getScene().getShotMarker() == 0) {

        wrapScene();

      }

      player.isturn = false;
      System.out.println("Player " + player.ID + "'s turn has ended.");
    }

    public void rehearse(Player player)
    {

      if(player.getRehearsal == player.currentRole.getScene().getBudget() - 1) {
        System.out.println("You have too many rehearsal tokens and you must act.");
        return;
      }

      player.rehearsalTok++;
      System.out.println("Player " + player.ID + " has increased their rehearsal tokens to " + player.getRehearsal());

    }

    public void endTurn(Player player) {

      System.out.println("Player " + player.ID + "'s turn has ended.");
      player.isTurn == false;

    }

    public int getID(){
      return this.ID;
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

}
