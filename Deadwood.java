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

    // try {
    //   Document d = p.getDocFromFile("board.xml");
    // //  p.readForTrailer(d);
    // //  p.readForOffice(d);
    //   //p.readCardData(d);
    //   p.readSceneData(d);
    // }
    // catch(ParserConfigurationException ex) {
    //   System.out.println("par C E");
    //
    // }

    Board board = new Board();
    board.setupBoard(board);
    Player player = new Player(1);
    Set space = new Set("dog", 10);
    Role role = new Role("cat","puppy",2,space,true);
    Card card = new Card("zebra", 3);
    role.setBudget(3);
    card.addRoles(role);
    space.setCard(card);
    //space.addRoles(role);
    player.setRole(role);
    player.setSpace(space);
    player.rehearse(player);
    player.act(player);

    System.out.println(player.getLocation().getName());
  //  board.printSets();
  }


}
