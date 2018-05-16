import java.util.ArrayList;

public class Set extends Space
{
    int shotMarker;
    ArrayList<Role> roles = new ArrayList<Role>();




    public Set(String name, int shotMarker)
    {
        super(name);
        this.shotMarker = shotMarker;
    }



    public int getShotMarker(){
      return this.shotMarker;
    }

    public void decrementShotMarker(){
      shotMarker--;
    }



}
