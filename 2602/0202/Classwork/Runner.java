public class Runner{
	public static void main(String[] args){
		Test test = new Test();
		
		//1)
		System.out.println("\n1)");
		int x = 5;
		System.out.println(x);
		test.changeX(x);
		System.out.println(x);
		
		//2)
		System.out.println("\n2)");
		String word = "cat";
		System.out.println(word);
		test.changeWord(word);
		System.out.println(word);
		
		//3)
		System.out.println("\n3)");
		int[] myArray = {1,2,3};
		System.out.println(myArray[0]);
		test.changeArray(myArray);
		System.out.println(myArray[0]);
		System.out.println("");
		
		//4)
		System.out.println("\n5)");
		int[] myArray2 = {1,2,3};
		System.out.println(myArray2[0]);
		test.changeArray2(myArray2);
		System.out.println(myArray2[0]);
		System.out.println("");
	}
}
