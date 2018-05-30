public class CastingOffice extends Space{

  int x;
  int y;
  int h;
  int w;
  /*
  * Since we only want one instance of this, we used the lazy method of initializing our CastingOffice
  * We felt that we wanted to keep consistency on differentiating the Casting Office from the other sets
  */
  private CastingOffice(String name, int x, int y, int h, int w)
  {
      super(name);
      this.x = x;
      this.y = y;
      this.h = h;
      this.w = w;
  }

  private static class LazyHolder
  {
    static final CastingOffice INSTANCE = new CastingOffice("office", 9, 459, 208, 209);
  }

  public static CastingOffice getInstance()
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
