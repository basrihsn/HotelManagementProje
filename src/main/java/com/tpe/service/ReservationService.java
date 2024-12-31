package com.tpe.service;

import com.tpe.domain.Guest;
import com.tpe.domain.Reservation;
import com.tpe.domain.Room;
import com.tpe.exceptions.ReservationNotFoundException;
import com.tpe.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final GuestService guestService;
    private final RoomService roomService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, GuestService guestService, RoomService roomService) {
        this.reservationRepository = reservationRepository;
        this.guestService = guestService;
        this.roomService = roomService;
    }

    // Create a new reservation
    public Reservation createReservation(Reservation reservation) {
        Guest guest = guestService.findGuestById(reservation.getGuest().getId());
        Room room = roomService.findRoomById(reservation.getRoom().getId());

        reservation.setGuest(guest);
        reservation.setRoom(room);

        return reservationRepository.save(reservation);
    }

    // Get all reservations
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Get a reservation by ID
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with ID: " + id));
    }

    // Update an existing reservation
    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        Reservation existingReservation = getReservationById(id);

        Guest guest = guestService.findGuestById(updatedReservation.getGuest().getId());
        Room room = roomService.findRoomById(updatedReservation.getRoom().getId());

        existingReservation.setGuest(guest);
        existingReservation.setRoom(room);
        existingReservation.setCheckInDate(updatedReservation.getCheckInDate());
        existingReservation.setCheckOutDate(updatedReservation.getCheckOutDate());

        return reservationRepository.save(existingReservation);
    }

    // Delete a reservation by ID
    public void deleteReservation(Long id) {
        Reservation reservation = getReservationById(id);
        reservationRepository.delete(reservation);
    }
}
