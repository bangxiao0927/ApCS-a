public class Profile {
    private String name;
    private int age;

    public Profile() {
        this.name = "blank";
        this.age = 0;
    }

    public Profile(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void printProfile() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}