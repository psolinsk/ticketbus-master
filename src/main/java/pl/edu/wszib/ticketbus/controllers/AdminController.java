package pl.edu.wszib.ticketbus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.ticketbus.model.Bus;
import pl.edu.wszib.ticketbus.model.User;
import pl.edu.wszib.ticketbus.services.IOrderService;
import pl.edu.wszib.ticketbus.services.IBusService;
import pl.edu.wszib.ticketbus.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class AdminController {

    @Autowired
    IBusService busService;

    @Autowired
    IOrderService orderService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable int id, Model model) {
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }
        Bus bus = this.busService.getBusById(id);
        model.addAttribute("bus", bus);
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        return "edit";
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute Bus bus) {
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }

        this.busService.updateBus(bus);

        return "redirect:/main";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBus(@PathVariable int id, Model model){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }
        Bus bus = this.busService.getBusById(id);
        model.addAttribute("bus", bus);
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        return "delete";
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@ModelAttribute Bus bus){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }
        this.busService.deleteBus(bus);
        return "redirect:/main";
    }

    @RequestMapping(value = "/addConnections", method = RequestMethod.GET)
    public String addForm(Model model){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        model.addAttribute("bus", new Bus());
        return "addConnections";
    }
    @RequestMapping(value = "/addConnections", method = RequestMethod.POST)
    public String add(@ModelAttribute Bus bus){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }
        if(this.busService.addBus(bus)) {
            return "redirect:/main";
        } else {
            this.sessionObject.setInfo("blad");
            return "redirect:/addConnections";
        }
    }
}
