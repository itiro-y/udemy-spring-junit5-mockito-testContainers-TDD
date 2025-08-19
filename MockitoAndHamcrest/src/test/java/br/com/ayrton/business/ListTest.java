package br.com.ayrton.business;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ListTest {

    @Test
    void testMockingList_WhenSizeIsCalled_ShouldReturn10(){
        // Given
        List list = mock(List.class);
        when(list.size()).thenReturn(10);

        // When & Then
        assertEquals(10, list.size(), () -> "The mocked list size should be 10!");
    }

    @Test
    void testMockingList_WhenSizeIsCalled_ShouldReturnMultipleValues(){
        // Given
        List list = mock(List.class);
        when(list.size()).thenReturn(10).thenReturn(20).thenReturn(30);

        // When & Then
        assertEquals(10, list.size(), () -> "The mocked list size should be 10!");
        assertEquals(20, list.size(), () -> "The mocked list size should be 10!");
        assertEquals(30, list.size(), () -> "The mocked list size should be 10!");
    }

    @Test
    void testMockingList_WhenGetIsCalled_ShouldReturnAyrton(){
        // Given
        List list = mock(List.class);
        when(list.get(0)).thenReturn("Ayrton");

        // When & Then
        assertEquals("Ayrton", list.get(0), () -> "The mocked list should return 'Ayrton' when get(0) is called!");
        assertNull(list.get(1), () -> "The mocked list should return null when get(1) is called!");
    }

    @Test
    void testMockingList_WhenGetIsCalledWithArgumentMatcher_ShouldReturnAyrton(){
        // Given
        List list = mock(List.class);

        // If you are using argument matchers, all arguments are provided by matchers
        when(list.get(anyInt())).thenReturn("Ayrton");

        // When & Then
        assertEquals("Ayrton", list.get(anyInt()), () -> "The mocked list should return 'Ayrton' when get(anyInt()) is called!");
        assertEquals("Ayrton", list.get(0), () -> "The mocked list should return 'Ayrton' when get(0) is called!");
        assertEquals("Ayrton", list.get(1), () -> "The mocked list should return 'Ayrton' when get(0) is called!");
//      assertNull(list.get(1), () -> "The mocked list should return null when get(1) is called!");
    }

    @Test
    void testMockingList_WhenThrowsAnException(){
        // Given
        List list = mock(List.class);

        // If you are using argument matchers, all arguments are provided by matchers
        when(list.get(anyInt())).thenThrow(new RuntimeException("Run time Exception!"));

        // When & Then
        assertThrows(
                RuntimeException.class,
                () -> {list.get(anyInt());},
                () -> "Expected RuntimeException to be thrown when get(anyInt()) is called");


    }
}
