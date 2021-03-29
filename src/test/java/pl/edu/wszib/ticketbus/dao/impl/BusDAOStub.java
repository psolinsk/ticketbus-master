package pl.edu.wszib.ticketbus.dao.impl;

import pl.edu.wszib.ticketbus.dao.IBusDAO;
import pl.edu.wszib.ticketbus.model.Bus;

import java.util.List;

public class BusDAOStub implements IBusDAO { // TODO


    @Override
    public Bus getBusById(int id) {
        return null;
    }

    @Override
    public void updateBus(Bus bus) {

    }

    @Override
    public boolean addBus(Bus bus) {
        return true;
    }

    @Override
    public void deleteBus(Bus bus) {
    }

    @Override
    public List<Bus> getAllBuses() {
        return null;
    }
}
