package pl.edu.wszib.ticketbus.dao;

import pl.edu.wszib.ticketbus.model.Bus;

import java.util.List;

public interface IBusDAO {
    Bus getBusById(int id);
    void updateBus(Bus bus);
    boolean addBus(Bus bus);
    void deleteBus(Bus bus);
    List<Bus> getAllBuses();
}

