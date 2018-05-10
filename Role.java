public class Role {

  int level;
  String name;
  String line;
  boolean onScene;
  Player takenBy = null;
  Scene scene;

  public Role(String name, String line, int level, boolean onScene, Scene scene){

    this.name = name;
    this.line = line;
    this.level = level;
    this.onScene = onScene;
    this.scene = scene;

  }


  public String getName()
  {
    return this.name;
  }

  public String getLine()
  {
    return this.line;
  }

  public Player getPlayer()
  {
    return this.takenBy;
  }

  public Scene getScene()
  {
    return this.scene;
  }

  public void checkWhere()
  {
    if(onScene == false)
      System.out.println("Off scene.");

    else
      System.out.println("On scene.");
  }

  public void removePlayer()
  {
    this.takenBy = null;
  }


}
