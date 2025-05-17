package com.example.ProductMicro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testCreateAndFetchProduct() {
        Product newProduct = new Product(null, "Integrationstest", 59.99, true);
        Product savedProduct = productService.createProduct(newProduct);

        Product fetched = productService.getProductById(savedProduct.getId());

        assertThat(fetched).isNotNull();
        assertThat(fetched.getName()).isEqualTo("Integrationstest");
    }
}
