package telran.time;

public class TimePoint implements Comparable<TimePoint> {

    private float amount;
    private TimeUnit timeUnit;

    // Constructor
    public TimePoint(float amount, TimeUnit timeUnit) {
        this.amount = amount;
        this.timeUnit = timeUnit;
    }

    // Getter of amount
    public float getAmount() {
        return amount;
    }

    // Getter of timeUnit
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    @Override
    public boolean equals(Object timePoint) {
        // Uppercast is needed because "equals()" works with type "Object".
        return this.compareTo((TimePoint) timePoint) == 0;
    }

    @Override
    public int compareTo(TimePoint timePoint) {
        // We have to cast result to int, since we are working with floats
        // Order of comparing is reversed due to order in "between()" being what it is
        return (int) TimeUnit.MILLISECOND.between(timePoint, this);
    }

    public TimePoint convert(TimeUnit timeUnit) {
        // Converting one TimePoint to a new one with a given TimeUnit
        float thisInMs = this.amount * this.timeUnit.getValueOfMs();
        float timeUnitInMs = timeUnit.getValueOfMs();
        float res = thisInMs / timeUnitInMs;
        return new TimePoint(res, timeUnit);
    }

    public TimePoint with(TimePointAdjuster adjuster) {
        return adjuster.adjust(this);
    }

}
