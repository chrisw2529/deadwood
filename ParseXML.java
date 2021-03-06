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

        for (int i=0; i<sceneList.getLength();i++){

            Node scene = sceneList.item(i);
            String sceneName = scene.getAttributes().getNamedItem("name").getNodeValue();

            Element neighbors = (Element) scene;
            NodeList sceneArea = neighbors.getElementsByTagName("area");
            NodeList neighborList = neighbors.getElementsByTagName("neighbor");
            NodeList takeList = neighbors.getElementsByTagName("take");
            NodeList partList = neighbors.getElementsByTagName("part");
            int shot = -1;
            int x = -1;
            int y = -1;
            int h = -1;
            int w = -1;
            Node asub = sceneArea.item(0);

            x = Integer.parseInt(asub.getAttributes().getNamedItem("x").getNodeValue());
            y = Integer.parseInt(asub.getAttributes().getNamedItem("y").getNodeValue());
            h = Integer.parseInt(asub.getAttributes().getNamedItem("h").getNodeValue());
            w = Integer.parseInt(asub.getAttributes().getNamedItem("w").getNodeValue());

            newSet = new Set(sceneName, x, y, h, w);

            for (int j=0; j< takeList.getLength(); j++){

                Node sub = takeList.item(j);
                String takeNum = sub.getAttributes().getNamedItem("number").getNodeValue();

                if(j == 0)
                  shot = Integer.parseInt(takeNum);

                x = 0;
                y = 0;
                h = 0;
                w = 0;

                NodeList area = sub.getChildNodes();
                Node subsub = area.item(0);

                if("area".equals(subsub.getNodeName())){

                    x = Integer.parseInt(subsub.getAttributes().getNamedItem("x").getNodeValue());
                    y = Integer.parseInt(subsub.getAttributes().getNamedItem("y").getNodeValue());
                    h = Integer.parseInt(subsub.getAttributes().getNamedItem("h").getNodeValue());
                    w = Integer.parseInt(subsub.getAttributes().getNamedItem("w").getNodeValue());

                }

                ShotMarker sm = new ShotMarker(x, y);
                newSet.addShots(sm);

            }

            newSet.setShot(shot);

            for (int j=0; j< partList.getLength(); j++){

                Node sub = partList.item(j);
                String partName = sub.getAttributes().getNamedItem("name").getNodeValue();
                String level = sub.getAttributes().getNamedItem("level").getNodeValue();
                String line = "";
                x = 0;
                y = 0;
                h = 0;
                w = 0;

                NodeList moreChildren = sub.getChildNodes();
                int lvl = -1;
                for (int k = 0; k < moreChildren.getLength(); k++ ) {

                    Node subsub = moreChildren.item(k);

                    if("area".equals(subsub.getNodeName())){

                      x = Integer.parseInt(subsub.getAttributes().getNamedItem("x").getNodeValue());
                      y = Integer.parseInt(subsub.getAttributes().getNamedItem("y").getNodeValue());
                      h = Integer.parseInt(subsub.getAttributes().getNamedItem("h").getNodeValue());
                      w = Integer.parseInt(subsub.getAttributes().getNamedItem("w").getNodeValue());

                    }

            if("line".equals(subsub.getNodeName()))
                line = subsub.getTextContent();

            lvl = Integer.parseInt(level);

            }
            Role newRole = new Role(partName, line, lvl, newSet, null, false, x, y, h, w);
            newSet.addRoles(newRole);
        }


            for (int j=0; j< neighborList.getLength(); j++){

                Node sub = neighborList.item(j);
                String neighborName = sub.getAttributes().getNamedItem("name").getNodeValue();
                newSet.addNeighbor(neighborName);

            }

            board.addToSets(newSet);
            board.addToMap(newSet.getName(), newSet);

        }

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
                    trailer.addNeighbor(neighborName);

                }
            }


        }
        b.setTrailer(trailer);
        b.addToMap(trailer.getName(), trailer);

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

            }

            if("neighbors".equals(subsub.getNodeName())){

                Element neighbors = (Element) subsub;
                NodeList trailerNeighbors = neighbors.getElementsByTagName("neighbor");

                for (int j = 0; j < trailerNeighbors.getLength(); j++) {

                    Node subs = trailerNeighbors.item(j);
                    String neighborName = subs.getAttributes().getNamedItem("name").getNodeValue();
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

                    Element areaUp = (Element) subsub;

                    NodeList areaOfUpgrades = areaUp.getElementsByTagName("area");
                    Node ssubsub = areaOfUpgrades.item(j);

                    xUpgrade = ssubsub.getAttributes().getNamedItem("x").getNodeValue();
                    yUpgrade = ssubsub.getAttributes().getNamedItem("y").getNodeValue();
                    hUpgrade = ssubsub.getAttributes().getNamedItem("h").getNodeValue();
                    wUpgrade = ssubsub.getAttributes().getNamedItem("w").getNodeValue();

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

            Node card = cardList.item(i);

            String cardName = card.getAttributes().getNamedItem("name").getNodeValue();
            String budget = card.getAttributes().getNamedItem("budget").getNodeValue();
            String img = card.getAttributes().getNamedItem("img").getNodeValue();

            c = new Card(cardName, Integer.parseInt(budget));
            c.setImg(img);
            NodeList children = card.getChildNodes();

            for (int j=0; j< children.getLength(); j++){

                Node sub = children.item(j);


                if("scene".equals(sub.getNodeName())){


                    String sceneNum =  sub.getAttributes().getNamedItem("number").getNodeValue();
                    String description = sub.getTextContent();

                    c.setSceneNum(Integer.parseInt(sceneNum));
                    c.setDescription(description);


                }

                if("part".equals(sub.getNodeName())){

                    NodeList moreChildren = sub.getChildNodes();
                    String line = "";
                    int x = 0;
                    int y = 0;
                    int h = 0;
                    int w = 0;

                    for (int k = 0; k < moreChildren.getLength(); k++ ) {

                        Node subsub = moreChildren.item(k);

                        if("area".equals(subsub.getNodeName())){

                          x = Integer.parseInt(subsub.getAttributes().getNamedItem("x").getNodeValue());
                          y = Integer.parseInt(subsub.getAttributes().getNamedItem("y").getNodeValue());
                          h = Integer.parseInt(subsub.getAttributes().getNamedItem("h").getNodeValue());
                          w = Integer.parseInt(subsub.getAttributes().getNamedItem("w").getNodeValue());
                        }

                        if("line".equals(subsub.getNodeName()))
                        line = subsub.getTextContent();

                    }

                    String partName =  sub.getAttributes().getNamedItem("name").getNodeValue();
                    String level =  sub.getAttributes().getNamedItem("level").getNodeValue();

                    Role cardRoles = new Role(partName, line, Integer.parseInt(level), null, c, true, x, y, h, w);
                    c.addRoles(cardRoles);

                }

            } //for childnodes

            b.addToCards(c);

        }

    }


}//class
