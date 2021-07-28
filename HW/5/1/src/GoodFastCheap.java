import java.util.*;

public final class GoodFastCheap {

    boolean good = false;
    boolean fast = false;
    boolean cheap = false;

    public void makeGood() {
        good = true;
        if (fast && cheap) {
            cheap = false;
        }
    }

    public void makeFast() {
        fast = true;
        if (good && cheap) {
            good = false;
        }
    }

    public void makeCheap() {
        cheap = true;
        if (fast && good) {
            fast = false;
        }
    }

    public void makeBad() {
        good = false;
    }

    public void makeSlow() {
        fast = false;
    }

    public void makeExpensive() {
        cheap = false;
    }

    public boolean isSatisfactory() {
        if ((good && fast) || (good && cheap) || (fast && cheap)) {
            return true;
        }
        return false;
    }
}