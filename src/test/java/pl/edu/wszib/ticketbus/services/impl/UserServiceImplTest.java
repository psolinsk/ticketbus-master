package pl.edu.wszib.ticketbus.services.impl;

import org.junit.Assert;
import org.junit.Before;
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
import pl.edu.wszib.ticketbus.model.User;
import pl.edu.wszib.ticketbus.model.view.RegistrationModel;
import pl.edu.wszib.ticketbus.services.IUserService;
import pl.edu.wszib.ticketbus.session.SessionObject;

import javax.annotation.Resource;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class UserServiceImplTest {

    @Autowired
    IUserService userService;

    @MockBean
    IBusDAO busDAO;

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IOrderDAO orderDAO;

    @MockBean
    IOrderPositionsDAO orderPositionsDAO;

    @Resource
    SessionObject sessionObject;

    @Before
    public void configureMocks(){
        Mockito.when(this.userDAO.getUserByLogin("testlogin")).thenReturn(null);
        Mockito.when(this.userDAO.persist(any())).thenReturn(true );
        Mockito.when(this.userDAO.getUserByLogin("logintwo")).thenReturn(new User());
        Mockito.when(this.userDAO.getUserByLogin("marek")).thenReturn(generateUser());
        Mockito.when(this.userDAO.getUserByLogin("kuba")).thenReturn(null);
        Mockito.when(this.userDAO.getUserByLogin("seba")).thenReturn(generateUser());
    }

    @Test
    public void registerTest(){
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setLogin("testlogin");
        registrationModel.setPassword("testpasswd");
        registrationModel.setPassword("testpasswd");

        boolean result = userService.register(registrationModel);

        Assert.assertTrue(result);
    }

    @Test
    public void registerLoginIncorrectTest(){
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setLogin("login2");
        registrationModel.setPassword("passwd2");
        registrationModel.setPassword("passwd2 ");

        boolean result = userService.register(registrationModel);

        Assert.assertFalse(result);
    }

    @Test
    public void correctAuthenticationTest(){
        User user = new User();
        user.setLogin("Jan");
        user.setPass("Jan");

        this.userService.authenticate(user);

        Assert.assertNotNull(this.sessionObject.getLoggedUser());
    }

    @Test
    public void incorrectAuthenticationTest(){
        User user = new User();
        user.setLogin("marek");
        user.setPass("marek");

        this.userService.authenticate(user);

        Assert.assertNull(this.sessionObject.getLoggedUser());
    }

    @Test
    public void incorrectPassTest(){
        User user = new User();
        user.setLogin("seba");
        user.setPass("sebatest");

        this.userService.authenticate(user);

        Assert.assertNull(this.sessionObject.getLoggedUser());
    }

    private User generateUser(){
        User user = new User();
        user.setId(5);
        user.setLogin("ela");
        user.setPass("elka");
        user.setRole(User.Role.USER);

        return user;
    }
}
