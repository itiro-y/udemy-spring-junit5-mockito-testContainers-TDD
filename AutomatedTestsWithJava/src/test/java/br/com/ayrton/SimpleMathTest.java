package br.com.ayrton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Math Operations in SimpleMath Class")
class SimpleMathTest {

    @Test
    @DisplayName("Test 6.2 + 2 = 8.2")
    void testSum_WhenSixDotTwoPlusTwo_ShouldEqualEightDotTwo() {
        // Given - Arrange
        SimpleMath simpleMath = new SimpleMath();
        double firstNumber = 6.2D;
        double secondNumber = 2D;
        double expected = 8.2D;

        // When - Act
        double result = simpleMath.sum(firstNumber, secondNumber);

        // Then - Assert
        assertEquals(expected, result,
                () -> firstNumber + " + " + secondNumber + " is not equal to " + expected);
        assertNotNull(result, "The result is null!");
    }

    @Test
    @DisplayName("Test 6.2 - 2 = 4.2")
    void testSubtraction() {
        SimpleMath simpleMath = new SimpleMath();

        double firstNumber = 6.2D;
        double secondNumber = 2D;
        double result = simpleMath.subtraction(firstNumber, secondNumber);
        double expected = 4.2D;

        assertEquals(expected, result,
                () -> firstNumber + " - " + secondNumber + " is not equal to " + expected);

        assertNotNull(result, "The result is null!");
    }

    @Test
    @DisplayName("Test 6.2 * 2 = 12.4")
    void testMultiplication() {
        SimpleMath simpleMath = new SimpleMath();

        double firstNumber = 6.2D;
        double secondNumber = 2D;
        double result = simpleMath.multiplication(firstNumber, secondNumber);
        double expected = 12.4D;

        assertEquals(expected, result,
                () -> firstNumber + " * " + secondNumber + " is not equal to " + expected);

        assertNotNull(result, "The result is null!");
    }

    @Test
    @DisplayName("Test 6.2 / 2 = 3.1")
    void testDivision() {
        SimpleMath simpleMath = new SimpleMath();

        double firstNumber = 6.2D;
        double secondNumber = 2D;
        double result = simpleMath.division(firstNumber, secondNumber);
        double expected = 3.1D;

        assertEquals(expected, result,
                () -> firstNumber + " / " + secondNumber + " is not equal to " + expected);

        assertNotNull(result, "The result is null!");
    }

    //@Test
    @DisplayName("Test division by zero")
    void testDivision_WhenFirstNumberIsDividedByZero_ShouldThrowArithmeticException() throws ArithmeticException {
        fail();
    }

    @Test
    @DisplayName("Test (6.2 + 2)/2 = 4.1")
    void testMean() {
        SimpleMath simpleMath = new SimpleMath();
        double firstNumber = 6.2D;
        double secondNumber = 2D;
        double result = simpleMath.mean(firstNumber, secondNumber);
        double expected = 4.1D;

        assertEquals(expected, result,
                () -> "The mean of " + firstNumber + " and " + secondNumber + " is not equal to " + expected);

        assertNotNull(result, "The result is null!");
    }

    @Test
    @DisplayName("Test Square Root of 25 = 5")
    void testSquareRoot() {
        SimpleMath simpleMath = new SimpleMath();
        double number = 25D;
        double result = simpleMath.squareRoot(number);
        double expected = 5D;

        assertEquals(expected, result,
                () -> "The square root of " + number + " is not equal to " + expected);

        assertNotNull(result, "The result is null!");
        assertTrue(result > 0, "The square root should be positive!");
    }
}