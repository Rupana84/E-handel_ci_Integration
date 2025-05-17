package com.example.ProductMicro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService service;

    @Autowired
    private ObjectMapper objectMapper;

    private final Product sampleProduct = new Product(1L, "Sample", 100.0, true);

    @Test
    void testGetAllProducts() throws Exception {
        when(service.getAllProducts()).thenReturn(List.of(sampleProduct));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Sample"))
                .andExpect(jsonPath("$[0].price").value(100.0));
    }

    @Test
    void testGetProductById() throws Exception {
        when(service.getProductById(1L)).thenReturn(sampleProduct);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sample"))
                .andExpect(jsonPath("$.price").value(100.0));
    }

    @Test
    void testCreateProduct() throws Exception {
        when(service.createProduct(any(Product.class))).thenReturn(sampleProduct);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Sample"));
    }

    @Test
    void testUpdateProduct() throws Exception {
        Product updated = new Product(1L, "Updated", 150.0, false);
        when(service.updateProduct(Mockito.eq(1L), any(Product.class))).thenReturn(updated);

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated"))
                .andExpect(jsonPath("$.available").value(false));
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isOk());

        Mockito.verify(service).deleteProduct(1L);
    }

    @Test
    void testIsProductAvailable() throws Exception {
        when(service.isProductAvailable(1L)).thenReturn(true);

        mockMvc.perform(get("/products/1/available"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }


}
