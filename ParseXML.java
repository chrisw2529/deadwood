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

                }

                for (int j=0; j< takeList.getLength(); j++){

                  Node sub = takeList.item(j);
                  String takeNum = sub.getAttributes().getNamedItem("number").getNodeValue();

                  System.out.println("takeNum = "+takeNum);

                }

                for (int j=0; j< partList.getLength(); j++){

                  Node sub = partList.item(j);
                  String partName = sub.getAttributes().getNamedItem("name").getNodeValue();
                  String level = sub.getAttributes().getNamedItem("level").getNodeValue();

                  System.out.println("partName = "+partName);
                  System.out.println("level = "+level);


                }
                System.out.println("\n");

            }

        }

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

                }

                for (int j=0; j< takeList.getLength(); j++){

                  Node sub = takeList.item(j);
                  String takeNum = sub.getAttributes().getNamedItem("number").getNodeValue();

                  System.out.println("takeNum = "+takeNum);

                }

                for (int j=0; j< partList.getLength(); j++){

                  Node sub = partList.item(j);
                  String partName = sub.getAttributes().getNamedItem("name").getNodeValue();
                  String level = sub.getAttributes().getNamedItem("level").getNodeValue();

                  System.out.println("partName = "+partName);
                  System.out.println("level = "+level);


                }
                System.out.println("\n");

            }

        }


}//class
