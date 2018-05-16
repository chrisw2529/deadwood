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

    Player joe = new Player(1);
    ParseXML p = new ParseXML();
    File board = new File("board.xml");
    // DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    // DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    // Document doc = dBuilder.parse(board);
    // doc.getDocumentElement().normalize();
    try {
      Document d = p.getDocFromFile("board.xml");
      p.readSceneData(d);
    }
    catch(ParserConfigurationException ex) {
      System.out.println("par C E");

    }
    //readSceneData(d);
  }


}
