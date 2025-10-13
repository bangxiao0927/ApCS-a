import java.util.Scanner;

public class MathMenuRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MathLibrary mathLib = new MathLibrary();

        while (true) {
            System.out.println("\nMath Library Menu:");
            System.out.println("1. Calculate Circumference of a Circle");
            System.out.println("2. Calculate Area of a Circle");
            System.out.println("3. Calculate Volume of a Cylinder");
            System.out.println("4. Calculate Volume of a Cone");
            System.out.println("5. Calculate Volume of a Sphere");
            System.out.println("6. Get Value of Pi");
            System.out.println("7. Exit");
            System.out.print("Enter your choice (1-7): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
                continue;
            }

            if (choice == 7) {
                System.out.println("Exiting program.");
                break;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter radius: ");
                        double radiusCirc = Double.parseDouble(scanner.nextLine());
                        System.out.printf("Circumference: %.2f%n", mathLib.circumference(radiusCirc));
                        break;
                    case 2:
                        System.out.print("Enter radius: ");
                        double radiusArea = Double.parseDouble(scanner.nextLine());
                        System.out.printf("Area: %.2f%n", mathLib.areaCircle(radiusArea));
                        break;
                    case 3:
                        System.out.print("Enter radius: ");
                        double radiusCyl = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter height: ");
                        double heightCyl = Double.parseDouble(scanner.nextLine());
                        System.out.printf("Volume of Cylinder: %.2f%n", mathLib.volCylinder(radiusCyl, heightCyl));
                        break;
                    case 4:
                        System.out.print("Enter radius: ");
                        double radiusCone = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter height: ");
                        double heightCone = Double.parseDouble(scanner.nextLine());
                        System.out.printf("Volume of Cone: %.2f%n", mathLib.volCone(radiusCone, heightCone));
                        break;
                    case 5:
                        System.out.print("Enter radius: ");
                        double radiusSphere = Double.parseDouble(scanner.nextLine());
                        System.out.printf("Volume of Sphere: %.2f%n", mathLib.volSphere(radiusSphere));
                        break;
                    case 6:
                        System.out.printf("Value of Pi: %.2f%n", mathLib.getPi());
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a number between 1 and 7.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numerical values.");
            }
        }

        scanner.close();
    }
}