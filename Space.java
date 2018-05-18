import java.util.ArrayList;

public class Space
{

    String name; //scene names, casting office, trailer
    ArrayList<String> neighbors = new ArrayList<String>();
    ArrayList<Player> players = new ArrayList<Player>();


    public Space(String name)
    {
        this.name = name;
    }

    public void addNeighbor(String name){
      neighbors.add(name);
    }

    public String getName(){
        return this.name;
    }
    public ArrayList<String> getNeighbors(){

        return this.neighbors;
    }


}
