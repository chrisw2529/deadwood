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
            System.out.println("in rsd");
            Element root = d.getDocumentElement();

            NodeList sceneList = root.getElementsByTagName("set");
            System.out.println(sceneList.getLength());
            for (int i=0; i<sceneList.getLength();i++){

                System.out.println("Printing information for sceneList "+(i+1));

                //reads data from the nodes
                Node scene = sceneList.item(i);
                String sceneName = scene.getAttributes().getNamedItem("name").getNodeValue();
                System.out.println("set name = "+sceneName);


                NodeList allNodes = scene.getChildNodes();

                  //System.out.println("allNodes length = " + allNodes.getLength());
                Node neighbors = allNodes.item(2);
                NodeList neighborsList = neighbors.getChildNodes();


                for (int j=0; j< neighborsList.getLength(); j++){
                  // System.out.println("j = " + j);
                   Node sub = neighborsList.item(j);
                   //System.out.println("neighbor names = " + sub.getNodeName());
                  //  String neighborName = sub.getAttributes().getNamedItem("name").getNodeValue();
                  //  System.out.println("neighborName = "+neighborName);

                 if("neighbors".equals(neighbors.getNodeName())){
                   String neighborName = sub.getAttributes().getNamedItem("name").getNodeValue();
                   System.out.println("neighborName = "+neighborName);
                    //  String title = sub.getTextContent();
                    //  System.out.println("Title = "+title);

                 }

                  // else if("author".equals(sub.getNodeName())){
                  //    String authorName = sub.getTextContent();
                  //    System.out.println(" Author = "+authorName);
                  //
                  // }
                  // else if("year".equals(sub.getNodeName())){
                  //    String yearVal = sub.getTextContent();
                  //    System.out.println(" Publication Year = "+yearVal);
                  //
                  // }
                  // else if("price".equals(sub.getNodeName())){
                  //    String priceVal = sub.getTextContent();
                  //    System.out.println(" Price = "+priceVal);
                  //
                  // }


                } //for childnodes

                System.out.println("\n");

            }//for book nodes

        }// method





}//class
