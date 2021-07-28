import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

public class MainTest {
    ArrayList arr;
    Iterator itr;

    @Before
    public void initial() {
        arr = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        itr = arr.iterator();
    }

    @Test(expected = IllegalStateException.class)
    public void TT_1() {
        itr.remove();
        int next = (int) itr.next();
    }

    @Test
    public void TF_2() {
        int next = (int) itr.next();
        itr.remove();
        int new_next = (int) itr.next();
        assertTrue(arr.get(0).equals(new_next));
    }
}