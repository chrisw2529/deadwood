import java.util.ArrayList;
public class Scene extends Space
{
    int shotMarker = 0;
    boolean isWrapped = false;
    ArrayList<player> playersOnScene = new ArrayList<player>();
    ArrayList<player> playersOffScene = new ArrayList<player>();


    public Scene(int spaceID, String name)
    {
        super(spaceID, name);
    }

    public static void act(player player)
    {

      //roll die

    }

    public static void rehearse(player player)
    {

    }

    public static void wrapScene(boolean isWrapped)
    {

    }

    public int getShotMarker(){
      return this.shotMarker;
    }

    public boolean getIsWrapped(){
      return this.isWrapped;
    }

    public ArrayList<player> getplayersOnScene(){
      return this.playersOnScene;
    }

    public ArrayList<player> getplayersOffScene(){
      return this.playersOffScene;
    }

    public void incrementShotMarker(){
      shotMarker++;
    }

    public void setWrapped(boolean wrap){
      this.isWrapped = wrap;
    }


}
