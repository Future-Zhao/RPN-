import org.junit.Test;

public class RPNTest {
    RpnNumerator cal = new RpnNumerator();
    @Test
    public void testExampleOne() throws Exception {
        cal.rpnExpression("5 2");
    }

    @Test
    public void testExampleTwo() throws Exception {
        cal.rpnExpression("2 sqrt");
    }

    @Test
    public void testExampleTwo_One() throws Exception {
        cal.rpnExpression("clear 9 sqrt");
    }

}
