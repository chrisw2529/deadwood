public class Role {

  int level;
  String name;
  String line;
  boolean onScene;
  Set setImOn;
  int budget;
  Player takenBy = null;

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
    return setImOn;
  }

  public int getBudget()
  {
    return budget;
  }

  public void setBudget(int budget)
  {
    this.budget = budget;
  }


  public void setPlayer(Player player)
  {
    this.takenBy = player;
  }




}
