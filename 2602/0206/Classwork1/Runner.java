public class Runner
{
    public static void main(String[] arg)
    {
        Stats st = new Stats('a');
        Stats st2 = new Stats('b');
        
        st.printScores();
        System.out.println();
        
        System.out.println("Testing for Part A");
        System.out.println("Add score of 5. result: " + st.record(5));
        System.out.println("Add score of 45. result: " + st.record(45));
        System.out.println("Add score of 60 twice. result: " + st.record(60) + " " + st.record(60));
        System.out.println("Add score of 85. result: " + st.record(85));
        System.out.println("Add score of 105 twice. result: " + st.record(105) + " " + st.record(105));
        st.printScores();
        System.out.println();
        
        
        int[] scores = {3,4,1,5,3,2,3,6,7,9,4,7,8,3,2,5,6,2,1,3};
        System.out.println("Testing for Part B");
        st2.recordScores(scores);
        st2.printScores();
    }
}
