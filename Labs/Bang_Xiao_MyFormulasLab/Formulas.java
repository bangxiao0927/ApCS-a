import java.util.Scanner;

public class Formulas {
    public String choice;
    private double pi = 3.14;
    private double answer;
    private double radius;
    private double length;
    private double width;
    private double height;
    private double base;
    public Scanner scan = new Scanner(System.in);

    private String[] formulas = {
            "Area of a Circle",
            "Circumference of a Circle",
            "Area of a Rectangle",
            "Perimeter of a Rectangle",
            "Area of a Triangle",
            "Pythagorean Theorem",
            "Distance Formula",
            "Slope Formula",
            "Quadratic Formula",
            "Volume of a Rectangular Prism",
            "Volume of a Cylinder",
            "Double Angle Formula for tangent",
            "Surface Area of a Cylinder",
            "Spherical Volume",
            "Spherical Surface Area"
    };

    //formula methods
    private void areaOfACircle() {
        System.out.print("Enter the radius of the circle: ");
        radius = scan.nextDouble();
        answer = pi * radius * radius;
        System.out.println("The area of the circle is: " + answer);
    }

    private void circumferenceOfACircle() {
        System.out.print("Enter the radius of the circle: ");
        radius = scan.nextDouble();
        answer = 2 * pi * radius;
        System.out.println("The circumference of the circle is: " + answer);
    }

    private void areaOfARectangle() {
        System.out.print("Enter the length of the rectangle: ");
        length = scan.nextDouble();
        System.out.print("Enter the width of the rectangle: ");
        width = scan.nextDouble();
        answer = length * width;
        System.out.println("The area of the rectangle is: " + answer);
    }

    private void perimeterOfARectangle() {
        System.out.print("Enter the length of the rectangle: ");
        length = scan.nextDouble();
        System.out.print("Enter the width of the rectangle: ");
        width = scan.nextDouble();
        answer = 2 * (length + width);
        System.out.println("The perimeter of the rectangle is: " + answer);
    }

    private void areaOfATriangle() {
        System.out.print("Enter the base of the triangle: ");
        base = scan.nextDouble();
        System.out.print("Enter the height of the triangle: ");
        height = scan.nextDouble();
        answer = 0.5 * base * height;
        System.out.println("The area of the triangle is: " + answer);
    }

    private void PythagoreanTheorem() {
        System.out.print("Enter the length of side a: ");
        double a = scan.nextDouble();
        System.out.print("Enter the length of side b: ");
        double b = scan.nextDouble();
        answer = Math.sqrt(a * a + b * b);
        System.out.println("The length of side c is: " + answer);
    }

    private void distanceFormula() {
        System.out.print("Enter x1: ");
        double x1 = scan.nextDouble();
        System.out.print("Enter y1: ");
        double y1 = scan.nextDouble();
        System.out.print("Enter x2: ");
        double x2 = scan.nextDouble();
        System.out.print("Enter y2: ");
        double y2 = scan.nextDouble();
        answer = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        System.out.println("The distance between the two points (" + x1 + ", " + y1 + ") and (" + x2 + ", " + y2 + ") is: " + answer);
    }

    private void slopeFormula() {
        System.out.print("Enter x1: ");
        double x1 = scan.nextDouble();
        System.out.print("Enter y1: ");
        double y1 = scan.nextDouble();
        System.out.print("Enter x2: ");
        double x2 = scan.nextDouble();
        System.out.print("Enter y2: ");
        double y2 = scan.nextDouble();
        if (x2 - x1 == 0) {
            System.out.println("The slope is undefined (vertical line).");
        } else {
            answer = (y2 - y1) / (x2 - x1);
            System.out.println("The slope between the two points (" + x1 + ", " + y1 + ") and (" + x2 + ", " + y2 + ") is: " + answer);
        }
    }

