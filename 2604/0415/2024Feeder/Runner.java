// For testing the Feeder FRQ. Answers will vary because of the probability component of this FRQ.
public class Runner {


    public static void main(String[] args) {
        Feeder fObj = new Feeder(2400);
        int days;


        System.out.println("Call #1: simulateManyDays(10, 4)");
        System.out.println("Starting with " + fObj.getCurrentFood());
        days = fObj.simulateManyDays(10, 4);
        System.out.println("\tsimulateManyDays returned " + days);


        System.out.println("Call #2: simulateManyDays(10, 5)");
        fObj.updateCurrentFood(250);
        System.out.println("Starting with " + fObj.getCurrentFood());
        days = fObj.simulateManyDays(10, 5);
        System.out.println("\tsimulateManyDays returned " + days);


        System.out.println("Call #3: simulateManyDays(5, 10)");
        fObj.updateCurrentFood(0);
        System.out.println("Starting with " + fObj.getCurrentFood());
        days = fObj.simulateManyDays(5, 10);
        System.out.println("\tsimulateManyDays returned " + days);
    }
}
