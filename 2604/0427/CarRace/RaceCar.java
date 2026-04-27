public class RaceCar {
     private int number;
     public RaceCar(int number)
     { 
       this.number = number;
     }

     /**
     * Returns the number of seconds it takes the race car
     * to complete the lap specified by the parameter
     */
     public double getLapTime(int lapNum)
     { /* implementation not shown */

        if( number == 1){
            if(lapNum == 1){
                return 60.0;
            }
            else if(lapNum == 2){
                return 60.1;
            }
            else if(lapNum == 3){
                return 62.5;
            }
        } else if( number == 2){
            if(lapNum == 1){
                return 60.0;
            }
            else if(lapNum == 2){
                return 60.2;
            }
            else if(lapNum == 3){
                return 61.1;
            }
        }
     
        return 0.0;
        
     
     }
     /* There may be instance variables, constructors,
     and methods that are not shown. */

}
