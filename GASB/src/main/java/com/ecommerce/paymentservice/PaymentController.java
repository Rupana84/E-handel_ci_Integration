package com.ecommerce.paymentservice.controller;

import com.ecommerce.paymentservice.model.Payment;
import com.ecommerce.paymentservice.model.User;
import com.ecommerce.paymentservice.repository.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentRepository paymentRepository;
    private final WebClient webClient;

    public PaymentController(PaymentRepository paymentRepository, WebClient webClient) {
        this.paymentRepository = paymentRepository;
        this.webClient = webClient;
    }

    // Hämta betalning baserat på orderId
    @GetMapping("/order/{id}")
    public Mono<ResponseEntity<Payment>> getPaymentByOrderId(@PathVariable Long id) {
        return Mono.justOrEmpty(paymentRepository.findByOrderId(id))  // Se till att paymentRepository finner betalningen
                .flatMap(payment ->  // Om betalningen hittas, använd flatMap för att skapa ett Mono för att få användaren
                        webClient.get()
                                .uri("/users/" + payment.getOrderId())  // Gör ett anrop till en annan tjänst för användarinfo
                                .retrieve()
                                .bodyToMono(User.class)
                                .map(user -> {
                                    // Lägger till användarinformation i betalning
                                    payment.setUser(user);
                                    return ResponseEntity.ok(payment);  // Returnera betalningen med användare som ResponseEntity
                                })
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());  // Om ingen betalning hittas, returnera 404
    }
}
