public class Runner {
    public static void main(String[] args)
    {
        WordGrid wg = new WordGrid();
        System.out.println(wg);
        System.out.println("Number of ordered rows: " + wg.countOrderedRows());
    }
}
