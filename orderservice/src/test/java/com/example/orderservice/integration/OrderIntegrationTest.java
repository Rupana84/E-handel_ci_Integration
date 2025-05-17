package com.example.orderservice.integration;



import com.example.orderservice.controller.OrderController;
import com.example.orderservice.model.Product;
import com.example.orderservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.net.URI;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    private Long productId;

    @BeforeEach
    void setUp() {
        Product product = new Product();
        product.setName("Keyboard");
        product.setPrice(150.0);
        productId = productRepository.save(product).getId();
    }

    @Test
    @DisplayName("Should create order and return it ")
    void testCreateAndGetOrder() {
        // Prepare order request
        OrderController.OrderRequest request = new OrderController.OrderRequest();
        request.customerName = "Ali";
        request.productIds = List.of(productId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OrderController.OrderRequest> httpRequest = new HttpEntity<>(request, headers);

        // POST /orders
        ResponseEntity<Map<String, Object>> postResponse = restTemplate.exchange(
                "http://localhost:" + port + "/orders",
                HttpMethod.POST,
                httpRequest,
                new ParameterizedTypeReference<>() {}
        );

        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        URI location = postResponse.getHeaders().getLocation();
        assertThat(location).isNotNull();

        // GET /orders/{id}
        ResponseEntity<Map<String, Object>> getResponse = restTemplate.exchange(
                location,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Map<String, Object> order = getResponse.getBody();

        assertThat(order).isNotNull();
        assertThat(order.get("customerName")).isEqualTo("Ali");




    }
}