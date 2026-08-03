package com.afkir.hotel.payment.api.rest;

import java.util.List;
import java.util.UUID;

import com.afkir.hotel.payment.api.dto.PaymentResponse;
import com.afkir.hotel.payment.application.PaymentApplicationService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentApplicationService service;

    @PostMapping("/{id}/refund")
    public PaymentResponse refund(@PathVariable UUID id) {
        return PaymentResponse.from(service.refund(id));
    }

    @GetMapping("/{id}")
    public PaymentResponse get(@PathVariable UUID id) {
        return PaymentResponse.from(service.getPayment(id));
    }

    @GetMapping
    public List<PaymentResponse> byReservation(@RequestParam("reservationId") UUID reservationId) {
        return service.findByReservation(reservationId).stream().map(PaymentResponse::from).toList();
    }

}
