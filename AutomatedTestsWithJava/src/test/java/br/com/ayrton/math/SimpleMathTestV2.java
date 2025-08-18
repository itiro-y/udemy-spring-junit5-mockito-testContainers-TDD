package br.com.ayrton.math;

import br.com.ayrton.SimpleMath;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Math Operations in SimpleMath Class")
class SimpleMathTestV2 {

    SimpleMath simpleMath;

    @BeforeEach
    void beforeEachMethod() {
        simpleMath = new SimpleMath();
        System.out.println("Running @BeforeEach method !");
    }

//    public static Stream<Arguments> testDivision() {
//        return Stream.of(
//                Arguments.of(6.2D, 2D, 3.1D),
//                Arguments.of(71D, 14D, 5.07D),
//                Arguments.of(18.3D, 3.1D, 5.90D)
//        );
//    }
//    @MethodSource("testDivisionInputParameters")
//    @MethodSource()
//    @CsvSource({ "6.2, 2, 3.1",
//                 "71, 14, 5.07",
//                 "18.3, 3.1, 5.90" })
    @DisplayName("Test double division [double firstNumber, double secondNumber, double expectedResult]")
    @ParameterizedTest
    @CsvFileSource(resources = "/testDivision.csv")
    void testDivision(double firstNumber, double secondNumber, double expectedResult) {

        double result = simpleMath.division(firstNumber, secondNumber);

        assertEquals(expectedResult, result, 2D,
                () -> firstNumber + " / " + secondNumber + " is not equal to " + expectedResult);

        assertNotNull(result, "The result is null!");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Pedro", "Ana", "João"})
    void testValueSource(String firstName){
        System.out.println(firstName);
        assertNotNull(firstName, "The firstName is null!");
    }
}