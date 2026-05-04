public class Runner {
    public static void main(String[] args) {
        
        RobotMover robotMover = new RobotMover(6);

        System.out.println("Testing Part A: "); 
        System.out.println(robotMover.getMoveSequence());

        System.out.println("\nTesting Part B: ");
        RobotMover robotMover2 = new RobotMover("right_down_left_down right_down_up_up_up_");
        System.out.println("countOccurrences(\"right\"): " + robotMover2.countOccurrences("right"));
        System.out.println("countOccurrences(\"down\"): " + robotMover2.countOccurrences("down"));
        System.out.println("countOccurrences(\"right_d\"): " + robotMover2.countOccurrences("right_d"));
        System.out.println("countOccurrences(\"up_up\"): " + robotMover2.countOccurrences("up_up"));
    }
}
