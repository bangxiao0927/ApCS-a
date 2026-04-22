public class DogWalker
{
    /** The maximum number of dogs this walker can walk simultaneously
    per hour */
    private int maxDogs;
    /** The dog-walking company this dog walker is associated with */
    private DogWalkCompany company;

    /**
    * Assigns max to maxDogs and comp to company
    * Precondition: max > 0
    */
    public DogWalker(int max, DogWalkCompany comp)
    { 
        maxDogs = max;
        company = comp;
    }


    /**
    * Takes at least one dog for a walk during the time specified by
    * hour, as described in part (a)
    * Preconditions: 0 <= hour <= 23
    * maxDogs > 0
    */
    public int walkDogs(int hour)
    { 
        /* to be implemented in part (a) */ 
        // get the number of available dogs for the hour

        //compare it to maxDogs, if it num avaiable is greater than 
            // walk maxDogs

        // if not,
        //walk he number available

        //call update from the company

        int num = 0;
        int available = this.company.numAvailableDogs(hour);
        if ( available > maxDogs) {
            num = maxDogs;
        }
        else {
            num = available;
        }

        this.company.updateDogs(hour, num);
        return num;
    }

    /**
    * Performs an entire dog-walking shift and returns the amount
    * earned, in dollars, as described in part (b)
    * Preconditions: 0 <= startHour <= endHour <= 23
    * maxDogs > 0
    */
    public int dogWalkShift(int startHour, int endHour)
    { /* to be implemented in part (b) */
        
    }

    /* There may be instance variables, constructors,
    and methods that are not shown. */
}