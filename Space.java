import java.util.ArrayList;

public class Space
{

    String name; //scene names, casting office, trailer
    ArrayList<String> neighbors = new ArrayList<String>();
    int xPlayer;
    int yPlayer;

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

    public int getXPlayer(){
      return this.xPlayer;
    }
    
    public int getYPlayer(){
      return this.yPlayer;
    }

    public void setXPlayer(int pos){
      this.xPlayer = pos;
    }

    public void setYPlayer(int pos){
      this.yPlayer = pos;
    }

}
