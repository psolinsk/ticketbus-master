package pl.edu.wszib.ticketbus.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.ticketbus.dao.IBusDAO;
import pl.edu.wszib.ticketbus.model.Bus;
import pl.edu.wszib.ticketbus.services.IBusService;

import java.util.List;

@Service
public class BusServiceImpl implements IBusService {

    @Autowired
    IBusDAO busDAO;

    @Override
    public Bus getBusById(int id) {
        return this.busDAO.getBusById(id);
    }

    @Override
    public List<Bus> getAllBuses() {
        return this.busDAO.getAllBuses();
    }

    @Override
    public void updateBus(Bus bus) {
        Bus busFromDatabase = this.busDAO.getBusById(bus.getId());
        busFromDatabase.setMiastoPoczatkowe(bus.getMiastoPoczatkowe());
        busFromDatabase.setMiastoKoncowe(bus.getMiastoKoncowe());
        busFromDatabase.setNumerBiletu(bus.getNumerBiletu());
        busFromDatabase.setIloscMiejsc(bus.getIloscMiejsc());
        busFromDatabase.setCena(bus.getCena());

        this.busDAO.updateBus(busFromDatabase);
    }

    @Override
    public boolean addBus(Bus bus) {
        Bus newBus = new Bus(0, bus.getMiastoPoczatkowe(), bus.getMiastoKoncowe(), bus.getNumerBiletu(),
                bus.getIloscMiejsc(), bus.getCena());
        return this.busDAO.addBus(newBus);
    }

    @Override
    public void deleteBus(Bus bus) {
        Bus busFromDatabase = this.busDAO.getBusById(bus.getId());
        this.busDAO.deleteBus(busFromDatabase);
    }
}
