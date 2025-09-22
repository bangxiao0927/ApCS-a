public class Dog {
  private String name = "Albert";
  
  public void drawMe() {
    	System.out.println(" 0-----0");
        System.out.println("૮ - ﻌ •  ა         |");
    	System.out.println("   ₍      -------₎ |");
    	System.out.println("    ₍             |₎");
    	System.out.println("     ₍___________₎");
    	System.out.println("      |         |");
    	System.out.println("      -         -");
  }
  public void speak() {
      System.out.println("Woof!");
  }
  public void myInfo() {
      System.out.println("My name is " + name);
    	speak();
    	drawMe();
  }
}