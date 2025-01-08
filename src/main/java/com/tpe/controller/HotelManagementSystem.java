package com.tpe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tpe.service.HotelService;

@Component
public class HotelManagementSystem {

    @Autowired
    private HotelService hotelService;
}
