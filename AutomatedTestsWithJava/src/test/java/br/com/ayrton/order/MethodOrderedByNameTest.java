package br.com.ayrton.order;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
@Order(2)
public class MethodOrderedByNameTest {

    @Test
    void testC(){
        System.out.println("Test C");
    }

    @Test
    void testA(){
        System.out.println("Test A");
    }

    @Test
    void testD(){
        System.out.println("Test D");
    }

    @Test
    void testB(){
        System.out.println("Test B");
    }


}
