import java.util.ArrayList;

public class Set extends Space
{
    int shotMarker;
    String partName;
    ArrayList<Role> roles = new ArrayList<Role>();

    public Set(String name, int shotMarker)
    {
        super(name);
        this.shotMarker = shotMarker;
    }


    public ArrayList<Role> getRoles()
    {
      return this.roles;
    }

    public int getShotMarker(){
      return this.shotMarker;
    }

    public void decrementShotMarker(){
      shotMarker--;
    }

    public void addRoles(Role role){
      roles.add(role);
    }



}
