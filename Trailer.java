public class Trailer extends Space
{

  /*
  * Since we only want one instance of this, we used the lazy method of initializing our Trailer
  * We felt that we wanted to keep consistency on differentiating the Trailer from the other sets
  */
  private Trailer(String name)
  {
      super(name);
  }


  private static class LazyHolder
  {
    static final Trailer INSTANCE = new Trailer("trailer");
  }

  public static Trailer getInstance()
  {
    return LazyHolder.INSTANCE;
  }


}
