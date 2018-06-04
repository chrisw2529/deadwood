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
  BoardUI boardUI;

  private Board() {}

  //Holder for the board
  private static class LazyHolder
  {
    static final Board INSTANCE = new Board();
  }
  //getter for the board
  public static Board getInstance()
  {
    return LazyHolder.INSTANCE;
  }
  /*
  *takes in the board and calls parseXML and then startDay
  */
  public void setupBoard(Board board){

    parseXML(board);
    startDay();
    for(int i = 0; i < players.size();i++){
      boardUI.setPlayer(i, 1);
    }
  }
  /*
  *startDay shuffles all of the cards and places then in random order on the board spaces
  */
  public void startDay()
  {

    Collections.shuffle(cards);
    this.boardUI = boardUI.getInstance();

    for (int i = 0; i < sets.size(); i++){
      sets.get(i).setCard(cards.get(i));
      boardUI.setCard(cards.get(i), sets.get(i), cards.get(i).getSceneNum());
      cards.get(i).setSet(sets.get(i));

    }
    System.out.println("it is now day " + day);
  }

  /*
  *parseXML parses the entire XML file and creates all of the scenes, cards and roles along with the casting office and trailer
  */
  private static void parseXML(Board board){

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
  /*
  *creates an amount of players specified by the parameter (numPlayers)
  * it also makes it player 1's turn
  */
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

  /*
  *returns the player whos turn it currently is
  */
  public Player activePlayer(){
    for (int i = 0; i< players.size() ; i++ ) {
      if (players.get(i).isTurn()){
        return players.get(i);
      }
    }
    System.out.println("error: it is no ones turn rn!");
    return null;
  }

  /*
  * endDay resets the board at the end of the day, it resets all shot markers, wraps the final scene,
  * and puts all players back in the trailer and calls startDay to reset the cards.
  * endDay also calls endGame if it is the end of day 3
  */
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
      startDay();
    }

  }

  /*
  * endGame tallies up the scores of all players, anounces the winner, and terminates the program
  */
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

  /*
  * roles on die and return the result of the role
  */
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

  public void setRemainingScenes(int remainingScenes){
    this.remainingScenes = remainingScenes;
  }

  /*
  * prints the name of all of the sets
  */
  public void printSets()
  {
    for (int i = 0; i < sets.size() ; i++ ) {
      System.out.println(sets.get(i).getName());
      sets.get(i).getRoles();
    }
  }
  /*
  * prints the name of all of the scebes and what set there on
  */
  public void printScenes()
  {
    for (int i = 0; i < sets.size() ; i++ ) {
      System.out.println(sets.get(i).getCard().getName() + " on " + sets.get(i).getName());
    }
  }

  /*
  * prints the location of all the players
  */
  public void printAllPlayerLocation(){

    for (int i = 0; i < players.size(); i++ ) {
      System.out.println("player " +players.get(i).getID() + " is currently at the "+ players.get(i).getSpace().getName());
    }

  }



}
