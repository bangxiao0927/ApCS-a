public class Task {
    private int rank;
    private String name;
    
    public Task(int r,String n) {
        rank = r;
        name = n;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + " has rank " + rank;
    }
}
