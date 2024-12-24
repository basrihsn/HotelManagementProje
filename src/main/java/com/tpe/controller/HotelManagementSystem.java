package com.tpe.controller;

import com.tpe.config.HibernateUtils;
import com.tpe.repository.GuestRepository;
import com.tpe.repository.HotelRepository;
import com.tpe.repository.ReservationRepository;
import com.tpe.repository.RoomRepository;
import com.tpe.service.GuestService;
import com.tpe.service.HotelService;
import com.tpe.service.ReservationService;
import com.tpe.service.RoomService;

import java.util.Scanner;

public class HotelManagementSystem {

    private static Scanner scanner=new Scanner(System.in);

    //ana menü kullanıcıya gösterilir ve secimi alınır
    public static void displayHotelManagementSystemMenu() {
        HotelRepository hotelRepository=new HotelRepository();
        HotelService hotelService=new HotelService(hotelRepository);

        RoomRepository roomRepository=new RoomRepository();
        RoomService roomService=new RoomService(roomRepository,hotelService);

        GuestRepository guestRepository=new GuestRepository();
        GuestService guestService=new GuestService(guestRepository);

        ReservationRepository reservationRepository=new ReservationRepository();
        ReservationService reservationService=new ReservationService(reservationRepository,guestService,roomService);
        int choice;

        do {
            System.out.println("============ Hotel Management System ============");
            System.out.println("1. Hotel Operations");
            System.out.println("2. Room Operations");
            System.out.println("3. Guest Operations");
            System.out.println("4. Reservation Operations");
            System.out.println("0. EXIT");
            System.out.println("Enter your choice : ");
            choice= scanner.nextInt();
            scanner.nextLine();//dumpy

            switch (choice){
                case 1:
                    displayHotelOperationsMenu(hotelService);
                    break;
                case 2:
                    displayRoomOperationsMenu(roomService);
                    break;
                case 3:
                    displayGuestOperationsMenu(guestService);
                    break;
                case 4:
                    displayReservationOperationsMenu(reservationService);
                    break;
                case 0:
                    System.out.println("Good Bye...");
                    HibernateUtils.shutDown();
                    break;
                default:
                    System.out.println("Invalid Choice, Please try again");
                    break;
            }
        }while (choice!=0);

    }
    //her bir kategori icin ayri methodlar olusturmam gerekiyor : işlemleri gosteren ve secimini alan
    private static void displayHotelOperationsMenu(HotelService hotelService) {

        boolean exit = false;
        while (!exit) {
            System.out.println("==== Hotel Operations ====");
            System.out.println("1. Add a new hotel");
            System.out.println("2. Find Hotel By ID");
            System.out.println("3. Delete Hotel By ID");
            System.out.println("4. Find All Hotels");
            System.out.println("5. Update Hotel By ID");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //1-a : otel kaydetme
                    hotelService.saveHotel();
                    break;
                case 2:
                    //2-a : Hotel Bul id ile
                    System.out.println("Enter Hotel ID : ");
                    Long id=scanner.nextLong();
                    scanner.nextLine();
                    hotelService.findHotelById(id);
                    break;
                case 3:
                    //8-a
                    System.out.println("Enter Hotel ID : ");
                    Long deleteHotelId=scanner.nextLong();
                    scanner.nextLine();

                    hotelService.deleteHotelById(deleteHotelId);
                    break;
                case 4:
                    //3-a: tüm otelleri listeleme
                    hotelService.getAllHotels();
                    break;
                case 5:
                    //7-a otel güncelleme
                    System.out.println("Enter Hotel ID : ");
                    Long updateHotelId=scanner.nextLong();
                    scanner.nextLine();

                    hotelService.updateHotelById(updateHotelId);
                    break;
                case 0:
                    exit = true;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }//tamamlandı

    //room operations
    private static void displayRoomOperationsMenu(RoomService roomService) {

        //RoomService roomService=new RoomService();
        System.out.println("Room Operation Menu");
        boolean exit = false;
        while (!exit) {
            System.out.println("==== Room Operations ====");
            System.out.println("1. Add a new room");
            System.out.println("2. Find Room By ID");
            System.out.println("3. Delete Room By ID");
            System.out.println("4. Find All Rooms");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //4-a işlemini yapabilmek icin bir odaya ihtiyacim var
                    //4-a.1 oda olusturma
                    roomService.saveRoom();
                    break;
                case 2:
                    //5-a odev
                    System.out.println("Enter Room ID : ");
                    Long roomId=scanner.nextLong();
                    scanner.nextLine();

                    roomService.findRoomById(roomId);
                    break;
                case 3:
                    //todo: ODEV1: ID'si verilen odayı silme
                    break;
                case 4:
                    //6-a odev
                    //lütfen odalarını listelemek istediginiz otelin idsini giriniz
                    //geçerli mi diye
                    //geçerli ise sql ya da hql sorgusu kullanırdım
                    //select * from rooms where hotel_id=1;
                    roomService.getAllRooms();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Returning to Main Menu...");

                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }//1 tane odev var

    //guest operations
    private static void displayGuestOperationsMenu(GuestService guestService) {//
        System.out.println("Guest Operation Menu");

        boolean exit = false;
        while (!exit) {
            System.out.println("==== Guest Operations ====");
            System.out.println("1. Add a new guest");
            System.out.println("2. Find Guest By ID");
            System.out.println("3. Delete Guest By ID");
            System.out.println("4. Find All Guests");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //9-a yeni bir müsteri
                    guestService.saveGuest();
                    break;
                case 2:
                    //todo : ODEV2:Guesti bulma id ile
                    System.out.println("Enter Room ID : ");
                    Long guestId=scanner.nextLong();
                    scanner.nextLine();

                    guestService.findGuestById(guestId);
                    break;
                case 3:
                    //todo : ODEV3: guesti id ile silme
                    break;
                case 4:
                    //todo : ODEV4: tüm guestleri listeleme
                    break;
                case 0:
                    exit = true;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:

                    break;
            }
        }
    }

    //reservation operations
    private static void displayReservationOperationsMenu(ReservationService reservationService) {
        System.out.println("Reservation Operation Menu");

        boolean exit = false;
        while (!exit) {
            System.out.println("==== Reservation Operations ====");
            System.out.println("1. Add a new reservation");
            System.out.println("2. Find Reservation By ID");
            System.out.println("3. Find All Reservations");
            System.out.println("4. Delete Reservation By ID");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //10-a yeni rezervasyon
                    reservationService.createReservation();
                    break;
                case 2:
                    //todo : ODEV5: id ile reservasyon bulma
                    break;
                case 3:
                    //todo : ODEV6: tüm res. listeleme
                    break;
                case 4:
                    //todo : ODEV7: res. silme
                    break;
                case 0:
                    exit = true;
                    System.out.println("Returning to Main Menu...");

                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }


    }

}