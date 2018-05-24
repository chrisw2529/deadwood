public class CastingOffice extends Space{



  private CastingOffice(String name)
  {
      super(name);

  }

  private static class LazyHolder
  {
    static final CastingOffice INSTANCE = new CastingOffice("office");
  }

  public static CastingOffice getInstance()
  {
    return LazyHolder.INSTANCE;
  }



}
