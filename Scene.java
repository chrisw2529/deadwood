import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class Scene extends Space
{
    int shotMarker;
    int budget;
    ArrayList<Role> roles = new ArrayList<Role>();


    boolean isWrapped = false;


    public Scene(int spaceID, String name, int budget, int shotMarker)
    {
        super(spaceID, name);
        this.budget = budget;
        this.shotMarker = shotMarker;
    }

    public static void wrapScene()
    {
      int[] payout = new int[budget];
      for(int i = 0; i< payout.size(); i++){
        payout[i] = roleDie();
      }
      Arrays.sort(payout);
      System.out.printf("Modified arr[] : %s", Arrays.toString(arr));
      
    }

    public int getShotMarker(){
      return this.shotMarker;
    }

    public boolean getIsWrapped(){
      return this.isWrapped;
    }

    public int getBudget(){
      return this.budget;
    }

    public void decrementShotMarker(){
      shotMarker--;
    }

    public void setWrapped(boolean wrap){
      this.isWrapped = wrap;
    }


}
