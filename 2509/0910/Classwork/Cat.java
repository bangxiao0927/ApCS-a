public class Cat {
  private String name = "Bang";
  
  public void drawMe() {
    System.out.println(" ^___^");
    System.out.println("₍ . . ₎⟆");
    System.out.println("₍ ---- ₎⟆");
    System.out.println("     ₍    -------₎⟆");
    System.out.println("     ₍___________₎");
    System.out.println("      |         |");
    System.out.println("      |         |");
  }
  public void speak() {
      System.out.println("Meow!");
  }
  public void myInfo() {
    System.out.println("My name is " + name);
    speak();
    drawMe();
  }
}