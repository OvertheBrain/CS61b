import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void testFlik() {
        assertEquals(true, Flik.isSameNumber(127, 127));
        assertEquals(true, Flik.isSameNumber(129, 129));
        assertEquals(true, Flik.isSameNumber(500, 500));
        assertEquals(true, Flik.isSameNumber(128, 128));
    }
}
