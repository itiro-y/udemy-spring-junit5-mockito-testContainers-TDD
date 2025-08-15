package br.com.ayrton;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimpleMathTest {

    @Test
    void testSum() {
        SimpleMath simpleMath = new SimpleMath();
        Double result = simpleMath.sum(6.2D, 2D);
        Double expected = 8.2D;
        assertEquals(expected, result, "The testSum() did not pass!");
    }


}