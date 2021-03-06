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
    int img;
    ArrayList<Role> roles = new ArrayList<Role>();
    Set set = null;
    BoardUI boardUI;


    public Card(String name, int budget)
    {
        this.name = name;
        this.budget = budget;
        boardUI = boardUI.getInstance();
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

    public int getSceneNum()
    {
      return this.sceneNum;
    }

    public int getImg()
    {
      return this.img;
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

    public void setImg(String img)
    {
        this.img = Integer.parseInt(img.substring(0,2));
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

    /*
    * wrapScene is called when all of the shot marker count on a scene is equal to zero or when it is the last remaining scene on the board
    * wrapScene distributes on and off card wrap bonuses and makes it so that players can no longer take a role on that scene.
    */
    public void wrapScene(Board board)
    {

      boardUI.wrapSceneUI(getSet());
      //off card bonuses
      for (int i = 0 ; i < set.getRoles().size() ;i++ ) {
        if(set.getRoles().get(i).getPlayer() != null){
          int curCash = set.getRoles().get(i).getPlayer().getCash();
          set.getRoles().get(i).getPlayer().setCash(curCash + budget);
          boardUI.updateConsole("Distributing off card bonus player " + set.getRoles().get(i).getPlayer().getID()+ "'s cash increased by " + budget+ " dollars");

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
        boardUI.updateConsole("Distributing on card bonuses");
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
        boardUI.updateConsole("The die rolls where : " + Arrays.toString(payout));
        int count = 0;
        //payout on card roles
        while(count < payout.length){
          for(int j = roles.size() - 1; j >= 0; j--){
            Player payTo = roles.get(j).getPlayer();
            if(payTo != null){
              payTo.setCash(payTo.getCash() + payout[count]);
              boardUI.updateConsole("Player " + (payTo.getID()) + "'s cash increased by " + payout[count]);
            }
            count++;
            if(count >= payout.length){
              break;
            }
          }
        }
      }
      else{
        boardUI.updateConsole("No one was on card, no on-card bonuses");
      }

      //reset roles for off scene
      for (int i = 0; i < set.getRoles().size(); i++) {
        if(set.getRoles().get(i).getPlayer() != null){
          int offset = set.getXPlayer() -50 + (40 * set.getRoles().get(i).getPlayer().getID());
          boardUI.movePlayerImage(set.getRoles().get(i).getPlayer(), offset, set.getYPlayer() - 10);
          set.getRoles().get(i).getPlayer().setRehearsal(0);
          set.getRoles().get(i).getPlayer().setRole(null);
          set.getRoles().get(i).setPlayer(null);
        }

      }
      //reset roles for on scene
      for (int i = 0; i < roles.size(); i++) {
        if(roles.get(i).takenBy != null){
          int offset = set.getXPlayer() -50 + (40 * roles.get(i).getPlayer().getID());
          boardUI.movePlayerImage(roles.get(i).getPlayer(), offset, set.getYPlayer() - 10);
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

      boardUI.updateConsole("Scene wrapped");
    }

}
