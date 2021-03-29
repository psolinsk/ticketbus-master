package pl.edu.wszib.ticketbus.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.ticketbus.configuration.TestConfiguration;
import pl.edu.wszib.ticketbus.dao.IOrderDAO;
import pl.edu.wszib.ticketbus.dao.IOrderPositionsDAO;
import pl.edu.wszib.ticketbus.dao.IBusDAO;
import pl.edu.wszib.ticketbus.dao.IUserDAO;
import pl.edu.wszib.ticketbus.model.Bus;
import pl.edu.wszib.ticketbus.services.IBusService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class TourServiceImplTest {


    @MockBean
    IBusDAO busDAO;

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IOrderDAO orderDAO;

    @MockBean
    IOrderPositionsDAO orderPositionsDAO;

    @Autowired
    IBusService busService;

    @Test
    public void addBusTest(){
        Bus bus = new Bus();
        bus.setId(10);
        bus.setMiastoPoczatkowe("Kraków");
        bus.setMiastoKoncowe("Warszawa");
        bus.setIloscMiejsc(10);
        bus.setCena(20.00);

        Mockito.when(this.busDAO.addBus(bus)).thenReturn(true);

        boolean result = this.busService.addBus(bus);

        Assert.assertNotNull(result);

    }

    @Test
    public void getBusByIdTest() {
        Bus bus = new Bus();
        bus.setId(10);

        Mockito.when(this.busDAO.getBusById(10)).thenReturn(bus);

        this.busService.getBusById(10);

        Assert.assertNotNull(this.busService.getBusById(10));
    }

    @Test
    public void getAllBusesTest(){
        List<Bus> buses = new ArrayList<>();

        Mockito.when(this.busDAO.getAllBuses()).thenReturn(buses);

        this.busService.getAllBuses();

        Assert.assertNotNull(buses);
    }
}
