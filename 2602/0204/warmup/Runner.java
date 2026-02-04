import java.util.ArrayList;
public class Runner {
    public static void main(String[] args) {
        // Create an ArrayList of Student
        ArrayList<Student> studentList = new ArrayList<>();

        // Add students to the ArrayList
        studentList.add(new Student("Henry", 34));
        studentList.add(new Student("Jose", 21));
        studentList.add(new Student("Carla", 21));
        studentList.add(new Student("Nancy", 19));

        // Add up all the ages in the ArrayList
        int totalAge = 0;
        for (Student student : studentList) {
            totalAge += student.getAge();
        }
        System.out.println("Total Age: " + totalAge);

        // Search and remove all students with the age of 21
        for (int i = studentList.size() - 1; i >= 0; i--) {
            if (studentList.get(i).getAge() == 21) {
                studentList.remove(i);
            }
        }

        // Print the ArrayList using a for each loop
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}