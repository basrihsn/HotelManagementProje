package com.tpe.config;

import com.tpe.domain.Guest;
import com.tpe.domain.Hotel;
import com.tpe.domain.Reservation;
import com.tpe.domain.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration config = new Configuration().configure().
                    addAnnotatedClass(Guest.class)
                    .addAnnotatedClass(Reservation.class)
                    .addAnnotatedClass(Room.class);
            sessionFactory = config.buildSessionFactory();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    //getter
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //sf kapatalım
    public static void shutDown(){
        getSessionFactory().close();
    }

    //session kapatma icin
    public static void closeSession(Session session){
        if (session!=null &&session.isOpen()){
            session.close();
        }
    }

}