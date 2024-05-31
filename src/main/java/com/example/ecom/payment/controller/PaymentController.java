package com.example.ecom.payment.controller;

import com.example.ecom.payment.model.CardDetails;
import com.example.ecom.payment.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@CrossOrigin
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public String processPayment(@RequestBody CardDetails cardDetails) throws StripeException {
        PaymentIntent intent = paymentService.processPayment(cardDetails);
        return intent.getClientSecret();
    }
}

