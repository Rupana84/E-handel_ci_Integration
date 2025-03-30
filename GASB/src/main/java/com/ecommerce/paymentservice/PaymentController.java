package com.ecommerce.paymentservice;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentRepository paymentRepository;
    private final WebClient webClient;  // Lägg till detta!

    public PaymentController(PaymentRepository paymentRepository, WebClient webClient) {
        this.paymentRepository = paymentRepository;
        this.webClient = webClient;  // Spara webClient som en instansvariabel

    }
    @GetMapping
    public ResponseEntity<?> getAllPayments() {
        return ResponseEntity.ok(paymentRepository.findAll());
    }
    @GetMapping("/order/{id}")
    public Mono<ResponseEntity<Payment>> getPaymentByOrderId(@PathVariable Long id) {
        return Mono.justOrEmpty((Payment) paymentRepository.findByOrderId(id))
                .flatMap(payment -> {
                        if (payment.getUser() == null) {
                            return Mono.just(ResponseEntity.ok(payment));
        }
                        return webClient.get()
                                .uri("/users/" + payment.getUser().getId())
                                .retrieve()
                                .bodyToMono(User.class)
                                .map(user -> {
                                    payment.setUser(user);
                                    return ResponseEntity.ok(payment);
                                });
    })
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
