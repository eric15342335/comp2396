public class Main {
  private static void toPikachu(Pikachu pkm) {
    pkm.releaseElectricity();
  }

  private static void toSquirtle(Squirtle pkm) {
    pkm.sprayWater();
  }

  public static void main(String[] args) {
    Ditto pkm = new ShinyDitto();
    toPikachu(pkm);
    toSquirtle(pkm);
  }
} 
