package br.com.ayrton.mockito.checkout;

import br.com.ayrton.checkout.CheckoutService;
import br.com.ayrton.checkout.PaymentProcessor;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

class CheckoutServiceTest {

    @Test
    void testPurchaseProductMockedConstructors() {
        try(MockedConstruction<PaymentProcessor> mockedConstruction = mockConstruction(
                PaymentProcessor.class, (mock, context) -> {
                        when(mock.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);
                })
        ) {
          CheckoutService service = new CheckoutService();
          BigDecimal result = service.purchaseProduct("Laptop", "42");

          assertEquals(BigDecimal.TEN, result);
          assertEquals(1, mockedConstruction.constructed().size());
        }
    }
}