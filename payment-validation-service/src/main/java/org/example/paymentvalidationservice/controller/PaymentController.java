package org.example.paymentvalidationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.paymentvalidationservice.EbookDeliveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment-validation-service")
public class PaymentController {

@Autowired
EbookDeliveryClient ebookDeliveryClient;

    @PostMapping ("/webhook")
    public String webhook(@RequestBody String paymentInfo) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root =objectMapper.readTree(paymentInfo);
        String email = root.path("data").path("object").path("customer_details").path("email").asText();
        ebookDeliveryClient.sendEmail(email);
        return paymentInfo;
    }
}
