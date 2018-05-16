import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

public class Board {

  int day = 0;
  int remainingScenes = 10;
  ArrayList<Player> players = new ArrayList<Player>();
  ArrayList<Set> sets = new ArrayList<Set>();
  HashMap<Set,String> setMap = new HashMap<>();

  public Board(){


  }

  public void setupBoard(Board board){

    ParseXML p = new ParseXML();

    try {
      Document d = p.getDocFromFile("board.xml");
      p.readSceneData(d, board);
      p.readForOffice(d, board);
      p.readForTrailer(d, board);
    }
    catch(ParserConfigurationException ex) {
      System.out.println("file not found");

    }

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

  public void addToSets(Set set)
  {
    this.sets.add(set);
  }

  public int getRemainingScenes(){
    return this.remainingScenes;
  }

  public void setDay(int day){
    this.day = day;
  }

  public void printSets()
  {
    for (int i = 0; i < sets.size() ; i++ ) {
      System.out.println(sets.get(i).getName());
      sets.get(i).getRoles();
    }
  }

  public void setRemainingScenes(int remainingScenes){
    this.remainingScenes = remainingScenes;
  }

}
