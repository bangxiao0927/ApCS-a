//Challenge: What does this do?
package box;

public class ShippingBox{   
	public void printInfo(){
		System.out.println("Running inside ShippingBox: ");
		StandardBox b = new StandardBox();
		
		//Answer the following
		
		//(6) Does companyName print? why?
		//System.out.println( b.companyName );
		//yes, because it is public

		//(7) Does password print? why?
		//System.out.println( b.password );
        //no, because it is private

		//(8) Does address print? why?
		//System.out.println( b.address );
		//no, because it is protected and not in the same package
				
	}
}
