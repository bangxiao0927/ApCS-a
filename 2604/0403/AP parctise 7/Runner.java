public class Runner
{
    public static void main(String args[])
    {
		int[][] arr = new int[3][4];
		arr[0][0] = 15;
		arr[0][1] = 5;
		arr[0][2] = 9;
		arr[0][3] = 10;
		
		arr[1][0] = 12;
		arr[1][1] = 16;
		arr[1][2] = 11;
		arr[1][3] = 6;
		
		arr[2][0] = 14;
		arr[2][1] = 8;
		arr[2][2] = 13;
		arr[2][3] = 7;
		
		System.out.println("Part A");
		Position p = Successors.findPosition(8, arr);
		if( p != null)
			System.out.println(p);
		else
			System.out.println("null");	
		p = Successors.findPosition(17, arr);
		if( p != null)
			System.out.println(p);
		else
			System.out.println("null");	
		
		
        System.out.println("\nPart B");
		Position[][] successorArray = Successors.getSuccessorArray(arr);
		for(int r=0; r<successorArray.length; r++)
		{
			for(int c=0; c<successorArray[r].length; c++)
			{
				if( successorArray[r][c] != null)
					System.out.print(successorArray[r][c] + "\t");
				else
					System.out.print("null \t");	
			}
			System.out.println();
		}
    }	
}