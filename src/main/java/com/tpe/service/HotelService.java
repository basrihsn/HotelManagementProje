package com.tpe.service;

import com.tpe.domain.Hotel;
import com.tpe.exception.HotelNotFoundException;
import com.tpe.repository.HotelRepository;

import java.util.List;
import java.util.Scanner;

public class HotelService {

    private Scanner scanner=new Scanner(System.in);

    private final HotelRepository hotelRepository;

    //paramli const
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    //1-c save hotel
    public void saveHotel(){
        Hotel hotel=new Hotel();

        System.out.println("Enter Hotel ID : ");
        Long id=scanner.nextLong();//todo: hali hazırda zaten bu idye sahip varsa kontrolu yapılcak
        scanner.nextLine();
        // Hotel foundHotel=findHotelById(id);
        // if (foundHotel!=null){
        //     System.out.println("Hotel save is Failed!!!!");
        // }else {
        hotel.setId(id);
        System.out.println("Enter Hotel Name : ");
        hotel.setName(scanner.nextLine());

        System.out.println("Enter Hotel Location ");
        hotel.setLocation(scanner.nextLine());

        hotelRepository.save(hotel);

        System.out.println("Hotel is saved successfully. Hotel ID: "+hotel.getId());
//    }
    }

    //2-b : idsi verilen hotelin yazdırılma işlemi.
    public Hotel findHotelById(Long id) {

        Hotel foundHotel=hotelRepository.findById(id);//=hotelRepository.findHotelById(id);

        try {
            if (foundHotel != null) {
                System.out.println("------------------------");
                System.out.println(foundHotel);
                System.out.println("------------------------");
                return foundHotel;
            } else {
                throw new HotelNotFoundException("Hotel not found by ID : " + id);
            }
        }catch (HotelNotFoundException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void getAllHotels() {

        List<Hotel>allHotels=hotelRepository.findAll();//

        if (allHotels.isEmpty()){
            System.out.println("Hotel list is EMPTY!");
        }else {
            System.out.println("-----------ALL HOTELS------------");
            for (Hotel hotel :allHotels) {
                System.out.println(hotel);
            }
            System.out.println("-----------ALL HOTELS------------");
        }

    }

    //8-b
    public void deleteHotelById(Long deleteHotelId) {
        //idsi verilen hotel var mı?
        Hotel deleteHotel=findHotelById(deleteHotelId);

        if (deleteHotel!=null){
            System.out.println(deleteHotel);
            System.out.println("Are you sure to delete : "+" Serious 🤔 :O");
            System.out.println("Please answere with Y or N : ");
            String select = scanner.next();

            if (select.equalsIgnoreCase("Y")){
                hotelRepository.delete(deleteHotel);
                System.out.println("Delete operation is Successfully");
            }else  {
                System.out.println("Delete operation is CANCELED!!!");
            }


        }else  {
            System.out.println("Delete operation is CANCELED!!!");
        }

    }

    //7-b
    public void updateHotelById(Long updateHotelId) {
        //boyle bir otel var mı?
        Hotel foundHotel=findHotelById(updateHotelId);
        if (foundHotel!=null){
            System.out.println("Enter the new hotel name : ");
            String name= scanner.next();
            scanner.nextLine();

            System.out.println("Enter the new location : ");
            String location= scanner.nextLine();
            System.out.println("Are you sure to update : "+" Serious 🤔 :O");
            System.out.println("Please answere with Y or N : ");
            String select = scanner.next();
            if (select.equalsIgnoreCase("Y")){
                foundHotel.setName(name);
                foundHotel.setLocation(location);
                hotelRepository.update(foundHotel);
            }else {
                System.out.println("Update operation is CANCELED!!!");

            }

        }else {
            System.out.println("Update operation is CANCELED!!!");
        }

    }
}