import java.util.Random;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

public class Deadwood{
  public static void main(String[] args){

    ParseXML p = new ParseXML();
    Card c = new Card("hello", 4);

    Board board = new Board();
    board.setupBoard(board);
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
