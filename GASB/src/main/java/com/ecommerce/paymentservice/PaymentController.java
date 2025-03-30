package com.ecommerce.paymentservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentRepository paymentRepository;
    private final WebClient webClient;

    public PaymentController(PaymentRepository paymentRepository, WebClient.Builder webClientBuilder) {
        this.paymentRepository = paymentRepository;
        this.webClient = webClientBuilder.baseUrl("http://ordermicro-env.eba-ha662mef.eu-north-1.elasticbeanstalk.com/").build();
    }

    @GetMapping
    public ResponseEntity<?> getAllPayments() {
        return ResponseEntity.ok(paymentRepository.findAll());
    }

    @GetMapping("/order/{id}")
    public Mono<ResponseEntity<PaymentsResponse>> getPaymentByOrderId(@PathVariable Long id) {
        return Mono.justOrEmpty(paymentRepository.findByOrderId(id))
                .flatMap(payment -> {
                    if (payment.getUserId() == null) {
                        return Mono.just(ResponseEntity.ok(new PaymentsResponse(payment, null)));
                    }
                    return webClient.get()
                            .uri("/users/" + payment.getUserId())
                            .retrieve()
                            .bodyToMono(User.class)
                            .map(user -> ResponseEntity.ok(new PaymentsResponse(payment, user)))
                            .defaultIfEmpty(ResponseEntity.ok(new PaymentsResponse(payment, null)));
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
