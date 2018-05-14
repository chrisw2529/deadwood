import java.util.ArrayList;

public class Board {

  int day = 0;
  int remainingScenes = 10;
  ArrayList<Player> players = new ArrayList<Player>();

  public Board(){



  }

  private static void setupBoard(Board board){

  }

  private static void createScene(){

  }

  private static void createPlayer(){

  }

  private static void endDay(){

  }

  private static void endGame(){

  }

  private static void printAllPlayerLocation(){

  }
  public static int roleDie(){
    Random rand = new Random();
    int dieRoll = (rand.nextInt(6) + 1);
    return dieRoll;
  }
  public int getDay(){
    return this.day;
  }

  public int getRemainingScenes(){
    return this.remainingScenes;
  }

  public void setDay(int day){
    this.day = day;
  }

  public void setRemainingScenes(int remainingScenes){
    this.remainingScenes = remainingScenes;
  }

}
