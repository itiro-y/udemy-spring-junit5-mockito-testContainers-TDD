package br.com.ayrton.math;

import br.com.ayrton.SimpleMath;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Math Operations in SimpleMath Class")
class SimpleMathTest {

    SimpleMath simpleMath;

    @BeforeAll
    static void setup() {
        System.out.println("Running @BeforeAll method!");
    }

    @AfterAll
    static void cleanup() {
        System.out.println("Running @AfterAll method!");
    }

    @BeforeEach
    void beforeEachMethod() {
        simpleMath = new SimpleMath();
        System.out.println("Running @BeforeEach method !");
    }

    @AfterEach
    void afterEachMethod() {
        System.out.println("Running @AfterEach method !");
    }

    @Test
    @DisplayName("Test 6.2 + 2 = 8.2")
    void testSum_WhenSixDotTwoPlusTwo_ShouldEqualEightDotTwo() {

        // Given - Arrange
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

        double firstNumber = 6.2D;
        double secondNumber = 2D;
        double result = simpleMath.division(firstNumber, secondNumber);
        double expected = 3.1D;

        assertEquals(expected, result,
                () -> firstNumber + " / " + secondNumber + " is not equal to " + expected);

        assertNotNull(result, "The result is null!");
    }

    // @Disabled("TODO: We still need to implement this test")
    @Test
    @DisplayName("Test division by zero")
    void testDivision_WhenFirstNumberIsDividedByZero_ShouldThrowArithmeticException() {
        // Given
        double firstNumber = 6.2D;
        double secondNumber = 0D;
        String expectedMessage = "Division by zero!";

        ArithmeticException actual = assertThrows(
                ArithmeticException.class, () -> {
                    // When & Then
                    simpleMath.division(firstNumber, secondNumber);
                }, () -> "Expected ArithmeticException to be thrown");

        assertEquals(expectedMessage, actual.getMessage(),
                () -> "The exception message is not equal to " + expectedMessage);

    }

    @Test
    @DisplayName("Test (6.2 + 2)/2 = 4.1")
    void testMean() {
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
        double number = 25D;
        double result = simpleMath.squareRoot(number);
        double expected = 5D;

        assertEquals(expected, result,
                () -> "The square root of " + number + " is not equal to " + expected);

        assertNotNull(result, "The result is null!");
        assertTrue(result > 0, "The square root should be positive!");
    }
}