    private void quadraticFormula() {
        System.out.print("Enter coefficient a: ");
        double a = scan.nextDouble();
        System.out.print("Enter coefficient b: ");
        double b = scan.nextDouble();
        System.out.print("Enter coefficient c: ");
        double c = scan.nextDouble();
        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.println("The roots are real and different.");
            System.out.println("Root 1: " + root1);
            System.out.println("Root 2: " + root2);
        }
        else if (discriminant == 0) {
            double root = -b / (2 * a);
            System.out.println("The roots are real and the same.");
            System.out.println("Root: " + root);
        }
        else {
            System.out.println("The roots are complex and different.");
            double realPart = -b / (2 * a);
            double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
            System.out.println("Root 1: " + realPart + " + " + imaginaryPart + "i");
            System.out.println("Root 2: " + realPart + " - " + imaginaryPart + "i");
        }
    }

    private void volumeOfARectangularPrism() {
        System.out.print("Enter the length of the rectangular prism: ");
        length = scan.nextDouble();
        System.out.print("Enter the width of the rectangular prism: ");
        width = scan.nextDouble();
        System.out.print("Enter the height of the rectangular prism: ");
        height = scan.nextDouble();
        answer = length * width * height;
        System.out.println("The volume of the rectangular prism is: " + answer);
    }

    private void volumeOfACylinder() {
        System.out.print("Enter the radius of the cylinder: ");
        radius = scan.nextDouble();
        System.out.print("Enter the height of the cylinder: ");
        height = scan.nextDouble();
        answer = pi * radius * radius * height;
        System.out.println("The volume of the cylinder is: " + answer);
    }

    private void doubleAngleFormulaForTangent() {
        System.out.print("Enter the angle in degrees: ");
        double degrees = scan.nextDouble();
        degrees = pi / 180 * degrees; // convert to radians
        answer = Math.tan(2*degrees);
        System.out.println("The value of tan(2θ) is: " + answer);
    }

    private void surfaceAreaOfACylinder() {
        System.out.print("Enter the radius of the cylinder: ");
        radius = scan.nextDouble();
        System.out.print("Enter the height of the cylinder: ");
        height = scan.nextDouble();
        answer = 2 * pi * radius * (radius + height);
        System.out.println("The surface area of the cylinder is: " + answer);
    }

    private void sphericalVolume() {
        System.out.print("Enter the radius of the sphere: ");
        radius = scan.nextDouble();
        answer = (4.0/3) * pi * Math.pow(radius, 3);
        System.out.println("The volume of the sphere is: " + answer);
    }

    private void sphericalSurfaceArea() {
        System.out.print("Enter the radius of the sphere: ");
        radius = scan.nextDouble();
        answer = 4 * pi * Math.pow(radius, 2);
        System.out.println("The surface area of the sphere is: " + answer);
    }


    // Utility Methods


    public void displayMenu() {
        System.out.println("================================");
        System.out.println("Choose a formula to calculate:");
        System.out.println("================================");
        for (int i = 0; i < formulas.length; i++) {
            System.out.println((i + 1) + ". " + formulas[i]);
        }
        System.out.println("================================");
        System.out.print("Enter the number of your choice (0 to exit): ");
        choice = scan.nextLine();
    }

    public int formulaDecider(String choice) {
        int choiceInt;

        try {
            choiceInt = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice");
            System.out.println("================================");
            return -1;
        }

        if(1 <= choiceInt && choiceInt <= 15) {
            System.out.println("================================");
            System.out.println("This formula is " + formulas[choiceInt - 1]);
            System.out.println("================================");

            switch (choiceInt) {
                case 0:
                    System.out.println("Exiting the program.");
                    return 0;
                case 1:
                    areaOfACircle();
                    return 0;
                case 2:
                    circumferenceOfACircle();
                    return 0;
                case 3:
                    areaOfARectangle();
                    return 0;
                case 4:
                    perimeterOfARectangle();
                    return 0;
                case 5:
                    areaOfATriangle();
                    return 0;
                case 6:
                    PythagoreanTheorem();
                    return 0;
                case 7:
                    doubleAngleFormulaForTangent();
                    return 0;
                case 8:
                    slopeFormula();
                    return 0;
                case 9:
                    quadraticFormula();
                    return 0;
                case 10:
                    volumeOfARectangularPrism();
                    return 0;
                case 11:
                    volumeOfACylinder();
                    return 0;
                case 12:
                    doubleAngleFormulaForTangent();
                    return 0;
                case 13:
                    surfaceAreaOfACylinder();
                    return 0;
                case 14:
                    sphericalVolume();
                    return 0;
                case 15:
                    sphericalSurfaceArea();
                    return 0;
            }
        }

        else {
            System.out.println("Invalid choice");
            System.out.println("================================");
        }

        return -1;
    }
}
