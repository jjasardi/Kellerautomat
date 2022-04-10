package ch.zhaw.THIN.kellerautomat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KellerautomatTest {

    private Kellerautomat kellerautomat;
    private static final String TEST_A = "34+62+89+43+***";
    private static final String TEST_B = "31+78+987+1214++7++++++";
    private static final String TEST_C_1 = "34+*";
    private static final String TEST_C_2 = "8+9+7*2*";

    @BeforeEach
    void setup() {
        this.kellerautomat = new Kellerautomat();
    }

    @Test
    public void testA() {
        String result = kellerautomat.run(TEST_A);
        assertEquals("6664", result);
    }

    @Test
    public void testB() {
        String result = kellerautomat.run(TEST_B);
        assertEquals("58", result);
    }

    @Test
    public void testC1() {
        String result = kellerautomat.run(TEST_C_1);
        assertEquals("Wrong Input!", result);
    }

    @Test
    public void testC2() {
        String result = kellerautomat.run(TEST_C_2);
        assertEquals("Wrong Input!", result);
    }

}
