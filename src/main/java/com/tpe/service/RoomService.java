package com.tpe.service;

import com.tpe.domain.Hotel;
import com.tpe.domain.Room;
import com.tpe.repository.RoomRepository;
import com.tpe.exceptions.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelService hotelService;

    @Autowired
    public RoomService(RoomRepository roomRepository, HotelService hotelService) {
        this.roomRepository = roomRepository;
        this.hotelService = hotelService;
    }

    // Save or update room
    public Room saveRoom(Room room) {
        // Validate that the room is associated with a valid hotel
        if (room.getHotel() == null || room.getHotel().getId() == null) {
            throw new IllegalArgumentException("Room must be associated with a valid hotel");
        }
    
        // Ensure the hotel exists in the database
        Hotel hotel = hotelService.findHotelById(room.getHotel().getId());
        room.setHotel(hotel); // Associate the room with the existing hotel
    
        // Save the room and return it
        return roomRepository.save(room);
    }      

    // Find a room by ID
    public Room findRoomById(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with ID: " + roomId));
    }

    // Get all rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Delete room by ID
    public void deleteRoom(Long roomId) {
        Room room = findRoomById(roomId);
        roomRepository.delete(room);
    }
}
