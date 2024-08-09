package telran.time;

public enum TimeUnit {
    MILLISECOND(1),
    SECOND(1_000),
    MINUTE(60_000),
    HOUR(3600_000);
    private float valueOfMs;

    // Constructor
    TimeUnit(float valueOfMs) {
        this.valueOfMs = valueOfMs;
    }

    // Getter for valueOfMs
    public float getValueOfMs() {
        return valueOfMs;
    }

    // Amount of "this" time units between p2 and p1
    public float between(TimePoint p1, TimePoint p2) {
        float p1_inMs = p1.getAmount() * p1.getTimeUnit().valueOfMs;
        float p2_inMs = p2.getAmount() * p2.getTimeUnit().valueOfMs;
        return (p2_inMs - p1_inMs) / this.valueOfMs;
        // p2 - p1 because our time axis is from left to right
        // "this." is not nescessary here, it works without it.
        // "return (p2_inMs - p1_inMs) / valueOfMs;" - works the same. Why?
    }
}
