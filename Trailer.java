public class Trailer extends Space
{

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
