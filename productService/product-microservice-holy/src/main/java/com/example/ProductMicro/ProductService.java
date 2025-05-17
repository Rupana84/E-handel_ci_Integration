package com.example.ProductMicro;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service

public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderServiceClient orderServiceClient;


    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }
    public Product createProduct(Product product){
        return productRepository.saveAndFlush(product);
    }

    public Product updateProduct(Long id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setAvailable(product.getAvailable());
            return productRepository.save(updatedProduct);
        }
        return null;
    }

    public void deleteProduct(Long id) { productRepository.deleteById(id); }

    public boolean isProductAvailable(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(Product::getAvailable).orElse(false);
    }


    public Mono<Map<Product, Integer>> getPopularProducts() {
        List<Product> products = productRepository.findAll();
        Map<Product, Integer> productPopularity = new HashMap<>();

        List<Mono<Void>> monoList = products.stream()
                .map(product -> orderServiceClient.getOrderCountByProductId(product.getId())
                        .doOnNext(count -> productPopularity.put(product, count))
                        .then())
                .toList();

        return Mono.when(monoList)
                .thenReturn(productPopularity);
    }


}
