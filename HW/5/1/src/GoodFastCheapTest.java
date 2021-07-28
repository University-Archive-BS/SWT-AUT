import org.junit.*;

import static org.junit.Assert.*;


public class GoodFastCheapTest {

    GoodFastCheap gfc;

    @Before
    public void initial() {
        gfc = new GoodFastCheap();
    }

    @Test
    public void TTF_2() {
        gfc.makeGood();
        gfc.makeFast();
        assertTrue(gfc.isSatisfactory());
    }

    @Test
    public void TFT_3() {
        gfc.makeGood();
        gfc.makeCheap();
        assertTrue(gfc.isSatisfactory());
    }

    @Test
    public void TFF_4() {
        gfc.makeGood();
        assertFalse(gfc.isSatisfactory());
    }

    @Test
    public void FTT_5() {
        gfc.makeCheap();
        gfc.makeFast();
        assertTrue(gfc.isSatisfactory());
    }

    @Test
    public void FTF_6() {
        gfc.makeFast();
        assertFalse(gfc.isSatisfactory());
    }

    @Test
    public void FFT_7() {
        gfc.makeCheap();
        assertFalse(gfc.isSatisfactory());
    }
}
