public class Circle
{
  private double pi;

  public Circle()
  {
    pi = 3.14;
  }

  public double area(double radius)
  {
    return pi * radius * radius;
  }

  public double volCyl(double radius, double height)
  {
    double area = area(radius);
    return area * height;
  }
}