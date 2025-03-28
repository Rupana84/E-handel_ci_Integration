package com.ecommerce.paymentservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://ordermicro-env.eba-ha662mef.eu-north-1.elasticbeanstalk.com/") // Sätt den externa bas-URL för användartjänst
                .build();
    }
}
