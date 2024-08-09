package telran.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import org.junit.jupiter.api.Test;

public class FutureProximityAdjusterTest {

    // TimePoints sorted downwards
    TimePoint minus_Ms_100 = new TimePoint(-100, TimeUnit.MILLISECOND);  // not in the array
    TimePoint ms_60 = new TimePoint(60, TimeUnit.MILLISECOND);
    TimePoint seconds_1 = new TimePoint(1, TimeUnit.SECOND);
    TimePoint ms_1001 = new TimePoint(1001, TimeUnit.MILLISECOND);
    TimePoint seconds_30 = new TimePoint(30, TimeUnit.SECOND);
    TimePoint minutes_1 = new TimePoint(1, TimeUnit.MINUTE);
    TimePoint seconds_90 = new TimePoint(90, TimeUnit.SECOND);
    TimePoint minutes_5 = new TimePoint(5, TimeUnit.MINUTE);
    TimePoint minutes_30 = new TimePoint(30, TimeUnit.MINUTE);
    TimePoint hours_0_5 = new TimePoint(0.5f, TimeUnit.HOUR);
    TimePoint minutes_45 = new TimePoint(45, TimeUnit.MINUTE);
    TimePoint hours_0_75 = new TimePoint(0.75f, TimeUnit.HOUR);
    TimePoint minutes_60 = new TimePoint(60, TimeUnit.MINUTE);
    TimePoint hours_1 = new TimePoint(1, TimeUnit.HOUR);
    TimePoint minutes_65 = new TimePoint(65, TimeUnit.MINUTE);
    TimePoint hours_1_5 = new TimePoint(1.5f, TimeUnit.HOUR);
    TimePoint hours_2 = new TimePoint(2, TimeUnit.HOUR);
    TimePoint seconds_18_000 = new TimePoint(18_000, TimeUnit.SECOND); // 5 hours
    TimePoint minutes_300 = new TimePoint(300, TimeUnit.MINUTE); // 5 hours
    TimePoint hours_5 = new TimePoint(5, TimeUnit.HOUR);
    TimePoint hours_6 = new TimePoint(6, TimeUnit.HOUR);

    // Unsorted array of timePoints
    TimePoint[] testTimePointsAxis = {hours_6, hours_1_5, hours_2, hours_1, hours_0_5, hours_0_75,
        minutes_65, minutes_300, minutes_45, minutes_60, minutes_5, minutes_30, seconds_18_000,
        minutes_1, seconds_30, seconds_90, hours_5, ms_1001, seconds_1, ms_60};

    FutureProximityAdjuster futureProximityAdjuster = new FutureProximityAdjuster(testTimePointsAxis);

    @Test
    void futureProximityAdjusterTest() {

        assertEquals(ms_60, minus_Ms_100.with(futureProximityAdjuster));
        assertEquals(ms_1001, seconds_1.with(futureProximityAdjuster));

        assertEquals(minutes_5, seconds_90.with(futureProximityAdjuster));
        assertEquals(minutes_45, minutes_30.with(futureProximityAdjuster));

        // Works without conversion
        assertEquals(minutes_60, hours_0_75.with(futureProximityAdjuster));
        assertEquals(hours_1, hours_0_75.with(futureProximityAdjuster));

        assertEquals(minutes_65, hours_1.with(futureProximityAdjuster));

        // Works without conversion
        assertEquals(seconds_18_000, hours_2.with(futureProximityAdjuster));
        assertEquals(minutes_300, hours_2.with(futureProximityAdjuster));
        assertEquals(hours_5, hours_2.with(futureProximityAdjuster));

        assertEquals(hours_6, hours_5.with(futureProximityAdjuster));

        // Case of given timePoint being the most to the "right"
        // on our axis, so it will throw an exeption
        assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> futureProximityAdjuster.adjust(hours_6));
    }
}
