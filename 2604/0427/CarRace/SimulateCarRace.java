public class SimulateCarRace{
   private RaceCar carOne;
   private RaceCar carTwo;


   public SimulateCarRace()
   { 
      this.carOne = new RaceCar(1); 
      this.carTwo = new RaceCar(2);
   }


   /**
    * Simulates a race with numLaps laps and returns a String
    * indicating the outcome, as described in part (a)
    * Precondition: numLaps > 0
   */
   public String findWinner(int numLaps){ 
      /* to be implemented in part (a) */
      double oneTime = 0.0;
      double twoTime = 0.0;

      for (int lap = 1; lap <= numLaps; lap++) {
         oneTime += carOne.getLapTime(lap);
         twoTime += carTwo.getLapTime(lap);
      }

      if (oneTime < twoTime) {
         return "Car 1 wins!";
      }
      else if (oneTime > twoTime) {
         return "Car 2 wins!";
      }
      else {
         return "Tie!";
      }
   }
   




   /**
    * Returns a shortened version of the parameter message with all
    * substrings that are equal to the parameter str removed, as
    * described in part (b)
    * Precondition: There are no overlapping occurrences of str
    * within message.
    */
   public String shortenMessage(String message, String str){ 
      /* to be implemented in part (b) */
      if (message.indexOf(str) == -1) {
         return message;
      }

      return message.replace(str, "");
   }




   /* There may be instance variables, constructors, and methods
   that are not shown. */
}
