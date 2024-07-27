import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        String message = "";
        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                ads1.addLast(i);
                message += "addLast(" + i + ")\n";
            } else if(numberBetweenZeroAndOne < 0.8) {
                sad1.addFirst(i);
                ads1.addFirst(i);
                message += "addFirst(" + i + ")\n";
            } else {
                message += "size()\n";
                assertEquals(message, ads1.size(), sad1.size());
            }
        }
        for (int i = 0; i < 10; i += 1) {
            Integer expected;
            Integer actual;
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                expected = ads1.removeFirst();
                actual = sad1.removeFirst();
                message += "removeFirst()\n";
                assertEquals(message, expected, actual);
            } else {
                expected = ads1.removeLast();
                actual = sad1.removeLast();
                message += "removeLast()\n";
                assertEquals(message, expected, actual);
            }
        }
    }
}
