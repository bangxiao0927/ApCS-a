public class Runner {
    public int factorial(int n) {
        //base case 
        if (n == 1 )  {
            return 1;
        }

        int result = n * factorial(n-1);
        return result;
    }
}
