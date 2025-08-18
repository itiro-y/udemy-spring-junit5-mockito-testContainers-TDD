package br.com.ayrton;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DemoRepeatedTest {

    SimpleMath simpleMath;

    @BeforeEach
    void beforeEachMethod() {
        simpleMath = new SimpleMath();
        System.out.println("Running @BeforeEach method !");
    }

    @RepeatedTest(value = 3, name = "{displayName}. Repetition {currentRepetition} of {totalRepetitions}")
    @DisplayName("Test division by zero")
    void testDivision_WhenFirstNumberIsDividedByZero_ShouldThrowArithmeticException(
            RepetitionInfo repetitionInfo, TestInfo testInfo
    ) {
        System.out.println("Running test: " + testInfo.getTestMethod().get().getName());
        System.out.println("Division by zero test - #" + repetitionInfo.getCurrentRepetition()
                + " of #" + repetitionInfo.getTotalRepetitions());
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
}
