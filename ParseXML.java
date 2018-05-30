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

    // reads data from board XML file and imports data to our Board class
    public void readSceneData(Document d, Board board){

        Element root = d.getDocumentElement();
        NodeList sceneList = root.getElementsByTagName("set");
        Set newSet;

        //System.out.println(sceneList.getLength());

        for (int i=0; i<sceneList.getLength();i++){

            // System.out.println("Printing information for sceneList "+(i+1));
            Node scene = sceneList.item(i);
            String sceneName = scene.getAttributes().getNamedItem("name").getNodeValue();
            // System.out.println("set name = "+sceneName);


            Element neighbors = (Element) scene;
            NodeList neighborList = neighbors.getElementsByTagName("neighbor");
            NodeList takeList = neighbors.getElementsByTagName("take");
            NodeList partList = neighbors.getElementsByTagName("part");
            int shot = -1;

            for (int j=0; j< takeList.getLength(); j++){

                Node sub = takeList.item(j);
                String takeNum = sub.getAttributes().getNamedItem("number").getNodeValue();

                if(j == 0)
                  shot = Integer.parseInt(takeNum);

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

                // System.out.println("takeNum = "+takeNum);
                // System.out.println("Area: " + x + ", " + y + ", " + h + ", " + w);
                // System.out.println();


            }


            newSet = new Set(sceneName, shot);

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
                int lvl = -1;
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



                // System.out.println("partName = "+partName);
                // System.out.println("level = "+level);
                // System.out.println("Line = " + line);
                // System.out.println("Area: " + x + ", " + y + ", " + h + ", " + w);
                // System.out.println();

                lvl = Integer.parseInt(level);



            }
            Role newRole = new Role(partName, line, lvl, newSet, false);
            newSet.addRoles(newRole);
            // System.out.println("\n");

        }


            for (int j=0; j< neighborList.getLength(); j++){

                Node sub = neighborList.item(j);

                String neighborName = sub.getAttributes().getNamedItem("name").getNodeValue();
                // System.out.println("neighborName = "+neighborName);
                // System.out.println();

                newSet.addNeighbor(neighborName);

            }

            board.addToSets(newSet);
            board.addToMap(newSet.getName(), newSet);

        }

        //board.addToSets(newSet);

    }

    // reads data from board XML file and imports data to our Board class
    public void readForTrailer(Document d, Board b)
    {

        Element root = d.getDocumentElement();
        NodeList trailerNode = root.getElementsByTagName("trailer");
        Node sub = trailerNode.item(0);
        NodeList childrenTrailer = sub.getChildNodes();

        Trailer trailer = null;
        trailer = trailer.getInstance();

        String x = "";
        String y = "";
        String h = "";
        String w = "";

        for (int k = 0; k < childrenTrailer.getLength(); k++ ) {

            Node subsub = childrenTrailer.item(k);

            if("area".equals(subsub.getNodeName())){

                x = subsub.getAttributes().getNamedItem("x").getNodeValue();
                y = subsub.getAttributes().getNamedItem("y").getNodeValue();
                h = subsub.getAttributes().getNamedItem("h").getNodeValue();
                w = subsub.getAttributes().getNamedItem("w").getNodeValue();

            }

            if("neighbors".equals(subsub.getNodeName())){

                Element neighbors = (Element) subsub;
                NodeList trailerNeighbors = neighbors.getElementsByTagName("neighbor");

                for (int j = 0; j < trailerNeighbors.getLength(); j++) {

                    Node subs = trailerNeighbors.item(j);
                    String neighborName = subs.getAttributes().getNamedItem("name").getNodeValue();
                    //System.out.println("neighborName = "+neighborName);

                    trailer.addNeighbor(neighborName);

                }
            }


        }
        b.setTrailer(trailer);
        b.addToMap(trailer.getName(), trailer);

      //  System.out.println("Area: " + x + ", " + y + ", " + h + ", " + w);

    }

    // reads data from board XML file and imports data to our Board class
    public void readForOffice(Document d, Board b)
    {

        Element root = d.getDocumentElement();
        NodeList officeNode = root.getElementsByTagName("office");
        Node sub = officeNode.item(0);
        NodeList childrenOffice = sub.getChildNodes();
        String x = "";
        String y = "";
        String h = "";
        String w = "";
        String xUpgrade = "";
        String yUpgrade = "";
        String hUpgrade = "";
        String wUpgrade = "";

        CastingOffice office = null;
        office = office.getInstance();

        for (int k = 0; k < childrenOffice.getLength(); k++ ) {

            Node subsub = childrenOffice.item(k);

            if("area".equals(subsub.getNodeName())){

                x = subsub.getAttributes().getNamedItem("x").getNodeValue();
                y = subsub.getAttributes().getNamedItem("y").getNodeValue();
                h = subsub.getAttributes().getNamedItem("h").getNodeValue();
                w = subsub.getAttributes().getNamedItem("w").getNodeValue();

                // System.out.println("Area of Office: " + x + ", " + y + ", " + h + ", " + w);
                // System.out.println();

            }

            if("neighbors".equals(subsub.getNodeName())){

                Element neighbors = (Element) subsub;
                NodeList trailerNeighbors = neighbors.getElementsByTagName("neighbor");

                for (int j = 0; j < trailerNeighbors.getLength(); j++) {

                    Node subs = trailerNeighbors.item(j);
                    String neighborName = subs.getAttributes().getNamedItem("name").getNodeValue();
                    // System.out.println("neighborName = "+neighborName);

                    office.addNeighbor(neighborName);

                }
            }

            if("upgrades".equals(subsub.getNodeName())){

                Element upgrade = (Element) subsub;
                NodeList upgradeNodes = upgrade.getElementsByTagName("upgrade");

                for (int j = 0; j < upgradeNodes.getLength(); j++) {

                    Node ssub = upgradeNodes.item(j);

                    String level = ssub.getAttributes().getNamedItem("level").getNodeValue();
                    String amt = ssub.getAttributes().getNamedItem("amt").getNodeValue();
                    String currency = ssub.getAttributes().getNamedItem("currency").getNodeValue();

                    // System.out.print("level : " + level + ", ");
                    // System.out.print("currency : " + currency + ", ");
                    // System.out.println("amt : " + amt);

                    Element areaUp = (Element) subsub;

                    NodeList areaOfUpgrades = areaUp.getElementsByTagName("area");
                    Node ssubsub = areaOfUpgrades.item(j);

                    xUpgrade = ssubsub.getAttributes().getNamedItem("x").getNodeValue();
                    yUpgrade = ssubsub.getAttributes().getNamedItem("y").getNodeValue();
                    hUpgrade = ssubsub.getAttributes().getNamedItem("h").getNodeValue();
                    wUpgrade = ssubsub.getAttributes().getNamedItem("w").getNodeValue();
                    // System.out.println("Area: " + xUpgrade + ", " + yUpgrade + ", " + hUpgrade + ", " + wUpgrade);
                    // System.out.println();


                }

            }

        }

        b.setCastingOffice(office);
        b.addToMap(office.getName(), office);


    }

    // reads data from card XML file and imports data to our Board class
    public void readCardData(Document d, Board b)
    {

        Element root = d.getDocumentElement();
        NodeList cardList = root.getElementsByTagName("card");
        Card c;

        for (int i=0; i<cardList.getLength();i++){

            // System.out.println("Printing information for cardList "+(i+1));
            Node card = cardList.item(i);

            String cardName = card.getAttributes().getNamedItem("name").getNodeValue();
            String budget = card.getAttributes().getNamedItem("budget").getNodeValue();
            String img = card.getAttributes().getNamedItem("img").getNodeValue();

            // System.out.println("card name = "+cardName);
            // System.out.println("budget = "+budget);
            c = new Card(cardName, Integer.parseInt(budget));

            NodeList children = card.getChildNodes();


            for (int j=0; j< children.getLength(); j++){

                Node sub = children.item(j);


                if("scene".equals(sub.getNodeName())){


                    String sceneNum =  sub.getAttributes().getNamedItem("number").getNodeValue();
                    String description = sub.getTextContent();

                    // System.out.println("Scene description = " + description);
                    // System.out.println("sceneNum = "+ sceneNum);

                    c.setSceneNum(Integer.parseInt(sceneNum));
                    c.setDescription(description);

                }

                if("part".equals(sub.getNodeName())){

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

                    Role cardRoles = new Role(partName, line, Integer.parseInt(level), c.getSet(), true);
                    c.addRoles(cardRoles);

                    // System.out.print("Part name = " + partName + ", ");
                    // System.out.println("Level = " + level);
                    // System.out.println("Line = " + line);
                    // System.out.println("Area: " + x + ", " + y + ", " + h + ", " + w);

                }

            } //for childnodes

            b.addToCards(c);
          //  System.out.println("\n");

        }

    }


}//class
