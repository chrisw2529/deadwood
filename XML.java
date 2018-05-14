public class XML{

  // returns a Document object after loading the book.xml file.

  readFile(String filename){
    public Document
getDocFromFile
(String filename)
throws
ParserConfigurationException
{
{
DocumentBuilderFactory
dbf =
DocumentBuilderFactory.newInstance
();
DocumentBuilder
db
=
dbf.newDocumentBuilder
();
Document doc = null
;
try{
doc =
db.parse
(filename);
} catch (Exception ex){
System.out.println
("XML parse failure");
ex.printStackTrace
();
}
return
doc;
} // exception handling
}
  }
}
