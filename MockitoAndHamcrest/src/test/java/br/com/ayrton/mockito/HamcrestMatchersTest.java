package br.com.ayrton.mockito;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersTest {

    @Test
    void testHamcrestMatchers(){
        // List Matchers
        List<Integer> scores = Arrays.asList(98, 99, 100, 101, 105);
        assertThat(scores, hasSize(5));
        assertThat(scores, hasItems(99, 100));
        assertThat(scores, everyItem(greaterThan(90)));
        assertThat(scores, everyItem(lessThan(110)));

        // Strings Matchers
        assertThat("", is(emptyString()));
        assertThat(null, is(emptyOrNullString()));

        // Array Matchers
        Integer[] myArr = {1, 2, 3};
        assertThat(myArr, arrayWithSize(3));
        assertThat(myArr, arrayContaining(1, 2, 3));
        assertThat(myArr, arrayContainingInAnyOrder(3, 1, 2));

    }
}
