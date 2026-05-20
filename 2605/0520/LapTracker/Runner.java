public class Runner {
    public static void main(String[] args) {
        LapTracker tracker = new LapTracker(3);
        
        System.out.println("Adding 8 laps.");
        System.out.println("Lap Count: " + tracker.addLaps(8));
        System.out.println("Adding 12 laps.");
        System.out.println("Lap Count: " + tracker.addLaps(12));
        System.out.println("Adding 10 laps.");      
        System.out.println("Lap Count: " + tracker.addLaps(10));      
        System.out.println("Adding 11 laps.");
        System.out.println("Lap Count: " + tracker.addLaps(11)); 
        System.out.println("Adding 5 laps.");
        System.out.println("Lap Count: " + tracker.addLaps(5)); 
        System.out.println("Adding 13 laps.");
        System.out.println("Lap Count: " + tracker.addLaps(13)); 
        System.out.println("Adding 7 laps.");
        System.out.println("Lap Count: " + tracker.addLaps(7)); 
        System.out.println();

        tracker = new LapTracker(2);
        System.out.println("Adding 20 laps.");
        System.out.println("Lap Count: " + tracker.addLaps(20));
        System.out.println("Adding 13 laps.");
        System.out.println("Lap Count: " + tracker.addLaps(13));
        System.out.println("Adding 10 laps.");
        System.out.println("Lap Count: " + tracker.addLaps(10));
    }
}