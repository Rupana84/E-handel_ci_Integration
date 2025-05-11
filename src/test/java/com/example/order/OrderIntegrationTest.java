//    package com.example.order;
//
//    import org.junit.jupiter.api.Test;
//    import org.mockito.Mockito;
//    import org.springframework.beans.factory.annotation.Autowired;
//    import org.springframework.boot.test.context.SpringBootTest;
//
//    import org.springframework.boot.test.context.TestConfiguration;
//   // import org.springframework.boot.test.mock.mockito.MockBean;
//    import org.springframework.context.annotation.Bean;
//    import org.springframework.test.context.ActiveProfiles;
//    import org.springframework.transaction.annotation.Transactional;
//
//    import java.util.Optional;
//
//    import static org.junit.jupiter.api.Assertions.*;
//
//
//    @ActiveProfiles("test")
//    @SpringBootTest
//    @Transactional
//    public class OrderIntegrationTest {
//
//        @Autowired
//        private OrderService orderService;
//
//        @Autowired
//        private OrderRepository orderRepository;
//
//        //  Mock external dependency to keep the test isolated from other services
//
//        @TestConfiguration
//        static class TestConfig {
//            @Bean
//            ProductClient productClient() {
//                return Mockito.mock(ProductClient.class);
//            }
//        }
//
//
////        @MockBean
//       private ProductClient productClient;
//
//        //Integration Test---> Test 1: Save and fetch Order from test DB of test_db
//
//        @Test
//        void shouldCreateAndFetchOrderFromDatabase() {
//            // Arrange
//            Order order = new Order();
//            order.setOrder("Car");
//
//            // Act
//            Order savedOrder = orderService.createOrder(order);
//            Optional<Order> fetchedOrder = orderService.getOrderById(savedOrder.getId());
//
//            // Assert
//            assertTrue(fetchedOrder.isPresent());
//
//        }
//
//
//         // Test 2: Update Order in DB and verify changes
//
//        @Test
//        void shouldUpdateOrderInDatabase() {
//            // Arrange
//            Order order = new Order();
//            order.setOrder("Jag");
//            Order saved = orderService.createOrder(order);
//
//            // Act
//            Order updateData = new Order();
//            updateData.setOrder("Tobias");
//
//
//            orderService.updateOrder(saved.getId(), updateData);
//            Optional<Order> updated = orderService.getOrderById(saved.getId());
//
//            // Assert
//            assertTrue(updated.isPresent());
//            assertEquals("Tobias", updated.get().getOrder());
//        }
//
//
//         //Test 3: Delete Order and ensure removal
//
//        @Test
//        void shouldDeleteOrderFromDatabase() {
//            // Arrange
//            Order order = new Order();
//            order.setOrder("DeleteMe");
//            Order saved = orderService.createOrder(order);
//
//            // Act
//            boolean deleted = orderService.deleteOrder(saved.getId());
//            Optional<Order> found = orderService.getOrderById(saved.getId());
//
//            // Assert the
//            assertTrue(deleted);
//            assertFalse(found.isPresent());
//        }
//    }