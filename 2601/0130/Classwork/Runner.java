public class Runner
{
	public static void main(String args[])
	{
		MasterOrder goodies = new MasterOrder();
		goodies.addOrder(new CookieOrder("Chocolate Chip", 1));
		goodies.addOrder(new CookieOrder("Shortbread", 5));
		goodies.addOrder(new CookieOrder("Macaroon", 2));
		goodies.addOrder(new CookieOrder("Chocolate Chip", 3));
		
		goodies.printAllOrders();
		
		System.out.println("\nTotal Orders: " + goodies.getTotalBoxes());
		
		System.out.println("\nRemoving Macaroon: " + goodies.removeVariety("Macaroon"));
		
		goodies.printAllOrders();
		
		System.out.println("\nTotal Orders: " + goodies.getTotalBoxes());
		
	}
	
}
