public class ShotMarker
{
  int x;
  int y;
  String id;
  private static int count = 0;

  public ShotMarker(int x, int y)
  {
    this.x = x;
    this.y = y;
    this.id = Integer.toString(this.count) + "sm";
    count++;
    //System.out.println(this.id);
  }

  public int getX(){
    return this.x;
  }
  public int getY(){
    return this.y;
  }

  public String getID(){
    return this.id;
  }



}
