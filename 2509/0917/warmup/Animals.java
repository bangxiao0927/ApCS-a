public class Animals {
    private String animalType;
    private int age;

    public void setVariables(String animalString, int age) {
        this.animalType = animalString;
        this.age = age;
    }

    public void printInfo() {
        System.out.println("The animal type is " + animalType + " and it is " + age + " years old.");
    }
}
