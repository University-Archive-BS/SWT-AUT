import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

@RunWith(Theories.class)
public class TheoryPointTest {
    @DataPoints
    public static Set[] points = {
            new HashSet(Arrays.asList(new Point(3, 2), new Point(3, 2), new Point(4, 2))),
            new HashSet(Arrays.asList(new Point(5, 2), new Point(3, 2), new Point(3, 2))),
            new HashSet(Arrays.asList(new Point(3, 2), new Point(3, 2), null)),
    };


    @Theory
    public void testEqualReflexive(Object b1) {
        assertTrue(b1 != null);
        assertTrue(b1.equals(b1));
    }

    @Theory
    public void testEqualsSymmetric(Object b1, Object b2) {
        assertTrue(b1 != null);
        assertTrue(b2 != null);

        assertTrue(b1.toString() + " != " + b2.toString(), b1.equals(b2));
        assertTrue(b1.toString() + " != " + b2.toString(), b2.equals(b1));
    }

    @Theory
    public void testEqualsTransitive(Object b1, Object b2, Object b3) {
        assertTrue(b1 != null);
        assertTrue(b2 != null);
        assertTrue(b3 != null);

        assertTrue(b1.equals(b2));
        assertTrue(b2.equals(b3));
        assertTrue(b1.equals(b3));
    }

    @Theory
    public void testEqualHashCodeReflexive(Object b1) {
        assertTrue(b1 != null);
        assertTrue(b1.hashCode() == b1.hashCode());
    }

    @Theory
    public void testEqualsHashCodeSymmetric(Object b1, Object b2) {
        assertTrue(b1 != null);
        assertTrue(b2 != null);

        assertTrue(b1.hashCode() == b2.hashCode());
        assertTrue(b2.hashCode() == b1.hashCode());
    }

    @Theory
    public void testEqualsHashCodeTransitive(Object b1, Object b2, Object b3) {
        assertTrue(b1 != null);
        assertTrue(b2 != null);
        assertTrue(b3 != null);

        assertTrue(b1.hashCode() == b2.hashCode());
        assertTrue(b2.hashCode() == b3.hashCode());
        assertTrue(b1.hashCode() == b3.hashCode());
    }
}