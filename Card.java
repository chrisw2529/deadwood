import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.*;
import java.lang.*;
public class Card
{
    String name;
    String description;
    int sceneNum;
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

    public void setUpCard(Card card)
    {

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

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setSceneNum(int sceneNum)
    {
        this.sceneNum = sceneNum;
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
      for(int i = 0; i < payout.length / 2; i++)
      {
        int temp = payout[i];
        payout[i] = payout[payout.length - i - 1];
        payout[payout.length - i - 1] = temp;
      }
      //Collections.reversep(Arrays.asList(payout));
      System.out.printf("Modified arr[] : %s\n", Arrays.toString(payout));
      int count = 0;
      while(count < payout.length){
        for(int j = roles.size() - 1; j >= 0; j--){
          Player payTo = roles.get(j).getPlayer();
          if(payTo != null){
            payTo.setCash(payTo.getCash() + payout[count]);
            System.out.println("player " + j + "cash increased by " + payout[count]);

          }
          count++;
          if(count >= payout.length){
            break;
          }
        }
      }
      System.out.println("scene wrapped");
    }

}
