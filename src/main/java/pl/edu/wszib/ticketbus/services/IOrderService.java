package pl.edu.wszib.ticketbus.services;

import pl.edu.wszib.ticketbus.model.Order;
import pl.edu.wszib.ticketbus.model.OrderPositions;
import pl.edu.wszib.ticketbus.model.User;

import java.util.List;

public interface IOrderService {
    double calculateTotal();
    boolean saveOrder(Order order, OrderPositions orderPositions);
    List<Order> getAllOrdersByUser(User user);
    List<Order> getAllOrders();
    void updateOrder(Order order);
    Order getOrderById(int id);
}
