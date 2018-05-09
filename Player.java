public class Player{

    int fame = 0;
    int cash = 0;
    int rank = 0;
    int rehearsalTok = 0;
    int ID = 0;
    boolean isTurn = false;

    public Player(int id)
    {
        this.ID = id;
    }

    public static void move(Player player)
    {

    }

    public static void endTurn(){

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

    public int getRehersal(){
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

    public void setRehersal(int rehearsalTok){
      this.rehearsalTok = rehearsalTok;
    }

    public void setTurn(boolean turn){
      this.isTurn = turn;
    }

}
