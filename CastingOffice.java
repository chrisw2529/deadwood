public class CastingOffice extends Space{

  public CastingOffice(String name)
  {
      super(name);

  }

  /* rankUpUsingCash(Player player)
  * takes a player and ranks
  */
  public static void rankUpUsingCash(Player player, int desiredRank){
    int cash = player.getCash();
    if(desiredRank * 5 <= cash){
      player.setCash(cash - desiredRank * 5);
      player.setRank(desiredRank);

    }
  }
  public static void rankUpUsingFame(Player player, int desiredRank){
    int fame = player.getFame();
    int reqFame = fameNeeded(desiredRank, 0);

    if(fame >= reqFame){
      player.setFame(fame - reqFame);
      player.setRank(desiredRank);

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


}
