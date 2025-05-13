//package com.example.order;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.TestConfiguration;
////import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//@ActiveProfiles("test")
//@WebMvcTest(OrderController.class)  //  Test only the controller
//public class OrderControllerComponentTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Import(TestConfig.class)
//    @TestConfiguration
//    static class TestConfig {
//        @Bean
//        OrderService orderService() {
//            return Mockito.mock(OrderService.class);
//        }
//    }
////    @MockBean
//   private OrderService orderService;  //  Mock service
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    /*
//      Component Test:
//      Tests POST /Orders endpoint, verifies status and response body.
//      Service is mocked. DB is NOT involved.
//     */
//    @Test
//    void shouldCreateOrder() throws Exception {
//        // Arrange
//        Order inputOrder = new Order();
//        inputOrder.setOrder("Linda");
//
//
//        Order mockResponseOrder = new Order();
//        mockResponseOrder.setId(1L);
//        mockResponseOrder.setOrder("Linda");
//
//
//        when(orderService.createOrder(any(Order.class))).thenReturn(mockResponseOrder);
//
//        // Act & Assert
//        mockMvc.perform(post("/orders")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(inputOrder)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Linda"));
//    }
//}