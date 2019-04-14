package testing.com.sampleapplication;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test(expected = ArithmeticException.class)
    public void divisionWithZero(){
        double total = 4/0;
        assertEquals(1.0,total);
    }

    @Test
    public void divisionWithSameNumber(){
        int total = 4/4;
        assertEquals("answer is correct : ",1,total);
    }
}