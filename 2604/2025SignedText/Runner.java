public class Runner {
    public static void main(String[] args) {
        
        System.out.println("Testing getSignature method");
        SignedText st1 = new SignedText("", "Wong");
        System.out.println(st1.getSignature());


        SignedText st2 = new SignedText("henri", "dubois");
        System.out.println(st2.getSignature());


        SignedText st3 = new SignedText("GRACE", "LOPEZ");
        System.out.println(st3.getSignature());


        System.out.println("\nTesting addSignature method");
        SignedText st4 = new SignedText("", "FOX");
        System.out.println(st4.addSignature("Dear"));
        System.out.println(st4.addSignature("Best wishesFOX"));
        System.out.println(st4.addSignature("FOXThanks"));
        System.out.println(st3.addSignature("G-LOPEZHello"));
        
    }
}
