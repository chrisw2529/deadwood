import java.util.Random;

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

    public void move(Player player)
    {

    }

    public void act(String role)
    {

          Random rand = new Random();
          int dieRoll = (rand.nextInt(6) + 1) + rehearsalTok;

          if(dieRoll >= currentRole.getScene().getBudget()) {

            if(currentRole.onScene == true)  {
              currentRole.getScene().decrementShotMarker();
              fame =+ 2;
            }

            else {
              fame++;
              cash++;
            }

          }

          else {

            if(currentRole.onScene == false)
              cash++;

          }

          if(currentRole.getScene().getShotMarker() == 0) {

            if(currentRole.onScene == true)
              currentRole.getScene().wrapScene();
              //remove all tokens

          }
    }

    public void rehearse(Player player)
    {

    }

    public void endTurn(){

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
