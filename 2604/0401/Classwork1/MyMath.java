public class MyMath {
    public void countDown(int n) {
        if (n < 0) {
            return;
        }
        System.out.println(n);
        countDown(n - 1);
    }

    public void countUp(int n) {
        if (n < 1) {
            return;
        }
        countUp(n - 1);
        System.out.println(n);
    }

    public int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public int summation(int n) {
        if (n == 1) {
            return 1;
        }
        return n + summation(n - 1);
    }
}
