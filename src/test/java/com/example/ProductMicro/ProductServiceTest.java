package com.example.ProductMicro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderServiceClient orderServiceClient;

    @InjectMocks
    private ProductService productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleProduct = new Product();
        sampleProduct.setId(1L);
        sampleProduct.setName("Test Product");
        sampleProduct.setPrice(100.0);
        sampleProduct.setAvailable(true);
    }

    @Test
    void testGetAllProducts() {
        List<Product> productList = List.of(sampleProduct);
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.getAllProducts();

        assertEquals(1, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductById_Found() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(sampleProduct));

        Product result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals("Test Product", result.getName());
    }

    @Test
    void testGetProductById_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Product result = productService.getProductById(1L);

        assertNull(result);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.saveAndFlush(sampleProduct)).thenReturn(sampleProduct);

        Product result = productService.createProduct(sampleProduct);

        assertEquals("Test Product", result.getName());
        verify(productRepository).saveAndFlush(sampleProduct);
    }

    @Test
    void testUpdateProduct_Found() {
        Product updatedData = new Product();
        updatedData.setName("Updated");
        updatedData.setPrice(200.0);
        updatedData.setAvailable(false);

        when(productRepository.findById(1L)).thenReturn(Optional.of(sampleProduct));
        when(productRepository.save(any(Product.class))).thenReturn(sampleProduct);

        Product result = productService.updateProduct(1L, updatedData);

        assertNotNull(result);
        assertEquals("Updated", result.getName());
        assertFalse(result.getAvailable());
    }

    @Test
    void testUpdateProduct_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Product result = productService.updateProduct(1L, new Product());

        assertNull(result);
    }

    @Test
    void testDeleteProduct() {
        productService.deleteProduct(1L);

        verify(productRepository).deleteById(1L);
    }

    @Test
    void testIsProductAvailable_True() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(sampleProduct));

        boolean result = productService.isProductAvailable(1L);

        assertTrue(result);
    }

    @Test
    void testIsProductAvailable_False() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        boolean result = productService.isProductAvailable(1L);

        assertFalse(result);
    }

    @Test
    void testGetPopularProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("P1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("P2");

        when(productRepository.findAll()).thenReturn(List.of(product1, product2));
        when(orderServiceClient.getOrderCountByProductId(1L)).thenReturn(Mono.just(10));
        when(orderServiceClient.getOrderCountByProductId(2L)).thenReturn(Mono.just(5));

        Mono<Map<Product, Integer>> resultMono = productService.getPopularProducts(1);

        StepVerifier.create(resultMono)
                .assertNext(map -> {
                    assertEquals(2, map.size());
                    assertEquals(10, map.get(product1));
                    assertEquals(5, map.get(product2));
                })
                .verifyComplete();
    }
}
