package br.com.ayrton.mockito;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SpyTest {

    @Test
    void testSpy(){
        List<String> mockArrayList = spy(ArrayList.class);

        assertEquals(0, mockArrayList.size(), () -> "The mocked ArrayList size should be 0!");

        when(mockArrayList.size()).thenReturn(5);
        mockArrayList.add("String A");

        assertEquals(5, mockArrayList.size(), () -> "The mocked ArrayList size should still be 5!");
    }

    @Test
    void testSpyV2(){
        List<String> spyArrayList = spy(ArrayList.class);
        assertEquals(0, spyArrayList.size(), () -> "The mocked ArrayList size should be 0!");

        spyArrayList.add("String A");
        assertEquals(1, spyArrayList.size(), () -> "The mocked ArrayList size should still be 1!");

        spyArrayList.remove("String A");
        assertEquals(0, spyArrayList.size(), () -> "The mocked ArrayList size should still be 0!");
    }


    @Test
    void testSpyV3(){
        List<String> spyArrayList = spy(ArrayList.class);
        assertEquals(0, spyArrayList.size(), () -> "The mocked ArrayList size should be 0!");

        when(spyArrayList.size()).thenReturn(5);

        spyArrayList.add("String A"); // No spy n adiciona no tamanho da lista, mas no mock sim
        assertEquals(5, spyArrayList.size(), () -> "The mocked ArrayList size should still be 1!");
    }

    @Test
    void testSpyV4(){
        List<String> spyArrayList = spy(ArrayList.class);
        spyArrayList.add("String A");
        verify(spyArrayList).add("String A"); // verificando se o metodo add foi chamado
        verify(spyArrayList, never()).remove("String A"); // verificando se o metodo remove nunca foi chamado
        verify(spyArrayList, never()).remove(anyString());
        verify(spyArrayList, never()).clear();
    }
}
