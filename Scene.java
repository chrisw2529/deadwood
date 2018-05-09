import java.util.ArrayList;
import java.util.Random;

public class Scene extends Space
{
    int shotMarker;
    int budget;
    boolean isWrapped = false;


    public Scene(int spaceID, String name, int budget, int shotMarker)
    {
        super(spaceID, name);
        this.budget = budget;
        this.shotMarker = shotMarker;
    }

    public static void act(Player player)
    {

      Random rand = new Random();
      int dieRoll = (rand.nextInt(6) + 1) player.getRehearsal();

      if(dieRoll >= budget) {

        if(/*onScene*/)  {
          player.currentSpace.decrementShotMarker();
          player.setFame(player.getFame()+2);
        }

        else {
          player.setFame(player.getFame()+1);
          player.setCash(player.getCash()+1);
        }

      }

      else {

        if(/*offScene*/)
          player.getCash(player.getCash()+1);

      }

      if(scene.getShotMarker() == 0) {

        isWrapped = true;

        if(/* on scene*/) {

          wrapScene();

        }
      }

    }

    public static void rehearse(Player player)
    {

      player.setRehearsal(player.getRehersal()+1);

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
