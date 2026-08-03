package com.afkir.hotel.rate.application;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.afkir.hotel.messaging.Bindings;
import com.afkir.hotel.messaging.RateChanged;
import com.afkir.hotel.outbox.OutboxAppender;
import com.afkir.hotel.rate.domain.model.RoomTypeRate;
import com.afkir.hotel.rate.domain.model.RoomTypeRateId;
import com.afkir.hotel.rate.domain.repository.RoomTypeRateRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RateApplicationService {

    private final RoomTypeRateRepository repository;

    private final OutboxAppender outboxAppender;

    @Transactional
    public RoomTypeRate upsert(UUID hotelId, UUID roomTypeId, LocalDate date, BigDecimal amount,
                               String currency) {
        RoomTypeRateId id = new RoomTypeRateId(hotelId, roomTypeId, date);
        RoomTypeRate rate = repository.findById(id)
                .orElseGet(() -> new RoomTypeRate(hotelId, roomTypeId, date, amount, currency));
        rate.changeRate(amount, currency);
        RoomTypeRate saved = repository.save(rate);
        publish(saved);
        return saved;
    }

    @Transactional
    public int replayAll() {
        List<RoomTypeRate> rates = repository.findAll();
        rates.forEach(this::publish);
        return rates.size();
    }

    private void publish(RoomTypeRate rate) {
        UUID messageId = UUID.randomUUID();
        RoomTypeRateId id = rate.getId();
        outboxAppender.append(Bindings.RATE_EVENTS, messageId, null,
                new RateChanged(messageId, id.getHotelId(), id.getRoomTypeId(), id.getDate(),
                        rate.getAmount(), rate.getCurrency()));
    }

    @Transactional(readOnly = true)
    public Optional<RoomTypeRate> find(UUID hotelId, UUID roomTypeId, LocalDate date) {
        return repository.findById(new RoomTypeRateId(hotelId, roomTypeId, date));
    }

    @Transactional(readOnly = true)
    public List<RoomTypeRate> findRange(UUID hotelId, UUID roomTypeId, LocalDate start,
                                        LocalDate end) {
        return repository.findRange(hotelId, roomTypeId, start, end);
    }

}
