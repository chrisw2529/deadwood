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

    public String getName()
    {
      return this.name;
    }

    public int getShotMarker(){
      return this.shotMarker;
    }

    public int getBudget(){
      return this.budget;
    }

    public Set getSet(){
      return this.set;
    }
    public ArrayList<Role> getRoles()
    {
      return this.roles;
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
    public void wrapScene(Board board)
    {
      //off card bonuses
      for (int i = 0 ; i < set.getRoles().size() ;i++ ) {
        if(set.getRoles().get(i).getPlayer() != null){
          int curCash = set.getRoles().get(i).getPlayer().getCash();
          set.getRoles().get(i).getPlayer().setCash(curCash + budget);
          System.out.println("distributing off card bonus player " + set.getRoles().get(i).getPlayer().getID()+ "'s cash incresed by " + budget+ " dollars");

        }
      }

      //on card bonuses
      int[] payout = new int[getBudget()];
      boolean someOneOnCard = false;
      for(int i = 0 ; i < roles.size(); i++){
        if(roles.get(i).getPlayer() != null){
          someOneOnCard = true;
        }
      }
      if(someOneOnCard){
        System.out.println("distributing on card bonuses");
        for(int i = 0; i< payout.length; i++){
          payout[i] = Board.roleDie();
        }
        Arrays.sort(payout);
        //reverse array order
        for(int i = 0; i < payout.length / 2; i++)
        {
          int temp = payout[i];
          payout[i] = payout[payout.length - i - 1];
          payout[payout.length - i - 1] = temp;
        }
        //Collections.reversep(Arrays.asList(payout));
        System.out.printf("The die rolls where : %s\n", Arrays.toString(payout));
        int count = 0;
        //payout on card roles
        while(count < payout.length){
          for(int j = roles.size() - 1; j >= 0; j--){
            Player payTo = roles.get(j).getPlayer();
            if(payTo != null){
              payTo.setCash(payTo.getCash() + payout[count]);
              System.out.println("player " + (j+1) + "'s cash increased by " + payout[count]);

            }
            count++;
            if(count >= payout.length){
              break;
            }
          }
        }
      }
      else{
        System.out.println("no one was on card no on-card bonuses");
      }

      //reset roles for off scene
      for (int i = 0; i < set.getRoles().size(); i++) {
        if(set.getRoles().get(i).getPlayer() != null){
          set.getRoles().get(i).getPlayer().setRehearsal(0);
          set.getRoles().get(i).getPlayer().setRole(null);
          set.getRoles().get(i).setPlayer(null);
        }

      }
      //reset roles for on scene

      for (int i = 0; i < roles.size(); i++) {
        if(roles.get(i).takenBy != null){
          roles.get(i).getPlayer().setRehearsal(0);
          roles.get(i).getPlayer().setRole(null);
          roles.get(i).setPlayer(null);
        }

      }

      //wrap set
      Set set = getSet();
      set.setIsWrapped(true);
      board.setRemainingScenes(board.getRemainingScenes() - 1);
      if(board.getRemainingScenes() <= 1){
        board.endDay();
      }
      System.out.println("scene wrapped");
      // Set set = getSet();
      // set.setIsWrapped(true);
      // board.setRemainingScenes(board.getRemainingScenes() - 1);
      // if(board.getRemainingScenes() <= 1){
      //   endDay();
      // }
      // System.out.println("scene wrapped");

    }

}
