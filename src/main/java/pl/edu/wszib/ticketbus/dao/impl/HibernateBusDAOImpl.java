package pl.edu.wszib.ticketbus.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.ticketbus.model.Bus;
import pl.edu.wszib.ticketbus.dao.IBusDAO;

import java.util.List;

@Repository
public class HibernateBusDAOImpl implements IBusDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Bus getBusById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Bus> query = session.createQuery("FROM pl.edu.wszib.ticketbus.model.Bus WHERE id = :id");
        query.setParameter("id", id);
        Bus bus = null;
        try {
            bus = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return bus;
    }

    @Override
    public void updateBus(Bus bus) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(bus);
            tx.commit();
        } catch (Exception e) {
            if (tx != null){
                tx.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public boolean addBus(Bus bus) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(bus);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }finally {
            session.close();
        }
        return true;
    }

    @Override
    public void deleteBus(Bus bus) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(bus);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public List<Bus> getAllBuses() {
        Session session = this.sessionFactory.openSession();
        Query<Bus> query = session.createQuery( "FROM pl.edu.wszib.ticketbus.model.Bus");
        List<Bus> buses = query.getResultList();
        session.close();
        return buses;
    }
}
