//Array2DDemo
//Methods
//-  print2DInt(int[][]) : void - Print the 2D array of int passed in. Each number will be separated by a tab. Each row will have its own line. 
//+ test1() : int[][] - Create a 2D Array of int that is 5x5.  Fill it with random numbers from 1 to 10 inclusive.  Return the 2D array.
//+ test2() :void - Call test1() and store the result. Print the 2D array by calling print2DInt(int[][]).   Print the largest number in the resulting 2D array.
//+ test3) : void - Call test1() and store the result. Print the 2D array by calling print2DInt(int[][]).  Ask the user for a number from 1 to 10 to change to 99.  Change all the occurrences in the 2D array of that number to 99.  Print the 2D array by calling print2DInt(int[][]).
//+ test4() : void - Create a 3x3 2D array of Strings of 9 different animals of your choice.  Print it as a table.  Ask the user for an animal.  Search the array.  If that animal exists, print "yes" and the row number and column number, otherwise print "no".  You should only print "yes" or "no" once.


public class Array2DDemo {
    private void print2DInt(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public int[][]test1() {
        int[][] arr = new int[5][5];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = (int) (Math.random() * 10) + 1;
            }
        }
        return arr;
    }

    public void test2() {
        int[][] arr = test1();
        print2DInt(arr);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > max) {
                    max = arr[i][j];
                }
            }
        }
        System.out.println("Largest number: " + max);
    }
    
    public void test3() {
        int[][] arr = test1();
        print2DInt(arr);
        System.out.print("Enter a number from 1 to 10 to change to 99: ");
        int num = new java.util.Scanner(System.in).nextInt();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == num) {
                    arr[i][j] = 99;
                }
            }
        }
        print2DInt(arr);
    }

    public void test4() {
        String[][] animals = {
            {"Cat", "Dog", "Rabbit"},
            {"Lion", "Tiger", "Bear"},
            {"Elephant", "Giraffe", "Zebra"}
        };
        for (int i = 0; i < animals.length; i++) {
            for (int j = 0; j < animals[i].length; j++) {
                System.out.print(animals[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.print("Enter an animal: ");
        String animal = new java.util.Scanner(System.in).nextLine();
        boolean found = false;
        for (int i = 0; i < animals.length; i++) {
            for (int j = 0; j < animals[i].length; j++) {
                if (animals[i][j].equalsIgnoreCase(animal)) {
                    System.out.println("Yes, found at row " + i + " and column " + j);
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        if (!found) {
            System.out.println("No");
        }
    }
}