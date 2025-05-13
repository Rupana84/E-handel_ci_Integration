//package com.example.order;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.test.context.ActiveProfiles;
//
//@ActiveProfiles("test")
//@ExtendWith(MockitoExtension.class)
//class OrderServiceTest {
//
//    @Mock
//    private OrderRepository orderRepository;
//
//    @InjectMocks
//    private OrderService orderService;
//
//
//     //Unit Test ---> createOrder when email does not exist.
//
//    @Test
//    void createOrder_shouldSaveOrder_whenEmailNotExists() {
//        // Arrange
//        Order order = new Order();
//        order.setOrder("rupana");
//
//
//        when(orderRepository.save(order)).thenReturn(order);
//
//        // Act
//        Order result = orderService.createOrder(order);
//
//        // Assert
//        assertEquals("rupana", result.getOrder());
//        verify(orderRepository).save(order);
//    }
//
//
//     //getOrderById returns Order.
//
//    @Test
//    void getOrderById_shouldReturnOrder_whenOrderExists() {
//        // Arrange
//        Order order = new Order();
//        order.setId(1L);
//        order.setOrder("Bil");
//
//
//        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
//
//        // Act
//        Optional<Order> result = orderService.getOrderById(1L);
//
//        // Assert
//        assertTrue(result.isPresent());
//        assertEquals("Bil", result.get().getOrder());
//        verify(orderRepository).findById(1L);
//    }
//
//      //deleteOrder with valid ID.
//
//    @Test
//    void deleteOrder_shouldReturnTrue_whenOrderExists() {
//        // Arrange
//        when(orderRepository.existsById(2L)).thenReturn(true);
//
//        // Act
//        boolean deleted = orderService.deleteOrder(2L);
//
//        // Assert
//        assertTrue(deleted);
//        verify(orderRepository).deleteById(2L);
//    }
//}
