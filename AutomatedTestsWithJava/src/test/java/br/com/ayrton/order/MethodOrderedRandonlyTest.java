package br.com.ayrton.order;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
@Order(1)
public class MethodOrderedRandonlyTest {
    @Test
    void testA(){
        System.out.println("Test A");
    }

    @Test
    void testB(){
        System.out.println("Test B");
    }

    @Test
    void testC(){
        System.out.println("Test C");
    }

    @Test
    void testD(){
        System.out.println("Test D");
    }

}
