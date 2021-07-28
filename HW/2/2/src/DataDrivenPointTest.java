import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class DataDrivenPointTest {
    public Object b1, b2;
    public Boolean isEqual;

    public DataDrivenPointTest(Object b1, Object b2, Boolean isEqual) {
        this.b1 = b1;
        this.b2 = b2;
        this.isEqual = isEqual;
    }

    private static List NullListGenerator() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(null);
        return list;
    }

    @Parameters
    public static Collection<Object[]> equalValues() {
        return Arrays.asList(
                new Object[][]{
                        {new Point(5, 2), new Point(5, 2), true},
                        {new Point(6, 2), new Point(5, 2), false},
                        {2.2, new Point(5, 2), false},
                        {null, new Point(5, 2), false}
                });
    }

    @Test
    public void pointTest() {
        assertTrue(b2.equals(b1) == isEqual);
    }
}