package com.tpe.controller;

import com.tpe.domain.Hotel;
import com.tpe.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class HotelManagementSystem {

    @Autowired
    private HotelService hotelService;

    private static final Scanner scanner = new Scanner(System.in);

    public void displayHotelManagementSystemMenu() {
        int choice;
        do {
            System.out.println("============ Hotel Management System ============");
            System.out.println("1. Add a new hotel");
            System.out.println("2. Find Hotel By ID");
            System.out.println("3. Delete Hotel By ID");
            System.out.println("4. Find All Hotels");
            System.out.println("5. Update Hotel By ID");
            System.out.println("0. EXIT");
            System.out.println("Enter your choice : ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    addHotel();
                    break;
                case 2:
                    findHotelById();
                    break;
                case 3:
                    deleteHotelById();
                    break;
                case 4:
                    findAllHotels();
                    break;
                case 5:
                    updateHotelById();
                    break;
                case 0:
                    System.out.println("Goodbye...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 0);
    }

    private void addHotel() {
        Hotel hotel = new Hotel();
        System.out.println("Enter Hotel ID: ");
        hotel.setId(scanner.nextLong());
        scanner.nextLine(); // Clear buffer
        System.out.println("Enter Hotel Name: ");
        hotel.setName(scanner.nextLine());
        System.out.println("Enter Hotel Location: ");
        hotel.setLocation(scanner.nextLine());
        hotelService.saveHotel(hotel);
        System.out.println("Hotel added successfully.");
    }

    private void findHotelById() {
        System.out.println("Enter Hotel ID: ");
        Long id = scanner.nextLong();
        Hotel hotel = hotelService.findHotelById(id);
        System.out.println(hotel);
    }

    private void deleteHotelById() {
        System.out.println("Enter Hotel ID: ");
        Long id = scanner.nextLong();
        hotelService.deleteHotelById(id);
        System.out.println("Hotel deleted successfully.");
    }

    private void findAllHotels() {
        hotelService.getAllHotels().forEach(System.out::println);
    }

    private void updateHotelById() {
        System.out.println("Enter Hotel ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Clear buffer
        System.out.println("Enter New Hotel Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter New Hotel Location: ");
        String location = scanner.nextLine();
        Hotel updatedHotel = new Hotel();
        updatedHotel.setName(name);
        updatedHotel.setLocation(location);
        hotelService.updateHotelById(id, updatedHotel);
        System.out.println("Hotel updated successfully.");
    }
}
