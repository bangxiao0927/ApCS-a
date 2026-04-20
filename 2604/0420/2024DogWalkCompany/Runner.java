public class Runner {
    public static void main(String[] args) {


        DogWalkCompany comp = new DogWalkCompany();
        DogWalker dw = new DogWalker(4, comp);


        System.out.println("Testing Part A:");
        comp.printInfo(0);
        System.out.println("After walking a max of 4 dogs.");
        dw.walkDogs(0);
        comp.printInfo(0);


        System.out.println();


        comp.printInfo(1);
        System.out.println("After walking a max of 4 dogs.");
        dw.walkDogs(1);
        comp.printInfo(1);


        System.out.println("\n");
        System.out.println("Testing Part B:");
        dw = new DogWalker(3, comp);
        System.out.println("Walking from hours 7 to 10 with a max of 3 dogs.");
        System.out.println("Total Earned: $" + dw.dogWalkShift(7,10));




    }
}
