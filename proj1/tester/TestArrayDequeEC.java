package tester;

import deque.ArrayDeque;
import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;
public class TestArrayDequeEC {

    @Test
    public void testRandomTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        double numberBetweenZeroAndOne = StdRandom.uniform();
        for (int i = 0; i < 10000; i++) {
            if (numberBetweenZeroAndOne < 0.5) {
                ad.addLast(i);
            } else {
                ad.addFirst(i);
            }
        }
        assertEquals(10000, ad.size());
    }

    @Test
    public void testRandomTest2() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        double numberBetweenZeroAndOne = StdRandom.uniform();
        for (int i = 0; i < 10000; i++) {
            if (numberBetweenZeroAndOne < 0.5) {
                ad.addLast(i);
            } else {
                ad.addFirst(i);
            }
        }
        assertEquals("Oh noooo!\nThis is bad:\n   Random number " + ad.size()
                + " not equal to " + 10000 + "!", 10000, ad.size());
    }
}
