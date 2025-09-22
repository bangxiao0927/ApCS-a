public class Animal {
    private String animalType;
    private int age;

    public Animal() {
        animalType = "blank";
        age = 0;
    }

    public Animal(String type, int a) {
        animalType = type;
        age = a;
    }

    public void printInfo() {
        System.out.println("Animal Type: " + animalType);
        System.out.println("Animal Age: " + age);
    }
}
