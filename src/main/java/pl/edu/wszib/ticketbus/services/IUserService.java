package pl.edu.wszib.ticketbus.services;

import pl.edu.wszib.ticketbus.model.User;
import pl.edu.wszib.ticketbus.model.view.RegistrationModel;

public interface IUserService {
    void authenticate(User user);
    void logout();
    boolean register(RegistrationModel registrationModel);
    User getUserById(int id);
}
