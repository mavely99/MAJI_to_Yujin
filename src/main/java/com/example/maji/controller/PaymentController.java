package com.example.maji.controller;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PaymentController {

    public PaymentController() {
        Stripe.apiKey = "sk_test_51QIPkGE9rP1Pfus2FyaDvwMg30AXefanurbNNN7L8UBs2pm4AgGJW2WbawqeffXPbY6MRKtUG4O2nwHLZfqU22WI00pVcrp9EM"; // 비공개 키 설정
    }

    @PostMapping("/create-payment-intent")
    public Map<String, String> createPaymentIntent(@RequestBody Map<String, Object> data) throws Exception {
        int amount = (int) data.get("amount"); // 결제할 금액 (단위는 센트)

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long) amount) // 예: 1000 센트 = $10.00
                .setCurrency("jpy") // 결제할 통화 (예: 일본 엔화)
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        Map<String, String> response = new HashMap<>();
        response.put("clientSecret", paymentIntent.getClientSecret());

        return response;
    }

}
