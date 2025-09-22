import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        
        String Choice = null;
        String customEntry = null;
        String newName = null;
        int newAge = 0;
        String newSubject = null;
        String newHobby = null;
        String newFood = null;

        Scanner keyboard = new Scanner(System.in);

        Profile p1 = new Profile("Joseph", 15, "Math", "Coding", "Sushi");
        Profile p2 = new Profile("Ishan", 15, "Science", "Hiking", "Sushi");
        Profile p3 = new Profile("Angela", 16, "Math", "Coding", "Pizza");
        
        //challenge for the lab
        while(true) {
            //print out the current profiles
            System.out.println("Current Profiles:");
            p1.printInfo();
            p2.printInfo();
            p3.printInfo();
            System.out.println("=====================");

            //ask the user which profile to change
            System.out.print("Which profile to change (p1, p2, or p3): ");
            Choice = keyboard.nextLine();

            //get the current info
            if(Choice.equals("p1")) {
                newName = p1.getName();
                newAge = p1.getAge();
                newSubject = p1.getSubject();
                newHobby = p1.getHobby();
                newFood = p1.getFood();
            }
            else if(Choice.equals("p2")) {
                newName = p2.getName();
                newAge = p2.getAge();
                newSubject = p2.getSubject();
                newHobby = p2.getHobby();
                newFood = p2.getFood();
            }
            else if(Choice.equals("p3")) {
                newName = p3.getName();
                newAge = p3.getAge();
                newSubject = p3.getSubject();
                newHobby = p3.getHobby();
                newFood = p3.getFood();
            }
            else {
                System.out.println("Invalid entry");
                System.out.println("=====================");
                System.out.print("Do you want to exit? (y/n): ");
                Choice = keyboard.nextLine();
                if(Choice.equals("y")) {
                    System.out.println("=====Exit the program=====");
                    break;
                }
                else {
                    continue;
                }
            }

            //ask for changes
            System.out.print("Enter new name(0 to skip): ");
            customEntry = keyboard.nextLine();
            if(!customEntry.equals("0")) {
                newName = customEntry;
            }

            System.out.print("Enter new age(0 to skip): ");
            customEntry = keyboard.nextLine();
            if(!customEntry.equals("0")) {
                newAge = Integer.parseInt(customEntry);
            }

            System.out.print("Enter new favorite subject(0 to skip): ");
            customEntry = keyboard.nextLine();
            if(!customEntry.equals("0")) {
                newSubject = customEntry;
            }

            System.out.print("Enter new hobby(0 to skip): ");
            customEntry = keyboard.nextLine();
            if(!customEntry.equals("0")) {
                newHobby = customEntry;
            }

            System.out.print("Enter new favorite food(0 to skip): ");
            customEntry = keyboard.nextLine();
            if(!customEntry.equals("0")) {
                newFood = customEntry;
            }

            //update
            if(Choice.equals("p1")) {
                p1.updateProfile(newName, newAge, newSubject, newHobby, newFood);
                p1.printInfo();
            }
            else if(Choice.equals("p2")) {
                p2.updateProfile(newName, newAge, newSubject, newHobby, newFood);
                p2.printInfo();
            }
            else if(Choice.equals("p3")) {
                p3.updateProfile(newName, newAge, newSubject, newHobby, newFood);
                p3.printInfo();
            }

            //exit
            System.out.println("=====================");
            System.out.print("Do you want to exit? (y/n): ");
            Choice = keyboard.nextLine();
            if(Choice.equals("y")) {
                System.out.println("=====Exit the program=====");
                break;
            }
        }
    }
}
