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

    ParseXML p = new ParseXML();
    Board board = new Board();
    board.setupBoard(board);
    board.initializePlayers(2);
    Scanner sc = new Scanner(System.in);
    String c = "";
    while(!c.equals("e")){
      c = sc.nextLine();
      //c.toLower();
      if(c.equals("show location of all players")){
        ArrayList<Player> players = board.getPlayers();
        for (int i = 0; i < players.size(); i++ ) {
          System.out.println("player " +players.get(i).getID() + " is currently at the "+ players.get(i).getSpace().getName());
        }
      }
      else if(c.contains("move to")){
        Player player = board.activePlayer();
        String loc = c.substring(8, c.length());
        player.move(player, loc, board);
      }
      else if(c.equals("active player")){
        Player player = board.activePlayer();
        System.out.println("the active player is player " + player.getID() + " they have " + player.getCash() + " Dollars and " +player.getFame()+ " Fame and they are rank " + player.getRank());
      }
      else if(c.equals("show location of active player")){
        Player player = board.activePlayer();
        System.out.println("the active player is player " + player.getID() + " they are currently at the " + player.getSpace().getName());
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
