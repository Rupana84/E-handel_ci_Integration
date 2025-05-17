package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.model.Product;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;

    public OrderController(OrderRepository orderRepo, ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    @GetMapping
    public List<Order> getAll() {
        return orderRepo.findAll();
    }
    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderRequest request) {
        Set<Product> products = new HashSet<>(productRepo.findAllById(request.productIds));

        Order order = new Order();
        order.setCustomerName(request.customerName);
        order.setDate(LocalDate.now());
        order.setProducts(products);

        Order saved = orderRepo.save(order);
        URI location = URI.create("/orders/" + saved.getId());
        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderRepo.deleteById(id);
    }

    public static class OrderRequest {
        public String customerName;
        public List<Long> productIds;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        return orderRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
