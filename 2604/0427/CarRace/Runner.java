public class Runner{
    public static void main(String[] args){
   
        SimulateCarRace sim = new SimulateCarRace();
        System.out.println("Testing Part A:");
        System.out.println("Lap 1 time: \t\t" + sim.findWinner(1));
        System.out.println("Lap 1,2 total time: \t" + sim.findWinner(2));
        System.out.println("Lap 1,2,3 total time: \t" + sim.findWinner(3));


        System.out.println("\nTesting Part B:");
        System.out.println(sim.shortenMessage("the_driver_of_the_first_car_is_Jerry", "the_"));
    }
}
