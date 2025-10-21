/*
public class MathLibrary {
    private double pi;
    
    public MathLibrary() {
        pi = 3.14;
    }
    
    public double circumference(double radius) {
        return (2 * pi * radius);
    }
    public double areaCircle(double radius) {
        return (pi * radius * radius);
    }
    
    public double volCylinder(double radius, double height) {
        return (areaCircle(radius) * height);
    }
    public double volCone(double radius, double height) {
        return (volCylinder(radius,height) * 1.0/3);
    }
    public double volSphere(double radius) {
        return (3/4.0 * this.pi * radius);
    }
}
*/
public class MathLibrary {
    private double pi;

    /**
     * Constructor that initializes pi to 3.14.
     */
    public MathLibrary() {
        this.pi = 3.14;
    }

    /**
     * Gets the value of pi.
     * @return The value of pi.
     */
    public double getPi() {
        return pi;
    }

    /**
     * Calculates the circumference of a circle given its radius.
     * @param radius The radius of the circle.
     * @return The circumference of the circle.
     * @throws IllegalArgumentException if radius is negative.
     */
    public double circumference(double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative.");
        }
        return 2 * pi * radius;
    }

    /**
     * Calculates the area of a circle given its radius.
     * @param radius The radius of the circle.
     * @return The area of the circle.
     * @throws IllegalArgumentException if radius is negative.
     */
    public double areaCircle(double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative.");
        }
        return pi * radius * radius;
    }

    /**
     * Calculates the volume of a cylinder given its radius and height.
     * @param radius The radius of the cylinder's base.
     * @param height The height of the cylinder.
     * @return The volume of the cylinder.
     * @throws IllegalArgumentException if radius or height is negative.
     */
    public double volCylinder(double radius, double height) {
        if (radius < 0 || height < 0) {
            throw new IllegalArgumentException("Radius and height cannot be negative.");
        }
        return pi * radius * radius * height;
    }

    /**
     * Calculates the volume of a cone given its radius and height.
     * @param radius The radius of the cone's base.
     * @param height The height of the cone.
     * @return The volume of the cone.
     * @throws IllegalArgumentException if radius or height is negative.
     */
    public double volCone(double radius, double height) {
        if (radius < 0 || height < 0) {
            throw new IllegalArgumentException("Radius and height cannot be negative.");
        }
        return (pi * radius * radius * height) / 3;
    }

    /**
     * Calculates the volume of a sphere given its radius.
     * @param radius The radius of the sphere.
     * @return The volume of the sphere.
     * @throws IllegalArgumentException if radius is negative.
     */
    public double volSphere(double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative.");
        }
        return (4 * pi * radius * radius * radius) / 3;
    }
}