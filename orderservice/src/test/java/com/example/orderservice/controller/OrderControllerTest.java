package com.example.orderservice.controller;


import com.example.orderservice.model.Order;
import com.example.orderservice.model.Product;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository orderRepo;

    @MockBean
    private ProductRepository productRepo;

    @Autowired
    private ObjectMapper objectMapper;

    private Order sampleOrder;
    private Product sampleProduct;
    private OrderRepository mockRepository;
    @BeforeEach
    void setup() {
        sampleProduct = new Product();
        sampleProduct.setId(1L);
        sampleProduct.setName("Test Product");

        sampleOrder = new Order();
        sampleOrder.setId(1L);
        sampleOrder.setCustomerName("Ali");
        sampleOrder.setDate(LocalDate.now());
        sampleOrder.setProducts(Set.of(sampleProduct));
    }

    @Test
    void testGetAllOrders() throws Exception {
        when(orderRepo.findAll()).thenReturn(List.of(sampleOrder));

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].customerName").value("Ali"));
    }


    @Test
    void testCreateOrder() throws Exception {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test Product");
        product.setPrice(100.0);

        OrderController.OrderRequest request = new OrderController.OrderRequest();
        request.customerName = "Ali";
        request.productIds = List.of(productId);

        Order savedOrder = new Order();
        savedOrder.setId(10L);
        savedOrder.setCustomerName("Ali");
        savedOrder.setDate(LocalDate.now());
        savedOrder.setProducts(Set.of(product));

        // Mock repositories
        Mockito.when(productRepo.findAllById(Set.of(productId))).thenReturn(List.of(product));
        Mockito.when(orderRepo.save(any(Order.class))).thenReturn(savedOrder);

        // Act + Assert
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/orders/10"))
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.customerName").value("Ali"));
    }


    @Test
    void testDeleteOrder() throws Exception {
        doNothing().when(orderRepo).deleteById(1L);

        mockMvc.perform(delete("/orders/1"))
                .andExpect(status().isOk());

        verify(orderRepo).deleteById(1L);
    }
}