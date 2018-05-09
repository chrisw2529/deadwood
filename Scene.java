import java.util.ArrayList;
import java.util.Random;

public class Scene extends Space
{
    int shotMarker;
    int budget;
    ArrayList<Role> rolesOnScene = new ArrayList<Role>();
    ArrayList<Role> rolesOffScene = new ArrayList<Role>();

    boolean isWrapped = false;


    public Scene(int spaceID, String name, int budget, int shotMarker)
    {
        super(spaceID, name);
        this.budget = budget;
        this.shotMarker = shotMarker;
    }

    public static void wrapScene()
    {

    }

    public int getShotMarker(){
      return this.shotMarker;
    }

    public boolean getIsWrapped(){
      return this.isWrapped;
    }

    public void decrementShotMarker(){
      shotMarker--;
    }

    public void setWrapped(boolean wrap){
      this.isWrapped = wrap;
    }


}
