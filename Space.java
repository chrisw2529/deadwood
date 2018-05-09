public class Space
{

    int spaceID = 0;
    String name = ""; //scene names, casting office, trailer

    public space(int spaceID, String name)
    {
        spaceID = this.spaceID;
        name = this.name;
    }

    public int getSpaceID(){
        return this.spaceID;
    }

    public String getName(){
        return this.name;
    }

}
