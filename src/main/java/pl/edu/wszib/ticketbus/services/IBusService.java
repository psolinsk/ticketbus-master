package pl.edu.wszib.ticketbus.services;

import pl.edu.wszib.ticketbus.model.Bus;

import java.util.List;

public interface IBusService { // TODO
    Bus getBusById(int id);
    List<Bus> getAllBuses();
    void updateBus(Bus bus);
    boolean addBus(Bus bus);
    void deleteBus(Bus bus);
}
