import java.util.ArrayList;

public class Set extends Space
{
    int shotMarker;
    ArrayList<Role> roles = new ArrayList<Role>();
    Card card;
    int budget;
    boolean isWrapped = false;

    public Set(String name, int shotMarker)
    {
        super(name);
        Space office = new Space(name);
        this.shotMarker = shotMarker;
    }



    public ArrayList<Role> getRoles()
    {
      return this.roles;
    }

    public int getShotMarker(){
      return this.shotMarker;
    }

    public int getBudget()
    {
      return this.budget;
    }

    public boolean getIsWrapped(){
      return this.isWrapped;
    }

    public Card getCard()
    {
      return this.card;
    }

    public void setIsWrapped()
    {
      this.isWrapped = true;
    }

    public void setCard(Card card)
    {
      this.card = card;
      this.budget = card.getBudget();
    }

    public void decrementShotMarker(){
      shotMarker--;
    }

    public void addRoles(Role role){
      roles.add(role);
    }



}
