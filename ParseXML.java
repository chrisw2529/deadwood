import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

public class ParseXML{


        // building a document from the XML file
        // returns a Document object after loading the book.xml file.
        public Document getDocFromFile(String filename)
        throws ParserConfigurationException{
        {


           DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
           DocumentBuilder db = dbf.newDocumentBuilder();
           Document doc = null;

           try{
               doc = db.parse(filename);
           } catch (Exception ex) {
               System.out.println("XML parse failure");
               ex.printStackTrace();
           }
           return doc;
        } // exception handling

        }

        // reads data from XML file and prints data
        public void readSceneData(Document d){

            Element root = d.getDocumentElement();
            NodeList sceneList = root.getElementsByTagName("set");

            System.out.println(sceneList.getLength());

            for (int i=0; i<sceneList.getLength();i++){

                System.out.println("Printing information for sceneList "+(i+1));
                Node scene = sceneList.item(i);
                String sceneName = scene.getAttributes().getNamedItem("name").getNodeValue();
                System.out.println("set name = "+sceneName);

                Element neighbors = (Element) scene;
                NodeList neighborList = neighbors.getElementsByTagName("neighbor");
                NodeList takeList = neighbors.getElementsByTagName("take");
                NodeList partList = neighbors.getElementsByTagName("part");


                for (int j=0; j< neighborList.getLength(); j++){

                  Node sub = neighborList.item(j);

                  String neighborName = sub.getAttributes().getNamedItem("name").getNodeValue();
                  System.out.println("neighborName = "+neighborName);
                  System.out.println();

                }

                for (int j=0; j< takeList.getLength(); j++){

                  Node sub = takeList.item(j);
                  String takeNum = sub.getAttributes().getNamedItem("number").getNodeValue();
                  String x = "";
                  String y = "";
                  String h = "";
                  String w = "";

                  NodeList area = sub.getChildNodes();
                  Node subsub = area.item(0);

                  if("area".equals(subsub.getNodeName())){

                    x = subsub.getAttributes().getNamedItem("x").getNodeValue();
                    y = subsub.getAttributes().getNamedItem("y").getNodeValue();
                    h = subsub.getAttributes().getNamedItem("h").getNodeValue();
                    w = subsub.getAttributes().getNamedItem("w").getNodeValue();

                  }

                  System.out.println("takeNum = "+takeNum);
                  System.out.println("Area: " + x + ", " + y + ", " + h + ", " + w);
                  System.out.println();


                }

                for (int j=0; j< partList.getLength(); j++){

                  Node sub = partList.item(j);
                  String partName = sub.getAttributes().getNamedItem("name").getNodeValue();
                  String level = sub.getAttributes().getNamedItem("level").getNodeValue();
                  String line = "";
                  String x = "";
                  String y = "";
                  String h = "";
                  String w = "";

                  NodeList moreChildren = sub.getChildNodes();

                  for (int k = 0; k < moreChildren.getLength(); k++ ) {

                    Node subsub = moreChildren.item(k);

                    if("area".equals(subsub.getNodeName())){

                      x = subsub.getAttributes().getNamedItem("x").getNodeValue();
                      y = subsub.getAttributes().getNamedItem("y").getNodeValue();
                      h = subsub.getAttributes().getNamedItem("h").getNodeValue();
                      w = subsub.getAttributes().getNamedItem("w").getNodeValue();

                    }

                    if("line".equals(subsub.getNodeName()))
                      line = subsub.getTextContent();

                  }


                  System.out.println("partName = "+partName);
                  System.out.println("level = "+level);
                  System.out.println("Line = " + line);
                  System.out.println("Area: " + x + ", " + y + ", " + h + ", " + w);
                  System.out.println();



                }
                System.out.println("\n");

            }


        }

        public void readCardData(Document d)
        {

          Element root = d.getDocumentElement();
          NodeList cardList = root.getElementsByTagName("card");
          System.out.println(cardList.getLength());

          for (int i=0; i<cardList.getLength();i++){

              System.out.println("Printing information for cardList "+(i+1));
              Node card = cardList.item(i);

              String cardName = card.getAttributes().getNamedItem("name").getNodeValue();
              String budget = card.getAttributes().getNamedItem("budget").getNodeValue();
              String img = card.getAttributes().getNamedItem("img").getNodeValue();

              System.out.println("card name = "+cardName);
              System.out.println("budget = "+budget);

              NodeList children = card.getChildNodes();


              for (int j=0; j< children.getLength(); j++){

                Node sub = children.item(j);


                if("scene".equals(sub.getNodeName())){


                  String sceneNum =  sub.getAttributes().getNamedItem("number").getNodeValue();
                  String description = sub.getTextContent();

                  System.out.println("Scene description = " + description);
                  System.out.println("sceneNum = "+ sceneNum);

                }

                else if("part".equals(sub.getNodeName())){

                  NodeList moreChildren = sub.getChildNodes();
                  String line = "";
                  String x = "";
                  String y = "";
                  String h = "";
                  String w = "";

                  for (int k = 0; k < moreChildren.getLength(); k++ ) {

                    Node subsub = moreChildren.item(k);

                    if("area".equals(subsub.getNodeName())){

                      x = subsub.getAttributes().getNamedItem("x").getNodeValue();
                      y = subsub.getAttributes().getNamedItem("y").getNodeValue();
                      h = subsub.getAttributes().getNamedItem("h").getNodeValue();
                      w = subsub.getAttributes().getNamedItem("w").getNodeValue();

                    }

                    if("line".equals(subsub.getNodeName()))
                      line = subsub.getTextContent();

                  }

                  String partName =  sub.getAttributes().getNamedItem("name").getNodeValue();
                  String level =  sub.getAttributes().getNamedItem("level").getNodeValue();

                  System.out.print("Part name = " + partName + ", ");
                  System.out.println("Level = " + level);
                  System.out.println("Line = " + line);
                  System.out.println("Area: " + x + ", " + y + ", " + h + ", " + w);

                }

              } //for childnodes

              System.out.println("\n");

          }

        }


}//class
