/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package tutorial1;

import org.junit.Test;
import static org.junit.Assert.*;

public class WeekendTest {
    @Test
    public void testIsIt() {
        assertFalse(Weekend.isIt("Tuesday", 2));
        assertFalse(Weekend.isIt("Tuesday", 3));
        assertTrue(Weekend.isIt("Tuesday", 4));
        assertTrue(Weekend.isIt("Tuesday", 5));
    }
}
