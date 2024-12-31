package com.tpe.service;

import com.tpe.domain.Hotel;
import com.tpe.exceptions.HotelNotFoundException;
import com.tpe.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    // Save a new hotel
    public void saveHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    // Find a hotel by ID
    public Hotel findHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with ID: " + id));
    }

    // Retrieve all hotels
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    // Delete hotel by ID
    public void deleteHotelById(Long id) {
        Hotel hotel = findHotelById(id); // Ensure hotel exists
        hotelRepository.delete(hotel);
    }

    // Update hotel details
    public void updateHotelById(Long id, Hotel updatedHotel) {
        Hotel existingHotel = findHotelById(id);
        existingHotel.setName(updatedHotel.getName());
        existingHotel.setLocation(updatedHotel.getLocation());
        hotelRepository.save(existingHotel);
    }
}
