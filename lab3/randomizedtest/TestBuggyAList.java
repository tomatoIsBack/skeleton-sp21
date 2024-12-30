package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> al = new AListNoResizing<>();
        BuggyAList<Integer> bl = new BuggyAList<>();
        al.addLast(4);
        al.addLast(5);
        al.addLast(6);
        bl.addLast(4);
        bl.addLast(5);
        bl.addLast(6);
        assertSame(al.removeLast(), bl.removeLast());
    }

    @Test
    public void randomizeedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                assertSame(L.size(),B.size());
            } else if (operationNumber == 2) {
                // getLast
                if (L.size() > 0) {
                    assertSame(L.getLast(),B.getLast());
                }
            } else if (operationNumber == 3) {
                // rm last
                if (L.size() > 0) {
                    assertSame(L.removeLast(),B.removeLast());
                }
            }
        }
    }
}
