public class Feeder {
	
	/**
	 * The amount of food, in grams, currently in the bird feeder; initialized in the constructor and
	 * always greater than or equal to zero
	 */
    private int currentFood;  // how much food is currently available


    // For testing purposes
    public Feeder(int currentFood) {
        this.currentFood = currentFood;
    }


    // for testing
    public int getCurrentFood() {
        return currentFood;
    }


    // for testing
    public void updateCurrentFood(int currentFood) {
        this.currentFood = currentFood;
    }
    
    /**
	 * Simulates one day with numBirds birds or possibly a bear at the bird feeder,
	 * as described in part (a)
	 * Precondition: numBirds > 0
	 */
    public void simulateOneDay(int numBirds) {
        /* to be implemented in part (a) */
        boolean day = (((int) (Math.random() * 100)) > 95);
        if (day) {
            currentFood = 0;
        }
        else {
            int food = ((int) (Math.random() * 40) + 10);
            int taken = food * numBirds;
            if(taken < currentFood) { currentFood = 0; } else { currentFood = currentFood - taken; }
        }
    }




    /**
	 * Returns the number of days birds or a bear found food to eat at the feeder in this simulation,
	 * as described in part (b)
	 * Preconditions: numBirds > 0, numDays > 0
	 */
    public int simulateManyDays(int numBirds, int numDays) {
        /* to be implemented in part (b) */
        for (int i = 0; i < numDays; i++) {
            simulateOneDay(numBirds);
            if(currentFood == 0) { return i + 1; }
        }
        return 0;
    }
}
