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
  int playerNum = 0;
  ArrayList<Player> players = new ArrayList<Player>();
  ArrayList<Set> sets = new ArrayList<Set>();
  ArrayList<Card> cards = new ArrayList<Card>();
  HashMap<String,Space> spaceMap = new HashMap<String, Space>();
  Trailer trailer;
  CastingOffice castingOffice;

  private Board() {}

  private static class LazyHolder
  {
    static final Board INSTANCE = new Board();
  }

  public static Board getInstance()
  {
    return LazyHolder.INSTANCE;
  }

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
    System.out.println("it is now day " + day);
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
    this.playerNum = numPlayers;
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
    System.out.println("error: it is no ones turn rn!");
    return null;
  }

  public void endDay(){
    //reset all shot markers to there inital value
    System.out.println("ending day");
    day++;
    if(day > 3 ){
      endGame();
    }
    else{
      //reset sets
      for (int i = 0; i < sets.size();i++ ) {
        sets.get(i).resetShotMarkers();
        sets.get(i).setIsWrapped(false);
        sets.get(i).setCard(null);
        for(int j = 0; j < sets.get(i).getRoles().size(); j++){
          sets.get(i).getRoles().get(j).setPlayer(null);
        }
      }
      //reset cards
      for (int i = 0; i < cards.size();i++ ) {
        cards.get(i).setSet(null);
        for(int j = 0; j < cards.get(i).getRoles().size(); j++){
          cards.get(i).getRoles().get(j).setPlayer(null);
        }
      }
      for(int i = 0; i< players.size(); i++){
        players.get(i).setRehearsal(0);
        players.get(i).setRole(null);
        players.get(i).setSpace(trailer);
      }
      remainingScenes = 10;
      startday();
    }

  }

  private void endGame(){
    System.out.println("The Game is over!");
    int highestScore = -1;
    int highestScoreingPlayer = -1;
    for (int i = 0; i < players.size() ; i++) {
      int fame = players.get(i).getFame();
      int cash = players.get(i).getCash();
      int rank = players.get(i).getRank();
      rank = rank * 5;
      int score = fame + cash + rank;
      System.out.println("Player "+ (i+1) + " has " + score + " points");
      if(highestScore <= score){
        highestScore = score;
        highestScoreingPlayer = i+1;
      }
    }
    System.out.println("the winner is player " + (highestScoreingPlayer)+ " they scored " + highestScore + " points!");
    System.exit(1);
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

  public int getNumOfPlayers(){
    return this.playerNum;
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

  public void printAllPlayerLocation(){

    for (int i = 0; i < players.size(); i++ ) {
      System.out.println("player " +players.get(i).getID() + " is currently at the "+ players.get(i).getSpace().getName());
    }

  }

  public void setRemainingScenes(int remainingScenes){
    this.remainingScenes = remainingScenes;
  }

}
