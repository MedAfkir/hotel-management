package com.afkir.hotel.shared;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoneyTest {

    @Test
    void addsAmountsOfSameCurrency() {
        Money a = Money.of(new BigDecimal("10.00"), "EUR");
        Money b = Money.of(new BigDecimal("5.50"), "EUR");
        assertEquals(Money.of(new BigDecimal("15.50"), "EUR"), a.add(b));
    }

    @Test
    void multipliesByFactor() {
        Money a = Money.of(new BigDecimal("12.00"), "EUR");
        assertEquals(Money.of(new BigDecimal("36.00"), "EUR"), a.multiply(3));
    }

    @Test
    void rejectsNegativeAmount() {
        assertThrows(DomainException.class, () -> Money.of(new BigDecimal("-1.00"), "EUR"));
    }

    @Test
    void rejectsCurrencyMismatch() {
        Money a = Money.of(new BigDecimal("10.00"), "EUR");
        Money b = Money.of(new BigDecimal("5.00"), "USD");
        assertThrows(DomainException.class, () -> a.add(b));
    }

}
