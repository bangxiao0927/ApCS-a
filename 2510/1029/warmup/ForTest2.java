public class ForTest2 {
    public void printCount1(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println(i);
        }
    }

    public void printCount2(int start, int end) {
        int i = start;
        while (i <= end) {
            System.out.println(i);
            i++;
        }
    }

    public int getSum1(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    public int getSum2(int n) {
        int sum = 0;
        int i = 1;
        while (i <= n) {
            sum += i;
            i++;
        }
        return sum;
    }
}
