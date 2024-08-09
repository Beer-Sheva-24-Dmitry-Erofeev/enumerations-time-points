package telran.time;

import java.util.Arrays;

public class FutureProximityAdjuster implements TimePointAdjuster {

    // It returns a new timePoint nearest to a given timePoint in the
    // "future" on our axis of timePoints
    TimePoint[] timePoints;

    public FutureProximityAdjuster(TimePoint[] timePoints) {
        // Making a copy of a given array to be able to sort it
        TimePoint[] newPoints = Arrays.copyOf(timePoints, timePoints.length);
        // Sorting the array. Since "TimePoint" implements "Comparable", and we
        // rewrote the "comareTo()", we don't have to specify how to compare.
        // At least I think that's why we don't have to do it
        Arrays.sort(newPoints);
        // Assingning the array to the feild "timePoints"
        this.timePoints = newPoints;

    }

    @Override
    public TimePoint adjust(TimePoint timePoint) {
        // Finding in "timePoints" nearest index to the value of "timePoint" using binarySearch
        // It works because "TimePoint" implements "Comparable" (?)
        int i = Arrays.binarySearch(timePoints, timePoint);
        // If result of binarySerach is positive, we are moving to the "right"
        // on our axis if we have logically the same timePoints
        if (i >= 0) {
            while (timePoints[i].equals(timePoints[i + 1])) {
                i++;
            }
            // One last shift to get a proper index in the "future"
            i++;
        } else {
            // If result of binarySearch is -1, then the "timePoint"
            // should be first and we do this to get index 0 for it
            i = -i - 1;
        }
        // In case of ArrayIndexOutOfBoundsException (i > timePoints.length)
        // Should we return "null" or catch the exeption?
        return timePoints[i];
    }

}
