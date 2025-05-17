package com.example.orderservice.service;


import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private OrderRepository repository;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        repository = mock(OrderRepository.class);
        orderService = new OrderService(repository);
    }

    @Test
    void testCreate_shouldSaveOrder() {
        // Arrange.
        Order order = new Order();
        order.setCustomerName("Ali");
        order.setDate(LocalDate.now());

        when(repository.save(order)).thenReturn(order);

        // Act
        Order result = orderService.create(order);

        // Assert
        assertEquals("Ali", result.getCustomerName());
        verify(repository).save(order);
    }

    @Test
    void testGetAll_shouldReturnListOfOrders() {
        // Arrange
        List<Order> orders = Arrays.asList(new Order(), new Order());
        when(repository.findAll()).thenReturn(orders);

        // Act
        List<Order> result = orderService.getAll();

        // Assert
        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void testGetById_whenFound_shouldReturnOrder() {
        // Arrange
        Order order = new Order();
        order.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(order));

        // Act
        Order result = orderService.getById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository).findById(1L);
    }

    @Test
    void testGetById_whenNotFound_shouldReturnNull() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Order result = orderService.getById(1L);

        // Assert
        assertNull(result);
        verify(repository).findById(1L);
    }

    @Test
    void testDelete_shouldCallRepository() {
        // Act
        orderService.delete(1L);

        // Assert
        verify(repository).deleteById(1L);
    }
}
