public class Runner
{
	public static void main(String[] args)
	{
	
		Mountain mn = new Mountain();
		
		int[] numArray1 = {11, 22, 33, 22, 11};
		int[] numArray2 = {11, 22, 11, 22, 11};
		int[] numArray3 = {11, 22, 33, 55, 77};
		int[] numArray4 = {99, 33, 55, 77, 120};
		int[] numArray5 = {99, 33, 55, 77, 55};
		int[] numArray6 = {33, 22, 11};
		
		int[] numArray7 = {1, 2, 3, 2, 1};
		int[] numArray8 = {1, 2, 1, 2, 1};
		int[] numArray9 = {1, 2, 3, 1, 5};
		int[] numArray10 = {1, 4, 2, 1, 0};
		int[] numArray11 = {9, 3, 5, 7, 5};
		int[] numArray12 = {3, 2, 1};
		
		System.out.println("Testing Part A");
		System.out.println("{11, 22, 33, 22, 11} : " + mn.getPeakIndex(numArray1));
		System.out.println("{11, 22, 11, 22, 11} : " + mn.getPeakIndex(numArray2));
		System.out.println("{11, 22, 33, 55, 77} : " + mn.getPeakIndex(numArray3));
		System.out.println("{99, 33, 55, 77, 120} : " + mn.getPeakIndex(numArray4));
		System.out.println("{99, 33, 55, 77, 55} : " + mn.getPeakIndex(numArray5));
		System.out.println("{33, 22, 11} : " + mn.getPeakIndex(numArray6));
		System.out.println();
		
		System.out.println("Testing Part B");
		System.out.println("{1, 2, 3, 2, 1} : " + mn.isMountain(numArray7));
		System.out.println("{1, 2, 1, 2, 1} : " + mn.isMountain(numArray8));
		System.out.println("{1, 2, 3, 1, 5} : " + mn.isMountain(numArray9));
		System.out.println("{1, 4, 2, 1, 0} : " + mn.isMountain(numArray10));
		System.out.println("{9, 3, 5, 7, 5} : " + mn.isMountain(numArray11));
		System.out.println("{3, 2, 1} : " + mn.isMountain(numArray12));
		
	}
}
