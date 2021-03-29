package pl.edu.wszib.ticketbus.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.ticketbus.dao.IBusDAO;
import pl.edu.wszib.ticketbus.model.Bus;
import pl.edu.wszib.ticketbus.services.IBasketService;
import pl.edu.wszib.ticketbus.session.SessionObject;

import javax.annotation.Resource;

@Service
public class BasketServiceImpl implements IBasketService {

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
    public void addBusByIdToBasket(int id) {
        Bus bus = this.busDAO.getBusById(id);
        if (bus.getIloscMiejsc() > 0) {
            deleteMiejsca(bus); // TODO
        }else {
            return;
        }
        for (Bus busFromBasket : this.sessionObject.getBasket()) {
            if (busFromBasket.getId() == bus.getId()) {
                if (bus.getIloscMiejsc() >= 0) {
                    busFromBasket.setIloscMiejsc(busFromBasket.getIloscMiejsc() + 1);
                    return;
                } else {
                    return;
                }
            }
        }
        bus.setIloscMiejsc(1);
        this.sessionObject.getBasket().add(bus);
    }

    @Override
    public void deleteBusFromBasket(int id) {
        Bus bus = this.busDAO.getBusById(id);

        for (Bus busFromBasket : this.sessionObject.getBasket()){
            if (busFromBasket.getId() == busFromBasket.getId()){
                if (busFromBasket.getIloscMiejsc() > 0){
                    busFromBasket.setIloscMiejsc(busFromBasket.getIloscMiejsc() - 1);
                    addSeats(bus);
                    return;
                }else
                    this.sessionObject.getBasket().remove(busFromBasket);
                    return;
            }
        }
        this.sessionObject.getBasket().remove(bus);
    }


    private void deleteMiejsca(Bus bus) {
        bus.setIloscMiejsc(bus.getIloscMiejsc() - 1);
        this.busDAO.updateBus(bus);
    }
    private void addSeats (Bus bus) {
        bus.setIloscMiejsc(bus.getIloscMiejsc() + 1);
        this.busDAO.updateBus(bus);
    }
}
