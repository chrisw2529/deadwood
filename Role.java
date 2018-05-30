public class Role {

  int level;
  String name;
  String line;
  boolean onScene;
  Set setImOn;
  Player takenBy = null;

  /*
  * creates a role with a specified name, role line, level you need to take the role, the set the role is on and wether or not the role is an on or off scene role.
  */
  public Role(String name, String line, int level, Set setImOn, boolean onScene){

    this.name = name;
    this.line = line;
    this.level = level;
    this.setImOn = setImOn;
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

  public Set getSet()
  {
    return this.setImOn;
  }

  public void setPlayer(Player player)
  {
    this.takenBy = player;
  }




}
