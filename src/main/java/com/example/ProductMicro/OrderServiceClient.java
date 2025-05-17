package com.example.ProductMicro;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class OrderServiceClient {
    private final WebClient webClient;

    /*
    public OrderServiceClient(WebClient.Builder webClientBuilder, @Value("${webclient.url}") String webClientUrl) {
        this.webClient = webClientBuilder.baseUrl(webClientUrl).build();
    } */

    public OrderServiceClient(WebClient.Builder webClientBuilder) {
        // Assuming OrderMicroservice runs locally on port 8081
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build();
    }

    public Mono<Integer> getOrderCountByProductId(Long productId) {
        return webClient.get()
                .uri("/orders/count/{productId}", productId)
                .retrieve()
                .bodyToMono(Integer.class);
    }
}


