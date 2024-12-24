package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Hotel;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

//room,guest ve reservation icin service ve repo classlarını olusturalım : ÖDEV!!!
public class HotelRepository {

    private Session session;

    //1-b
    public void save(Hotel hotel) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction trs = session.beginTransaction();
            session.save(hotel);//insert into t_hotel values(?,?,?)
            trs.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }

    }

    public Hotel findById(Long id) {
        try{
            session=HibernateUtils.getSessionFactory().openSession();
            //transaction olusturmuyorum çünkü fetch işlemlerinde ihtiyaç yok
            return session.get(Hotel.class,id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }

    //3-c DB'den tüm kayıtları getirme
    public List<Hotel> findAll() {
        try{
            session=HibernateUtils.getSessionFactory().openSession();
            return session.createQuery("From Hotel", Hotel.class).getResultList();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }

    //8-b
    public void delete(Hotel deleteHotel) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction trs = session.beginTransaction();

            //delete from t_hotel where id=deleteHotel.getId();
            session.delete(deleteHotel);

            trs.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }

    }
    //7-c
    public void update(Hotel foundHotel) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction trs = session.beginTransaction();

            session.update(foundHotel);


            trs.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
    }
}