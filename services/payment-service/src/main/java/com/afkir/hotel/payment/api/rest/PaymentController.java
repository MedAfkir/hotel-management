package com.afkir.hotel.payment.api.rest;

import com.afkir.hotel.payment.api.dto.PaymentResponse;
import com.afkir.hotel.payment.api.dto.ProcessPaymentRequest;
import com.afkir.hotel.payment.application.PaymentApplicationService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentApplicationService service;

    public PaymentController(PaymentApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> process(@Valid @RequestBody ProcessPaymentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(PaymentResponse.from(
                service.processPayment(request.reservationId(), request.amount(), request.currency(),
                        request.method())));
    }

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
