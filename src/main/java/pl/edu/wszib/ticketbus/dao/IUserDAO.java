package pl.edu.wszib.ticketbus.dao;

import pl.edu.wszib.ticketbus.model.User;

public interface IUserDAO {
    User getUserByLogin(String login);
    boolean persist(User user);
    User getUserById(int id);
}
