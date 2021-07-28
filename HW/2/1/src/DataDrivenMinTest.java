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
public class DataDrivenMinTest {
    public List list;
    public Object min;

    public DataDrivenMinTest(List list, Object min) {
        this.list = list;
        this.min = min;
    }

    private static List NullListGenerator() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(null);
        return list;
    }

    @Parameters
    public static Collection<Object[]> minValues() {
        return Arrays.asList(
                new Object[][]{
                        {Arrays.asList(null, "cat"), NullPointerException.class},
                        {NullListGenerator(), NullPointerException.class},
                        {null, NullPointerException.class},
                        {Arrays.asList("dog", "cat", 1), ClassCastException.class},
                        {Arrays.asList(), IllegalArgumentException.class},
                        {Arrays.asList("cat"), "cat"},
                        {Arrays.asList("dog", "cat"), "cat"},
                        {Arrays.asList(-2, 4, 10), -2},
                        {Arrays.asList(2, 2, 6), 2}
                });
    }

    @Test
    public void minTest() {
        try {
            assertEquals("Min Test", Min.min(list), min);
        } catch (Exception e) {
            assertEquals(e.getClass().getName(), e.getClass(), min);
        }
    }
}
