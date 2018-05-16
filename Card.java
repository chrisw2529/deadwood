import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class Card
{
    String name;
    int shotMarker;
    int budget;
    boolean isWrapped = false;
    ArrayList<Role> roles = new ArrayList<Role>();
    Set set = null;



    public Card(String name, int budget)
    {
        this.name = name;
        this.budget = budget;
    }


    public int getShotMarker(){
      return this.shotMarker;
    }

    public int getBudget(){
      return this.budget;
    }

    public boolean getIsWrapped(){
      return this.isWrapped;
    }

    public Set getSet(){
      return this.set;
    }

    public void setSet(Set set){
      this.set = set;
    }

    public void addRoles(Role role){
      roles.add(role);
    }

    //$$$$$$$
    //to do
    //$$$$$$$
    public void wrapScene()
    {
      int[] payout = new int[getBudget()];
      for(int i = 0; i< payout.length; i++){
        payout[i] = Board.roleDie();
      }
      Arrays.sort(payout);
      System.out.printf("Modified arr[] : %s", Arrays.toString(payout));

    }

}
