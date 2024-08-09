package telran.time;

public class PlusTimePointAdjuster implements TimePointAdjuster {

    // It allows us to add (or subtract, if the amount is negative) a certain
    // amount of "timeUnits" represented in this adjuster to a given timePoint
    float amount;
    TimeUnit timeUnit;

    // Constructor
    public PlusTimePointAdjuster(float amount, TimeUnit timeUnit) {
        this.amount = amount;
        this.timeUnit = timeUnit;
    }

    @Override
    public TimePoint adjust(TimePoint timePoint) {
        // Copying timePoint to newPoint with "this" timeUnit
        TimePoint newPoint = timePoint.convert(this.timeUnit);
        // Calculating "amount" on a new point
        float newAmount = newPoint.getAmount() + this.amount;
        // Creating resulting timePoint
        TimePoint resPoint = new TimePoint(newAmount, this.timeUnit);
        // Returning resulting timePoint in proper timeUnit
        return resPoint.convert(timePoint.getTimeUnit());
    }
}
