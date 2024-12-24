package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Guest;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GuestRepository {

    private Session session;

    //9-c
    public void save(Guest guest) {
        try {

            session = HibernateUtils.getSessionFactory().openSession();
            Transaction trs = session.beginTransaction();

            session.saveOrUpdate(guest);

            trs.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
    }

    //odev2 : c
    public Guest findById(Long guestId) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Guest guest=session.get(Guest.class,guestId);
            return guest;


        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }
}