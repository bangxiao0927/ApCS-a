public class Array2D {
    public void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void scramble(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int randRow = (int) (Math.random() * arr.length);
                int randCol = (int) (Math.random() * arr[i].length);
                int temp = arr[i][j];
                arr[i][j] = arr[randRow][randCol];
                arr[randRow][randCol] = temp;
            }
        }
    }
}