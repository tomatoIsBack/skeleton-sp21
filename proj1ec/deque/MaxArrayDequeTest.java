package deque;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {

    private class IntComparator<Integer> implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return (int) o1 - (int) o2;
        }
    }
    @Test
    public void intComparatorTest() {
        MaxArrayDeque<Integer> d = new MaxArrayDeque<>(new IntComparator());
        d.addFirst(1);
        d.addFirst(2);
        d.addFirst(3);
        assertEquals(3, (int) d.max());
    }
}
