package br.com.ayrton;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArraysCompareTest {
    @Test
    void testArrays() {
        int[] numbers = {3, 2, 1};
        int[] expectedArray = {1, 2, 3};

        Arrays.sort(numbers);

        // Using assertArrayEquals to compare arrays
        assertArrayEquals(numbers, expectedArray,
                "The arrays are not equal!");
    }

    @Test
    // @Timeout(1) - segundos
    @Timeout(value = 1, unit = TimeUnit.MILLISECONDS)
    void testSortPerformance() {
        int[] numbers = {3, 2, 1, 7, 9, 4};
        for(int i = 0; i < 1000000; i++){
            numbers[0] = i;
            Arrays.sort(numbers);
        }
    }
}
