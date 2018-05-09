public class Player{

    int fame = 0;
    int cash = 0;
    int rank = 0;
    int rehearsalTok = 0;
    int ID = 0;
    Space currentSpace;
    Role currentRole == null;
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
          int dieRoll = (rand.nextInt(6) + 1) + player.getRehearsal();

          if(dieRoll >= budget) {

            if(/*onScene*/)  {
              player.currentSpace.decrementShotMarker();
              player.setFame(player.getFame()+2);
            }

            else {
              player.setFame(player.getFame()+1);
              player.setCash(player.getCash()+1);
            }

          }

          else {

            if(/*offScene*/)
              player.getCash(player.getCash()+1);

          }

          if(scene.getShotMarker() == 0) {

            isWrapped = true;

            if(/* on scene*/) {

              wrapScene();

            }
          }
    }

    public void rehearse(Player player)
    {

      if(currentRole != null) {

        if()
        player.setRehearsal(player.getRehersal()+1);

    }

    public void endTurn(){

    }

    public void getLocation()
    {
      return player.currentSpace.getName();
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
