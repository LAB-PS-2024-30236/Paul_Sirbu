package com.example.ecom.payment.service;


import com.example.ecom.payment.model.CardDetails;
import com.example.ecom.payment.repository.CardDetailsRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private CardDetailsRepository cardDetailsRepository;

    public PaymentIntent processPayment(CardDetails cardDetails) throws StripeException {
        PaymentIntentCreateParams createParams = PaymentIntentCreateParams.builder()
                .setAmount((long) (cardDetails.getAmount() * 100)) // Amount in cents
                .setCurrency("usd")
                .addPaymentMethodType("card")
                .build();

        PaymentIntent intent = PaymentIntent.create(createParams);

        cardDetails.setPaymentIntentId(intent.getId());
        cardDetailsRepository.save(cardDetails);

        return intent;
    }
}
