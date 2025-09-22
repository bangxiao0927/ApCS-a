public class Bird {
  private String name = "Lucius";
  
  public void drawMe() {
      System.out.println("""
          ⠀⠀⠀⣀⡴⠚⠉⠙⢦⡀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⣴⡯⢤⡀    ⠈⢧⡀
    ⠀⠀⠀⠀⠀⠀⣸⢹⢸⢇⡇⠀⣠⠤⠤⣄⡀⢹
    ⠀⠀⠀⠀⠀⢰⠃⠈⠉⠉⠀⢠⠇⠀⠀⠀⢹⣾
    ⠀⠀⠀⠀⠀⡞⠀⠀⠀⠀⠀⠀⣇⠀⠀⠀⠀⢸
    ⠀⠀⠀⠀⢸⠁⠀⠀⠀⠀⠀⠀⠘⢦⠀⠀⢀⠇
    ⠀⠀⠀⠀⡎⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⠀⣾⠁
    ⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⢹⠀
    ⠀⠀⠀⢸⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀
    ⠀⠀⠀⣸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀
    ⠀⠀⢠⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀
    ⠀⢠⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀
      """);
  }
  public void speak() {
      System.out.println("Caw-caw!");
  }
  public void myInfo() {
      System.out.println("My name is " + name);
    	speak();
    	drawMe();
  }
}