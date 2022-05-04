import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AsteroidHashTest {

    /* NOTE: Take these tests with a grain of salt: Hashcodes cannot really be
       right or wrong. They can only be more or less efficient. */

    private static final double DIST = -3400.12;
    private static final double THETA = 300.59;
    private static final double PHI = -29.34;
    private static final int SPEED = -403;
    private static final SizeCategory SIZE = SizeCategory.LARGE;

    private static final double DELTA = 0.001;

    private Asteroid m_rockA;
    private Asteroid m_rockB;

    @Before
    public void setUp() {
        m_rockA = new Asteroid(DIST, THETA, PHI, SPEED, SIZE);
        m_rockB = new Asteroid(-9730.6, -14.46, 233.29, 371, SizeCategory.MEDIUM);
    }

    private Object getPrivateField(Object getObj, Class<?> c, String fieldName) {
        try {
            Field f = c.getDeclaredField(fieldName);
            f.setAccessible(true);
            return f.get(getObj);
        } catch (Exception e) {
            System.out.println("Error retrieving private int field " + fieldName + ": " + e);
            return null;
        }
    }

    private void setPrivateField(Object setObj, Class<?> c, String fieldName, Object value) {
        try {
            Field f = c.getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(setObj, value);
        } catch (Exception e) {
            System.out.println("Error setting private field " + fieldName + ": " + e);
        }
    }

    private void setupEqualAsteroids() {
        setPrivateField(m_rockA, m_rockA.getClass(), "distance", DIST);
        setPrivateField(m_rockB, m_rockB.getClass(), "distance", DIST);
        setPrivateField(m_rockA, m_rockA.getClass(), "theta", THETA);
        setPrivateField(m_rockB, m_rockB.getClass(), "theta", THETA);
        setPrivateField(m_rockA, m_rockA.getClass(), "phi", PHI);
        setPrivateField(m_rockB, m_rockB.getClass(), "phi", PHI);
        setPrivateField(m_rockA, m_rockA.getClass(), "speed", SPEED);
        setPrivateField(m_rockB, m_rockB.getClass(), "speed", SPEED);
        setPrivateField(m_rockA, m_rockA.getClass(), "size", SIZE);
        setPrivateField(m_rockB, m_rockB.getClass(), "size", SIZE);
    }

    @Test
    public void testHashSameInstance() {
        int codeA = m_rockA.hashCode();
        int codeB = m_rockA.hashCode();

        assertEquals("Hashcode expected to be the same for same instance.", codeA, codeB);
    }

    @Test
    public void testHashSameState() {
        setupEqualAsteroids();

        int codeA = m_rockA.hashCode();
        int codeB = m_rockB.hashCode();

        assertEquals("Hashcode expected to be the same for objects with the same state.", codeA, codeB);
    }


    @Test
    public void testHashDiffState() {
        int codeA = m_rockA.hashCode();
        int codeB = m_rockB.hashCode();

        assertNotEquals("Hashcode should be different for objects with different state.", codeA, codeB);
    }

    @Test
    public void testHashChangeDist() {
        int codeA = m_rockA.hashCode();

        setPrivateField(m_rockA, m_rockA.getClass(), "distance", DIST + 35);
        int codeB = m_rockA.hashCode();

        assertNotEquals("Hashcode should be different if state has changed.", codeA, codeB);
    }

    @Test
    public void testHashChangeTheta() {
        int codeA = m_rockA.hashCode();

        setPrivateField(m_rockA, m_rockA.getClass(), "theta", THETA - 20.12);
        int codeB = m_rockA.hashCode();

        assertNotEquals("Hashcode should be different if state has changed.", codeA, codeB);
    }

    @Test
    public void testHashChangePhi() {
        int codeA = m_rockA.hashCode();

        setPrivateField(m_rockA, m_rockA.getClass(), "phi", PHI - 156.12);
        int codeB = m_rockA.hashCode();

        assertNotEquals("Hashcode should be different if state has changed.", codeA, codeB);
    }

    @Test
    public void testHashChangeSpeed() {
        int codeA = m_rockA.hashCode();

        setPrivateField(m_rockA, m_rockA.getClass(), "speed", SPEED + 709);
        int codeB = m_rockA.hashCode();

        assertNotEquals("Hashcode should be different if state has changed.", codeA, codeB);
    }

    @Test
    public void testHashChangeSize() {
        int codeA = m_rockA.hashCode();

        setPrivateField(m_rockA, m_rockA.getClass(), "size", SizeCategory.MEDIUM);
        int codeB = m_rockA.hashCode();

        assertNotEquals("Hashcode should be different if state has changed.", codeA, codeB);
    }

}
