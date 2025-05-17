package com.example.ProductMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import reactor.core.publisher.Mono;
import java.util.Map;


@RestController
@RequestMapping("/products")
class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return service.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return service.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
    }

    @GetMapping("/{id}/available")
    public boolean isProductAvailable(@PathVariable Long id) {
        return service.isProductAvailable(id);
    }

    @GetMapping("/popular")
    public Mono<Map<Product, Integer>> getPopularProducts() {
        return service.getPopularProducts();
    }

}
