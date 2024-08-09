package telran.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PlusTimePointAdjusterTest {

    TimePoint hours_1 = new TimePoint(1, TimeUnit.HOUR);
    TimePoint minutes_55 = new TimePoint(55, TimeUnit.MINUTE);

    PlusTimePointAdjuster plus_5_minutes = new PlusTimePointAdjuster(5, TimeUnit.MINUTE);
    PlusTimePointAdjuster minus_5_minutes = new PlusTimePointAdjuster(-5, TimeUnit.MINUTE);

    @Test
    void plusTimePointAdjusterTest() {

        assertEquals(hours_1, minutes_55.with(plus_5_minutes));  // Why it works without conversion?
        assertEquals(minutes_55, hours_1.with(minus_5_minutes)); // Why it works without conversion?

    }

}
