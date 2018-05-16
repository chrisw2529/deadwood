import java.util.ArrayList;

public class Space
{

    String name; //scene names, casting office, trailer
    ArrayList<String> neighbors = new ArrayList<String>();

    public Space(String name)
    {
        name = this.name;
    }

    public void addNeighbor(String name){
      neighbors.add(name);
    }
    public String getName(){
        return this.name;
    }

}
