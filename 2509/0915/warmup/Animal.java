public class Animal {
    private static String name;
    private static int age;

    public void setVariables() {
        name = "dog";
        age = 5;
    }

    public void printInfo() {
        System.out.println("Animal Name: " + name);
        System.out.println("Animal Age: " + age);
    }   
}
