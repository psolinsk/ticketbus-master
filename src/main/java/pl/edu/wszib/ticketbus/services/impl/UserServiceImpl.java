package pl.edu.wszib.ticketbus.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.ticketbus.dao.IUserDAO;
import pl.edu.wszib.ticketbus.model.User;
import pl.edu.wszib.ticketbus.model.view.RegistrationModel;
import pl.edu.wszib.ticketbus.services.IUserService;
import pl.edu.wszib.ticketbus.session.SessionObject;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IUserDAO userDAO;

    @Override
    public User getUserById(int id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    public void authenticate(User user) {
        User userFromDatabase = this.userDAO.getUserByLogin(user.getLogin());
        if(userFromDatabase == null) {
            return;
        }

        if(user.getPass().equals(userFromDatabase.getPass())) {
            this.sessionObject.setLoggedUser(userFromDatabase);
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);
    }

    @Override
    public boolean register(RegistrationModel registrationModel) {
        if(this.userDAO.getUserByLogin(registrationModel.getLogin()) != null) {
            return false;
        }

        User newUser = new User(0, registrationModel.getLogin(), registrationModel.getPassword(), User.Role.USER );

        return this.userDAO.persist(newUser);
    }
}
