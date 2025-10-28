public class Count {
    public int getTotal(int a) {
        int sum = 0;
        int i = 0;
        while (i < a) {
            sum += i;
            i +=2 ;
        }
        return sum;
    }
}
