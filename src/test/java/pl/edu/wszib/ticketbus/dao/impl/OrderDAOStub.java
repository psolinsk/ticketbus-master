package pl.edu.wszib.ticketbus.dao.impl;

import pl.edu.wszib.ticketbus.dao.IOrderDAO;
import pl.edu.wszib.ticketbus.model.Order;
import pl.edu.wszib.ticketbus.model.User;

import java.util.List;

public class OrderDAOStub implements IOrderDAO { // TODO
    @Override
    public boolean saveOrder(Order order) {
        return true;
    }

    @Override
    public Order getOrderById(int id) {
        return null;
    }

    @Override
    public List<Order> getAllOrdersByUser(User user) {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public boolean updateOrder(Order order) {
        return true;
    }

}
