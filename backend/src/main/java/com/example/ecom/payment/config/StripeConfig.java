package com.example.ecom.payment.config;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;


@Configuration
public class StripeConfig {

    @PostConstruct
    public void init() {
        Stripe.apiKey = "sk_test_51PKzXqCMQGEPYZPHzCcqOJE3sOlCbwbRqTZiF2BkFQQOaXxwisu9YgT3EzDhwpwSs1tdNNhsOo1yqGqvZPfsGK9K00q8vKvimA";
    }
}