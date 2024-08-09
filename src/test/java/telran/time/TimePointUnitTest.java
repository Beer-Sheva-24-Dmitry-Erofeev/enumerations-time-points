package telran.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TimePointUnitTest {

    TimePoint hours_5 = new TimePoint(5, TimeUnit.HOUR);
    TimePoint hours_6 = new TimePoint(6, TimeUnit.HOUR);
    TimePoint minutes_300 = new TimePoint(300, TimeUnit.MINUTE); // 5 hours
    TimePoint seconds_18_000 = new TimePoint(18_000, TimeUnit.SECOND); // 5 hours
    TimePoint seconds_30 = new TimePoint(30, TimeUnit.SECOND);
    TimePoint hours_0_008f = new TimePoint(0.008333334f, TimeUnit.HOUR); // 1/120 of hour

    @Test
    void convertTest() {

        assertEquals(hours_5, minutes_300.convert(TimeUnit.HOUR));
        assertEquals(hours_5, minutes_300);
        // Same thing works without conversion. Why?
        // Because we rewrote ".equals()" for "TimePoint"?

        assertEquals(minutes_300, seconds_18_000.convert(TimeUnit.MINUTE));
        assertEquals(hours_0_008f, seconds_30.convert(TimeUnit.HOUR)); // 30s is 1/120 of hour
        assertEquals(hours_0_008f, seconds_30); // Same without conversion
    }

    @Test
    void betweenTest() {

        assertEquals(1, TimeUnit.HOUR.between(hours_5, hours_6));
        assertEquals(60, TimeUnit.MINUTE.between(hours_5, hours_6));
        assertEquals(3600, TimeUnit.SECOND.between(hours_5, hours_6));
        assertEquals(3600_000, TimeUnit.MILLISECOND.between(hours_5, hours_6));

        assertEquals(-1, TimeUnit.HOUR.between(hours_6, hours_5));
        assertEquals(-60, TimeUnit.MINUTE.between(hours_6, hours_5));
        assertEquals(-3600, TimeUnit.SECOND.between(hours_6, hours_5));
        assertEquals(-3600_000, TimeUnit.MILLISECOND.between(hours_6, hours_5));

        assertEquals(0, TimeUnit.HOUR.between(hours_5, minutes_300));
        assertEquals(0, TimeUnit.MINUTE.between(hours_5, minutes_300));
        assertEquals(0, TimeUnit.SECOND.between(minutes_300, hours_5));
        assertEquals(0, TimeUnit.MILLISECOND.between(minutes_300, hours_5));
    }

    @Test
    void equalsTest() {
        // This automaticly tests for proper work of compareTo()
        assertTrue(hours_5.equals(minutes_300));
        assertFalse(hours_5.equals(hours_6));
    }

    @Test
    void compareToTest() {
        assertTrue(hours_5.compareTo(minutes_300) == 0);
        assertTrue(hours_5.compareTo(seconds_30) > 0);
        assertTrue(seconds_18_000.compareTo(hours_6) < 0);
    }
}
