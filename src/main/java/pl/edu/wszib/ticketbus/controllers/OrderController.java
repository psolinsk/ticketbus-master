package pl.edu.wszib.ticketbus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.ticketbus.model.Order;
import pl.edu.wszib.ticketbus.model.OrderPositions;
import pl.edu.wszib.ticketbus.model.User;
import pl.edu.wszib.ticketbus.services.IBasketService;
import pl.edu.wszib.ticketbus.services.IOrderService;
import pl.edu.wszib.ticketbus.services.IBusService;
import pl.edu.wszib.ticketbus.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class OrderController {
    @Autowired
    IBusService busService;

    @Autowired
    IBasketService basketService;

    @Autowired
    IOrderService orderService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String showUserOrder(Model model) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        User user = this.sessionObject.getLoggedUser();
        model.addAttribute("userOrder", this.orderService.getAllOrdersByUser(user));
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("info", this.sessionObject.getInfo());
        return "order";
    }

    @RequestMapping(value = "/preorder", method = RequestMethod.GET)
    public String order(Model model){
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("newOrder", new Order());
        model.addAttribute("newOrderPosition", new OrderPositions());
        return "preorder";
    }

    @RequestMapping(value = "/preorder", method = RequestMethod.POST)
    public String makeOrder(@ModelAttribute Order order, @ModelAttribute OrderPositions orderPositions) {

        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        if (this.orderService.saveOrder(order, orderPositions)){
            return "redirect:/order";
        }else {
            this.sessionObject.setInfo("Błąd zamówienia");
            return "redirect:/preorder";
        }
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String showAllOrders(Model model){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }
        model.addAttribute("allOrders", this.orderService.getAllOrders());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);

        return "orders";
    }

    @RequestMapping(value = "/editorder/{id}", method = RequestMethod.GET)
    public String editOrderForm(@PathVariable int id, Model model) {
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }
        Order orderFromBasket = this.orderService.getOrderById(id);

        model.addAttribute("currentOrder", orderFromBasket);
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        return "editorder";
    }
    @RequestMapping(value = "/editorder/{id}", method = RequestMethod.POST)
    public String editOrder(@ModelAttribute Order order) {
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }
        this.orderService.updateOrder(order);
        return "redirect:/orders";
    }
}

