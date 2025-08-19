package br.com.ayrton.mockito.order;

import br.com.ayrton.order.Order;
import br.com.ayrton.order.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private final OrderService orderService = new OrderService();
    private final UUID defaultUUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private final LocalDateTime defaultLocalDateTime = LocalDateTime.of(2025, 8, 19, 18, 38);

    @Test
    void testShouldIncludeRandomOrderId_WhenNoOrderIdExists(){
        try(MockedStatic<UUID> mockedUUID = mockStatic(UUID.class)){
            mockedUUID.when(UUID::randomUUID).thenReturn(defaultUUID);

            Order result = orderService.createOrder("Product A", 2L, null);

            assertEquals(defaultUUID.toString(), result.getId());
        }
    }

    @Test
    void testShouldIncludeCurrentTime_WhenCreatingANewOrder(){
        try(MockedStatic<LocalDateTime> mockedLocalDateTime = mockStatic(LocalDateTime.class)){
            mockedLocalDateTime.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);

            Order result = orderService.createOrder("Product A", 2L, null);

            assertEquals(defaultLocalDateTime, result.getCreationDate());
        }
    }
}