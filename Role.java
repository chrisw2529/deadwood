public class Role {

  int level;
  String name;
  String line;
  boolean onScene;
  Set setImOn;
  Card card;
  Player takenBy = null;
  int x;
  int y;
  int h;
  int w;

  /*
  * creates a role with a specified name, role line, level you need to take the role, the set the role is on and wether or not the role is an on or off scene role.
  */
  public Role(String name, String line, int level, Set setImOn, Card card, boolean onScene, int x, int y, int h, int w){

    this.name = name;
    this.line = line;
    this.level = level;
    this.setImOn = setImOn;
    this.card = card;
    this.onScene = onScene;
    this.x = x;
    this.y = y;
    this.h = h;
    this.w = w;
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
      return this.onScene;
  }
  public Player getPlayer()
  {
    return this.takenBy;
  }

  public Set getSet()
  {
    return this.setImOn;
  }
  public Card getCard()
  {
    return this.card;
  }

  public void setPlayer(Player player)
  {
    this.takenBy = player;
  }

  public int getX(){
    return this.x;
  }
  public int getY(){
    return this.y;
  }
  public int getH(){
    return this.h;
  }
  public int getW(){
    return this.w;
  }

  public void setSet(Set set)
  {
    this.setImOn = set;
  }



}
