public class Item
{
   private String name;
   private double price;
   
   public Item(String n, double p)
   {
      name = n;
      price = p;
   }
   
   public double getPrice()
   {
      return price;
   }
   
   public String getName()
   {
      return name;
   }
   
   public String toString()
   {
      return name + " : " + price;
   }
}