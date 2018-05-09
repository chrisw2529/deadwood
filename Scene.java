import java.util.ArrayList;
public class Scene extends Space
{
    int shotMarker = 0;
    int budget;
    boolean isWrapped = false;


    public Scene(int spaceID, String name, int budget)
    {
        super(spaceID, name);
        this.budget = budget;
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

    public void incrementShotMarker(){
      shotMarker++;
    }

    public void setWrapped(boolean wrap){
      this.isWrapped = wrap;
    }


}
