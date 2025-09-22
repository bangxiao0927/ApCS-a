//15,35,79,85,90,100

import java.util.Scanner;

class BinaryExchange {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("input 1: 10 -> 2\ninput 2: 2 -> 10");
        int choice = scan.nextInt();
        if(choice == 1){
            System.out.println("Enter the amount you want to loop: ");
            int looptime = scan.nextInt() + 1;
            for (int i = 0; i < looptime; i++) {
                System.out.println("Enter a number to turn to binary: ");
                int num = scan.nextInt();
                String binary = Integer.toBinaryString(num);
                System.out.println("binary number: " + binary);
            }
        }
        else {
            System.out.println("Enter the amount you want to loop: ");
            int looptime = scan.nextInt() + 1;
            for (int i = 0; i < looptime; i++) {
                System.out.println("Enter a number to turn to decimal: ");
                int num = scan.nextInt();
                int baseTen = Integer.parseInt(Integer.toBinaryString(num), 2);
                System.out.println("10base number: " + baseTen);
            }
        }
    }
}