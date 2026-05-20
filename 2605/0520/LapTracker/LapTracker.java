public class LapTracker {
    private int lapCount;
    private int lapLimit;

    public LapTracker(int lapLimit) {
        this.lapLimit = lapLimit;
        this.lapCount = 0;
    }

    public int addLaps(int laps) {
        lapCount += laps;
        if (lapCount > lapLimit) {
            lapCount = lapLimit;
        }
        return lapCount;
    }
}
