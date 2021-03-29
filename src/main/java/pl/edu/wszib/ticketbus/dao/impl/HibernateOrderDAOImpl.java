package pl.edu.wszib.ticketbus.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.ticketbus.model.Order;
import pl.edu.wszib.ticketbus.model.User;
import pl.edu.wszib.ticketbus.dao.IOrderDAO;

import java.util.List;

@Repository
public class HibernateOrderDAOImpl implements IOrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public boolean saveOrder(Order order) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx=session.beginTransaction();
            session.save(order);
            tx.commit();
        } catch (Exception e) {
            if (tx != null){
                tx.rollback();
            }
        }
        finally {
            session.close();
        }
        return true;
    }

    @Override
    public Order getOrderById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM pl.edu.wszib.ticketbus.model.Order where id = : id");
        query.setParameter("id", id);
        Order order = query.getSingleResult();
        session.close();
        return order;
    }

    @Override
    public List<Order> getAllOrdersByUser(User user) {
        Session session =this.sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM pl.edu.wszib.ticketbus.model.Order where user = : user");
        query.setParameter("user", user);
        List<Order> orders = query.getResultList();
        session.close();
        return orders;
    }

    @Override
    public List<Order> getAllOrders() {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery( "FROM pl.edu.wszib.ticketbus.model.Order");
        List<Order> orders = query.getResultList();
        session.close();
        return orders;
    }

    @Override
    public boolean updateOrder(Order order) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx=session.beginTransaction();
            session.update(order);
            tx.commit();
        } catch (Exception e) {
            if (tx != null){
                tx.rollback();
            }
        }
        finally {
            session.close();
        }
        return true;
    }
}
