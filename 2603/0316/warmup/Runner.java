
public class Runner {
    public static void main(String[] args) {
        WarmUp warmUp = new WarmUp();
        int[][] my2DArray = warmUp.create2DArray();
        warmUp.print(my2DArray);
        warmUp.replace(my2DArray);
        warmUp.print(my2DArray);
    }
}