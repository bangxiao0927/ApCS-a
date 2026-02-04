public class Student {
    // Instance Variables
    private String name;
    private int age;

    // Constructor
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Methods
    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " : " + age;
    }
}