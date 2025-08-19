package br.com.ayrton.mockito.myutil;

import br.com.ayrton.myutil.MyUtils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

class MyUtilsTest {

    @Test
    void testGetWelcomeMessage_ShouldMockStaticMethodWithParams() {
        try(MockedStatic<MyUtils> mockedStatic = mockStatic(MyUtils.class)){
            mockedStatic.when(() -> MyUtils.getWelcomeMessage( eq("Ayrton"), anyBoolean()) )
                    .thenReturn("Howdy Ayrton!");

            String result = MyUtils.getWelcomeMessage("Ayrton", false);

            assertEquals("Howdy Ayrton!", result);
        }
    }
}