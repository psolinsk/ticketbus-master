package pl.edu.wszib.ticketbus.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.wszib.ticketbus.model.Order;
import pl.edu.wszib.ticketbus.model.Bus;
import pl.edu.wszib.ticketbus.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class SessionObject {

    private User loggedUser = null;
    private String info = null;
    private final List<Bus> basket = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public boolean isLogged() {
        return this.loggedUser != null;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        String temp = this.info;
        this.info = null;
        return temp;
    }
    public List<Bus> getBasket() {
        return basket;
    }

    public List<Order> getOrders(){
        return orders;
    }

}
