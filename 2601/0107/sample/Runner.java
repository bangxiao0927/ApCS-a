import box.*; //Challenge: What does .* do?
/* or
import box.StandardBox;
import box.ShippingBox;
*/

public class Runner{
	public static void main (String[] args){
		
		ShippingBox obj = new ShippingBox();
		obj.printInfo();
				
		System.out.println("\nRunning inside Runner: ");
		
		
		StandardBox obj2 = new StandardBox();
		
		//Answer the following...
		
		//(1) Does it print? why?
		//System.out.println( obj2.companyName );
		//yes, because it is public

		//(2) Does it print? why?
		//System.out.println( obj2.password );
		//no, because it is private

		//(3) Does it print? why?
		//System.out.println( obj2.address );
		//no, because it is protected and not in the same package

		//(4) What do you have import to access the StandardBox class?
		//import box.*; or import box.StandardBox;

		//(5) What folder is the StandardBox class in?
		//box
	}
}
