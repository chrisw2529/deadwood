
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
    OpeningScreen startGame = new OpeningScreen();
    while(board.getNumOfPlayers() == 0){
      try {
        Thread.sleep(200);
      }
      catch(InterruptedException e) {
      }
    }
    howManyPlayers = board.getNumOfPlayers();
    board.setUpPlayers();
   }



}
