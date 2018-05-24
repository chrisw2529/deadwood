import java.util.Random;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.lang.Object;
import java.lang.System;
import java.lang.*;

import java.util.*;

public class Deadwood{
  public static void main(String[] args){

    Boolean beginGame = false;
    int howManyPlayers = -1;
    Board board = null;
    board = board.getInstance();
    board.setupBoard(board);
    Scanner sc = new Scanner(System.in);

    System.out.println("Welcome to Deadwood.java, please indicate if you would like to play with 2 or 3 players ");

    while(!beginGame) {

      try{

        howManyPlayers = Integer.parseInt(sc.nextLine());

        if(howManyPlayers == 2 || howManyPlayers == 3)
          beginGame = true;
        else
          System.out.println("Only available players is 2 or 3!");

      }catch (NumberFormatException ex) {

        System.out.println("Please enter an integer!");

      }

    }

    board.initializePlayers(howManyPlayers);
    System.out.println("You have chosen to play with " + howManyPlayers + " players.");
    System.out.println();
    String c = "";

    while(!c.equals("e")){
      c = sc.nextLine();
      Player player = board.activePlayer();


      //c.toLower();
      if(c.equals("show all")){
        board.printAllPlayerLocation();
      }

      else if(c.equals("get day")){
        System.out.println(board.getDay());
      }

      else if(c.equals("get rs")){
        System.out.println(board.getRemainingScenes());
      }
      else if(c.equals("get rehearse")) {
        System.out.println(player.getRehearsal());
      }

      else if(c.equals("get sets")){
        board.printSets();
      }

      else if(c.equals("get scenes")){
        board.printScenes();
      }

      else if(c.contains("move to")){
        if(c.length() <= 8){
          System.out.println("Please specify where to move");
        }
        else{
          if(c.substring(8, c.length()) != null){
            String loc = c.substring(8, c.length());
            player.move(player, loc, board);
          }
          else{
            System.out.println("Not a valid move entry try again");
          }
        }


      }

      //must be able to take scene if already on a set

      else if(c.equals("take role")){
        player.takeRole(player, board, true);
      }

      else if(c.equals("active player")){
        player.getPlayerInfo(board.activePlayer(), board);
      }

      else if(c.contains("ru cash")){
        int rankTo = 0;

        if(c.length() <= 7){
          System.out.println("Please specify what rank you would like to get to");
        }

        else {

          try{

            rankTo = Integer.parseInt(c.substring(8, c.length()));
            player.rankUpUsingCash(player, rankTo);

          }catch (NumberFormatException ex) {

            System.out.println("Not a valid entry try again");

          }
        }

      }

      else if(c.contains("ru fame")){
        int rankTo = 0;

        if(c.length() <= 7){
          System.out.println("Please specify what rank you would like to get to");
        }

        else {

          try{

            rankTo = Integer.parseInt(c.substring(8, c.length()));
            player.rankUpUsingFame(player, rankTo);

          }catch (NumberFormatException ex) {

            System.out.println("Not a valid entry try again");

          }
        }


      }

      else if(c.equals("act")){
        //player.setSpace
        player.act(player, board);
      }

      else if(c.equals("rehearse")){
        //player.setSpace
        player.rehearse(player, board);
      }

      else if(c.equals("where CIM")){
        System.out.println("Player "+ player.getID()+ " can move to ");
        ArrayList<String> neighbors =  player.getSpace().getNeighbors();
        // /HashMap<String,Space> spaceMap = board.getSpaceMap();
        for (int i = 0; i< neighbors.size(); i++) {

          System.out.println(neighbors.get(i));
        }
      }

      else if(c.equals("e")){
        break;
      }

      else{
        System.out.println("Not a valid entry try again");
      }
    }
    //String command = System.in();
    // Player player = new Player(1);
    // Set space = new Set("dog", 10);
    // Role role = new Role("cat","puppy",1,space,true);
    // Role role2 = new Role("rabbit","carrot",4,space,false);
    //
    // role.setPlayer(player);
    // Card card = new Card("zebra", 5);
    // role.setBudget(3);
    // card.addRoles(role);
    // space.addRoles(role2);
    //
    // space.setCard(card);
    // //space.addRoles(role);
    // player.setRole(role);
    // player.setSpace(space);
    // card.wrapScene();
    // //player.rehearse(player);
    // //player.act(player);
    //
    // System.out.println(player.getLocation().getName());
  //  board.printSets();
  }


}
