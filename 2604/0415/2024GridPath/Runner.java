public class Runner {

        public static void main( String[] args) {

                // create a Location object
                Location loc;
                // test part a, getNextLoc
                System.out.println("Testing Part A:");
                System.out.println("Test getNextLoc():");
                int[][] arr1 = new int[][]{{12,3,4,13,5},{11,21,2,14,16},{7,8,9,15,0},{10,17,20,19,1},{18,22,30,25,6}};
                GridPath grid = new GridPath(arr1);

                loc = grid.getNextLoc(0, 0);
                System.out.println("\tNeighbor to (0, 0) is ("+loc.getRow() + ", " + loc.getCol() + ")");

                loc = grid.getNextLoc(1, 3);
                System.out.println("\tNeighbor to (1, 3) is ("+loc.getRow() + ", " + loc.getCol() + ")");

                loc = grid.getNextLoc(2, 4);
                System.out.println("\tNeighbor to (2, 4) is ("+loc.getRow() + ", " + loc.getCol() + ")");
                
                loc = grid.getNextLoc(4, 3);
                System.out.println("\tNeighbor to (4, 3) is ("+loc.getRow() + ", " + loc.getCol() + ")");

                // Test sumPath
                System.out.println("\nTesting Part B:");
                System.out.println("Test sumPath():");
                int[][] arr2 = new int[][]{{12,30,40,25,5},{11,3,22,15,43},{7,2,9,4,0},{8,33,18,6,1}};
                GridPath grid2 = new GridPath(arr2);
                System.out.println("\tThe sum of the path that starts at (1, 1) is " + grid2.sumPath(1, 1));
                
        }   
}