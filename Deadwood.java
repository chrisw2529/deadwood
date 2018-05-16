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
  //  board.printSets();
  }


}
