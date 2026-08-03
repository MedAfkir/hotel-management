package com.afkir.hotel.reservation.application;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.afkir.hotel.reservation.application.command.CreateReservationCommand;
import com.afkir.hotel.reservation.domain.model.InsufficientInventoryException;
import com.afkir.hotel.reservation.domain.model.Reservation;
import com.afkir.hotel.reservation.domain.model.ReservationStatus;
import com.afkir.hotel.reservation.domain.model.RoomTypeInventory;
import com.afkir.hotel.reservation.domain.model.RoomTypeInventoryId;
import com.afkir.hotel.reservation.domain.repository.ReservationRepository;
import com.afkir.hotel.reservation.domain.repository.RoomTypeInventoryRepository;
import com.afkir.hotel.shared.DateRange;
import com.afkir.hotel.shared.DomainException;
import com.afkir.hotel.shared.Money;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationApplicationService {

    private final ReservationRepository reservationRepository;

    private final RoomTypeInventoryRepository inventoryRepository;

    private final RateReadModel rateReadModel;

    private final double overbookingFactor;

    private final String currency;

    private final java.math.BigDecimal defaultNightlyRate;

    public ReservationApplicationService(ReservationRepository reservationRepository,
                                         RoomTypeInventoryRepository inventoryRepository,
                                         RateReadModel rateReadModel,
                                         @Value("${reservation.overbooking-factor:1.0}") double overbookingFactor,
                                         @Value("${reservation.currency:EUR}") String currency,
                                         @Value("${reservation.default-nightly-rate:100.00}") java.math.BigDecimal defaultNightlyRate) {
        this.reservationRepository = reservationRepository;
        this.inventoryRepository = inventoryRepository;
        this.rateReadModel = rateReadModel;
        this.overbookingFactor = overbookingFactor;
        this.currency = currency;
        this.defaultNightlyRate = defaultNightlyRate;
    }

    @Transactional
    public Reservation createReservation(CreateReservationCommand command) {
        return reservationRepository.findByIdempotencyKey(command.idempotencyKey())
                .orElseGet(() -> create(command));
    }

    private Reservation create(CreateReservationCommand command) {
        DateRange range = new DateRange(command.startDate(), command.endDate());
        Money total = Money.zero(currency);
        for (LocalDate date : range.dates()) {
            RoomTypeInventoryId id =
                    new RoomTypeInventoryId(command.hotelId(), command.roomTypeId(), date);
            RoomTypeInventory inventory = inventoryRepository.findById(id)
                    .orElseThrow(() -> new InsufficientInventoryException(command.roomTypeId(), date));
            inventory.reserve(command.numberOfRooms(), overbookingFactor);
            inventoryRepository.save(inventory);
            total = total.add(nightlyAmount(command, date).multiply(command.numberOfRooms()));
        }
        Reservation reservation = new Reservation(UUID.randomUUID(), command.idempotencyKey(),
                command.hotelId(), command.roomTypeId(), command.guestId(), command.startDate(),
                command.endDate(), command.numberOfRooms(), total.amount(),
                total.currency().getCurrencyCode());
        return reservationRepository.save(reservation);
    }

    private Money nightlyAmount(CreateReservationCommand command, LocalDate date) {
        return rateReadModel.find(command.hotelId(), command.roomTypeId(), date)
                .map(snapshot -> Money.of(snapshot.getAmount(), currency))
                .orElseGet(() -> Money.of(defaultNightlyRate, currency));
    }

    @Transactional
    public Optional<Reservation> markPaidIfPending(UUID reservationId) {
        Reservation reservation = getReservation(reservationId);
        if (reservation.getStatus() != ReservationStatus.PENDING) {
            return Optional.empty();
        }
        reservation.markPaid();
        return Optional.of(reservationRepository.save(reservation));
    }

    @Transactional
    public Optional<Reservation> rejectAndReleaseIfPending(UUID reservationId) {
        Reservation reservation = getReservation(reservationId);
        if (reservation.getStatus() != ReservationStatus.PENDING) {
            return Optional.empty();
        }
        reservation.reject();
        releaseInventory(reservation);
        return Optional.of(reservationRepository.save(reservation));
    }

    @Transactional(readOnly = true)
    public Reservation getReservation(UUID reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new DomainException("reservation not found: " + reservationId));
    }

    @Transactional
    public Reservation cancel(UUID reservationId) {
        Reservation reservation = getReservation(reservationId);
        reservation.cancel();
        releaseInventory(reservation);
        return reservationRepository.save(reservation);
    }

    private void releaseInventory(Reservation reservation) {
        DateRange range = new DateRange(reservation.getStartDate(), reservation.getEndDate());
        for (LocalDate date : range.dates()) {
            RoomTypeInventoryId id = new RoomTypeInventoryId(reservation.getHotelId(),
                    reservation.getRoomTypeId(), date);
            inventoryRepository.findById(id).ifPresent(inventory -> {
                inventory.release(reservation.getNumberOfRooms());
                inventoryRepository.save(inventory);
            });
        }
    }

    @Transactional(readOnly = true)
    public List<Reservation> findByGuest(UUID guestId) {
        return reservationRepository.findByGuestId(guestId);
    }

    @Transactional
    public void setInventory(UUID hotelId, UUID roomTypeId, LocalDate start, LocalDate end,
                             int totalInventory) {
        DateRange range = new DateRange(start, end);
        for (LocalDate date : range.dates()) {
            RoomTypeInventoryId id = new RoomTypeInventoryId(hotelId, roomTypeId, date);
            RoomTypeInventory inventory = inventoryRepository.findById(id)
                    .orElseGet(() -> new RoomTypeInventory(hotelId, roomTypeId, date, 0));
            inventory.setTotalInventory(totalInventory);
            inventoryRepository.save(inventory);
        }
    }

}
