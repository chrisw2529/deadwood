public class CastingOffice extends Space{


  /*
  * Since we only want one instance of this, we used the lazy method of initializing our CastingOffice
  * We felt that we wanted to keep consistency on differentiating the Casting Office from the other sets
  */
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
