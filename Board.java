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
  String endLine = "";
  ArrayList<Player> players = new ArrayList<Player>();
  ArrayList<Set> sets = new ArrayList<Set>();
  ArrayList<Card> cards = new ArrayList<Card>();
  HashMap<String,Space> spaceMap = new HashMap<String, Space>();
  Trailer trailer;
  CastingOffice castingOffice;
  BoardUI boardUI;

  private Board() {

  }

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
  *takes in the board and calls parseXML and then startDay then setPlayerPositions
  */
  public void setupBoard(Board board){

    parseXML(board);
    startDay();
    setPlayerPositions();
  }
  //wrapper for set player in boardUI.java
  public void setUpPlayers(){
     for(int i = 1; i < players.size() + 1; i++){
       boardUI.setPlayer(i, 1);
     }
  }

  //puts the position of each set into a HashMap to be used to move the images of players when they move
  private void setPlayerPositions(){
    this.spaceMap.get("Train Station").setXPlayer(21);
    this.spaceMap.get("Train Station").setYPlayer(69);

    this.spaceMap.get("Secret Hideout").setXPlayer(27);
    this.spaceMap.get("Secret Hideout").setYPlayer(732);

    this.spaceMap.get("Church").setXPlayer(623);
    this.spaceMap.get("Church").setYPlayer(734);

    this.spaceMap.get("Hotel").setXPlayer(969);
    this.spaceMap.get("Hotel").setYPlayer(740);

    this.spaceMap.get("Main Street").setXPlayer(969);
    this.spaceMap.get("Main Street").setYPlayer(28);

    this.spaceMap.get("Jail").setXPlayer(281);
    this.spaceMap.get("Jail").setYPlayer(27);

    this.spaceMap.get("General Store").setXPlayer(370);
    this.spaceMap.get("General Store").setYPlayer(282);

    this.spaceMap.get("Ranch").setXPlayer(252);
    this.spaceMap.get("Ranch").setYPlayer(478);

    this.spaceMap.get("Bank").setXPlayer(623);
    this.spaceMap.get("Bank").setYPlayer(475);

    this.spaceMap.get("Saloon").setXPlayer(632);
    this.spaceMap.get("Saloon").setYPlayer(280);

    this.spaceMap.get("trailer").setXPlayer(991);
    this.spaceMap.get("trailer").setYPlayer(248);

    this.spaceMap.get("office").setXPlayer(9);
    this.spaceMap.get("office").setYPlayer(459);

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
      boardUI.setCard(sets.get(i), cards.get(i).getImg());

      if(this.day == 1)
        boardUI.setCardBacks(sets.get(i));

      cards.get(i).setSet(sets.get(i));

    }

    if(this.day == 1){

      for (int i = 0; i < sets.size(); i++){
        for (int j = 0; j < sets.get(i).getShotMarkers().size(); j++) {
          boardUI.addShotMarkers(sets.get(i).getShotMarkers().get(j));
        }

      }

    }

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
    boardUI.updateConsole("Ending day");
    day++;
    if(day > 3 ){
      endGame();
    }
    else{
      //reset players
      for(Player player: players){
        player.setX(trailer.getX());
        player.setY(trailer.getY());
        int offset = player.getX() -50 + (40 * player.ID);
        player.label.setBounds(offset, player.getY() -10, 40, 40);
      }

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
      //resets roles of players and puts them in the trailer
      for(int i = 0; i< players.size(); i++){
        players.get(i).setRehearsal(0);
        players.get(i).setRole(null);
        players.get(i).setSpace(trailer);
      }
      remainingScenes = 10;
      boardUI.resetBoard();
      startDay();
    }

  }

  /*
  * endGame tallies up the scores of all players, and anounces the winner
  */
  private void endGame(){
    setEndGameLine("The Game is over!");
    int highestScore = -1;
    int highestScoreingPlayer = -1;
    for (int i = 0; i < players.size() ; i++) {
      int fame = players.get(i).getFame();
      int cash = players.get(i).getCash();
      int rank = players.get(i).getRank();
      rank = rank * 5;
      int score = fame + cash + rank;
      setEndGameLine("Player "+ (i+1) + " has " + score + " points");
      if(highestScore <= score){
        highestScore = score;
        highestScoreingPlayer = i+1;
      }
    }
    //boardUI.updateConsole("The winner is player " + (highestScoreingPlayer)+ ", they scored " + highestScore + " points!");
    setEndGameLine("The winner is Player " + (highestScoreingPlayer)+ ", they scored " + highestScore + " points!");
    boardUI.disposeBoard();
    EndGameScreen end = new EndGameScreen();




  }

  /*
  * roles one die and return the result of the role
  */
  public static int roleDie(){
    Random rand = new Random();
    int dieRoll = (rand.nextInt(6) + 1);
    return dieRoll;
  }



  /*
  * prints the name of all of the sets
  *(helper method)
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
  *(helper method)
  */
  public void printScenes()
  {
    for (int i = 0; i < sets.size() ; i++ ) {
      System.out.println(sets.get(i).getCard().getName() + " on " + sets.get(i).getName());
    }
  }

  /*
  * prints the location of all the players
  *(helper method)
  */
  public void printAllPlayerLocation(){

    for (int i = 0; i < players.size(); i++ ) {
      System.out.println("player " +players.get(i).getID() + " is currently at the "+ players.get(i).getSpace().getName());
    }
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

  public void setEndGameLine(String endLine) {
      this.endLine += endLine + "<br/>";
  }
  public String getEndGameLine() {
      return endLine;
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




}
