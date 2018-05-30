public class Trailer extends Space
{
  int x;
  int y;
  int h;
  int w;
  /*
  * Since we only want one instance of this, we used the lazy method of initializing our Trailer
  * We felt that we wanted to keep consistency on differentiating the Trailer from the other sets
  */
  private Trailer(String name, int x, int y, int h, int w)
  {
      super(name);
      this.x = x;
      this.y = y;
      this.h = h;
      this.w = w;
  }


  private static class LazyHolder
  {
    static final Trailer INSTANCE = new Trailer("trailer", 991, 248, 194, 201);
  }

  public static Trailer getInstance()
  {
    return LazyHolder.INSTANCE;
  }

  public int getX(){
    return this.x;
  }
  public int getY(){
    return this.y;
  }
  public int getH(){
    return this.h;
  }
  public int getW(){
    return this.w;
  }


}
