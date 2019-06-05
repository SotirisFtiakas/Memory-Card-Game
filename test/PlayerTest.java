import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gregory Barmpas
 * @author Sotiris Ftiakas
 */
public class PlayerTest {
    public PlayerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addWin method, of class Player.
     */
    @Test
    public void testAddWin() {
        System.out.println("addWin");
        Player instance = new Player("Test");
        int expResult = 1;
        instance.addWin();
        int result = instance.getWins();
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of addTries method, of class Player.
     */
    @Test
    public void testAddTries() {
        System.out.println("addTries");
        Player instance = new Player("Test");
        int expResult = 2;
        instance.addTries();
        instance.addTries();
        int result = instance.getTries();
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of addCorrectGuesses method, of class Player.
     */
    @Test
    public void testAddCorrectGuesses() {
        System.out.println("addCorrectGuesses");
        Player instance = new Player("Test");
        int expResult = 3;
        instance.addCorrectGuesses();
        instance.addCorrectGuesses();
        instance.addCorrectGuesses();
        int result = instance.getCorrectGuesses();
        assertEquals(expResult, result, 0.1);
    }

}
