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
import java.util.Random;
import java.lang.Object;
import java.util.Collections;

public class Board {

  int day = 1;
  int remainingScenes = 10;
  ArrayList<Player> players = new ArrayList<Player>();
  ArrayList<Set> sets = new ArrayList<Set>();
  ArrayList<Card> cards = new ArrayList<Card>();
  HashMap<String,Space> spaceMap = new HashMap<String, Space>();
  Trailer trailer;
  CastingOffice castingOffice;

  public void setupBoard(Board board){

    createScene(board);
    startday();
  }

  public void startday()
  {

    Collections.shuffle(cards);

    for (int i = 0; i < sets.size(); i++){
      sets.get(i).setCard(cards.get(i));
      cards.get(i).setSet(sets.get(i));
    }

  }


  private static void createScene(Board board){

    ParseXML p = new ParseXML();

    try {
      Document d = p.getDocFromFile("XML_Files/board.xml");
      p.readSceneData(d, board);
      p.readForOffice(d, board);
      p.readForTrailer(d, board);
    }
    catch(ParserConfigurationException ex) {
      System.out.println("file not found");

    }



    try {
      Document d = p.getDocFromFile("XML_Files/cards.xml");
      p.readCardData(d, board);
    }
    catch(ParserConfigurationException ex) {
      System.out.println("file not found");

    }



  }

  public void initializePlayers(int numPlayers){
    for(int i = 1; i < numPlayers + 1; i++){
      Player player = new Player(i);
      players.add(player);
      player.setSpace(trailer);
      if(i == 1){
        player.setTurn(true);
      }
    }
  }
  public Player activePlayer(){
    for (int i = 0; i< players.size() ; i++ ) {
      if (players.get(i).isTurn()){
        return players.get(i);
      }
    }
    System.out.println("it is no ones turn rn!");
    return null;
  }

  private static void endDay(){

  }

  private static void endGame(){

  }

  public void printAllPlayerLocation(){

    for (int i = 0; i < players.size(); i++ ) {
      System.out.println("player " +players.get(i).getID() + " is currently at the "+ players.get(i).getSpace().getName());
    }

  }
  public static int roleDie(){
    Random rand = new Random();
    int dieRoll = (rand.nextInt(6) + 1);
    return dieRoll;
  }
  public int getDay(){
    return this.day;
  }
  public  HashMap<String,Space> getSpaceMap(){
    return this.spaceMap;
  }

  public void addToSets(Set set)
  {
    this.sets.add(set);
  }

  public void addToCards(Card card)
  {
    this.cards.add(card);
  }

  public void addToMap(String name, Space space)
  {
    this.spaceMap.put(name, space);
  }

  public int getRemainingScenes(){
    return this.remainingScenes;
  }

  public ArrayList<Player> getPlayers(){
    return this.players;
  }

  public ArrayList<Card> getCardList(){
    return this.cards;
  }

  public ArrayList<Set> getSetList(){
    return this.sets;
  }

  public Trailer getTrailer(){
    return this.trailer;
  }
  public CastingOffice getCastingOffice(){
    return this.castingOffice;
  }

  public void setDay(int day){
    this.day = day;
  }

  public void setTrailer(Trailer trailer){
    this.trailer = trailer;
  }

  public void setCastingOffice(CastingOffice castingOffice){
    this.castingOffice = castingOffice;
  }

  public void printSets()
  {
    for (int i = 0; i < sets.size() ; i++ ) {
      System.out.println(sets.get(i).getName());
      sets.get(i).getRoles();
    }
  }

  public void printScenes()
  {
    for (int i = 0; i < sets.size() ; i++ ) {
      System.out.println(sets.get(i).getCard().getName() + " on " + sets.get(i).getName());
    }
  }

  public void setRemainingScenes(int remainingScenes){
    this.remainingScenes = remainingScenes;
  }

}
