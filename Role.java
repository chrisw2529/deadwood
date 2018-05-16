public class Role {

  int level;
  String name;
  String line;
  boolean onScene;
  Player takenBy = null;

  public Role(String name, String line, int level, boolean onScene){

    this.name = name;
    this.line = line;
    this.level = level;
    this.onScene = onScene;
  }


  public String getName()
  {
    return this.name;
  }

  public String getLine()
  {
    return this.line;
  }
  public int getLevel()
  {
    return this.level;
  }
  public boolean getOnScene()
  {
      return onScene;
  }
  public Player getPlayer()
  {
    return this.takenBy;
  }
  public void setPlayer(Player player)
  {
    this.takenBy = player;
  }




}
