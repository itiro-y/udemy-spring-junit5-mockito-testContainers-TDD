package br.com.ayrton.order;

import org.junit.jupiter.api.*;

//@Order(3)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MethodOrderedByOrderIndexTest {
    StringBuilder builder = new StringBuilder();

    @AfterEach
    void afterEach(){
        System.out.println("The actual value is: " + builder);
    }

    @Test
    @Order(4)
    void testC(){
        System.out.println("Test C");
        builder.append("4");
    }

    @Test
    @Order(2)
    void testA(){
        System.out.println("Test A");
        builder.append("2");
    }

    @Test
    @Order(3)
    void testD(){
        System.out.println("Test D");
        builder.append("3");
    }

    @Test
    @Order(1)
    void testB(){
        System.out.println("Test B");
        builder.append("1");
    }


}
