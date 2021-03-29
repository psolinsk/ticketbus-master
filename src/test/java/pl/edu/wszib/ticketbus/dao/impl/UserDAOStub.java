package pl.edu.wszib.ticketbus.dao.impl;

import pl.edu.wszib.ticketbus.dao.IUserDAO;
import pl.edu.wszib.ticketbus.model.User;

public class UserDAOStub implements IUserDAO { //TODO
    @Override
    public User getUserByLogin(String login) {
        if (login.equals("login2")){
            return new User();
        }
        return null;
    }

    @Override
    public boolean persist(User user) {
        return true;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }
}
