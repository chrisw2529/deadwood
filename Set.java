import java.util.ArrayList;

public class Set extends Space
{
    final int initialShotMarker;
    int shotMarker;
    ArrayList<Role> roles = new ArrayList<Role>();
    Card card;
    //int budget;
    boolean isWrapped = false;

    /*
    * Set Constructor
    * @param: String name, indicates what the set name will be
    * @param: int shotMarker, each set gets their own shotMarker to indicate how many scenes there are
    */

    public Set(String name, int shotMarker)
    {
        super(name);
        Space office = new Space(name);
        this.shotMarker = shotMarker;
        this.initialShotMarker = shotMarker;
    }



    public ArrayList<Role> getRoles()
    {
      return this.roles;
    }

    public int getShotMarker(){
      return this.shotMarker;
    }



    public boolean getIsWrapped(){
      return this.isWrapped;
    }

    public Card getCard()
    {
      return this.card;
    }

    public void setIsWrapped(boolean bool)
    {
      this.isWrapped = bool;
    }


    public void setCard(Card card)
    {
      this.card = card;
    }

    public void decrementShotMarker(){
      shotMarker--;
    }

    public void addRoles(Role role){
      roles.add(role);
    }
    public void resetShotMarkers(){
      this.shotMarker = this.initialShotMarker;
    }



}
