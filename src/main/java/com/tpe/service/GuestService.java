package com.tpe.service;

import com.tpe.domain.Guest;
import com.tpe.exceptions.GuestNotFoundException;
import com.tpe.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    public Guest saveGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    public Guest findGuestById(Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new GuestNotFoundException("Guest not found with ID: " + id));
    }

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public void deleteGuestById(Long id) {
        Guest guest = findGuestById(id);
        guestRepository.delete(guest);
    }

    public Guest updateGuestById(Long id, Guest updatedGuest) {
        Guest existingGuest = findGuestById(id);
        existingGuest.setName(updatedGuest.getName());
        existingGuest.setAddress(updatedGuest.getAddress());
        return guestRepository.save(existingGuest);
    }
}
