public class Student {
    private String name;

    public Student() {
        this.name = "none";
    }

    public Student(String name) {
        this.name = name;
    }

    public void printName() {
        System.out.println("Student Name: " + name);
    }

    public void printName(String age) {
        System.out.println("Student Name: " + name + "\nAge: " + age);
    }
}