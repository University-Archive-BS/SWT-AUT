import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {
    @Test
    public void testEqualPoints() {
        Point p1 = new Point(5, 2);
        Point p2 = new Point(5, 2);
        assertTrue(p1.equals(p2));
    }

    @Test
    public void testForNotEqualPoints() {
        Point p1 = new Point(6, 2);
        Point p2 = new Point(5, 2);
        assertFalse(p1.equals(p2));
    }

    @Test
    public void testForInconsistentTypeItems() {
        Double p1 = 2.2;
        Point p2 = new Point(5, 2);
        assertFalse(p2.equals(p1));
    }

    @Test
    public void testForSoloNullItem() {
        Point p = new Point(5, 2);
        assertFalse(p.equals(null));
    }

    @Test
    public void testHashEquals() {
        Point p1 = new Point(4, 7);
        Point p2 = new Point(4, 7);
        assertTrue(p1.hashCode() == p2.hashCode());
    }
}
