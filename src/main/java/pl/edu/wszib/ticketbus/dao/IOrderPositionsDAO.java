package pl.edu.wszib.ticketbus.dao;

import pl.edu.wszib.ticketbus.model.OrderPositions;

public interface IOrderPositionsDAO {
    boolean saveOrderPosition(OrderPositions orderPositions);
}
