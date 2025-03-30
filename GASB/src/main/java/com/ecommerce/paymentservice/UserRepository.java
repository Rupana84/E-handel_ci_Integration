package com.ecommerce.paymentservice;

import com.ecommerce.paymentservice.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.reactive.function.client.WebClient;

public interface UserRepository extends JpaRepository<User, Long> {
    @Configuration
    class WebClientConfig {

        @Bean
        public WebClient webClient() {
            return WebClient.builder()
                    .baseUrl("http://ordermicro-env.eba-ha662mef.eu-north-1.elasticbeanstalk.com/") // Sätt den externa bas-URL för användartjänst
                    .build();
        }
    }
}

