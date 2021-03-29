package pl.edu.wszib.ticketbus.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import pl.edu.wszib.ticketbus.services.IBasketService;
import pl.edu.wszib.ticketbus.session.SessionObject;

import javax.annotation.Resource;
import java.sql.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class BasketServiceImplTest {

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IBusDAO busDAO;

    @MockBean
    IOrderDAO orderDAO;

    @MockBean
    IOrderPositionsDAO orderPositionsDAO;

    @Autowired
    IBasketService basketService;

    @Resource
    SessionObject sessionObject;

    @Test
    public void calculateTotalTest(){
        Date startDate = Date.valueOf("2021-01-12");
        Date endDate = Date.valueOf("2021-01-24");
        sessionObject.getBasket().add(new Bus(1, "Kraków", "Warszawa",1 ,10, 20.00));

        double expectedResult = 700.0 ;

        double result = this.basketService.calculateTotal();

        Assert.assertEquals(expectedResult, result, 0.01);
    }



}
