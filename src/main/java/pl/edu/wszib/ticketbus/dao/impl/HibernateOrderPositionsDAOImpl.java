package pl.edu.wszib.ticketbus.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.ticketbus.model.OrderPositions;
import pl.edu.wszib.ticketbus.dao.IOrderPositionsDAO;

@Repository
public class HibernateOrderPositionsDAOImpl implements IOrderPositionsDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public boolean saveOrderPosition(OrderPositions orderPositions) {
        Session session=this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx.begin();
            session.saveOrUpdate(orderPositions);
            tx.commit();
        } catch (Exception e) {
            if (tx != null){
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return true;
    }
}
