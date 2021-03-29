package pl.edu.wszib.ticketbus.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.ticketbus.dao.IOrderDAO;
import pl.edu.wszib.ticketbus.dao.IOrderPositionsDAO;
import pl.edu.wszib.ticketbus.dao.IBusDAO;
import pl.edu.wszib.ticketbus.model.Order;
import pl.edu.wszib.ticketbus.model.OrderPositions;
import pl.edu.wszib.ticketbus.model.Bus;
import pl.edu.wszib.ticketbus.model.User;
import pl.edu.wszib.ticketbus.services.IOrderService;
import pl.edu.wszib.ticketbus.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;


@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    IOrderDAO orderDAO;

    @Autowired
    IOrderPositionsDAO orderPositionsDAO;

    @Autowired
    IBusDAO busDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public double calculateTotal() {
        double sum=0;
        for (Bus bus : this.sessionObject.getBasket()){
            sum = sum + bus.getCena() * bus.getIloscMiejsc();
        }
        return sum;
    }

    @Override
    public List<Order> getAllOrdersByUser(User user) {
        return this.orderDAO.getAllOrdersByUser(user);
    }

    @Override
    public Order getOrderById(int id) {
        return this.orderDAO.getOrderById(id);
    }

    @Override
    public boolean saveOrder(Order order, OrderPositions orderPositions){
        List<Bus> buses = this.sessionObject.getBasket();

        OrderPositions positions = new OrderPositions();
        Order newOrder = new Order();
        newOrder.setUser(this.sessionObject.getLoggedUser());
        newOrder.setPrice(calculateTotal());

        for (int i=0; i<buses.size(); i++){
            OrderPositions newPosition = new OrderPositions();
            newPosition.setBus(buses.get(i));
            newPosition.setOrder(newOrder);
            newOrder.getPositions().add(newPosition);
            newPosition.setPieces(buses.get(i).getIloscMiejsc());
            positions = newPosition;
        }

        return this.orderPositionsDAO.saveOrderPosition(positions) && this.orderDAO.saveOrder(newOrder);
    }

    @Override
    public List<Order> getAllOrders() {
        return this.orderDAO.getAllOrders();
    }

    @Override
    public void updateOrder(Order order) {
        Order orderFromBasket = this.orderDAO.getOrderById(order.getId());
        this.orderDAO.updateOrder(orderFromBasket);
    }
}
