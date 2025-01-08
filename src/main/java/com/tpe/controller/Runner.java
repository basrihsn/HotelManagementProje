package com.tpe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private HotelManagementSystem hotelManagementSystem;

    @Override
    public void run(String... args) {
        // Just log startup message instead of displaying menu
        System.out.println("Hotel Management System is running...");
    }
}
