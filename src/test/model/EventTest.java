package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit tests for the Event class
// adapted from AlarmSystem
public class EventTest {
    private Event e1;
    private Event e2;
    private Event e3;
    private Date d1;

    // NOTE: these tests might fail if time at which line (2) below is executed
    // is different from time that line (1) is executed. Lines (1) and (2) must
    // run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e2 = new Event("Sensor open at door");
        e3 = new Event("Sensor open at ");
        e1 = new Event("Sensor open at door"); // (1)
        d1 = Calendar.getInstance().getTime(); // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Sensor open at door", e1.getDescription());
        assertEquals(d1, e1.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(d1.toString() + "\n" + "Sensor open at door", e1.toString());
    }

    @Test
    public void testEquals() {
        assertTrue(e1.equals(e2));
        assertFalse(e1.equals(null));
        assertFalse(e1.equals(d1));
        assertFalse(e1.equals(e3));
        Event e4 = new Event("Sensor open at door");
        assertFalse(e1.equals(e4));
        assertEquals(e1.hashCode(), e2.hashCode());
    }
}
