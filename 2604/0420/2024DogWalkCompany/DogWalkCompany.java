public class DogWalkCompany
{
    
    private int[] dogsAvailableByHour = new int[11];
    
    public DogWalkCompany() {
        dogsAvailableByHour[0] = 10;
        dogsAvailableByHour[1] = 3;

        dogsAvailableByHour[7] = 3;
        dogsAvailableByHour[8] = 2;
        dogsAvailableByHour[9] = 2;
        dogsAvailableByHour[10] = 3;
    }

    /**
    * Returns the number of dogs, always greater than 0, that are available
    * for a walk during the time specified by hour
    * Precondition: 0 <= hour <= 23
    */
    public int numAvailableDogs(int hour)
    { /* implementation not shown */
        return dogsAvailableByHour[hour];
    }

    /* Testing purpose */
    public void printInfo(int hour){
        System.out.println( "Dogs Available: " + dogsAvailableByHour[hour] ); 
    }

    
    /**
    * Decreases, by numberDogsWalked, the number of dogs available for a walk
    * during the time specified by hour
    * Preconditions: 0 <= hour <= 23
    * numberDogsWalked > 0
    */
    public void updateDogs(int hour, int numberDogsWalked)
    { /* implementation not shown */ 
        dogsAvailableByHour[hour] -= numberDogsWalked;
    }
    /* There may be instance variables, constructors,
    and methods that are not shown. */

}