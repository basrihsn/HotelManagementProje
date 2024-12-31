package com.tpe.controller;

import com.tpe.domain.Hotel;
import com.tpe.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addHotel(@RequestBody Hotel hotel) {
        System.out.println("Payload_hotel: " + hotel);
        hotelService.saveHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Hotel added successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.findHotelById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateHotel(@PathVariable Long id, @RequestBody Hotel updatedHotel) {
        hotelService.updateHotelById(id, updatedHotel);
        return ResponseEntity.ok("Hotel updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotelById(id);
        return ResponseEntity.ok("Hotel deleted successfully");
    }
}
